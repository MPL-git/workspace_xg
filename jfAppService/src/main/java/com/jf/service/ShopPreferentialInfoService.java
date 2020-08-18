package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ShopPreferentialInfoCustomMapper;
import com.jf.dao.ShopPreferentialInfoMapper;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCustom;
import com.jf.entity.CouponExample;
import com.jf.entity.FullCut;
import com.jf.entity.FullCutExample;
import com.jf.entity.FullDiscount;
import com.jf.entity.FullDiscountExample;
import com.jf.entity.FullGive;
import com.jf.entity.FullGiveCustom;
import com.jf.entity.FullGiveExample;
import com.jf.entity.Product;
import com.jf.entity.ShopPreferentialInfo;
import com.jf.entity.ShopPreferentialInfoExample;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月19日 下午5:03:46 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ShopPreferentialInfoService extends BaseService<ShopPreferentialInfo, ShopPreferentialInfoExample> {
	@Autowired
	private ShopPreferentialInfoMapper shopPreferentialInfoMapper;
	@Autowired
	private ShopPreferentialInfoCustomMapper shopPreferentialInfoCustomMapper;
	@Resource
	private FullCutService fullCutService;
	@Resource
	private FullDiscountService fullDiscountService;
	@Resource
	private FullGiveService fullGiveService;
	@Resource
	private CouponService couponService;
	@Resource
	private MemberCouponService memberCouponService;
	@Resource
	private ProductService productService;
	@Resource
	private ActivityAreaService activityAreaService;
	@Resource
	private OrderService orderService;
	@Resource
	private ShoppingCartService shoppingCartService;

	@Autowired
	public void setShopPreferentialInfoMapper(ShopPreferentialInfoMapper shopPreferentialInfoMapper) {
		this.setDao(shopPreferentialInfoMapper);
		this.shopPreferentialInfoMapper = shopPreferentialInfoMapper;
	}

	public Map<String, String> getShopPreferentialInfo(List<Integer> mchtIds) {
		Date date = new Date();
		String disInfo = "";
		Map<String,String> shopPreferentialInfoMap = new HashMap<String, String>();
		if(CollectionUtils.isNotEmpty(mchtIds)){
			ShopPreferentialInfoExample preferentialInfoExample = new ShopPreferentialInfoExample();
			preferentialInfoExample.createCriteria().andTypeEqualTo("2").andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThan(date)
			.andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtIdIn(mchtIds);
//			preferentialInfoExample.setOrderByClause("id desc");
			List<ShopPreferentialInfo> preferentialInfos = selectByExample(preferentialInfoExample);
			if(CollectionUtils.isNotEmpty(preferentialInfos)){
				for (ShopPreferentialInfo shopPreferentialInfo : preferentialInfos) {
					Integer mchtId = shopPreferentialInfo.getMchtId();
					if(!shopPreferentialInfoMap.containsKey(mchtId)){
						String type = shopPreferentialInfo.getType();
						Integer preferentialId = shopPreferentialInfo.getPreferentialId();
						if(type.equals("2")){
							FullCut cut = fullCutService.selectByPrimaryKey(preferentialId);
							if(cut != null){
								String rult = cut.getRule();
								String[] rults = rult.split("\\|");
								BigDecimal dis = new BigDecimal("0");
								for (int i = 0; i < rults.length; i++) {
									String[] rsi = rults[i].split(",");
									BigDecimal min = new BigDecimal(rsi[1]);
									if(min.compareTo(dis) > 0){
										dis = min;
										disInfo += "满"+rsi[0]+"减"+rsi[1]+"";
									}
								}
							}
							shopPreferentialInfoMap.put(mchtId.toString(), disInfo);
						}
					}
				}
			}
		}
		return shopPreferentialInfoMap;
	}

	public Map<String, Object> getMchtShopPreferInfoByMchtId(Integer mchtId, Map<String, Object> map,String memberId,String type, JSONObject reqPRM) {
		Date date = new Date();
		String system = reqPRM.getString("system");
		Integer version = reqPRM.getInt("version");
		map.put("preferentialType", "0");
		ShopPreferentialInfoExample shopPreInfoExample = new ShopPreferentialInfoExample();
		shopPreInfoExample.createCriteria().andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThan(date)
		.andMchtIdEqualTo(mchtId).andStatusEqualTo("1").andDelFlagEqualTo("0");
		List<ShopPreferentialInfo> preferentialInfos = selectByExample(shopPreInfoExample);
		if(CollectionUtils.isNotEmpty(preferentialInfos)){
			List<Integer> couponIdList = new ArrayList<Integer>();
			ShopPreferentialInfo preferentialInfo = preferentialInfos.get(0);
			//优惠类型(1优惠劵 2满减 3满赠 4多买优惠)
			String preferentialInfoType = preferentialInfo.getType();
			map.put("preferentialType", preferentialInfoType);
			if(preferentialInfoType.equals("1")){
				String couponIdGroup = preferentialInfo.getCouponIdGroup();
				if(!StringUtil.isBlank(couponIdGroup)){
					for (String couponId : couponIdGroup.split(",")) {
						couponIdList.add(Integer.valueOf(couponId));
					}
					if(CollectionUtils.isNotEmpty(couponIdList)){
						CouponExample couponExample = new CouponExample();
						couponExample.createCriteria().andIdIn(couponIdList).andStatusEqualTo("1")
						.andRecBeginDateLessThanOrEqualTo(date).andRecEndDateGreaterThan(date).andDelFlagEqualTo("0");
						List<Coupon> coupons = couponService.selectByExample(couponExample);
						if(type.equals("1")){
							List<CouponCustom> couponCustomList = new ArrayList<CouponCustom>();
							if(CollectionUtils.isNotEmpty(coupons)){
								for (Coupon coupon : coupons) {
									CouponCustom couponCustom = new CouponCustom();
									BeanUtils.copyProperties(coupon, couponCustom);
									Map<String, Object> couponMap = memberCouponService.getMemberIsReceiveCoupon(coupon,memberId);
									couponCustom.setIsHasCoupon((boolean)couponMap.get("isHasCoupon"));
									couponCustom.setCouponReceiveMsg(couponMap.get("couponReceiveMsg")+"");
									couponCustomList.add(couponCustom);
								}
							}
							map.put("coupons", couponCustomList);
						}else{
							map.put("coupons", coupons);
						}
					}
				}
			}else if(preferentialInfoType.equals("2")){
				FullCutExample fullCutExample = new FullCutExample();
				fullCutExample.createCriteria().andIdEqualTo(preferentialInfo.getPreferentialId()).andDelFlagEqualTo("0");
				List<FullCut> fullCuts = fullCutService.selectByExample(fullCutExample);
				map.put("fullCuts", fullCuts);
			}else if(preferentialInfoType.equals("3")){
				FullGiveExample fullGiveExample = new FullGiveExample();
				fullGiveExample.createCriteria().andIdEqualTo(preferentialInfo.getPreferentialId()).andDelFlagEqualTo("0");
				List<FullGive> fullGives = fullGiveService.selectByExample(fullGiveExample);
				List<FullGiveCustom> fullGiveCustoms=new ArrayList<>();
				if(CollectionUtils.isNotEmpty(fullGives)){
					FullGive fullGive = fullGives.get(0);
					FullGiveCustom fullGiveCustom=new FullGiveCustom();
					BeanUtils.copyProperties(fullGive, fullGiveCustom);
					if("1".equals(fullGive.getProductFlag())){
						Product product=productService.selectByPrimaryKey(fullGive.getProductId());
						fullGiveCustom.setProductName(product.getName());
					}
					if("1".equals(fullGive.getCouponFlag())){
						String[] couponIds=fullGive.getCouponIdGroup().split(",");
						StringBuffer couponNameGroup=new StringBuffer();
						for (int i = 0; i < couponIds.length; i++) {
							Coupon coupon=couponService.selectByPrimaryKey(Integer.valueOf(couponIds[i]));
							couponNameGroup.append(",").append(coupon.getName());
						}
						fullGiveCustom.setCouponNameGroup(couponNameGroup.toString().substring(1));
					}
					fullGiveCustoms.add(fullGiveCustom);	
				}
				map.put("fullGives", fullGiveCustoms);
			}else if(preferentialInfoType.equals("4")){
				FullDiscountExample fullDiscountExample = new FullDiscountExample();
				fullDiscountExample.createCriteria().andIdEqualTo(preferentialInfo.getPreferentialId()).andDelFlagEqualTo("0");
				List<FullDiscount> fullDiscounts = fullDiscountService.selectByExample(fullDiscountExample);
				if(CollectionUtils.isNotEmpty(fullDiscounts) && type.equals("2")){
					String newRule = "";
					for (FullDiscount fullDiscount : fullDiscounts) {
						String newRule1 = "";
						String newRule2 = "";
						if(fullDiscount.getType().equals("3")){
							List<String> list = new ArrayList<String>();
							String[] rules = fullDiscount.getRule().split("\\|");
							for (String rule : rules) {
								String[] ruleStrs = rule.split(",");
								newRule1 = ruleStrs[0];
								newRule2 = String.valueOf(Double.valueOf(ruleStrs[1].toString())/10);
								newRule = newRule1+","+newRule2;
								list.add(newRule);
							}
							String returnStr=StringUtils.join(list, "|");
							fullDiscount.setRule(returnStr);
						}else if(fullDiscount.getType().equals("4")){
							if((version <= 34 && system.equals(Const.ANDROID)
									|| (version <= 35 && system.equals(Const.IOS)))){
								//兼容原因，旧版本没有这种优惠内容
								fullDiscounts.clear();
								break;
							}else{
								String[] rules = fullDiscount.getRule().split(",");
								newRule1 = rules[0];
								newRule2 = String.valueOf(Double.valueOf(rules[1].toString())/10);
								newRule = newRule1+","+newRule2;
								fullDiscount.setRule(newRule);
							}
						}
					}
				}
				map.put("fullDiscounts", fullDiscounts);
			}
		}
		return map;
	}

	public List<ShopPreferentialInfo> findModelsByMchtId(Integer mchtId, String type) {
		Date currentDate = new Date();
		ShopPreferentialInfoExample shopPreferentialInfoExample = new ShopPreferentialInfoExample();
		shopPreferentialInfoExample.createCriteria().andTypeEqualTo(type).andBeginDateLessThanOrEqualTo(currentDate).andEndDateGreaterThan(currentDate)
		.andStatusEqualTo("1").andDelFlagEqualTo("0").andMchtIdEqualTo(mchtId);
		List<ShopPreferentialInfo> shopPreferentialInfos = selectByExample(shopPreferentialInfoExample);
		return shopPreferentialInfos;
	}

}
