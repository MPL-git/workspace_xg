package com.jf.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.dao.CouponCenterBottomNavigationMapper;
import com.jf.dao.CouponCenterTimeMapper;
import com.jf.dao.SourceProductTypeMapper;
import com.jf.entity.BrandTeamType;
import com.jf.entity.Coupon;
import com.jf.entity.CouponCenterBottomNavigation;
import com.jf.entity.CouponCenterBottomNavigationExample;
import com.jf.entity.CouponCenterTime;
import com.jf.entity.CouponCenterTimeExample;
import com.jf.entity.CouponCustom;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductType;
import com.jf.entity.SourceProductType;
import com.jf.entity.SourceProductTypeExample;
import com.jf.entity.SysParamCfg;

@Service
@Transactional
public class SourceProductTypeService extends BaseService<SourceProductType, SourceProductTypeExample> {
	@Autowired
	private SourceProductTypeMapper sourceProductTypeMapper;
	@Resource
	private CustomAdPageService customAdPageService;
	@Resource
	private AdInfoService adInfoService;
	@Resource
	private DecorateInfoService decorateInfoService;
	@Resource
	private ProductService productService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private BrandTeamTypeService brandTeamTypeService;
	@Resource
	private BrandTeamTypeAdInfoService brandTeamTypeAdInfoService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private SourceNicheService sourceNicheService;
	@Resource
	private CouponCenterTimeMapper couponCenterTimeMapper;
	@Resource
	private CouponCenterBottomNavigationMapper couponCenterBottomNavigationMapper;
	@Resource
	private CouponService couponService;
	@Resource
	private MemberCouponService memberCouponService;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	@Autowired
	public void setSourceProductTypeMapper(SourceProductTypeMapper sourceProductTypeMapper) {
		this.setDao(sourceProductTypeMapper);
		this.sourceProductTypeMapper = sourceProductTypeMapper;
	}
	public List<Map<String, Object>> getProductCategoryManage(JSONObject reqDataJson) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		String type = reqDataJson.getString("type"); //资源位类型
		List<SourceProductType> list = findCategoryModes(type,null);
		if(CollectionUtils.isNotEmpty(list)){
			for (SourceProductType sptModel : list) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("sourceProductTypeId", sptModel.getId());
				dataMap.put("sourceProductTypeName", sptModel.getSourceProductTypeName());
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	public List<SourceProductType> findCategoryModes(String type,Integer id) {
		SourceProductTypeExample sourceProductTypeExample = new SourceProductTypeExample();
		SourceProductTypeExample.Criteria criteria = sourceProductTypeExample.createCriteria();
		criteria.andStatusEqualTo("1").andDelFlagEqualTo("0").andTypeEqualTo(type);
		if(id != null){
			criteria.andIdEqualTo(id);
		}
		sourceProductTypeExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		return selectByExample(sourceProductTypeExample);
	}
	
	public Map<String, Object> getProductCategoryAdManage(JSONObject reqPRM, JSONObject reqDataJson) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> adList = new ArrayList<>();
		String type = reqDataJson.getString("type"); //资源位类型
		Integer sourceProductTypeId = null;
		if(!JsonUtils.isEmpty(reqDataJson, "sourceProductTypeId")){
			sourceProductTypeId = reqDataJson.getInt("sourceProductTypeId");
		}
		Integer memberId = null;
		if(reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))){
			memberId = reqDataJson.getInt("memberId");
		}
		Integer decorateInfoId = null;
		Integer sptId = null;
		String pageType = "";
		String catalog = "";
		List<SourceProductType> sourceProductTypes = findCategoryModes(type,sourceProductTypeId);
		if(CollectionUtils.isNotEmpty(sourceProductTypes)){ //只有1，2，3，6，11，12 才有值！
			SourceProductType sourceProductType = sourceProductTypes.get(0);
			sptId = sourceProductType.getId();
			if("1".equals(type)){
				pageType = Const.PAGE_TYPE_12;
				catalog = Const.AD_CATALOG_GOOD_EVERYDAY;
			}else if("2".equals(type)){
				pageType = Const.PAGE_TYPE_13;
				catalog = Const.AD_CATALOG_GOOD_SHOP;
			}else if("3".equals(type)){
				pageType = "";
				catalog = Const.AD_CATALOG_GOOD_RECOMMEND;
			}else if("6".equals(type)){
				pageType = Const.PAGE_TYPE_16;
				catalog = Const.AD_CATALOG_CODE_BREAKING_PREFERENCE;
			}else if("11".equals(type)) {
				pageType = Const.PAGE_TYPE_24;
				catalog = Const.AD_CATALOG_COUPON_CENTER;
			}else if ("12".equals(type)) {
				pageType = Const.PAGE_TYPE_25;
				catalog = Const.AD_CATALOG_HOT_RECOMMEND;
			}else{
				throw new ArgException("类型出错");
			}
			adList = adInfoService.getAdList(Const.AD_STATUS_UP,Const.AD_TYPE_1,catalog,Const.AD_POSITION_TOP,sptId);
		}else{
			//TODO 4.潮鞋上新，5.潮流男装，7.运动鞋服，8.潮流美妆，9.食品超市，10.大学生创业
			if("4".equals(type)){
				pageType = Const.PAGE_TYPE_14;
				catalog = Const.AD_CATALOG_NEW_TRENDS;
			}else if("5".equals(type)){
				pageType = Const.PAGE_TYPE_15;
				catalog = Const.AD_CATALOG_TRENDS_MEN_WEAR;
			}else if("7".equals(type)){
				pageType = Const.PAGE_TYPE_17;
				catalog = Const.AD_CATALOG_SPORT_SHOES;
			}else if("8".equals(type)){
				pageType = Const.PAGE_TYPE_18;
				catalog = Const.AD_CATALOG_TRENDS_BEAUTY_MAKEUP;
			}else if("9".equals(type)){
				pageType = Const.PAGE_TYPE_19;
				catalog = Const.AD_CATALOG_FOOD_SUPERMARKET;
			}else if("10".equals(type)){
				pageType = Const.PAGE_TYPE_22;
				catalog = Const.AD_CATALOG_COLLEGE_STUDENT;
			}else if("11".equals(type)){
				pageType = Const.PAGE_TYPE_24;
				catalog = Const.AD_CATALOG_COUPON_CENTER;
			}
			adList = adInfoService.getAdList(Const.AD_STATUS_UP,Const.AD_TYPE_1,catalog,Const.AD_POSITION_TOP,sptId);
		}
		Map<String,Object> adPageMap = customAdPageService.getCustomAdPage(pageType,sptId);
		if(!StringUtil.isBlank(adPageMap.get("decorateInfoId").toString())){
			decorateInfoId = Integer.parseInt(adPageMap.get("decorateInfoId").toString());
		}
		Map<String, Object> decorateInfoMap = decorateInfoService.getHomePageDecorateInfo(decorateInfoId, reqPRM, memberId);
		map.put("decorateInfoMap", decorateInfoMap);
		map.put("adList", adList);
		return map;
	}
	
	/**
	 * 每日好货商品列表
	 * @param pageSize 
	 */
	public Map<String, Object> getGoodEveryDayProductList(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize) {
		Integer sourceProductTypeId = reqDataJson.getInt("sourceProductTypeId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		String type = "1";
		if(reqDataJson.containsKey("type")){
			type = reqDataJson.getString("type");
		}
		Double spreadAmountRate = 0.00;
		List<SourceProductType> sourceProductTypes = findCategoryModes(type,sourceProductTypeId);
		if(CollectionUtils.isNotEmpty(sourceProductTypes)){
			SourceProductType sourceProductType = sourceProductTypes.get(0);
			Integer productType1Id = sourceProductType.getProductType1Id();
			String productType2Id = sourceProductType.getProductType2Id();
			List<Integer> productType2Ids = new ArrayList<>();
			if(!StringUtil.isBlank(productType2Id)){
				productType1Id = null;
				for (String type2Id : productType2Id.split(",")) {
					if(!StringUtil.isBlank(type2Id)){
						productType2Ids.add(Integer.parseInt(type2Id));
					}
				}
			}
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("productType1Id", productType1Id);
			paramsMap.put("productType2Ids", productType2Ids);
			paramsMap.put("type", type);
			paramsMap.put("currentPage", currentPage * pageSize);
			paramsMap.put("pageSize", pageSize);
			List<ProductCustom> psCustom = productService.getEveryDayProductList(paramsMap);
			if(CollectionUtils.isNotEmpty(psCustom)){
				if("3".equals(type)){
					List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_DISTRIBUTION_RATE");
					if(CollectionUtils.isNotEmpty(cfgs)){
						spreadAmountRate = Double.valueOf(cfgs.get(0).getParamValue());
					}
				}
				for (ProductCustom ps : psCustom) {
					Map<String, Object> dataMap = new HashMap<>();
					Integer productId = ps.getId();
					Map<String, Object> activityTypeMap = productService.getProductActivityType(ps, productId);
					BigDecimal salePrice = new BigDecimal(activityTypeMap.get("salePrice").toString());
					BigDecimal tagPrice = new BigDecimal(activityTypeMap.get("tagPrice").toString());
					BigDecimal popFeeRate = new BigDecimal("0");
					try {
						//预防没有技术服务费，导致抛出异常
						Map<String, Object> popFeeRateParamsMap = new HashMap<>();
						popFeeRateParamsMap.put("productId", productId);
						popFeeRateParamsMap.put("activityId", activityTypeMap.get("activityId"));
						popFeeRateParamsMap.put("mchtId", ps.getMchtId());
						popFeeRateParamsMap.put("brandId", ps.getBrandId());
						popFeeRateParamsMap.put("productType1Id", ps.getProductType1Id());
						popFeeRateParamsMap.put("productType2Id", ps.getProductType2Id());
						popFeeRateParamsMap.put("productType3Id", ps.getProductTypeId());
						popFeeRate = mchtProductBrandService.getPopFeeRate(popFeeRateParamsMap);
					} catch (Exception e) {
						popFeeRate = new BigDecimal("0");
					}
					String productPic = StringUtil.getPic(ps.getPic(), "");
					String productName = ps.getName();
					String recommendRemarks = ps.getRecommendRemarks() == null ? "" : ps.getRecommendRemarks();
					Integer supportQuantity = ps.getSupportQuantity() == null ? 0 : ps.getSupportQuantity();
					BigDecimal spreadAmount = salePrice.multiply(popFeeRate).multiply(new BigDecimal(spreadAmountRate+""));
					String spreadAmountStr = "预估赚"+spreadAmount.setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()+"元";
					dataMap.put("productId", productId);
					dataMap.put("salePrice", salePrice);
					dataMap.put("tagPrice", tagPrice);
					dataMap.put("productPic", productPic);
					dataMap.put("productName", productName);
					dataMap.put("recommendRemarks", recommendRemarks);
					dataMap.put("supportQuantity", supportQuantity);
					dataMap.put("sourceNicheId", ps.getSourceNicheId());
					dataMap.put("spreadAmountStr", spreadAmountStr);
					dataList.add(dataMap);
				}
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	public Map<String, Object> findHotRecommendProduct(JSONObject reqDataJson, Integer pageSize) {
		List<Map<String, Object>> dataList = new ArrayList<>(); //爆款推荐商品列表

		String type = "12"; // 12爆款推荐
		Integer sourceProductTypeId = reqDataJson.getInt("sourceProductTypeId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer productType1Id = null;
		List<Integer> productType2Ids = new ArrayList<>();
		List<SourceProductType> sourceProductTypes = findCategoryModes(type, sourceProductTypeId);
		if (CollectionUtils.isNotEmpty(sourceProductTypes)) {
			SourceProductType sourceProductType = sourceProductTypes.get(0);
			productType1Id = sourceProductType.getProductType1Id();
			if (!StringUtil.isBlank(sourceProductType.getProductType2Id())) {
				productType1Id = null;
				for (String type2Id : sourceProductType.getProductType2Id().split(",")) {
					if (!StringUtil.isBlank(type2Id)) {
						productType2Ids.add(Integer.parseInt(type2Id));
					}
				}
			}
		}
		Map<String, Object> paramsMap = new HashMap<>();
		int offset = currentPage * pageSize;
		paramsMap.put("type", type);
		paramsMap.put("productType1Id", productType1Id);
		paramsMap.put("productType2Ids", productType2Ids);
		paramsMap.put("offset", offset); //从0开始
		paramsMap.put("fetchSize", pageSize);
		List<ProductCustom> psCustom = productService.findHotRecommendProduct(paramsMap);
		if (CollectionUtils.isNotEmpty(psCustom)) {
			int index = 1;
			for (ProductCustom ps : psCustom) {
				Map<String, Object> dataMap = new HashMap<>();
				Map<String, Object> activityTypeMap = productService.getProductActivityType(ps, ps.getId());
				dataMap.put("productId", ps.getId());
				dataMap.put("salePrice", new BigDecimal(activityTypeMap.get("salePrice").toString()));
				dataMap.put("tagPrice", new BigDecimal(activityTypeMap.get("tagPrice").toString()));
				dataMap.put("productPic", StringUtil.getPic(ps.getPic(), ""));
				dataMap.put("productName", ps.getName());
				dataMap.put("lastThreeMonthOrderCount", "近期出售"+StringUtil.decorateSaleCount(ps.getOrderCount90Days()) + "件"); //近3个月销量
				dataMap.put("order", offset + index); //排名
				index++;
				dataList.add(dataMap);
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("title", "热卖榜单");
		result.put("subTitle", "热卖排行依据销量与销售额计算，每日上榜更新");
		result.put("dataList", dataList);
		return result;
	}

	public Map<String, Object> getGoodEveryDayShopList(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize) {
		Integer sourceProductTypeId = reqDataJson.getInt("sourceProductTypeId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<>();
		String type = "2";
		Integer showNum = 3;
		List<SourceProductType> sourceProductTypes = findCategoryModes(type,sourceProductTypeId);
		if(CollectionUtils.isNotEmpty(sourceProductTypes)){
			SourceProductType sourceProductType = sourceProductTypes.get(0);
			Integer productType1Id = sourceProductType.getProductType1Id();
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("productType1Id", productType1Id);
			paramsMap.put("showNum", showNum);
			paramsMap.put("currentPage", currentPage * pageSize);
			paramsMap.put("pageSize", pageSize);
			List<MchtInfoCustom> mchtInfoCustoms = mchtInfoService.getEveryDayShopList(paramsMap);
			if(CollectionUtils.isNotEmpty(mchtInfoCustoms)){
				for (MchtInfoCustom mchtInfoCustom : mchtInfoCustoms) {
					Map<String, Object> dataMap = new HashMap<>();
					List<Map<String, Object>> productDataList = new ArrayList<>();
					Map<String, Object> productParamsMap = new HashMap<>();
					productParamsMap.put("mchtId", mchtInfoCustom.getId());
					productParamsMap.put("showNum", showNum);
					List<ProductCustom> psCustoms = productService.getEveryDayShopProduct(productParamsMap);
					dataMap.put("mchtId", mchtInfoCustom.getId());
					dataMap.put("shopName", mchtInfoCustom.getShopName());
					dataMap.put("shopLogo", StringUtil.getPic(mchtInfoCustom.getShopLogo(), ""));
					for (ProductCustom productCustom : psCustoms) {
						Map<String, Object> productDataMap = new HashMap<>();
						productDataMap.put("productId", productCustom.getId());
						productDataMap.put("productPic", StringUtil.getPic(productCustom.getPic(), ""));
						productDataList.add(productDataMap);
					}
					dataMap.put("productDataList", productDataList);
					dataList.add(dataMap);
				}
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	public List<Map<String, Object>> getBrandGroupCategoryTop(String resType) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<BrandTeamType> teamTypes = brandTeamTypeService.findModels();
		if(CollectionUtils.isNotEmpty(teamTypes)){
			Map<String, Object> dataMap = new HashMap<>();
			String type = "1";//1推荐 2品牌团3预告品牌团
			if("1".equals(resType)){
				dataMap.put("id", "");
				dataMap.put("productTypeId", "");
				dataMap.put("name", "推荐");
				dataMap.put("type", type);
				dataList.add(dataMap);
			}
			List<String> specialHideCategoryList = sysParamCfgService.getSpecialHideCategoryList();
			boolean needHide = CollectionUtils.isNotEmpty(specialHideCategoryList);
			for (BrandTeamType brandTeamType : teamTypes) {
				if (needHide && specialHideCategoryList.contains(brandTeamType.getName())) {
					continue; //小程序审核时要求隐藏
				}
				dataMap = new HashMap<>();
				type = "2";
				if(brandTeamType.getProductTypeId() == 0){
					type = "3";
				}
				dataMap.put("id", brandTeamType.getId());
				dataMap.put("productTypeId", brandTeamType.getProductTypeId()==null?"":brandTeamType.getProductTypeId());
				dataMap.put("name", brandTeamType.getName());
				dataMap.put("type", type);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	public Map<String, Object> getBrandGroupCategoryAds(JSONObject reqPRM, JSONObject reqDataJson) {
		List<Map<String, Object>> adList = new ArrayList<>();
		Integer brandTeamTypeId = reqDataJson.getInt("brandTeamTypeId");
		Integer decorateInfoId = null;
		List<BrandTeamType> brandTeamTypes = brandTeamTypeService.findModelsById(brandTeamTypeId);
		if(CollectionUtils.isNotEmpty(brandTeamTypes)){
			decorateInfoId = brandTeamTypes.get(0).getDecorateInfoId();
			adList = brandTeamTypeAdInfoService.getBrandAdList(brandTeamTypeId);
		}
		Map<String, Object> decorateInfoMap = decorateInfoService.getHomePageDecorateInfo(decorateInfoId, reqPRM, null);
		Map<String, Object> map = new HashMap<>();
		map.put("adList", adList);
		map.put("decorateInfoMap", decorateInfoMap);
		return map;
	}
	
	public Map<String, Object> getCouponCenterDataList(JSONObject reqPRM,JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<>();
		String memberId = "";
		if(reqDataJson.has("memberId")){
			memberId = reqDataJson.getString("memberId");
		}
		//每日必抢
		List<Map<String, Object>> timeList = this.getTimeList(memberId);
		map.put("eachDayTitle", "每日必抢");
		map.put("timeList", timeList);
		//获取商品券
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(StringUtil.isEmpty(memberId)){
			memberId = "0";
		}
		paramMap.put("limitStart", 0);
		paramMap.put("limitSize", 8);
		paramMap.put("memberId", memberId);
		List<Map<String,Object>> productCouponList = sourceNicheService.getProductCouponList(paramMap);
		map.put("productCouponList", productCouponList);
		//获取底部导航栏
		List<Map<String, Object>> bottomNavigationList = this.getCouponCenterBottomNavigationList(memberId);
		map.put("bottomNavigationList", bottomNavigationList);
		
		return map;
	}
	
	private List<Map<String, Object>> getTimeList(String memberId) {
		List<String> numberList = new ArrayList<String>();
		numberList.add("00");
		numberList.add("01");
		numberList.add("02");
		numberList.add("03");
		numberList.add("04");
		numberList.add("05");
		numberList.add("06");
		numberList.add("07");
		numberList.add("08");
		numberList.add("09");
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CouponCenterTimeExample e = new CouponCenterTimeExample();
		e.setOrderByClause("start_hours asc,start_min asc");
		e.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<CouponCenterTime> couponCenterTimeList = couponCenterTimeMapper.selectByExample(e);
		List<Date> eachTimeList = new ArrayList<Date>();
		for(CouponCenterTime couponCenterTime:couponCenterTimeList){
			String eachTime = couponCenterTime.getStartHours()+":"+couponCenterTime.getStartMin();
			String eachTodayTime = df.format(now)+" "+eachTime+":00";
			try {
				String continueHours = couponCenterTime.getContinueHours();
				String continueMin = couponCenterTime.getContinueMin();
				if(numberList.contains(continueHours)){
					continueHours = continueHours.substring(1);
				}
				if(numberList.contains(continueMin)){
					continueMin = continueMin.substring(1);
				}
				int minutes = Integer.parseInt(continueHours)*60+Integer.parseInt(continueMin);
				Date endDate = DateUtil.addMinute(sdf.parse(eachTodayTime+":00"), minutes);
				if(endDate.after(now)){//每日必抢时长还没有过期的
					eachTimeList.add(f.parse(eachTodayTime));
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		List<Map<String,Object>> timeList = new ArrayList<Map<String,Object>>();
		if(eachTimeList!=null && eachTimeList.size()>0){
//			List<Coupon> couponList = couponService.getCouponListByRecBeginDate(eachTimeList);
			Map<String,Object> paramMap = new HashMap<String,Object>();
			if(StringUtil.isEmpty(memberId)){
				memberId = "0";
			}
			paramMap.put("memberId", Integer.parseInt(memberId));
			paramMap.put("eachTimeList", eachTimeList);
			List<CouponCustom> couponCustomList = sourceNicheService.getCouponListByRecBeginDate(paramMap);
			if(couponCustomList!=null && couponCustomList.size()>0){
				DecimalFormat decimalFormat = new DecimalFormat();
				decimalFormat.applyPattern("#.##");
				Integer i =0;
				String index="";
				for(CouponCenterTime couponCenterTime:couponCenterTimeList){
					Map<String,Object> couponCenterTimeMap = new HashMap<>();
					String eachTime = couponCenterTime.getStartHours()+":"+couponCenterTime.getStartMin();
					String eachTodayTime = df.format(now)+" "+eachTime+":00";
					String continueHours = couponCenterTime.getContinueHours();
					String continueMin = couponCenterTime.getContinueMin();
					if(numberList.contains(continueHours)){
						continueHours = continueHours.substring(1);
					}
					if(numberList.contains(continueMin)){
						continueMin = continueMin.substring(1);
					}
					int minutes = Integer.parseInt(continueHours)*60+Integer.parseInt(continueMin);
					Date beginDate = null;
					Date endDate = null;
					String timeDesc = "";
					try {
						beginDate = sdf.parse(eachTodayTime+":00");
						endDate = DateUtil.addMinute(sdf.parse(eachTodayTime+":00"), minutes);
						if(endDate.before(now)){//时长过期的不返回给前端
							continue;
						}
						if(beginDate.after(now)){
							timeDesc = "即将开始";
						}else if(endDate.after(now) && beginDate.before(now)){
							timeDesc = "正在疯抢";
						}else{
							timeDesc = "已开抢";
						}
						couponCenterTimeMap.put("beginDate", beginDate.getTime());
						couponCenterTimeMap.put("endDate", endDate.getTime());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					couponCenterTimeMap.put("id", couponCenterTime.getId());
					couponCenterTimeMap.put("eachTime", eachTime);
					couponCenterTimeMap.put("timeDesc", timeDesc);
					couponCenterTimeMap.put("currentTime", now.getTime());
					
					List<Map<String,Object>> subCouponList = new ArrayList<Map<String,Object>>();
					for(CouponCustom couponCustom:couponCustomList){
						if(eachTodayTime.equals(f.format(couponCustom.getRecBeginDate()))){
							Map<String,Object> couponMap = new HashMap<String,Object>();
//							Map<String, Object> msgMap = memberCouponService.getMemberIsReceiveCoupon(coupon, memberId);
//							String msgType = "0";
//							msgType = msgMap.get("msgType").toString();
//							if(!"1".equals(msgType) && !"2".equals(msgType)){
//								if(coupon.getRecBeginDate().compareTo(new Date()) > 0){
//									msgType = "3";
//									couponMap.put("recBeginDate", coupon.getRecBeginDate().getTime());
//								}else{
//									msgType = "0";
//								}
//							}
							// 优惠券发行数量
							Integer grantQuantity = couponCustom.getGrantQuantity() == null ? 0 : couponCustom.getGrantQuantity();
							// 优惠券已领数量
							Integer recQuantity = couponCustom.getRecQuantity() == null ? 0 : couponCustom.getRecQuantity();
							// 优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
							String recLimitType = couponCustom.getRecLimitType() == null ? "" : couponCustom.getRecLimitType();
							// 优惠券 限领数量
							Integer recEach = couponCustom.getRecEach() == null ? 0 : couponCustom.getRecEach();
							String msgType = "0";//0.可领取 1已领取 2.领光
							String msg = "";
							if (grantQuantity > 0 && grantQuantity > recQuantity) {
								if (couponCustom.getRecDate()!=null) {
									String recDate = DateUtil.getFormatDate(couponCustom.getRecDate(), "yyyyMMdd");
									String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
									// 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
									if (recLimitType.equals("1")) {
										if (recDate.equals(currentDate)) {
											msgType = "1";
											msg = "每人每天限领1张 ";
										}
									} else if (recLimitType.equals("2")) {
										if (couponCustom.getMemberCouponIds().split(",").length >= recEach) {
											msgType = "1";
											msg = "每人限领 " + recEach + "张";
										}
									} else if (recLimitType.equals("3")) {//3.不限
										
									} else {
										msgType = "2";
										msg = "优惠券已抢光";
									}
								}
							} else {
								msgType = "2";
								msg = "优惠券已抢光";
							}
							couponMap.put("couponType", couponCustom.getCouponType());
							couponMap.put("couponTypeDesc", couponCustom.getCouponType().equals("1")?"平台券":"品类券");
							couponMap.put("recType", couponCustom.getRecType());
							couponMap.put("msgType", msgType);
							couponMap.put("couponId", couponCustom.getId().toString());
							couponMap.put("preferentialType", couponCustom.getPreferentialType());
							if(couponCustom.getPreferentialType().equals("1")){//1.固定面额
								couponMap.put("preferentialInfo", decimalFormat.format(couponCustom.getMoney()));
								couponMap.put("couponInfoStr", "满"+decimalFormat.format(couponCustom.getMinimum())+"元可用");
							}else{//2.折扣
								couponMap.put("preferentialInfo", decimalFormat.format(couponCustom.getDiscount().multiply(new BigDecimal(10))));
								if("3".equals(couponCustom.getConditionType())){//3.满几件可用
									if(couponCustom.getMinicount()!=null){//正常数据都是Minicount不会空
										couponMap.put("couponInfoStr", "满"+decimalFormat.format(couponCustom.getMinicount())+"件可用");
									}else if(couponCustom.getMinimum()!=null){
										couponMap.put("couponInfoStr", "满"+decimalFormat.format(couponCustom.getMinimum())+"件可用");
									}
								}else if("2".equals(couponCustom.getConditionType())){//2.满多少可用
									if(couponCustom.getMinimum()!=null){//正常数据都是Minimum不会空
										couponMap.put("couponInfoStr", "满"+decimalFormat.format(couponCustom.getMinimum())+"元可用");
									}else if(couponCustom.getMinicount()!=null){
										couponMap.put("couponInfoStr", "满"+decimalFormat.format(couponCustom.getMinicount())+"元可用");
									}
								}else{//1.无条件
									couponMap.put("couponInfoStr", "");
								}
							}
							if(subCouponList.size()<3){
								subCouponList.add(couponMap);
							}
						}
					}
					if(subCouponList!=null && subCouponList.size()>0){
						couponCenterTimeMap.put("subCouponList", subCouponList);
						timeList.add(couponCenterTimeMap);
						if(endDate.after(now) && beginDate.before(now)){//正在疯抢（含已开抢，当前选中的时间点显示为“正在疯抢”，在当前时间之前的时间点显示为“已开抢”）
							i++;
							if(StringUtil.isEmpty(index)){
								index = i.toString();
							}else{
								index += ","+i.toString();
							}
						}
					}
				}
				if(!StringUtil.isEmpty(index) && index.split(",").length>1){//这段代码是为了设置文字描述“已开抢”（当前选中的时间点之前的所有时间点都显示为已开抢）
					String[] indexArray = index.split(",");
					for(int j=0;j<indexArray.length;j++){
						if(j!=indexArray.length-1){
							int idx = Integer.parseInt(indexArray[j]);
							--idx;
							timeList.get(idx).put("timeDesc", "已开抢");
						}
					}
				}
			}else{
				return timeList;
			}
		}else{
			return timeList;
		}
		return timeList;
	}
	
	private List<Map<String, Object>> getCouponCenterBottomNavigationList(String memberId) {
		CouponCenterBottomNavigationExample ccbne = new CouponCenterBottomNavigationExample();
		ccbne.setOrderByClause("IFNULL(seq_no,99999) asc");
		ccbne.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("1");
		List<CouponCenterBottomNavigation> couponCenterBottomNavigationList = couponCenterBottomNavigationMapper.selectByExample(ccbne);
		List<Map<String,Object>> bottomNavigationList = new ArrayList<Map<String,Object>>();
		for(CouponCenterBottomNavigation couponCenterBottomNavigation:couponCenterBottomNavigationList){
			Map<String, Object> bottomNavigationMap = new HashMap<>();
			bottomNavigationMap.put("name", couponCenterBottomNavigation.getName());
			bottomNavigationMap.put("pic", StringUtil.getPic(couponCenterBottomNavigation.getPic(), ""));
			bottomNavigationMap.put("seqNo", couponCenterBottomNavigation.getSeqNo());
			String linkType = couponCenterBottomNavigation.getLinkType();
			String linkValue = couponCenterBottomNavigation.getLinkValue();
			String linkUrl = "";
			String adCatalog = "";
			String adCatalogName = "";
			String msgType = "0";
			if(!StringUtil.isEmpty(linkType) && !StringUtil.isEmpty(linkValue)){
				if(linkType.equals("6") && linkValue != null){
					SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_HOME_CATALOG", linkValue);
					if(cfg != null){
						adCatalog = cfg.getParamOrder();
						adCatalogName = cfg.getParamName();
					}
				}else if(linkType.equals("9") ){
					linkUrl = linkValue;
					linkValue = "";
				}else if(linkType.equals("11") && linkValue != null){
					//优惠券，判断是否已抢光
					Coupon coupon = couponService.selectByPrimaryKey(Integer.parseInt(linkValue));
					bottomNavigationMap.put("recType", coupon.getRecType());
					if(StringUtil.isEmpty(memberId)){
						memberId = "0";
					}
					Map<String, Object> msgMap = memberCouponService.getMemberIsReceiveCoupon(coupon, Integer.parseInt(memberId));
					msgType = msgMap.get("msgType").toString();
					if(!"1".equals(msgType) && !"2".equals(msgType)){
						if(coupon.getRecBeginDate().compareTo(new Date()) > 0){
							msgType = "3";
							bottomNavigationMap.put("recBeginDate", coupon.getRecBeginDate().getTime());
						}else{
							msgType = "0";
						}
					}
				}else if(linkType.equals("14") || linkType.equals("16") || (Integer.parseInt(linkType)>=18 && Integer.parseInt(linkType)<=31)){
					if(!StringUtil.isEmpty(linkValue)){
						linkUrl = linkValue;
					}
				}
				bottomNavigationMap.put("msgType", msgType);//0可以领取 1已领取 2已抢光 3时间还未开始
				bottomNavigationMap.put("linkType", linkType);
				bottomNavigationMap.put("linkValue", linkValue);
				bottomNavigationMap.put("linkUrl", linkUrl);
				bottomNavigationMap.put("adCatalog", adCatalog);
				bottomNavigationMap.put("adCatalogName", adCatalogName);
				bottomNavigationMap.put("currentDate", new Date());
			}
			bottomNavigationList.add(bottomNavigationMap);
		}
		return bottomNavigationList;
	}
	
	public Map<String, Object> getProductCouponList(JSONObject reqPRM,JSONObject reqDataJson, Integer pageSize) {
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Integer currentPage = reqDataJson.getInt("currentPage");
		String productTypeId = "";
		if(reqDataJson.has("productTypeId")){
			productTypeId = reqDataJson.getString("productTypeId");
		}
		String memberId = "";
		if(reqDataJson.has("memberId")){
			memberId = reqDataJson.getString("memberId");
		}
		List<ProductType> productTypeList = sourceNicheService.getProductTypeList();
		if(StringUtil.isEmpty(productTypeId)){
			if(productTypeList!=null && productTypeList.size()>0){
				productTypeId = productTypeList.get(0).getId().toString();
			}
		}
		List<Map<String,String>> productTypeMapList = new ArrayList<Map<String,String>>();
		for(ProductType productType:productTypeList){
			Map<String,String> productTypeMap = new HashMap<String,String>();
			productTypeMap.put("productTypeId", productType.getId().toString());
			productTypeMap.put("productTypeName", productType.getName());
			productTypeMapList.add(productTypeMap);
		}
		dataMap.put("productTypeMapList", productTypeMapList);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("productTypeId", productTypeId);
		paramMap.put("limitStart", currentPage*pageSize);
		paramMap.put("limitSize", pageSize);
		List<Map<String,Object>> productCouponList = sourceNicheService.getProductCouponListByProductTypeId(paramMap);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> productCoupon:productCouponList){
			Map<String,Object> map = new HashMap<String,Object>();
			Integer grantQuantity = (Integer)productCoupon.get("grantQuantity") == null ? 0 : (Integer)productCoupon.get("grantQuantity");
			// 优惠券已领数量
			Integer recQuantity = (Integer)productCoupon.get("recQuantity") == null ? 0 : (Integer)productCoupon.get("recQuantity");
			// 优惠券 限领类型(1每人每天限领1张 2每人限领指定数量 3不限)
			String recLimitType = StringUtil.isEmpty((String)productCoupon.get("recLimitType"))? "" : (String)productCoupon.get("recLimitType");
			// 优惠券 限领数量
			Integer recEach = (Integer)productCoupon.get("recEach") == null ? 0 : (Integer)productCoupon.get("recEach");
			//优惠券，判断是否已抢光
			Coupon coupon = new Coupon();
			coupon.setId((Integer)productCoupon.get("couponId"));
			coupon.setGrantQuantity(grantQuantity);
			coupon.setRecQuantity(recQuantity);
			coupon.setRecLimitType(recLimitType);
			coupon.setRecEach(recEach);
			coupon.setRecBeginDate((Date)productCoupon.get("recBeginDate"));
			if(StringUtil.isEmpty(memberId)){
				memberId = "0";
			}
			Map<String, Object> msgMap = memberCouponService.getMemberIsReceiveCoupon(coupon, Integer.parseInt(memberId));
			String msgType = "0";
			msgType = msgMap.get("msgType").toString();
			if(!"1".equals(msgType) && !"2".equals(msgType)){
				if(coupon.getRecBeginDate().compareTo(new Date()) > 0){
					msgType = "3";
					map.put("recBeginDate", coupon.getRecBeginDate().getTime());
				}else{
					msgType = "0";
				}
			}
			map.put("recType", (String)productCoupon.get("recType"));
			map.put("msgType", msgType);
			map.put("noThreshold", "无门槛");
			map.put("postCouponPrice", "券后价");
			map.put("name", (String)productCoupon.get("name"));
			map.put("couponId", productCoupon.get("couponId"));
			map.put("productId", productCoupon.get("productId"));
			map.put("pic", StringUtil.getPic((String)productCoupon.get("pic"), "M"));
			DecimalFormat df = new DecimalFormat();
			df.applyPattern("#.##");
			map.put("money", df.format(productCoupon.get("money")));
			if(productCoupon.get("salePrice")!=null){
				BigDecimal showPrice = ((BigDecimal)productCoupon.get("salePrice")).subtract((BigDecimal)productCoupon.get("money"));
				map.put("showPrice", df.format(showPrice));
			}else{
				BigDecimal showPrice = ((BigDecimal)productCoupon.get("minMallPrice")).subtract((BigDecimal)productCoupon.get("money"));
				map.put("showPrice", df.format(showPrice));
			}
			map.put("couponType", (String)productCoupon.get("couponType"));
			map.put("couponTypeDesc", "商品券");
			resultList.add(map);
		}
		dataMap.put("productCouponList", resultList);
		return dataMap;
	}
	
	public Map<String, Object> getActivityAreaCouponList(JSONObject reqPRM,JSONObject reqDataJson, Integer pageSize) {
		Map<String,Object> dataMap = new HashMap<String,Object>();
		Integer sourceProductTypeId = null;
		Integer productTypeId = null;
		if(reqDataJson.has("sourceProductTypeId")){
			if(!StringUtil.isEmpty(reqDataJson.getString("sourceProductTypeId"))){
				sourceProductTypeId = Integer.parseInt(reqDataJson.getString("sourceProductTypeId"));
				SourceProductType sourceProductType = sourceProductTypeMapper.selectByPrimaryKey(sourceProductTypeId);
				productTypeId = sourceProductType.getProductType1Id();
			}
		}
		String memberId = "";
		if(reqDataJson.has("memberId")){
			memberId = reqDataJson.getString("memberId");
		}
		List<SourceProductType> sourceProductTypeList = sourceNicheService.getSourceProductTypeListByActivityAreaCoupon();
		List<Map<String,Object>> typeMapList = new ArrayList<Map<String,Object>>();
		for(SourceProductType sourceProductType:sourceProductTypeList){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sourceProductTypeId", sourceProductType.getId());
			map.put("sourceProductTypeName", sourceProductType.getSourceProductTypeName());
			typeMapList.add(map);
		}
		dataMap.put("sourceProductTypeList", typeMapList);
		
		if(sourceProductTypeId == null){
			if(sourceProductTypeList!=null && sourceProductTypeList.size()>0){
				sourceProductTypeId = sourceProductTypeList.get(0).getId();
				productTypeId = sourceProductTypeList.get(0).getProductType1Id();
			}
		}
		Integer currentPage = reqDataJson.getInt("currentPage");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("limitStart", currentPage*pageSize);
		paramMap.put("limitSize", pageSize);
		paramMap.put("productTypeId", productTypeId);
		if(StringUtil.isEmpty(memberId)){
			memberId = "0";
		}
		paramMap.put("memberId", Integer.parseInt(memberId));
		List<CouponCustom> activityAreaCouponList = sourceNicheService.getActivityAreaCouponList(paramMap);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#.##");
		for(CouponCustom couponCustom:activityAreaCouponList){
			Map<String,Object> couponCustomMap = new HashMap<String,Object>();
			couponCustomMap.put("activityId", couponCustom.getActivityId());
			couponCustomMap.put("mchtId", couponCustom.getMchtId());
			couponCustomMap.put("couponIds", couponCustom.getCouponIds());
			couponCustomMap.put("activityName", couponCustom.getActivityName());
			BigDecimal money = (BigDecimal)couponCustom.getMoney();
			couponCustomMap.put("money", df.format(money));
			BigDecimal minimum = (BigDecimal)couponCustom.getMinimum();
			couponCustomMap.put("preferentialInfo", "满"+df.format(minimum)+"元使用");
			couponCustomMap.put("productPic", StringUtil.getPic(couponCustom.getProductPic(), "M"));
			String msgType = "0";//0.可领取 1.已领 2.已抢光
			String[] quantityInfoArray = couponCustom.getQuantityInfo().split(",");
			boolean isHasCoupon = true;
			for(String item:quantityInfoArray){
				Integer grantQuantity = Integer.parseInt(item.split(";")[0]);
				Integer recQuantity = Integer.parseInt(item.split(";")[1]);
				if(grantQuantity < 0 || grantQuantity <= recQuantity){
					isHasCoupon = false;
					continue;
				}else{
					isHasCoupon = true;
					break;
				}
			}
			if(!isHasCoupon){
				msgType = "2";//2.已抢光
			}else{
				if(!StringUtil.isEmpty(memberId)){
//					String[] couponIdArray = couponCustom.getCouponIds().split(",");
//					List<Integer> couponIdList = new ArrayList<Integer>();
//					for(String couponId:couponIdArray){
//						couponIdList.add(Integer.parseInt(couponId));
//					}
//					MemberCouponExample memberCouponExample = new MemberCouponExample();
//					memberCouponExample.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId)).andCouponIdIn(couponIdList).andDelFlagEqualTo("0");
//					memberCouponExample.setOrderByClause("rec_date desc");
//					List<MemberCoupon> memberCoupons = memberCouponService.selectByExample(memberCouponExample);
//					if(memberCoupons!=null && memberCoupons.size()>0){
//						String recDate = DateUtil.getFormatDate(memberCoupons.get(0).getRecDate(), "yyyyMMdd");
//						String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
//						// 限领类型:1每人每天限领1张 
//						if (recDate.equals(currentDate)) {
//							msgType = "1";
//						}
//					}
					if(couponCustom.getRecDate()!=null){
						String recDate = DateUtil.getFormatDate(couponCustom.getRecDate(), "yyyyMMdd");
						String currentDate = DateUtil.getFormatDate(new Date(), "yyyyMMdd");
						// 限领类型:1每人每天限领1张 
						if (recDate.equals(currentDate)) {
							msgType = "1";
						}
					}
				}
			}
			couponCustomMap.put("msgType",msgType);
			resultList.add(couponCustomMap);
		}
		dataMap.put("activityAreaCouponList", resultList);
		dataMap.put("sourceProductTypeId", sourceProductTypeId==null?"":sourceProductTypeId);
		return dataMap;
	}
}
