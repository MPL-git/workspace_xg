package com.jf.service;

import com.google.common.collect.Lists;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.MemberInfo;
import com.jf.entity.ProductCustom;
import com.jf.entity.SvipRecommendNavigation;
import com.jf.entity.SysParamCfg;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class SvipService {
	@Autowired
	private SvipMemberSettingService svipMemberSettingService;
	@Autowired
	private MemberCouponService memberCouponService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private AdInfoService adInfoService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private OrderDtlService orderDtlService;
	@Autowired
	private IntegralDtlService integralDtlService;
	@Autowired
	private CustomAdPageService customAdPageService;
	@Autowired
	private DecorateInfoService decorateInfoService;
	@Autowired
	private SvipProductRecommendService svipProductRecommendService;
	@Autowired
	private SvipRecommendNavigationService svipRecommendNavigationService;
	
	public Map<String, Object> getPurchaseSvipPage(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer version = reqPRM.getInt("version");
		String system = reqPRM.getString("system");
		Integer memberId = reqDataJson.getInt("memberId");
		Map<String, Object> map = new HashMap<>();
		MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		String nick = memberInfo.getNick();
		if(StringUtil.isBlank(nick)){
			nick = memberInfo.getMobile();
			if(!StringUtil.isBlank(nick)){
				nick = nick.substring(0, 3) + "****" + nick.substring(9, nick.length());
			}
		}
		String isSvip = memberInfoService.isRealSVip(memberInfo, memberId) ? "1" : "0";
		boolean svipRenewButton = false;
		int memberSvipCoupon = 0;//svip会员专属优惠券
		Integer monthIntegral = 0;//每月可赠送的积分
		BigDecimal saveAmont = new BigDecimal("0");
		boolean isRecMonthIntegral = true;
		List<Map<String, String>> svipWelfarePics = new ArrayList<>();
		List<Map<String, String>> svipPrivilegePics = new ArrayList<>();
		if("1".equals(isSvip)){
			svipRenewButton = true;
			memberSvipCoupon = memberCouponService.getMemberCouponCountByType("5",memberId);
			isRecMonthIntegral = integralDtlService.getIsRecMonthIntegral(memberId);
			saveAmont = orderDtlService.getSvipSaveAmount(memberId);
			if(saveAmont == null){
				saveAmont = new BigDecimal("0");
			}
		}
		List<Map<String, Object>> svipSettings = svipMemberSettingService.getSvipSettingList();//购买svip会员列表*
		if(CollectionUtils.isNotEmpty(svipSettings)){
			Map<String, Object> svipSettingsMap = svipSettings.get(0);
			monthIntegral = Integer.parseInt(svipSettingsMap.get("monthIntegral").toString());
		}
		List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_SVIP_PRIVILEGE_PIC");
		if(CollectionUtils.isNotEmpty(cfgs)){//STORY #1563,版本60开始,SVIP专属特权，点击图标跳转改为原生，不再直接跳转到相应的H5页面(旧版本还是跳转H5页面)
			for (SysParamCfg sysParamCfg : cfgs) {
				Map<String, String> cfgMap = new HashMap<>();
				String linkId = sysParamCfg.getParamName();
				cfgMap.put("linkId", linkId);
				cfgMap.put("pic", StringUtil.getPic(sysParamCfg.getParamValue(), ""));
				svipPrivilegePics.add(cfgMap);
			}
		}
		//STORY #1563
		if((version <=59 && system.equals(Const.ANDROID)) || (version <= 59 && system.equals(Const.IOS)) || "1".equals(isSvip)){
			cfgs = DataDicUtil.getSysParamCfg("APP_SVIP_WELFARE_PIC");
			if(CollectionUtils.isNotEmpty(cfgs)){
				for (SysParamCfg sysParamCfg : cfgs) {
					Map<String, String> cfgMap = new HashMap<>();
					String type = sysParamCfg.getParamName();
					if(!"1".equals(isSvip)){
						type = "0";
					}
					cfgMap.put("type", type);
					cfgMap.put("pic", StringUtil.getPic(sysParamCfg.getParamValue(), ""));
					svipWelfarePics.add(cfgMap);
				}
			}
		}
		List<Map<String, Object>> svipCoupons = couponService.getCouponList("4",memberId,isSvip);//svip专属优惠券列表
		List<Map<String, Object>> svipAds = adInfoService.getAdList(Const.AD_STATUS_UP,Const.AD_TYPE_1,Const.AD_CATALOG_SVIP,"",null,reqPRM);//svip广告列表
		List<Map<String, Object>> svipProducts = DataDicUtil.getSvipProductList();//svip专属商品列表
		//STORY #1563,版本60开始，多了SVIP装修模块(原生)
		if((version >=60 && system.equals(Const.ANDROID)) || (version >= 60 && system.equals(Const.IOS))){
			map.put("expectedSavings", "1260");//开通SVIP会员每年预计可省  1260
			Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_23,null);//23.SVIP装修
			String decorateInfoId = adPageMap.get("decorateInfoId").toString();
			Map<String,Object> decorateInfoMap = decorateInfoService.getHomePageDecorateInfo(StringUtil.isBlank(decorateInfoId) ? null : Integer.valueOf(decorateInfoId),reqPRM,memberId);
			map.put("decorateInfo", decorateInfoMap);
		}
		map.put("svipSettings", svipSettings);
		map.put("svipCoupons", svipCoupons);
		map.put("svipAds", svipAds);
		map.put("svipProducts", svipProducts);
		map.put("memberSvipCoupon", memberSvipCoupon);
		map.put("monthIntegral", monthIntegral);
		map.put("saveAmont", saveAmont);
		map.put("memberNick", nick);
		map.put("memberPic", StringUtil.getPic(memberInfo.getPic(), ""));
		if(!StringUtil.isEmpty(memberInfo.getIsSvip()) && "1".equals(memberInfo.getIsSvip())) {
			map.put("svipExpireDateStr", DateUtil.getFormatDate(memberInfo.getSvipExpireDate(), "yyyy-MM-dd")+"年尊享到期");
		}else {
			map.put("svipExpireDateStr", "");
		}
		map.put("svipWelfarePics", svipWelfarePics);
		map.put("svipPrivilegePics", svipPrivilegePics);
		map.put("isRecMonthIntegral", isRecMonthIntegral);
		map.put("isSvip", isSvip);
		map.put("svipRenewButton", svipRenewButton);
		return map;
	}

	public Map<String, Object> getSvipBuyPage(JSONObject reqPRM, JSONObject reqDataJson) {
		List<Map<String, Object>> svipSettings = svipMemberSettingService.getSvipSettingList();//购买svip会员列表*
		Map<String, Object> map = new HashMap<>();
		map.put("svipSettings", svipSettings);
		return map;
	}
	
	//STORY #1563 -> #2017
	public Map<String, Object> getSvipProductRecommendList(JSONObject reqPRM, JSONObject reqDataJson,Integer pageSize) {
		int selectedNavId = reqDataJson.optInt("productTypeId", 0);
		List<SvipRecommendNavigation> navList = svipRecommendNavigationService.findOnline();
		List<String> keywordList = Lists.newArrayList();
		if (selectedNavId > 0) {
			for (SvipRecommendNavigation nav : navList) {
				if (selectedNavId == nav.getId() && nav.getKeyword() != null) {
					keywordList.addAll(Arrays.asList(nav.getKeyword().split(",")));
					break;
				}
			}
		} else if (!CollectionUtil.isEmpty(navList)) {
			selectedNavId = navList.get(0).getId();
			if (navList.get(0).getKeyword() != null) {
				keywordList.addAll(Arrays.asList(navList.get(0).getKeyword().split(",")));
			}
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("currentPage", reqDataJson.getInt("currentPage")*pageSize);
		params.put("pageSize", pageSize);
		params.put("keywordList", keywordList);
		List<ProductCustom> productCustomList = svipProductRecommendService.getSvipProductRecommendList(params);

		JSONArray navViewList = new JSONArray();
		for (SvipRecommendNavigation nav : navList) {
			JSONObject jo = new JSONObject();
			jo.put("id", nav.getId());
			jo.put("name", nav.getName());
			navViewList.add(jo);
		}
		JSONArray productViewList = new JSONArray();
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#.##");
		for(ProductCustom productCustom:productCustomList){
			JSONObject jo = new JSONObject();
			jo.put("id", productCustom.getId());
			jo.put("name", productCustom.getName());
			jo.put("productPic", StringUtil.getPic(productCustom.getPic(), ""));
			if(productCustom.getSalePrice()!=null){
				jo.put("currentPrice", df.format(productCustom.getSalePrice()));
				BigDecimal svipPrice = productCustom.getSalePrice().multiply(productCustom.getSvipDiscount());
				jo.put("svipPrice", df.format(svipPrice));
			}else{
				jo.put("currentPrice", df.format(productCustom.getMinMallPrice()));
				BigDecimal svipPrice = productCustom.getMinMallPrice().multiply(productCustom.getSvipDiscount());
				jo.put("svipPrice", df.format(svipPrice));
			}
			jo.put("tagPrice", df.format(productCustom.getMinTagPrice()));
			productViewList.add(jo);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("productTypeId", selectedNavId);
		result.put("productTypeList", navViewList);
		result.put("productCustomList", productViewList);
		return result;
	}
}
