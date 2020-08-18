package com.jf.controller;
import com.gzs.common.utils.StringUtil;
import com.jf.bean.ExcelBean;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.StringUtils;
import com.jf.dao.BlackListMapper;
import com.jf.dao.IndexTopStyleMapper;
import com.jf.entity.*;
import com.jf.service.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MarketingController extends BaseController {
	
	@Resource
	private CouponService couponService;

	@Resource
	private MemberGroupService memberGroupService;
	
	@Resource
	private MemberCouponService memberCouponService;
	
	@Resource
	private ProductTypeService productTypeService;
	
	@Resource
	private SignInManageService signInManageService;
	
	@Resource
	private SignInSettingService signInSettingService;
	
	@Resource
	private CumulativeSignInSettingService cumulativeSignInSettingService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private SupplementarySignInSettingService supplementarySignInSettingService;
	
	@Resource
	private MemberSupplementarySignInService memberSupplementarySignInService;
	
	@Resource
	private BlackListMapper blackListMapper;
	
	@Resource
	private AssistanceDtlService assistanceDtlService;
	
	@Resource
	private MemberSignInSettingService memberSignInSettingService;
	
	@Resource
	private MemberCumulativeSignInService memberCumulativeSignInService;
	
	@Resource
	private MemberInfoService memberInfoService;
	
	@Autowired
	private BlackListService blackListService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	private CashTransferService cashTransferService;
	
	@Autowired
	private WithdrawOrderService withdrawOrderService;
	
	@Autowired
	private IndexTopStyleMapper indexTopStyleMapper;
	
	@Autowired
	private IndexTopTabService indexTopTabService;
	
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	@Autowired
	private BrandteamTypeService brandteamTypeService;
	
	@Autowired
	private MallCategoryService mallCategoryService;
	
	@Autowired
	private CouponAddTaskConfigService couponAddTaskConfigService;
	
	@Autowired
	private ActivityAreaService activityAreaService;
	
	@Autowired
	private CustomPageService customPageService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private CouponCombineRecLimitDtlService couponCombineRecLimitDtlService;
	
	@Autowired
	private CouponCombineRecLimitService couponCombineRecLimitService;

	@Autowired
	private PersonalCenterThemeBackgroundService personalCenterThemeBackgroundService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 创建优惠劵
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/coupon/add.shtml")
	public String marketingCouponAdd(Model model,HttpServletRequest request) {
		String rtPage = "/marketing/coupon/add";// 重定向
		
		MemberGroupExample memberGroupExample=new MemberGroupExample();
		memberGroupExample.createCriteria().andDelFlagEqualTo("0");	
		List<MemberGroup> memberGroups=memberGroupService.selectByExample(memberGroupExample);
		model.addAttribute("memberGroups", memberGroups);
		model.addAttribute("expiryTypes", DataDicUtil.getStatusList("BU_COUPON", "EXPIRY_TYPE"));
		model.addAttribute("recTypes", DataDicUtil.getStatusList("BU_COUPON", "REC_TYPE"));
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		model.addAttribute("productTypes", productTypes);
		model.addAttribute("conditionTypeS", DataDicUtil.getStatusList("BU_COUPON", "CONITION_TYPE"));
		
		BrandteamTypeExample brandteamTypeExample=new BrandteamTypeExample();//新品牌团一级类目
		brandteamTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<BrandteamType> brandteamTypes=brandteamTypeService.selectByExample(brandteamTypeExample);
		model.addAttribute("brandteamTypes", brandteamTypes);
		
		MallCategoryExample mallCategoryExample=new MallCategoryExample();//商城一级类目
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategories=mallCategoryService.selectByExample(mallCategoryExample);
		model.addAttribute("mallCategories", mallCategories);
		
		
		List<String> linkTypeNames = new ArrayList<String>();
		linkTypeNames.add("会场ID");
		linkTypeNames.add("活动ID");
		linkTypeNames.add("商品ID");
		linkTypeNames.add("外部链接");
		linkTypeNames.add("无链接");
		linkTypeNames.add("栏目");
		linkTypeNames.add("自建页面ID");
		linkTypeNames.add("淘宝优选关键字");
		linkTypeNames.add("品牌团一级类目");
		linkTypeNames.add("商家店铺");
		linkTypeNames.add("品牌特卖一级分类");
		linkTypeNames.add("淘宝优选频道");
		linkTypeNames.add("新品牌团二级分类");
		linkTypeNames.add("有好货二级分类");
		linkTypeNames.add("有好货品牌ID");
		linkTypeNames.add("潮鞋上新二级分类");
		linkTypeNames.add("潮鞋上新品牌ID");
		linkTypeNames.add("潮流男装二级分类");
		linkTypeNames.add("潮流男装品牌ID");
		linkTypeNames.add("断码特惠二级分类");
		linkTypeNames.add("断码特惠品牌ID");
		linkTypeNames.add("运动鞋服二级分类");
		linkTypeNames.add("运动鞋服品牌ID");
		linkTypeNames.add("潮流美妆二级分类");
		linkTypeNames.add("潮流美妆品牌ID");
		linkTypeNames.add("食品超市二级分类");
		linkTypeNames.add("食品超市品牌ID");
		linkTypeNames.add("商城一级分类");
		linkTypeNames.add("优惠券ID");

		JSONArray linkTypeJa = new JSONArray();
		for(int i=1; i<=linkTypeNames.size();i++){
			if(i==1 || i==2 || i==3 || i==4 || i==6 || i==7 || i==9 || i==11 || i==28){
				JSONObject jo = new JSONObject();
				jo.put("linkType", i);
				jo.put("linkTypeName", linkTypeNames.get(i-1));
				linkTypeJa.add(jo);
			}else {
				continue;
			}
		}
		model.addAttribute("linkTypeList", linkTypeJa);
		
		List<String> linkValueNames = new ArrayList<String>();
		linkValueNames.add("新用户专享");
		linkValueNames.add("单品爆款");
		linkValueNames.add("限时抢购");
		linkValueNames.add("新用户秒杀");
		linkValueNames.add("积分商城");
		linkValueNames.add("断码清仓");
		linkValueNames.add("签到");
		linkValueNames.add("砍价");
		linkValueNames.add("邀请免单");
		linkValueNames.add("有好货");
		linkValueNames.add("每日好店");
		linkValueNames.add("新品牌团");
		linkValueNames.add("新星计划");
		linkValueNames.add("淘宝优选");
		linkValueNames.add("潮鞋上新");
		linkValueNames.add("潮流男装");
		linkValueNames.add("断码特惠");
		linkValueNames.add("运动鞋服");
		linkValueNames.add("潮流美妆");
		linkValueNames.add("食品超市");
		linkValueNames.add("大学生创业");
		linkValueNames.add("SVIP");
		JSONArray linkValueJa = new JSONArray();
		for(int j=1; j<=linkValueNames.size();j++){
			JSONObject jo = new JSONObject();
			jo.put("linkValue", j);
			jo.put("linkValueName", linkValueNames.get(j-1));
			linkValueJa.add(jo);
		}
		model.addAttribute("linkValueList", linkValueJa);
		
		return rtPage;
	}
	
	/**
	 * 优惠劵录入保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/coupon/saveSubmit.shtml")
	public ModelAndView marketingCouponSaveSubmit(HttpServletRequest request, Coupon coupon,String linkValue,String linkValue0,String linkValue1,String linkValue2,String linkValue3,String productTypeId,String discount,String days,String addCount,String minimum1) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			
			if (!"2".equals(coupon.getRecType())){
				coupon.setNeedIntegral(0);
			}
			
			if (!"2".equals(coupon.getRecLimitType())){
				coupon.setRecEach(0);
			}
			coupon.setCreateBy(staffId);
			coupon.setCreateDate(new Date());
			/*coupon.setPreferentialType("1");*/
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("recBeginDate")) ){
				coupon.setRecBeginDate(dateFormat.parse(request.getParameter("recBeginDate")+":00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("recEndDate")) ){
				coupon.setRecEndDate(dateFormat.parse(request.getParameter("recEndDate")+":59"));
			}
			
			if ("1".equals(coupon.getExpiryType())){
				if(!StringUtil.isEmpty(request.getParameter("expiryBeginDate")) ){
					coupon.setExpiryBeginDate(dateFormat.parse(request.getParameter("expiryBeginDate")+":00"));
				}
				
				if(!StringUtil.isEmpty(request.getParameter("expiryEndDate")) ){
					coupon.setExpiryEndDate(dateFormat.parse(request.getParameter("expiryEndDate")+":59"));
				}
			}
			/*if(!StringUtils.isEmpty(coupon.getTypeIds())){//2.品类券
				coupon.setCouponType("2");
			}else{//1.全场通用
				coupon.setCouponType("1");
			}*/
			if (!StringUtils.isEmpty(coupon.getCouponType())){
				if (coupon.getCouponType().equals("2")) {//2.品类券
					coupon.setCouponType("2");
					if (!StringUtils.isEmpty(productTypeId)) {
						coupon.setTypeIds(productTypeId);
					}
				}else {//1.全场通用
					coupon.setCouponType("1");
				}
			}
			if (!StringUtils.isEmpty(linkValue)) {
				coupon.setLinkValue(linkValue);
			}else if (!StringUtils.isEmpty(linkValue0)) {
				coupon.setLinkValue(linkValue0);
			}else if (!StringUtils.isEmpty(linkValue1)) {
				coupon.setLinkValue(linkValue1);
			}else if (!StringUtils.isEmpty(linkValue2)) {
				coupon.setLinkValue(linkValue2);
			}else if (!StringUtils.isEmpty(linkValue3)) {
				coupon.setLinkValue(linkValue3);
			}
			
			if (!StringUtils.isEmpty(discount)) {
				BigDecimal Discount = new BigDecimal(discount).divide(new BigDecimal(10),2, BigDecimal.ROUND_HALF_DOWN);
				coupon.setDiscount(Discount);
			}
			
			if (!StringUtil.isEmpty(minimum1)) {
				coupon.setMinimum(new BigDecimal(minimum1));
			}
			
			if (request.getParameter("recLimitType").equals("2")) {
				coupon.setRecEach(Integer.valueOf(request.getParameter("recEach")));
			}
			
			if (!StringUtil.isEmpty(request.getParameter("isSupportCouponRain"))) {//是否参与红包雨
				coupon.setIsSupportCouponRain(request.getParameter("isSupportCouponRain"));
			}
			
			if (!StringUtil.isEmpty(request.getParameter("limitCouponRainScore"))) {
				coupon.setLimitCouponRainScore(Integer.valueOf(request.getParameter("limitCouponRainScore")));
			}
			
			couponService.insertSelective(coupon);
			
			if (!StringUtils.isEmpty(days)) {
				if (days.equals("1")) {//追加日期
					CouponAddtaskConfig couponAddtaskConfig=new CouponAddtaskConfig();
					couponAddtaskConfig.setCouponId(coupon.getId());
					if(!StringUtil.isEmpty(request.getParameter("beginDate")) ){
						couponAddtaskConfig.setBeginDate(dateFormat.parse(request.getParameter("beginDate")+":00"));
					}
					
					if(!StringUtil.isEmpty(request.getParameter("endDate")) ){
						couponAddtaskConfig.setEndDate(dateFormat.parse(request.getParameter("endDate")+":59"));
					}
					couponAddtaskConfig.setAddCount(Integer.valueOf(addCount));
					couponAddtaskConfig.setCreateBy(staffId);
					couponAddtaskConfig.setCreateDate(new Date());
					couponAddtaskConfig.setStatus("1");
					couponAddtaskConfig.setDelFlag("0");
					couponAddTaskConfigService.insert(couponAddtaskConfig);
				}
				
			}
			
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 优惠劵列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/coupon/list.shtml")
	public ModelAndView marketingCouponList(HttpServletRequest request) {
		String rtPage = "/marketing/coupon/list";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("couponStatus", DataDicUtil.getStatusList("BU_COUPON", "STATUS"));
		resMap.put("roleType", request.getParameter("roleType"));
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 优惠劵列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/coupon/data.shtml")
	@ResponseBody
	public Map<String, Object> marketingCouponData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			CouponCustomExample couponCustomExample=new CouponCustomExample();
			CouponCustomExample.CouponCustomCriteria couponCustomCriteria=couponCustomExample.createCriteria();
			couponCustomCriteria.andDelFlagEqualTo("0").andRangEqualTo("1").andRecTypeNotEqualTo("4");
			couponCustomExample.setOrderByClause("a.id desc");
						
			if(!StringUtil.isEmpty(request.getParameter("staff"))){
				StaffBean staffBean = this.getSessionStaffBean(request);
				int staffId=Integer.valueOf(staffBean.getStaffID());
				couponCustomCriteria.andCreateByEqualTo(staffId);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("name")) ){
				String name=request.getParameter("name");
				couponCustomCriteria.andNameLike("%"+name+"%");
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("date_begin")) ){
				couponCustomCriteria.andCreateDateGreaterThanOrEqualTo(dateFormat.parse(request.getParameter("date_begin")+" 00:00:00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("date_end")) ){
				couponCustomCriteria.andCreateDateLessThanOrEqualTo(dateFormat.parse(request.getParameter("date_end")+" 23:59:59"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				couponCustomCriteria.andStatusEqualTo(status);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("expiryFlag")) ){
				String expiryFlag=request.getParameter("expiryFlag");
				if ("1".equals(expiryFlag)){
					couponCustomCriteria.andWhetherExpiry("<=");
				}else{
					couponCustomCriteria.andWhetherExpiry(">");
				}
			}
			
			if(!StringUtil.isEmpty(request.getParameter("typeIds"))){
				couponCustomCriteria.andTypeIdsEqualTo(request.getParameter("typeIds"));
			}
			
			if (!StringUtil.isEmpty(request.getParameter("id"))) {
				couponCustomCriteria.andIdEqualTo(Integer.valueOf(request.getParameter("id")));
			}
			
			if (!StringUtil.isEmpty(request.getParameter("isSupportCouponRain"))) {
				couponCustomCriteria.andIsSupportCouponRainEqualTo(request.getParameter("isSupportCouponRain"));
			}
			totalCount = couponService.countCouponCustomByExample(couponCustomExample);
			
			couponCustomExample.setLimitStart(page.getLimitStart());
			couponCustomExample.setLimitSize(page.getLimitSize());
			List<CouponCustom> couponCustoms=couponService.selectCouponCustomByExample(couponCustomExample);
		
			resMap.put("Rows", couponCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 查看优惠劵
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/coupon/view.shtml")
	public String marketingCouponView(Model model,HttpServletRequest request) {
		String rtPage = "/marketing/coupon/view";// 重定向
		
		CouponCustom couponCustom=couponService.selectCouponCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));	
		model.addAttribute("couponCustom", couponCustom);
	
		if (couponCustom.getDiscount()!=null) {
			String Discount1=couponCustom.getDiscount().toString();//折扣价
			BigDecimal Discount = new BigDecimal(Discount1).multiply(new BigDecimal(10)).setScale(1);
			model.addAttribute("Discount", Discount);	
		}
		
		CouponCombineRecLimitDtlExample combineRecLimitDtlExample=new CouponCombineRecLimitDtlExample();
		combineRecLimitDtlExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(couponCustom.getId());
		List<CouponCombineRecLimitDtl> combineRecLimitDtls=couponCombineRecLimitDtlService.selectByExample(combineRecLimitDtlExample);
		if (combineRecLimitDtls.size()>0) {//关联优惠券组
			CouponCombineRecLimitCustom combineRecLimitCustom=couponCombineRecLimitService.selectByCustomPrimaryKey(combineRecLimitDtls.get(0).getCouponCombineRecLimitId());
			model.addAttribute("CouponIdgroup", combineRecLimitCustom.getCouponIdgroup());
		}
		
		
		model.addAttribute("expiryTypes", DataDicUtil.getStatusList("BU_COUPON", "EXPIRY_TYPE"));
		model.addAttribute("recTypes", DataDicUtil.getStatusList("BU_COUPON", "REC_TYPE"));
		model.addAttribute("conditionTypeS", DataDicUtil.getStatusList("BU_COUPON", "CONITION_TYPE"));
		
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		model.addAttribute("productTypes", productTypes);
		if(!StringUtils.isEmpty(couponCustom.getTypeIds())){
			String[] typeIdsArray = couponCustom.getTypeIds().split(",");
			List<Integer> typeIdList = new ArrayList<Integer>();
			for(String typeId:typeIdsArray){
				if(!StringUtils.isEmpty(typeId)){
					typeIdList.add(Integer.parseInt(typeId));
				}
			}
			model.addAttribute("typeIdList", typeIdList);
		}
		
		CouponAddtaskConfigExample couponAddtaskConfigExample=new CouponAddtaskConfigExample();
		couponAddtaskConfigExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(couponCustom.getId());
		List<CouponAddtaskConfig> couponAddtaskConfigs=couponAddTaskConfigService.selectByExample(couponAddtaskConfigExample);
		if (couponAddtaskConfigs!=null && couponAddtaskConfigs.size()>0) {
			
			model.addAttribute("couponAddtaskConfigs", couponAddtaskConfigs.get(0));
		}
		
		
		BrandteamTypeExample brandteamTypeExample=new BrandteamTypeExample();//新品牌团一级类目
		brandteamTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<BrandteamType> brandteamTypes=brandteamTypeService.selectByExample(brandteamTypeExample);
		model.addAttribute("brandteamTypes", brandteamTypes);
		
		MallCategoryExample mallCategoryExample=new MallCategoryExample();//商城一级类目
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategories=mallCategoryService.selectByExample(mallCategoryExample);
		model.addAttribute("mallCategories", mallCategories);
		
		List<String> linkTypeNames = new ArrayList<String>();
		linkTypeNames.add("会场ID");
		linkTypeNames.add("活动ID");
		linkTypeNames.add("商品ID");
		linkTypeNames.add("外部链接");
		linkTypeNames.add("无链接");
		linkTypeNames.add("栏目");
		linkTypeNames.add("自建页面ID");
		linkTypeNames.add("淘宝优选关键字");
		linkTypeNames.add("品牌团一级类目");
		linkTypeNames.add("商家店铺");
		linkTypeNames.add("品牌特卖一级分类");
		linkTypeNames.add("淘宝优选频道");
		linkTypeNames.add("新品牌团二级分类");
		linkTypeNames.add("有好货二级分类");
		linkTypeNames.add("有好货品牌ID");
		linkTypeNames.add("潮鞋上新二级分类");
		linkTypeNames.add("潮鞋上新品牌ID");
		linkTypeNames.add("潮流男装二级分类");
		linkTypeNames.add("潮流男装品牌ID");
		linkTypeNames.add("断码特惠二级分类");
		linkTypeNames.add("断码特惠品牌ID");
		linkTypeNames.add("运动鞋服二级分类");
		linkTypeNames.add("运动鞋服品牌ID");
		linkTypeNames.add("潮流美妆二级分类");
		linkTypeNames.add("潮流美妆品牌ID");
		linkTypeNames.add("食品超市二级分类");
		linkTypeNames.add("食品超市品牌ID");
		linkTypeNames.add("商城一级分类");
		linkTypeNames.add("优惠券ID");

		JSONArray linkTypeJa = new JSONArray();
		for(int i=1; i<=linkTypeNames.size();i++){
			if(i==1 || i==2 || i==3 || i==4 || i==6 || i==7 || i==9 || i==11 || i==28){
				JSONObject jo = new JSONObject();
				jo.put("linkType", i);
				jo.put("linkTypeName", linkTypeNames.get(i-1));
				linkTypeJa.add(jo);
			}else {
				continue;
			}
		}
		model.addAttribute("linkTypeList", linkTypeJa);
		
		List<String> linkValueNames = new ArrayList<String>();
		linkValueNames.add("新用户专享");
		linkValueNames.add("单品爆款");
		linkValueNames.add("限时抢购");
		linkValueNames.add("新用户秒杀");
		linkValueNames.add("积分商城");
		linkValueNames.add("断码清仓");
		linkValueNames.add("签到");
		linkValueNames.add("砍价");
		linkValueNames.add("邀请免单");
		linkValueNames.add("有好货");
		linkValueNames.add("每日好店");
		linkValueNames.add("新品牌团");
		linkValueNames.add("新星计划");
		linkValueNames.add("淘宝优选");
		linkValueNames.add("潮鞋上新");
		linkValueNames.add("潮流男装");
		linkValueNames.add("断码特惠");
		linkValueNames.add("运动鞋服");
		linkValueNames.add("潮流美妆");
		linkValueNames.add("食品超市");
		linkValueNames.add("大学生创业");
		linkValueNames.add("SVIP");
		JSONArray linkValueJa = new JSONArray();
		for(int j=1; j<=linkValueNames.size();j++){
			JSONObject jo = new JSONObject();
			jo.put("linkValue", j);
			jo.put("linkValueName", linkValueNames.get(j-1));
			linkValueJa.add(jo);
		}
		model.addAttribute("linkValueList", linkValueJa);
		return rtPage;
	}
	
	/**
	 * 管理优惠劵
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/coupon/edit.shtml")
	public String marketingCouponEdit(Model model,HttpServletRequest request) {
		String rtPage = "/marketing/coupon/edit";// 重定向
		
		String staffId = this.getSessionStaffBean(request).getStaffID();
		SysStaffRoleExample staffRoleExample = new SysStaffRoleExample();
		staffRoleExample.createCriteria().andStatusEqualTo("A").andStaffIdEqualTo(Integer.parseInt(staffId)).andRoleIdEqualTo(Const.ROLE_ID_84);
		List<SysStaffRole> staffRoleList = sysStaffRoleService.selectByExample(staffRoleExample);
		CouponCustom couponCustom=couponService.selectCouponCustomByPrimaryKey(Integer.valueOf(request.getParameter("id")));
		boolean subFlag = true;
		String subMag = "";
		if(couponCustom.getCreateBy().toString().equals(staffId)) {
			subFlag = false;
			subMag = "对不起，创建人不可以启用优惠券。";
		}else {
			if(CollectionUtils.isEmpty(staffRoleList)) {
				subFlag = false;
				subMag = "对不起，您没有权限启用高额优惠券。";
			}
		}
		
		model.addAttribute("subFlag", subFlag);
		model.addAttribute("subMag", subMag);
		model.addAttribute("couponCustom", couponCustom);
		
		MemberGroupExample memberGroupExample=new MemberGroupExample();
		memberGroupExample.createCriteria().andDelFlagEqualTo("0");	
		List<MemberGroup> memberGroups=memberGroupService.selectByExample(memberGroupExample);
		model.addAttribute("memberGroups", memberGroups);
		
		model.addAttribute("expiryTypes", DataDicUtil.getStatusList("BU_COUPON", "EXPIRY_TYPE"));
		model.addAttribute("recTypes", DataDicUtil.getStatusList("BU_COUPON", "REC_TYPE"));
		model.addAttribute("couponStatus", DataDicUtil.getStatusList("BU_COUPON", "STATUS"));
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1").andTLevelEqualTo(1);
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		model.addAttribute("productTypes", productTypes);
		
		
		List<String> linkTypeNames = new ArrayList<String>();
		linkTypeNames.add("会场ID");
		linkTypeNames.add("活动ID");
		linkTypeNames.add("商品ID");
		linkTypeNames.add("外部链接");
		linkTypeNames.add("无链接");
		linkTypeNames.add("栏目");
		linkTypeNames.add("自建页面ID");
		linkTypeNames.add("淘宝优选关键字");
		linkTypeNames.add("品牌团一级类目");
		linkTypeNames.add("商家店铺");
		linkTypeNames.add("品牌特卖一级分类");
		linkTypeNames.add("淘宝优选频道");
		linkTypeNames.add("新品牌团二级分类");
		linkTypeNames.add("有好货二级分类");
		linkTypeNames.add("有好货品牌ID");
		linkTypeNames.add("潮鞋上新二级分类");
		linkTypeNames.add("潮鞋上新品牌ID");
		linkTypeNames.add("潮流男装二级分类");
		linkTypeNames.add("潮流男装品牌ID");
		linkTypeNames.add("断码特惠二级分类");
		linkTypeNames.add("断码特惠品牌ID");
		linkTypeNames.add("运动鞋服二级分类");
		linkTypeNames.add("运动鞋服品牌ID");
		linkTypeNames.add("潮流美妆二级分类");
		linkTypeNames.add("潮流美妆品牌ID");
		linkTypeNames.add("食品超市二级分类");
		linkTypeNames.add("食品超市品牌ID");
		linkTypeNames.add("商城一级分类");
		linkTypeNames.add("优惠券ID");

		JSONArray linkTypeJa = new JSONArray();
		for(int i=1; i<=linkTypeNames.size();i++){
			if(i==1 || i==2 || i==3 || i==4 || i==6 || i==7 || i==9 || i==11 || i==28){
				JSONObject jo = new JSONObject();
				jo.put("linkType", i);
				jo.put("linkTypeName", linkTypeNames.get(i-1));
				linkTypeJa.add(jo);
			}else {
				continue;
			}
		}
		model.addAttribute("linkTypeList", linkTypeJa);
		
		List<String> linkValueNames = new ArrayList<String>();
		linkValueNames.add("新用户专享");
		linkValueNames.add("单品爆款");
		linkValueNames.add("限时抢购");
		linkValueNames.add("新用户秒杀");
		linkValueNames.add("积分商城");
		linkValueNames.add("断码清仓");
		linkValueNames.add("签到");
		linkValueNames.add("砍价");
		linkValueNames.add("邀请免单");
		linkValueNames.add("有好货");
		linkValueNames.add("每日好店");
		linkValueNames.add("新品牌团");
		linkValueNames.add("新星计划");
		linkValueNames.add("淘宝优选");
		linkValueNames.add("潮鞋上新");
		linkValueNames.add("潮流男装");
		linkValueNames.add("断码特惠");
		linkValueNames.add("运动鞋服");
		linkValueNames.add("潮流美妆");
		linkValueNames.add("食品超市");
		linkValueNames.add("大学生创业");
		linkValueNames.add("SVIP");
		JSONArray linkValueJa = new JSONArray();
		for(int j=1; j<=linkValueNames.size();j++){
			JSONObject jo = new JSONObject();
			jo.put("linkValue", j);
			jo.put("linkValueName", linkValueNames.get(j-1));
			linkValueJa.add(jo);
		}
		model.addAttribute("linkValueList", linkValueJa);
		
		CouponAddtaskConfigExample couponAddtaskConfigExample=new CouponAddtaskConfigExample();
		couponAddtaskConfigExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(couponCustom.getId());
		List<CouponAddtaskConfig> couponAddtaskConfigs=couponAddTaskConfigService.selectByExample(couponAddtaskConfigExample);
		if (couponAddtaskConfigs!=null && couponAddtaskConfigs.size()>0) {
			
			model.addAttribute("couponAddtaskConfigs", couponAddtaskConfigs.get(0));
		}
		
		BrandteamTypeExample brandteamTypeExample=new BrandteamTypeExample();//新品牌团一级类目
		brandteamTypeExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<BrandteamType> brandteamTypes=brandteamTypeService.selectByExample(brandteamTypeExample);
		model.addAttribute("brandteamTypes", brandteamTypes);
		
		MallCategoryExample mallCategoryExample=new MallCategoryExample();//商城一级类目
		mallCategoryExample.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<MallCategory> mallCategories=mallCategoryService.selectByExample(mallCategoryExample);
		model.addAttribute("mallCategories", mallCategories);
		
		if (couponCustom.getDiscount()!=null) {
			String Discount1=couponCustom.getDiscount().toString();//折扣价
			BigDecimal Discount = new BigDecimal(Discount1).multiply(new BigDecimal(10)).setScale(1);
			model.addAttribute("Discount", Discount);	
		}
		
		CouponCombineRecLimitDtlExample combineRecLimitDtlExample=new CouponCombineRecLimitDtlExample();
		combineRecLimitDtlExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(couponCustom.getId());
		List<CouponCombineRecLimitDtl> combineRecLimitDtls=couponCombineRecLimitDtlService.selectByExample(combineRecLimitDtlExample);
		if (combineRecLimitDtls.size()>0) {//关联优惠券组
			CouponCombineRecLimitCustom combineRecLimitCustom=couponCombineRecLimitService.selectByCustomPrimaryKey(combineRecLimitDtls.get(0).getCouponCombineRecLimitId());
			model.addAttribute("CouponIdgroup", combineRecLimitCustom.getCouponIdgroup());
		}
		
		model.addAttribute("conditionTypeS", DataDicUtil.getStatusList("BU_COUPON", "CONITION_TYPE"));
		return rtPage;
	}
	
	/**
	 * 商家录入保存
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/coupon/editSubmit.shtml")
	public ModelAndView marketingCouponEditSubmit(HttpServletRequest request, Coupon coupon,String linkValue,String linkValue0,String linkValue1,String linkValue2,String linkValue3,String productTypeId,String discount,String days,String addCount,String minimum1,String preferentialType) {
		String rtPage = "/success/success";
		Map<String, Object> resMap = new HashMap<String, Object>(); 
		String code = null;
		String msg =null;
		try {
			Integer staffId=Integer.valueOf(this.getSessionStaffBean(request).getStaffID());
			
			coupon.setUpdateBy(staffId);
			coupon.setUpdateDate(new Date());
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtil.isEmpty(request.getParameter("recBeginDate")) ){
				coupon.setRecBeginDate(dateFormat.parse(request.getParameter("recBeginDate")+":00"));
			}
			
			if(!StringUtil.isEmpty(request.getParameter("recEndDate")) ){
				coupon.setRecEndDate(dateFormat.parse(request.getParameter("recEndDate")+":59"));
			}
			
			if ("1".equals(coupon.getExpiryType())){
				if(!StringUtil.isEmpty(request.getParameter("expiryBeginDate")) ){
					coupon.setExpiryBeginDate(dateFormat.parse(request.getParameter("expiryBeginDate")+" 00:00:00"));
				}
				
				if(!StringUtil.isEmpty(request.getParameter("expiryEndDate")) ){
					coupon.setExpiryEndDate(dateFormat.parse(request.getParameter("expiryEndDate")+" 23:59:59"));
				}
			}
			
			if (!StringUtils.isEmpty(coupon.getCouponType())){
				if (coupon.getCouponType().equals("2")) {//2.品类券
					coupon.setCouponType("2");
					if (!StringUtils.isEmpty(productTypeId)) {
						coupon.setTypeIds(productTypeId);
					}
				}else {//1.全场通用
					coupon.setCouponType("1");
				}
			}
			if (!StringUtils.isEmpty(linkValue) && (coupon.getLinkType().equals("1") || coupon.getLinkType().equals("2") || coupon.getLinkType().equals("3") || coupon.getLinkType().equals("4") || coupon.getLinkType().equals("7"))) {
				coupon.setLinkValue(linkValue);
			}else if (!StringUtils.isEmpty(linkValue0) && coupon.getLinkType().equals("6")) {
				coupon.setLinkValue(linkValue0);
			}else if (!StringUtils.isEmpty(linkValue1) && coupon.getLinkType().equals("11")) {
				coupon.setLinkValue(linkValue1);
			}else if (!StringUtils.isEmpty(linkValue2) && coupon.getLinkType().equals("9")) {
				coupon.setLinkValue(linkValue2);
			}else if (!StringUtils.isEmpty(linkValue3) && coupon.getLinkType().equals("28")) {
				coupon.setLinkValue(linkValue3);
			}
			
			if (!StringUtils.isEmpty(discount)) {
				BigDecimal Discount = new BigDecimal(discount).divide(new BigDecimal(10),2, BigDecimal.ROUND_HALF_DOWN);
				coupon.setDiscount(Discount);
			}
			
			if (!StringUtil.isEmpty(minimum1) && "2".equals(preferentialType)) {
				coupon.setMinimum(new BigDecimal(minimum1));
			}
			
			couponService.updateByPrimaryKeySelective(coupon);
			
			
			if (!StringUtils.isEmpty(days)) {
				if (days.equals("1")) {//追加日期
					
					CouponAddtaskConfigExample couponAddtaskConfigExample=new CouponAddtaskConfigExample();
					couponAddtaskConfigExample.createCriteria().andDelFlagEqualTo("0").andCouponIdEqualTo(coupon.getId());
					List<CouponAddtaskConfig> couponAddtaskConfigs=couponAddTaskConfigService.selectByExample(couponAddtaskConfigExample);
					if (couponAddtaskConfigs.size()>0) {
						
						CouponAddtaskConfig couponAddtaskConfig=new CouponAddtaskConfig() ;
						if(!StringUtil.isEmpty(request.getParameter("beginDate")) ){
							couponAddtaskConfig.setBeginDate(dateFormat.parse(request.getParameter("beginDate")+":00"));
						}
						
						if(!StringUtil.isEmpty(request.getParameter("endDate")) ){
							couponAddtaskConfig.setEndDate(dateFormat.parse(request.getParameter("endDate")+":59"));
						}
						couponAddtaskConfig.setAddCount(Integer.valueOf(addCount));
						couponAddtaskConfig.setUpdateBy(staffId);
						couponAddtaskConfig.setUpdateDate(new Date());
						couponAddTaskConfigService.updateByExampleSelective(couponAddtaskConfig, couponAddtaskConfigExample);
					}else {
						
						CouponAddtaskConfig couponAddtaskConfig=new CouponAddtaskConfig();
						couponAddtaskConfig.setCouponId(coupon.getId());
						if(!StringUtil.isEmpty(request.getParameter("beginDate")) ){
							couponAddtaskConfig.setBeginDate(dateFormat.parse(request.getParameter("beginDate")+":00"));
						}
						
						if(!StringUtil.isEmpty(request.getParameter("endDate")) ){
							couponAddtaskConfig.setEndDate(dateFormat.parse(request.getParameter("endDate")+":59"));
						}
						couponAddtaskConfig.setAddCount(Integer.valueOf(addCount));
						couponAddtaskConfig.setCreateBy(staffId);
						couponAddtaskConfig.setCreateDate(new Date());
						couponAddtaskConfig.setStatus("1");
						couponAddtaskConfig.setDelFlag("0");
						couponAddTaskConfigService.insert(couponAddtaskConfig);
					}
					
				}
				
			}
			
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
			msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
		} catch (Exception e) {
			  e.printStackTrace();
			 code = StateCode.JSON_AJAX_ERROR.getStateCode();
			 msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		resMap.put(this.JSON_RESULT_CODE, code);
		resMap.put(this.JSON_RESULT_MESSAGE, msg);

		return new ModelAndView(rtPage, resMap);
	}
	
	/**
	 * 优惠劵明细
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/coupon/detail.shtml")
	public ModelAndView marketingCouponDetail(HttpServletRequest request) {
		String rtPage = "/marketing/coupon/detail";// 首页
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("memberCouponStatus", DataDicUtil.getStatusList("BU_MEMBER_COUPON", "STATUS"));
		resMap.put("couponId", Integer.valueOf(request.getParameter("id")));
		return new ModelAndView(rtPage,resMap);
	}
	
	@RequestMapping(value = "/marketing/coupon/dataDetail.shtml")
	@ResponseBody
	public Map<String, Object> marketingCoupondataDetail(HttpServletRequest request,Page page) {
		Map<String,Object> resMap = new HashMap<String,Object>();
		Integer totalCount =0;

		try {
			 
			MemberCouponCustomExample memberCouponCustomExample=new MemberCouponCustomExample();
			MemberCouponCustomExample.MemberCouponCustomCriteria memberCouponCustomCriteria=memberCouponCustomExample.createCriteria();
			memberCouponCustomCriteria.andDelFlagEqualTo("0");
			memberCouponCustomExample.setOrderByClause(" a.id desc");
			
			memberCouponCustomCriteria.andCouponIdEqualTo(Integer.valueOf(request.getParameter("couponId")));
			
			if(!StringUtil.isEmpty(request.getParameter("nick")) ){
				String nick=request.getParameter("nick");
				memberCouponCustomCriteria.andNickLike(nick);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("mobile")) ){
				String mobile=request.getParameter("mobile");
				memberCouponCustomCriteria.andMobileEqualTo(mobile);
			}
			
			if(!StringUtil.isEmpty(request.getParameter("status")) ){
				String status=request.getParameter("status");
				memberCouponCustomCriteria.andStatusEqualTo(status);
			}
			
			totalCount = memberCouponService.countMemberCouponCustomByExample(memberCouponCustomExample);
			
			memberCouponCustomExample.setLimitStart(page.getLimitStart());
			memberCouponCustomExample.setLimitSize(page.getLimitSize());
			List<MemberCouponCustom> memberCouponCustoms=memberCouponService.selectMemberCouponCustomByExample(memberCouponCustomExample);
			
			resMap.put("Rows", memberCouponCustoms);
			resMap.put("Total", totalCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 签到管理列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInManage/list.shtml")
	public ModelAndView signInManageList(HttpServletRequest request) {
		String rtPage = "/marketing/signInManage/signInManage_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 签到管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInManage/data.shtml")
	@ResponseBody
	public Map<String, Object> signInManageData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SignInManageExample e = new SignInManageExample();
			e.setOrderByClause("year asc,month asc");
			SignInManageExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			String searchYear = request.getParameter("year");
			String searchMonth = request.getParameter("month");
			if(!StringUtils.isEmpty(searchYear)){
				c.andYearEqualTo(Integer.parseInt(searchYear));
			}
			if(!StringUtils.isEmpty(searchMonth)){
				c.andMonthEqualTo(Integer.parseInt(searchMonth));
			}
			totalCount = signInManageService.countSignInManageCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<SignInManageCustom> signInManageCustoms = signInManageService.selectSignInManageCustomByExample(e);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(SignInManageCustom signInManageCustom:signInManageCustoms){
				String year = signInManageCustom.getYear().toString();
				String month = signInManageCustom.getMonth().toString();
				String dateStrBegin = year+"-"+month+"-"+"01 00:00:00";
				Date dateBegin = sdf.parse(dateStrBegin);
				if(dateBegin.after(new Date())){
					signInManageCustom.setStatusDesc("未开始");
				}
				int lastDay = DateUtil.getDayMouth(year+"-"+month);
				String dateStrEnd = year+"-"+month+"-"+lastDay+" 23:59:59";
				Date dateEnd = sdf.parse(dateStrEnd);
				if(dateEnd.before(new Date())){
					signInManageCustom.setStatusDesc("已过期");
				}
				if(dateBegin.before(new Date()) && dateEnd.after(new Date())){
					signInManageCustom.setStatusDesc("已开始");
				}
			}
			resMap.put("Rows", signInManageCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 新增签到
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInManage/toEdit.shtml")
	public ModelAndView signInManageToEdit(Model model,HttpServletRequest request) {
		String rtPage = "/marketing/signInManage/signInManage_toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String dateStr = DateUtil.getMonth(1);
		resMap.put("year", dateStr.split("-")[0]);
		resMap.put("month", dateStr.split("-")[1]);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存签到
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInManage/save.shtml")
	@ResponseBody
	public Map<String, Object> signInManageSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			SignInManageExample e = new SignInManageExample();
			e.createCriteria().andYearEqualTo(Integer.parseInt(year)).andMonthEqualTo(Integer.parseInt(month)).andDelFlagEqualTo("0");
			int count = signInManageService.countByExample(e);
			if(count>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "年份月份重复，无法添加");
			}else{
				SignInManage signInManage = new SignInManage();
				signInManage.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				signInManage.setCreateDate(new Date());
				signInManage.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				signInManage.setUpdateDate(new Date());
				signInManage.setDelFlag("0");
				signInManage.setYear(Integer.parseInt(year));
				signInManage.setMonth(Integer.parseInt(month));
				signInManageService.insertSelective(signInManage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 签到设置列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/marketing/signInSetting/list.shtml")
	public ModelAndView signInSettingList(HttpServletRequest request) throws ParseException {
		String rtPage = "/marketing/signInSetting/signInSetting_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String signInManageId = request.getParameter("signInManageId");
		SignInSettingExample example = new SignInSettingExample();
		example.createCriteria().andDelFlagEqualTo("0").andSignInManageIdEqualTo(Integer.parseInt(signInManageId));
		int count = signInSettingService.countByExample(example);
		if(count == 0){//批量插入整月的签到数据
			SignInManage signInManage = signInManageService.selectByPrimaryKey(Integer.parseInt(signInManageId));
			String year = signInManage.getYear().toString();
			String month = signInManage.getMonth().toString();
			int lastDay = DateUtil.getDayMouth(year+"-"+month);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<SignInSetting> signInSettingList = new ArrayList<SignInSetting>();
			for(int i=1;i<=lastDay;i++){
				SignInSetting signInSetting = new SignInSetting();
				signInSetting.setDelFlag("0");
				signInSetting.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				signInSetting.setCreateDate(new Date());
				signInSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				signInSetting.setUpdateDate(new Date());
				signInSetting.setSignInManageId(Integer.parseInt(signInManageId));
				signInSetting.setSignInDate(sdf.parse(year+"-"+month+"-"+i));
				signInSetting.setRewardName("现金红包");
				signInSetting.setRewardType("1");//1.现金红包
				signInSettingList.add(signInSetting);
			}
			if(signInSettingList.size() > 0){
				signInSettingService.batchInsert(signInSettingList);
			}
		}
		resMap.put("rewardTypeList", DataDicUtil.getStatusList("BU_SIGN_IN_SETTING", "REWARD_TYPE"));
		resMap.put("signInManageId", signInManageId);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 签到设置列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInSetting/data.shtml")
	@ResponseBody
	public Map<String, Object> signInSettingData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SignInSettingExample e = new SignInSettingExample();
			e.setOrderByClause("sign_in_date asc");
			SignInSettingExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andSignInManageIdEqualTo(Integer.parseInt(request.getParameter("signInManageId")));
			String rewardType = request.getParameter("rewardType");
			if(!StringUtils.isEmpty(rewardType)){
				c.andRewardTypeEqualTo(rewardType);
			}
			totalCount = signInSettingService.countSignInSettingCustomByExample(e);
			List<SignInSettingCustom> signInSettingCustoms = signInSettingService.selectSignInSettingCustomByExample(e);
			resMap.put("Rows", signInSettingCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 编辑签到设置
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInSetting/toEdit.shtml")
	public ModelAndView signInSettingToEdit(Model model,HttpServletRequest request) {
		String rtPage = "/marketing/signInSetting/signInSetting_toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		SignInSetting signInSetting = signInSettingService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("signInSetting", signInSetting);
		if(signInSetting.getSignInDate().after(new Date())){
			resMap.put("canEdit", true);
		}
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 校验优惠券ID
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInSetting/checkCouponIds.shtml")
	@ResponseBody
	public Map<String, Object> checkCouponIds(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String couponIds = request.getParameter("couponIds");
			String[] couponIdsArray = couponIds.split(",");
			String errorcouponIds = "";
			for(String couponIdStr:couponIdsArray){
				Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(couponIdStr));
				if(coupon == null){
					errorcouponIds+=couponIdStr+",";
				}else{
					if(coupon.getRecQuantity()>=coupon.getGrantQuantity()){
						errorcouponIds+=couponIdStr+",";
					}
					if(coupon.getExpiryType().equals("1")){//绝对时间
						if(coupon.getExpiryEndDate().before(new Date())){
							errorcouponIds+=couponIdStr+",";
						}
					}
				}
			}
			if(!StringUtils.isEmpty(errorcouponIds)){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "错误的优惠券ID："+errorcouponIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 保存签到设置
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInSetting/save.shtml")
	@ResponseBody
	public Map<String, Object> signInSettingSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String rewardName = request.getParameter("rewardName");
			String rewardType = request.getParameter("rewardType");
			String rewardGift = request.getParameter("rewardGift");
			if(rewardType.equals("3")){//优惠券
				String[] couponIdsArray = rewardGift.split(",");
				for(String couponIdStr:couponIdsArray){
					Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(couponIdStr));
					if(coupon == null){
						resMap.put("returnCode", "0000");
						resMap.put("returnMsg", "优惠券ID有误，请检查");
						return resMap;
					}else{
						if(coupon.getRecQuantity()>=coupon.getGrantQuantity()){
							resMap.put("returnCode", "0000");
							resMap.put("returnMsg", "优惠券ID有误，请检查");
							return resMap;
						}
						if(coupon.getExpiryType().equals("1")){//绝对时间
							if(coupon.getExpiryEndDate().before(new Date())){
								resMap.put("returnCode", "0000");
								resMap.put("returnMsg", "优惠券ID有误，请检查");
								return resMap;
							}
						}
					}
				}
			}
			SignInSetting signInSetting = new SignInSetting();
			signInSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			signInSetting.setUpdateDate(new Date());
			signInSetting.setRewardName(rewardName);
			signInSetting.setRewardType(rewardType);
			signInSetting.setRewardGift(rewardGift);
			SignInSettingExample example = new SignInSettingExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
			signInSettingService.updateByExampleSelective(signInSetting, example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 累签设置列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/marketing/cumulativeSignInSetting/list.shtml")
	public ModelAndView cumulativeSignInSettingList(HttpServletRequest request) throws ParseException {
		String rtPage = "/marketing/cumulativeSignInSetting/cumulativeSignInSetting_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String signInManageId = request.getParameter("signInManageId");
		CumulativeSignInSettingExample example = new CumulativeSignInSettingExample();
		example.createCriteria().andDelFlagEqualTo("0").andSignInManageIdEqualTo(Integer.parseInt(signInManageId));
		int count = cumulativeSignInSettingService.countByExample(example);
		if(count == 0){//批量插入整月的累签数据
			List<CumulativeSignInSetting> cumulativeSignInSettingList = new ArrayList<CumulativeSignInSetting>();
			for(int i=0;i<5;i++){
				CumulativeSignInSetting cumulativeSignInSetting = new CumulativeSignInSetting();
				cumulativeSignInSetting.setDelFlag("0");
				cumulativeSignInSetting.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				cumulativeSignInSetting.setCreateDate(new Date());
				cumulativeSignInSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				cumulativeSignInSetting.setUpdateDate(new Date());
				cumulativeSignInSetting.setSignInManageId(Integer.parseInt(signInManageId));
				cumulativeSignInSetting.setStatus("1");//1.启用
				if(i==0){
					cumulativeSignInSetting.setCumulativeSignInCount(3);
				}else{
					cumulativeSignInSetting.setCumulativeSignInCount(i*7);
				}
				cumulativeSignInSettingList.add(cumulativeSignInSetting);
			}
			if(cumulativeSignInSettingList.size() > 0){
				cumulativeSignInSettingService.batchInsert(cumulativeSignInSettingList);
			}
		}
		resMap.put("signInManageId", signInManageId);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 累签设置列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/cumulativeSignInSetting/data.shtml")
	@ResponseBody
	public Map<String, Object> cumulativeSignInSettingData(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			CumulativeSignInSettingExample e = new CumulativeSignInSettingExample();
			e.setOrderByClause("cumulative_sign_in_count asc");
			CumulativeSignInSettingExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andSignInManageIdEqualTo(Integer.parseInt(request.getParameter("signInManageId")));
			totalCount = cumulativeSignInSettingService.countCumulativeSignInSettingCustomByExample(e);
			List<CumulativeSignInSettingCustom> cumulativeSignInSettingCustoms = cumulativeSignInSettingService.selectCumulativeSignInSettingCustomByExample(e);
			resMap.put("Rows", cumulativeSignInSettingCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 编辑累签奖励设置
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/cumulativeSignInSetting/toEdit.shtml")
	public ModelAndView cumulativeSignInSettingToEdit(Model model,HttpServletRequest request) {
		String rtPage = "/marketing/cumulativeSignInSetting/cumulativeSignInSetting_toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		CumulativeSignInSetting cumulativeSignInSetting = cumulativeSignInSettingService.selectByPrimaryKey(Integer.parseInt(request.getParameter("id")));
		resMap.put("cumulativeSignInSetting", cumulativeSignInSetting);
		resMap.put("type", request.getParameter("type"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 校验商品ID
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/cumulativeSignInSetting/checkProductCode.shtml")
	@ResponseBody
	public Map<String, Object> checkProductCode(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String productCode = request.getParameter("productCode");
			ProductCustomExample e = new ProductCustomExample();
			e.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(productCode).andSaleTypeEqualTo("2");
			List<ProductCustom> productCustoms = productService.selectProductCustomByExample(e);
			if(productCustoms==null || productCustoms.size()==0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "商品ID有误，请检查");
				return resMap;
			}else{
				if(productCustoms.get(0).getStock()<=0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "商品ID有误，请检查");
					return resMap;
				}
				String status = cumulativeSignInSettingService.getSingleProductActivityStatus(productCustoms.get(0).getId());
				if(!status.equals("0")){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "商品ID有误，请检查");
					return resMap;
				}
				resMap.put("stock", productCustoms.get(0).getStock());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 保存累签奖励设置
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/cumulativeSignInSetting/save.shtml")
	@ResponseBody
	public Map<String, Object> cumulativeSignInSettingSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String rewardName = request.getParameter("rewardName");
			String integral = request.getParameter("integral");
			String couponIds = request.getParameter("couponIds");
			String productCode = request.getParameter("productCode");
			String stock = request.getParameter("stock");
			if(!StringUtils.isEmpty(couponIds)){//优惠券
				String[] couponIdsArray = couponIds.split(",");
				for(String couponIdStr:couponIdsArray){
					Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(couponIdStr));
					if(coupon == null){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "优惠券ID有误，请检查");
						return resMap;
					}else{
						if(coupon.getRecQuantity()>=coupon.getGrantQuantity()){
							resMap.put("returnCode", "4004");
							resMap.put("returnMsg", "优惠券ID有误，请检查");
							return resMap;
						}
						if(coupon.getExpiryType().equals("1")){//绝对时间
							if(coupon.getExpiryEndDate().before(new Date())){
								resMap.put("returnCode", "4004");
								resMap.put("returnMsg", "优惠券ID有误，请检查");
								return resMap;
							}
						}
					}
				}
			}
			if(!StringUtils.isEmpty(productCode)){
				ProductCustomExample e = new ProductCustomExample();
				e.createCriteria().andDelFlagEqualTo("0").andCodeEqualTo(productCode).andSaleTypeEqualTo("2");
				List<ProductCustom> productCustoms = productService.selectProductCustomByExample(e);
				if(productCustoms==null || productCustoms.size()==0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "商品ID有误，请检查");
					return resMap;
				}else{
					if(productCustoms.get(0).getStock()<=0){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "商品ID有误，请检查");
						return resMap;
					}
					String status = cumulativeSignInSettingService.getSingleProductActivityStatus(productCustoms.get(0).getId());
					if(!status.equals("0")){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "商品ID有误，请检查");
						return resMap;
					}
				}
			}
			CumulativeSignInSetting cumulativeSignInSetting = new CumulativeSignInSetting();
			cumulativeSignInSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cumulativeSignInSetting.setUpdateDate(new Date());
			cumulativeSignInSetting.setRewardName(rewardName);
			if(!StringUtils.isEmpty(integral)){
				cumulativeSignInSetting.setIntegral(Integer.parseInt(integral));
			}
			if(!StringUtils.isEmpty(couponIds)){
				cumulativeSignInSetting.setCouponIds(couponIds);
			}
			if(!StringUtils.isEmpty(productCode)){
				cumulativeSignInSetting.setProductCode(productCode);
			}
			if(!StringUtils.isEmpty(stock)){
				cumulativeSignInSetting.setStock(Integer.parseInt(stock));
			}
			CumulativeSignInSettingExample example = new CumulativeSignInSettingExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
			cumulativeSignInSettingService.updateByExampleSelective(cumulativeSignInSetting, example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: clearRewardName
	 * @Description: (清除)
	 * @author Pengl
	 * @date 2019年4月1日 下午5:30:12
	 */
	@ResponseBody
	@RequestMapping(value = "/marketing/cumulativeSignInSetting/clearRewardName.shtml")
	public Map<String, Object> clearRewardName(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String type = request.getParameter("type");
			if(!StringUtil.isEmpty(id) && !StringUtil.isEmpty(type) ) {
				CumulativeSignInSetting cumulativeSignInSetting = cumulativeSignInSettingService.selectByPrimaryKey(Integer.parseInt(id));
				cumulativeSignInSetting.setRewardName(null);
				if("1".equals(type) ) {
					cumulativeSignInSetting.setIntegral(null);
				}
				if("2".equals(type) ) {
					cumulativeSignInSetting.setCouponIds(null);
				}
				if("3".equals(type) ) {
					cumulativeSignInSetting.setProductCode(null);
				}
				cumulativeSignInSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				cumulativeSignInSetting.setUpdateDate(new Date());
				cumulativeSignInSettingService.updateByPrimaryKey(cumulativeSignInSetting);
			}else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "ID为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 启用/关闭
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/cumulativeSignInSetting/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> changeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			CumulativeSignInSetting cumulativeSignInSetting = cumulativeSignInSettingService.selectByPrimaryKey(Integer.parseInt(id));
			if(cumulativeSignInSetting.getStatus().equals("0")){
				cumulativeSignInSetting.setStatus("1");
			}else{
				cumulativeSignInSetting.setStatus("0");
			}
			cumulativeSignInSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			cumulativeSignInSetting.setUpdateDate(new Date());
			cumulativeSignInSettingService.updateByPrimaryKeySelective(cumulativeSignInSetting);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 补签卡设置列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/supplementarySignInSetting/list.shtml")
	public ModelAndView supplementarySignInSettingList(HttpServletRequest request) {
		String rtPage = "/marketing/supplementarySignInSetting/supplementarySignInSetting_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 补签卡设置列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/supplementarySignInSetting/data.shtml")
	@ResponseBody
	public Map<String, Object> supplementarySignInSettingData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SupplementarySignInSettingExample e = new SupplementarySignInSettingExample();
			e.setOrderByClause("invitation_count asc");
			SupplementarySignInSettingExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			totalCount = supplementarySignInSettingService.countSupplementarySignInSettingCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<SupplementarySignInSettingCustom> supplementarySignInSettingCustoms = supplementarySignInSettingService.selectSupplementarySignInSettingCustomByExample(e);
			resMap.put("Rows", supplementarySignInSettingCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 新增补签卡页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/supplementarySignInSetting/toEdit.shtml")
	public ModelAndView supplementarySignInSettingToEdit(Model model,HttpServletRequest request) {
		String rtPage = "/marketing/supplementarySignInSetting/supplementarySignInSetting_toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存补签卡设置
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/supplementarySignInSetting/save.shtml")
	@ResponseBody
	public Map<String, Object> supplementarySignInSettingSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String invitationCount = request.getParameter("invitationCount");
			String supplementaryCardCount = request.getParameter("supplementaryCardCount");
			SupplementarySignInSettingExample e = new SupplementarySignInSettingExample();
			e.createCriteria().andSupplementaryCardCountEqualTo(Integer.parseInt(supplementaryCardCount)).andInvitationCountEqualTo(Integer.parseInt(invitationCount)).andDelFlagEqualTo("0");
			int count = supplementarySignInSettingService.countByExample(e);
			if(count>0){
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "已存在相同设置，无法添加");
			}else{
				SupplementarySignInSetting supplementarySignInSetting = new SupplementarySignInSetting();
				supplementarySignInSetting.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				supplementarySignInSetting.setCreateDate(new Date());
				supplementarySignInSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				supplementarySignInSetting.setUpdateDate(new Date());
				supplementarySignInSetting.setDelFlag("0");
				supplementarySignInSetting.setInvitationCount(Integer.parseInt(invitationCount));
				supplementarySignInSetting.setSupplementaryCardCount(Integer.parseInt(supplementaryCardCount));
				supplementarySignInSetting.setStatus("0");
				supplementarySignInSettingService.insertSelective(supplementarySignInSetting);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 启用/关闭
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/supplementarySignInSetting/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> supplementarySignInSettingChangeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			SupplementarySignInSetting supplementarySignInSetting = supplementarySignInSettingService.selectByPrimaryKey(Integer.parseInt(id));
			if(supplementarySignInSetting.getStatus().equals("0")){
				supplementarySignInSetting.setStatus("1");
			}else{
				supplementarySignInSetting.setStatus("0");
			}
			supplementarySignInSetting.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			supplementarySignInSetting.setUpdateDate(new Date());
			supplementarySignInSettingService.updateByPrimaryKeySelective(supplementarySignInSetting);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 补签卡邀请记录列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/memberSupplementarySignIn/list.shtml")
	public ModelAndView memberSupplementaryList(HttpServletRequest request) {
		String rtPage = "/marketing/memberSupplementarySignIn/memberSupplementarySignIn_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 补签卡邀请记录列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/memberSupplementarySignIn/data.shtml")
	@ResponseBody
	public Map<String, Object> memberSupplementarySignInData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			MemberSupplementarySignInExample e = new MemberSupplementarySignInExample();
			MemberSupplementarySignInExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(request.getParameter("memberId"))){
				c.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			totalCount = memberSupplementarySignInService.countMemberSupplementarySignInCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<MemberSupplementarySignInCustom> memberSupplementarySignInCustoms = memberSupplementarySignInService.selectMemberSupplementarySignInCustomByExample(e);
			resMap.put("Rows", memberSupplementarySignInCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 邀请记录列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/assistanceDtl/list.shtml")
	public ModelAndView assistanceDtlList(HttpServletRequest request) {
		String rtPage = "/marketing/assistanceDtl/assistanceDtl_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("memberSupplementarySignInId", request.getParameter("memberSupplementarySignInId"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 邀请记录列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/assistanceDtl/data.shtml")
	@ResponseBody
	public Map<String, Object> assistanceDtlData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String memberSupplementarySignInId = request.getParameter("memberSupplementarySignInId");
			AssistanceDtlExample e = new AssistanceDtlExample();
			AssistanceDtlExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMemberSupplementarySignInIdEqualTo(Integer.parseInt(memberSupplementarySignInId));
			if(!StringUtils.isEmpty(request.getParameter("memberId"))){
				c.andMemberIdEqualTo(Integer.parseInt(request.getParameter("memberId")));
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(request.getParameter("complete_date_begin"))){
				c.andCompleteDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("complete_date_begin")+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(request.getParameter("complete_date_end"))){
				c.andCompleteDateLessThanOrEqualTo(sdf.parse(request.getParameter("complete_date_end")+" 23:59:59"));
			}
			totalCount = assistanceDtlService.countAssistanceDtlCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<AssistanceDtlCustom> assistanceDtlCustoms = assistanceDtlService.selectAssistanceDtlCustomByExample(e);
			resMap.put("Rows", assistanceDtlCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 签到记录列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/memberSignInSetting/list.shtml")
	public ModelAndView memberSignInSettingList(HttpServletRequest request) {
		String rtPage = "/marketing/memberSignInSetting/memberSignInSetting_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("memberId", request.getParameter("memberId"));
		resMap.put("statusList", DataDicUtil.getStatusList("BU_SIGN_IN_SETTING", "REWARD_TYPE"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 签到记录列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/memberSignInSetting/data.shtml")
	@ResponseBody
	public Map<String, Object> memberSignInSettingData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String memberId = request.getParameter("memberId");
			MemberSignInSettingCustomExample e = new MemberSignInSettingCustomExample();
			MemberSignInSettingCustomExample.MemberSignInSettingCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMemberIdEqualTo(Integer.parseInt(memberId));
			String sign_in_time_begin = request.getParameter("sign_in_time_begin");
			String sign_in_time_end = request.getParameter("sign_in_time_end");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(sign_in_time_begin)){
				c.andSignInTimeGreaterThanOrEqualTo(sdf.parse(sign_in_time_begin+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(sign_in_time_end)){
				c.andSignInTimeLessThanOrEqualTo(sdf.parse(sign_in_time_end+" 23:59:59"));
			}
			String rewardType = request.getParameter("rewardType");
			if(!StringUtils.isEmpty(rewardType)){
				c.andRewardTypeEqualTo(rewardType);
			}
			String signInType = request.getParameter("signInType");
			if(!StringUtils.isEmpty(signInType)){
				c.andSignInTypeEqualTo(signInType);
			}
			totalCount = memberSignInSettingService.countMemberSignInSettingCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<MemberSignInSettingCustom> memberSignInSettingCustoms = memberSignInSettingService.selectMemberSignInSettingCustomByExample(e);
			resMap.put("Rows", memberSignInSettingCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 累签记录列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/memberCumulativeSignIn/list.shtml")
	public ModelAndView memberCumulativeSignInList(HttpServletRequest request) {
		String rtPage = "/marketing/memberCumulativeSignIn/memberCumulativeSignIn_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("memberId", request.getParameter("memberId"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 累签记录列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/memberCumulativeSignIn/data.shtml")
	@ResponseBody
	public Map<String, Object> memberCumulativeSignInData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String memberId = request.getParameter("memberId");
			MemberCumulativeSignInExample e = new MemberCumulativeSignInExample();
			MemberCumulativeSignInExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			c.andMemberIdEqualTo(Integer.parseInt(memberId));
			String receive_time_begin = request.getParameter("receive_time_begin");
			String receive_time_end = request.getParameter("receive_time_end");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(receive_time_begin)){
				c.andReceiveTimeGreaterThanOrEqualTo(sdf.parse(receive_time_begin+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(receive_time_end)){
				c.andReceiveTimeLessThanOrEqualTo(sdf.parse(receive_time_end+" 23:59:59"));
			}
			totalCount = memberCumulativeSignInService.countMemberCumulativeSignInCustomByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<MemberCumulativeSignInCustom> memberCumulativeSignInCustoms = memberCumulativeSignInService.selectMemberCumulativeSignInCustomByExample(e);
			resMap.put("Rows", memberCumulativeSignInCustoms);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 签到用户管理列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInMemberManage/list.shtml")
	public ModelAndView signInMemberManageList(HttpServletRequest request) {
		String rtPage = "/marketing/signInMemberManage/signInMemberManage_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 签到用户管理列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/signInMemberManage/data.shtml")
	@ResponseBody
	public Map<String, Object> signInMemberManageData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			String memberId = request.getParameter("memberId");
			if(!StringUtils.isEmpty(memberId)){
				paramMap.put("memberId", memberId);
			}
			String sign_in_time_begin = request.getParameter("sign_in_time_begin");
			String sign_in_time_end = request.getParameter("sign_in_time_end");
			if(!StringUtils.isEmpty(sign_in_time_begin)){
				paramMap.put("sign_in_time_begin", sign_in_time_begin);
			}
			if(!StringUtils.isEmpty(sign_in_time_end)){
				paramMap.put("sign_in_time_end", sign_in_time_end);
			}
			String signinCount=request.getParameter("signinCount");
			if (!StringUtils.isEmpty(signinCount)) {
				paramMap.put("signinCount", signinCount);
			}
			String endYear = request.getParameter("endYear");
			String endMonth = request.getParameter("endMonth");
			if (!StringUtils.isEmpty(endYear) && !StringUtils.isEmpty(endMonth)) {
				paramMap.put("endYear", endYear);
				paramMap.put("endMonth", endMonth);
			}
			
			String cumulativesigninCount = request.getParameter("cumulativesigninCount");
			if (!StringUtils.isEmpty(cumulativesigninCount)) {
				paramMap.put("cumulativesigninCount", cumulativesigninCount);
			}
			paramMap.put("limitStart", page.getLimitSize()*(page.getPage() - 1));
			paramMap.put("limitSize", page.getLimitSize());
			totalCount = memberSignInSettingService.countSignInMemberManageList(paramMap);
			List<HashMap<String,Object>> list = memberSignInSettingService.selectSignInMemberManageList(paramMap);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 线下提现转账列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/cashTransfer/list.shtml")
	public ModelAndView cashTransferList(HttpServletRequest request) {
		String rtPage = "/marketing/cashTransfer/cashTransfer_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
		// 限出纳（角色ID为：95）才能操作【转账】、【驳回】
		boolean auditPower = false;
		SysStaffRoleExample sysStaffRoleExample = new SysStaffRoleExample();
		sysStaffRoleExample.createCriteria().andStatusEqualTo("A").andRoleIdEqualTo(Const.ROLE_ID_95).andStaffIdEqualTo(staffID);
		List<SysStaffRole> sysStaffRolesList = sysStaffRoleService.selectByExample(sysStaffRoleExample);
		if(sysStaffRolesList != null && sysStaffRolesList.size() > 0) {
			auditPower = true;
		}
		resMap.put("auditPower", auditPower);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 线下提现转账列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/cashTransfer/data.shtml")
	@ResponseBody
	public Map<String, Object> cashTransferData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String memberId = request.getParameter("memberId");
			String status = request.getParameter("status");
			CashTransferCustomExample e = new  CashTransferCustomExample();
			e.setOrderByClause(" t.status asc,t.withdraw_order_id desc");
			CashTransferCustomExample.CashTransferCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(memberId)){
				c.andMemberIdEqualTo(Integer.parseInt(memberId));
			}
			if(!StringUtils.isEmpty(status)){
				c.andStatusEqualTo(status);
			}
			if(!StringUtils.isEmpty(request.getParameter("alipayAccount")) ){
				c.andAlipayAccountEqualTo(request.getParameter("alipayAccount"));
			}
			String createDateBegin = null;
			String createDateEnd = null;
			if(!StringUtils.isEmpty(request.getParameter("createDateBegin")) ){
				createDateBegin = request.getParameter("createDateBegin") + " 00:00:00";
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateEnd")) ){
				createDateEnd = request.getParameter("createDateEnd") + " 23:59:59";
			}
			c.andWithdrawOrderCreateDate(createDateBegin, createDateEnd);
			if(!StringUtils.isEmpty(request.getParameter("updateDateBegin")) ){
				c.andUpdateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("updateDateBegin") + " 00:00:00"));
			}
			if(!StringUtils.isEmpty(request.getParameter("updateDateEnd")) ){
				c.andUpdateDateLessThanOrEqualTo(sdf.parse(request.getParameter("updateDateEnd") + " 23:59:59"));
			}
			totalCount = cashTransferService.countByCustomExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<CashTransferCustom> list = cashTransferService.selectByCustomExample(e);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 
	 * @MethodName: updateStatusManager
	 * @Description: (线下提现驳回)
	 * @author Pengl
	 * @date 2019年3月25日 下午2:29:36
	 */
	@RequestMapping(value = "/marketing/cashTransfer/updateStatusManager.shtml")
	public ModelAndView updateStatusManager(HttpServletRequest request) {
		ModelAndView m = new ModelAndView("/marketing/cashTransfer/updateStatus");
		m.addObject("id", request.getParameter("id"));
		return m;
	}
	
	/**
	 * 
	 * @MethodName: updateStatus
	 * @Description: (线下提现驳回)
	 * @author Pengl
	 * @date 2019年3月25日 下午3:49:37
	 */
	@RequestMapping(value = "/marketing/cashTransfer/updateStatus.shtml")
	public ModelAndView updateStatus(HttpServletRequest request, Coupon coupon) {
		ModelAndView m = new ModelAndView("/success/success");
		String code = null;
		String msg = null;
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			String rejectReason = request.getParameter("rejectReason");
			String returnStr = cashTransferService.updateStatus(request.getParameter("id"), staffID, rejectReason);
			if(!StringUtil.isEmpty(returnStr)) {
				msg = returnStr;
			}else {
				msg = StateCode.JSON_AJAX_SUCCESS.getStateMessage();
			}
			code = StateCode.JSON_AJAX_SUCCESS.getStateCode();
		} catch (Exception e) {
			e.printStackTrace();
			code = StateCode.JSON_AJAX_ERROR.getStateCode();
			msg = StateCode.ERR_APP_CALLAPP.getStateMessage();
		}
		m.addObject(this.JSON_RESULT_CODE, code);
		m.addObject(this.JSON_RESULT_MESSAGE, msg);
		return m;
	}
	
	/**
	 * 
	 * @MethodName: dataExport
	 * @Description: (线下提现转账导出)
	 * @author Pengl
	 * @date 2019年3月25日 下午4:06:14
	 */
	@RequestMapping("/marketing/cashTransfer/dataExport.shtml")
	public void dataExport(HttpServletRequest request, HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String memberId = request.getParameter("memberId");
			String status = request.getParameter("status");
			CashTransferCustomExample e = new  CashTransferCustomExample();
			e.setOrderByClause(" t.status asc,t.withdraw_order_id desc");
			CashTransferCustomExample.CashTransferCustomCriteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(memberId)){
				c.andMemberIdEqualTo(Integer.parseInt(memberId));
			}
			if(!StringUtils.isEmpty(status)){
				c.andStatusEqualTo(status);
			}
			if(!StringUtils.isEmpty(request.getParameter("alipayAccount")) ){
				c.andAlipayAccountEqualTo(request.getParameter("alipayAccount"));
			}
			String createDateBegin = null;
			String createDateEnd = null;
			if(!StringUtils.isEmpty(request.getParameter("createDateBegin")) ){
				createDateBegin = request.getParameter("createDateBegin") + " 00:00:00";
			}
			if(!StringUtils.isEmpty(request.getParameter("createDateEnd")) ){
				createDateEnd = request.getParameter("createDateEnd") + " 23:59:59";
			}
			c.andWithdrawOrderCreateDate(createDateBegin, createDateEnd);
			if(!StringUtils.isEmpty(request.getParameter("updateDateBegin")) ){
				c.andUpdateDateGreaterThanOrEqualTo(sdf.parse(request.getParameter("updateDateBegin") + " 00:00:00"));
			}
			if(!StringUtils.isEmpty(request.getParameter("updateDateEnd")) ){
				c.andUpdateDateLessThanOrEqualTo(sdf.parse(request.getParameter("updateDateEnd") + " 23:59:59"));
			}
			List<CashTransferCustom> dataList = cashTransferService.selectByCustomExample(e);
			String[] titles = { "会员ID", "会员名称", "姓名", "支付宝账号", "提现类型", "提现金额", "申请时间", "转账时间", "状态"};
			ExcelBean excelBean = new ExcelBean("线下提现转账.xls", "线下提现转账", titles);
			List<String[]> datas = new ArrayList<String[]>();
			for(CashTransferCustom cashTransferCustom : dataList) {
				String[] data = {
						cashTransferCustom.getMemberId()==null?"":cashTransferCustom.getMemberId().toString(),
						cashTransferCustom.getMemberNick(),
						cashTransferCustom.getRealName(),
						cashTransferCustom.getAlipayAccount(),
						cashTransferCustom.getWithdrawTypeDesc(),
						cashTransferCustom.getAmount()==null?"":cashTransferCustom.getAmount().toString(),
						cashTransferCustom.getApplyCreateDate()==null?"":sdf.format(cashTransferCustom.getApplyCreateDate()),
						cashTransferCustom.getUpdateDate()==null?"":sdf.format(cashTransferCustom.getUpdateDate()),
						cashTransferCustom.getStatusDesc()
				};
				datas.add(data);
			}
			excelBean.setDataList(datas);
			ExcelUtils.export(excelBean,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新为转账状态
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/cashTransfer/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> cashTransferChangeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			Integer staffID = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if(!StringUtil.isEmpty(request.getParameter("ids")) ) {
				String returnStr = cashTransferService.update(request.getParameter("ids"), staffID);
				if(!StringUtil.isEmpty(returnStr)) {
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "此会员ID"+returnStr+"，提现金额与冻结金额不符！");
				}
			}else {
				resMap.put("returnCode", "4004");
				resMap.put("returnMsg", "ID为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 首页顶部Tab列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopTab/list.shtml")
	public ModelAndView indexTopTabList(HttpServletRequest request) {
		String rtPage = "/marketing/indexTopTab/indexTopTab_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 首页顶部Tab列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopTab/data.shtml")
	@ResponseBody
	public Map<String, Object> indexTopTabData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String status = request.getParameter("status");
			String createDateBegin = request.getParameter("createDateBegin");
			String createDateEnd = request.getParameter("createDateEnd");
			IndexTopTabExample e = new  IndexTopTabExample();
			e.setOrderByClause("IFNULL(t.seq_no, 999999999) asc,t.id desc");
			IndexTopTabExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(status)){
				c.andStatusEqualTo(status);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(createDateBegin)){
				c.andCreateDateGreaterThanOrEqualTo(sdf.parse(createDateBegin+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(createDateEnd)){
				c.andCreateDateLessThanOrEqualTo(sdf.parse(createDateEnd+" 23:59:59"));
			}
			totalCount = indexTopTabService.countByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<IndexTopTabCustom> list = indexTopTabService.selectByCustomExample(e);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 首页顶部Tab编辑页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopTab/toEdit.shtml")
	public ModelAndView indexTopTabToEdit(HttpServletRequest request) {
		String rtPage = "/marketing/indexTopTab/toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			IndexTopTab indexTopTab = indexTopTabService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("indexTopTab", indexTopTab);
		}
		ProductTypeExample pte = new ProductTypeExample();
		pte.createCriteria().andDelFlagEqualTo("0").andTLevelEqualTo(1).andStatusEqualTo("1");
		List<ProductType> productTypes = productTypeService.selectByExample(pte);
		resMap.put("productTypes", productTypes);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存首页顶部Tab
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopTab/save.shtml")
	@ResponseBody
	public Map<String, Object> saveIndexTopTab(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			String type = request.getParameter("type");
			String productTypeId = request.getParameter("productTypeId");
			String keyword = request.getParameter("keyword");
			IndexTopTab indexTopTab = new IndexTopTab();
			if(StringUtils.isEmpty(id)){
				indexTopTab.setCreateDate(new Date());
				indexTopTab.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				indexTopTab.setDelFlag("0");
				indexTopTab.setStatus("0");
			}else{
				indexTopTab = indexTopTabService.selectByPrimaryKey(Integer.parseInt(id));
				indexTopTab.setUpdateDate(new Date());
				indexTopTab.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			}
			indexTopTab.setType(type);
			indexTopTab.setProductTypeId(Integer.parseInt(productTypeId));
			indexTopTab.setKeyword(keyword);
			if(!StringUtils.isEmpty(productTypeId)){
				ProductType productType = productTypeService.selectByPrimaryKey(Integer.parseInt(productTypeId));
				if(!productType.getStatus().equals("1")){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "一级分类已被停用，请重新选择分类");
					return resMap;
				}
				SysParamCfgExample e = new SysParamCfgExample();
				e.createCriteria().andParamCodeEqualTo("APP_HOME_TOP_CATALOG").andParamValueEqualTo(productTypeId);
				List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(e);
				if(sysParamCfgs!=null && sysParamCfgs.size()>0){
					indexTopTab.setCatalog(sysParamCfgs.get(0).getParamOrder());
				}
			}
			if(StringUtils.isEmpty(id)){
				indexTopTabService.insertSelective(indexTopTab);
			}else{
				indexTopTabService.updateByPrimaryKey(indexTopTab);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 上架/下架
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopTab/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> indexTopTabChangeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			IndexTopTab indexTopTab = indexTopTabService.selectByPrimaryKey(Integer.parseInt(id));
			if(indexTopTab.getStatus().equals("0")){
				indexTopTab.setStatus("1");
			}else{
				indexTopTab.setStatus("0");
			}
			indexTopTab.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			indexTopTab.setUpdateDate(new Date());
			indexTopTabService.updateByPrimaryKeySelective(indexTopTab);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopTab/delete.shtml")
	@ResponseBody
	public Map<String, Object> indexTopTabDelete(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			IndexTopTab indexTopTab = indexTopTabService.selectByPrimaryKey(Integer.parseInt(id));
			indexTopTab.setDelFlag("1");
			indexTopTab.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			indexTopTab.setUpdateDate(new Date());
			indexTopTabService.updateByPrimaryKeySelective(indexTopTab);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 保存排序
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopTab/saveSeqNo.shtml")
	@ResponseBody
	public Map<String, Object> saveSeqNo(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			String seqNo = request.getParameter("seqNo");
			IndexTopTab indexTopTab = indexTopTabService.selectByPrimaryKey(Integer.parseInt(id));
			indexTopTab.setSeqNo(Integer.parseInt(seqNo));
			indexTopTab.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			indexTopTab.setUpdateDate(new Date());
			indexTopTabService.updateByPrimaryKeySelective(indexTopTab);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 首页顶部风格/其他页面顶部风格列表
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopStyle/list.shtml")
	public ModelAndView indexTopStyleList(HttpServletRequest request) {
		String rtPage = "/marketing/indexTopStyle/indexTopStyle_list";
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("type", request.getParameter("type"));
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 首页顶部风格/其他页面顶部风格列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopStyle/data.shtml")
	@ResponseBody
	public Map<String, Object> indexTopStyleData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		try {
			String status = request.getParameter("status");
			String createDateBegin = request.getParameter("createDateBegin");
			String createDateEnd = request.getParameter("createDateEnd");
			String type = request.getParameter("type");
			IndexTopStyleExample e = new  IndexTopStyleExample();
			e.setOrderByClause("id desc");
			IndexTopStyleExample.Criteria c = e.createCriteria();
			c.andDelFlagEqualTo("0");
			if(!StringUtils.isEmpty(status)){
				c.andStatusEqualTo(status);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(createDateBegin)){
				c.andCreateDateGreaterThanOrEqualTo(sdf.parse(createDateBegin+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(createDateEnd)){
				c.andCreateDateLessThanOrEqualTo(sdf.parse(createDateEnd+" 23:59:59"));
			}
			if(!StringUtils.isEmpty(type)){
				c.andTypeEqualTo(type);
			}
			totalCount = indexTopStyleMapper.countByExample(e);
			e.setLimitStart(page.getLimitStart());
			e.setLimitSize(page.getLimitSize());
			List<IndexTopStyle> list = indexTopStyleMapper.selectByExample(e);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 *首页顶部风格/其他页面顶部风格编辑页面
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopStyle/toEdit.shtml")
	public ModelAndView indexTopStyleToEdit(HttpServletRequest request) {
		String rtPage = "/marketing/indexTopStyle/toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			IndexTopStyle indexTopStyle = indexTopStyleMapper.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("indexTopStyle", indexTopStyle);
		}
		String type = request.getParameter("type");
		resMap.put("type", type);
		return new ModelAndView(rtPage,resMap);
	}
	
	/**
	 * 保存首页顶部风格/其他页面顶部风格
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopStyle/save.shtml")
	@ResponseBody
	public Map<String, Object> saveIndexTopStyle(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			String bgPic = request.getParameter("bgPic");
			String activityBeginDate = request.getParameter("activityBeginDate");
			String activityEndDate = request.getParameter("activityEndDate");
			String type = request.getParameter("type");
			String fontType = request.getParameter("fontType");
			IndexTopStyle indexTopStyle = new IndexTopStyle();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isEmpty(id)){
				indexTopStyle.setCreateDate(new Date());
				indexTopStyle.setCreateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
				indexTopStyle.setDelFlag("0");
				indexTopStyle.setStatus("0");
			}else{
				indexTopStyle = indexTopStyleMapper.selectByPrimaryKey(Integer.parseInt(id));
				indexTopStyle.setUpdateDate(new Date());
				indexTopStyle.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			}
			indexTopStyle.setBgPic(bgPic);
			indexTopStyle.setActivityBeginDate(sdf.parse(activityBeginDate));
			indexTopStyle.setActivityEndDate(sdf.parse(activityEndDate));
			indexTopStyle.setType(type);
			indexTopStyle.setFontType(fontType);
			if(StringUtils.isEmpty(id)){
				indexTopStyleMapper.insertSelective(indexTopStyle);
			}else{
				indexTopStyleMapper.updateByPrimaryKey(indexTopStyle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 上架/下架
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopStyle/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> indexTopStyleChangeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			IndexTopStyle indexTopStyle = indexTopStyleMapper.selectByPrimaryKey(Integer.parseInt(id));
			if(indexTopStyle.getStatus().equals("0")){
				indexTopStyle.setStatus("1");
			}else{
				indexTopStyle.setStatus("0");
			}
			indexTopStyle.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			indexTopStyle.setUpdateDate(new Date());
			indexTopStyleMapper.updateByPrimaryKeySelective(indexTopStyle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/indexTopStyle/delete.shtml")
	@ResponseBody
	public Map<String, Object> indexTopStyleDelete(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			IndexTopStyle indexTopStyle = indexTopStyleMapper.selectByPrimaryKey(Integer.parseInt(id));
			indexTopStyle.setDelFlag("1");
			indexTopStyle.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			indexTopStyle.setUpdateDate(new Date());
			indexTopStyleMapper.updateByPrimaryKeySelective(indexTopStyle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}
	
	
	@RequestMapping(value = "/marketing/checkExists.shtml")
	@ResponseBody
	public Map<String, Object> checkExists(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String linkValue = request.getParameter("linkValue");
			String linkType=request.getParameter("linkType");
			if (linkType.equals("1")) {//会场
				ActivityArea activityArea = activityAreaService.selectByPrimaryKey(Integer.parseInt(linkValue));
				if(activityArea == null){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "ID有误，请输入正确的ID");
				}
			}
			 if (linkType.equals("2")) {
				 Activity activity = activityService.selectByPrimaryKey(Integer.parseInt(linkValue));
					if(activity == null){
						resMap.put("returnCode", "4004");
						resMap.put("returnMsg", "ID有误，请输入正确的ID");
				 }
			}
			
			if (linkType.equals("3")) {//商品
				ProductExample pe = new ProductExample();
				ProductExample.Criteria pec = pe.createCriteria();
				pec.andDelFlagEqualTo("0");
				pec.andCodeEqualTo(linkValue);
				List<Product> products = productService.selectByExample(pe);
				if(products == null || products.size() == 0){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "ID有误，请输入正确的ID");
				}
			}
			if (linkType.equals("7")) {
				CustomPage customPage = customPageService.selectByPrimaryKey(Integer.parseInt(linkValue));
				if(customPage == null){
					resMap.put("returnCode", "4004");
					resMap.put("returnMsg", "ID有误，请输入正确的ID");
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			resMap.put("returnCode", "4004");
			resMap.put("returnMsg", "保存失败，请稍后重试");
			return resMap;
		}
		return resMap;
	}

	/**
	 * 人格中心主题背景
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/personalCenterBackground/list.shtml")
	public ModelAndView personalCenterBackgroundList(HttpServletRequest request) {
		String rtPage = "/marketing/personalCenterBackground/list";
		return new ModelAndView(rtPage);
	}

	/**
	 * 人格中心主题背景的列表数据
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/personalCenterBackground/data.shtml")
	@ResponseBody
	public Map<String, Object> personalCenterBackgroundData(HttpServletRequest request,Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Integer totalCount = 0;
		List<PersonalCenterThemeBackground> list = null;
		try {
			String status = request.getParameter("status");
			String createDateBegin = request.getParameter("createDateBegin");
			String createDateEnd = request.getParameter("createDateEnd");

			PersonalCenterThemeBackgroundExample example = new PersonalCenterThemeBackgroundExample();
			example.setOrderByClause("id desc");
			PersonalCenterThemeBackgroundExample.Criteria criteria = example.createCriteria();
			criteria.andDelFlagEqualTo("0");
			if(!StringUtils.isBlank(status)){
				criteria.andStatusEqualTo(status);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(createDateBegin)){
				criteria.andCreateDateGreaterThanOrEqualTo(sdf.parse(createDateBegin+" 00:00:00"));
			}
			if(!StringUtils.isEmpty(createDateEnd)){
				criteria.andCreateDateLessThanOrEqualTo(sdf.parse(createDateEnd+" 23:59:59"));
			}

			totalCount = personalCenterThemeBackgroundService.countByExample(example);
			list = personalCenterThemeBackgroundService.selectByExample(example);
			resMap.put("Rows", list);
			resMap.put("Total", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}


	/**
	 * 新增个人中心主题背景
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/personalCenterBackground/toEdit.shtml")
	public ModelAndView personalCenterBackgroundToEdit(Model model,HttpServletRequest request) {
		String rtPage = "/marketing/personalCenterBackground/toEdit";
		Map<String, Object> resMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		if(!StringUtils.isBlank(id)){
			PersonalCenterThemeBackground personalCenterThemeBackground = personalCenterThemeBackgroundService.selectByPrimaryKey(Integer.parseInt(id));
			resMap.put("personalCenterThemeBackground",personalCenterThemeBackground);
		}
		return new ModelAndView(rtPage,resMap);
	}

	/**
	 * 保存个人中心主题背景
	 * @param request
	 * @param response
	 * @param paramMap
	 * @return
	 */
	@RequestMapping(value = "/marketing/personalCenterBackground/save.shtml")
	@ResponseBody
	public Map<String, Object> personalCenterBackgroundSave(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "成功");
		try {
			String id = request.getParameter("id");
			String commonPic = request.getParameter("commonPic");
			String svipPic = request.getParameter("svipPic");
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Integer staffId= Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			PersonalCenterThemeBackground pctb = new PersonalCenterThemeBackground();
			if(!StringUtils.isBlank(id)){
				pctb= personalCenterThemeBackgroundService.selectByPrimaryKey(Integer.parseInt(id));
				pctb.setUpdateDate(new Date());
				pctb.setUpdateBy(staffId);
			}else {
				pctb.setStatusDate(new Date());
				pctb.setCreateDate(new Date());
				pctb.setCreateBy(staffId);
				pctb.setStatus("0");
			}
			pctb.setCommonThemeBackgroundPic(commonPic);
			pctb.setSvipThemeBackgroundPic(svipPic);
			pctb.setBeginDate(sdf.parse(beginDate));
			pctb.setEndDate(sdf.parse(endDate));
			if(!StringUtils.isBlank(id)){
				personalCenterThemeBackgroundService.updateByPrimaryKeySelective(pctb);
			}else {
				personalCenterThemeBackgroundService.insertSelective(pctb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}


	/**
	 * 上架/下架
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/marketing/personalCenterBackground/changeStatus.shtml")
	@ResponseBody
	public Map<String, Object> personalCenterBackgroundChangeStatus(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			int updateId = Integer.parseInt(this.getSessionStaffBean(request).getStaffID());
			if("1".equals(status)){
				PersonalCenterThemeBackgroundExample example = new PersonalCenterThemeBackgroundExample();
				example.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
				PersonalCenterThemeBackground personalCenter = new PersonalCenterThemeBackground();
				personalCenter.setStatus("0");
				personalCenter.setUpdateBy(updateId);
				personalCenter.setUpdateDate(new Date());
				personalCenterThemeBackgroundService.updateByExampleSelective(personalCenter,example);
			}
			PersonalCenterThemeBackground pctb = personalCenterThemeBackgroundService.selectByPrimaryKey(Integer.parseInt(id));
			pctb.setStatus(status);
			pctb.setUpdateBy(updateId);
			pctb.setUpdateDate(new Date());
			personalCenterThemeBackgroundService.updateByPrimaryKeySelective(pctb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/marketing/personalCenterBackground/delete.shtml")
	@ResponseBody
	public Map<String, Object> personalCenterBackgroundDelete(HttpServletRequest request) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("returnCode", "0000");
		resMap.put("returnMsg", "操作成功");
		try {
			String id = request.getParameter("id");
			PersonalCenterThemeBackground pctb = personalCenterThemeBackgroundService.selectByPrimaryKey(Integer.parseInt(id));
			pctb.setDelFlag("1");
			pctb.setUpdateBy(Integer.parseInt(this.getSessionStaffBean(request).getStaffID()));
			pctb.setUpdateDate(new Date());
			personalCenterThemeBackgroundService.updateByPrimaryKeySelective(pctb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

}
