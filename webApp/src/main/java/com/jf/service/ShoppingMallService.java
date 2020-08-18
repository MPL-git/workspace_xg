package com.jf.service;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.*;

import com.jf.vo.request.FindMchtShopProductRequest;
import com.jf.vo.request.FindMchtShopProductTypeRequest;
import com.jf.vo.response.ProductTypeView;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月19日 下午2:35:26 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ShoppingMallService{
	@Resource
	private ProductService productService;
	@Resource
	private ProductItemService productItemService;
	@Resource
	private ShopPreferentialInfoService shopPreferentialInfoService;
	@Resource
	private MallCategoryTopService mallCategoryTopService;
	@Resource
	private MallCategorySubService mallCategorySubService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private ActivityService activityService;
	@Resource
	private ShopProductCustomTypeService shopProductCustomTypeService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private XiaonengCustomerServiceService xiaonengCustomerServiceService;
	@Resource
	private MallCategoryService mallCategoryService;
	@Resource
	private MallCategoryModuleService mallCategoryModuleService;
	@Resource
	private MallCategoryItemService mallCategoryItemService;
	@Resource
	private ProductBrandService productBrandService;
	@Resource
	private CommentService commentService;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private KeywordHomoionymService keywordMomoionymService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private SysParamCfgService sysParamCfgService;
	
	public Map<String, Object> getShoppingMallData(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize) {
		Date date = new Date();
		String productTypeOne = "";
		String productTypeTwo = "";
		String productBrandIds = "";
		String productType2Ids = "";
		Integer decorateInfoId = null;
		Integer productType1 = null;
		String name = "商城";
		List<Integer> brandIdList = new ArrayList<Integer>();
		List<Integer> productTypeTwoList = new ArrayList<Integer>();
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		List<ProductCustom> productCustoms = new ArrayList<ProductCustom>();
		Integer currentPage = reqDataJson.getInt("currentPage");
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("currentPage", pageSize*currentPage);
		if(reqDataJson.containsKey("productTypeOne") && !StringUtil.isBlank(reqDataJson.getString("productTypeOne"))){
			//一级页面
			productTypeOne = reqDataJson.getString("productTypeOne");
			if(!StringUtil.isBlank(productTypeOne)){
				if(!productTypeOne.equals("1")){
					paramsMap.put("productTypeOne", Integer.valueOf(productTypeOne));
				}
				MallCategoryTopExample topExample = new MallCategoryTopExample();
				topExample.createCriteria().andProductTypeIdEqualTo(Integer.valueOf(productTypeOne))
				.andStatusEqualTo("1").andUpDateLessThanOrEqualTo(date).andDownDateGreaterThan(date).andDelFlagEqualTo("0");
				topExample.setLimitStart(0);
				topExample.setLimitSize(1);
				topExample.setOrderByClause("id desc");
				List<MallCategoryTop> tops = mallCategoryTopService.selectByExample(topExample);
				if(CollectionUtils.isNotEmpty(tops)){
					decorateInfoId = tops.get(0).getDecorateInfoId();
					if(tops.get(0).getProductTypeId() == 1){
						name = "商城";
					}else{
						ProductType productType = productTypeService.selectByPrimaryKey(tops.get(0).getProductTypeId());
						name = productType.getName();
					}
				}
				productCustoms = productService.getShoppingMallProductData(paramsMap);
			}
		}else if(reqDataJson.containsKey("productTypeTwo") && !StringUtil.isBlank(reqDataJson.getString("productTypeTwo"))){
			//二级页面
			productTypeTwo = reqDataJson.getString("productTypeTwo");
			if(!StringUtil.isBlank(productTypeTwo)){
				MallCategorySubExample subExample = new MallCategorySubExample();
				subExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andIdEqualTo(Integer.valueOf(productTypeTwo));
				subExample.setLimitStart(0);
				subExample.setLimitSize(1);
				subExample.setOrderByClause("id desc");
				List<MallCategorySub> subs = mallCategorySubService.selectByExample(subExample);
				if(CollectionUtils.isNotEmpty(subs)){
					name = subs.get(0).getName();
					decorateInfoId = subs.get(0).getDecorateInfoId();
					productBrandIds = subs.get(0).getProductBrandIds();
					productType1 = subs.get(0).getProductType1();
					productType2Ids = subs.get(0).getProductType2Ids();
					if(!StringUtil.isBlank(productBrandIds)){
						for (String brandId : productBrandIds.split(",")) {
							brandIdList.add(Integer.valueOf(brandId));
						}
					}
					if(!StringUtil.isBlank(productType2Ids)){
						for (String productType2Id : productType2Ids.split(",")) {
							productTypeTwoList.add(Integer.valueOf(productType2Id));
						}
					}
					paramsMap.put("productTypeOne", Integer.valueOf(productType1));
					paramsMap.put("brandIdList", brandIdList);
					paramsMap.put("productTypeTwoList", productTypeTwoList);
					productCustoms = productService.getShoppingMallProductData(paramsMap);
				}
			}
		}else{
			//商城首页页面
			MallCategoryTopExample topExample = new MallCategoryTopExample();
			topExample.createCriteria().andProductTypeIdEqualTo(1).andStatusEqualTo("1")
			.andUpDateLessThanOrEqualTo(date).andDownDateGreaterThan(date).andDelFlagEqualTo("0");
			topExample.setLimitStart(0);
			topExample.setLimitSize(1);
			topExample.setOrderByClause("id desc");
			List<MallCategoryTop> tops = mallCategoryTopService.selectByExample(topExample);
			if(CollectionUtils.isNotEmpty(tops)){
				decorateInfoId = tops.get(0).getDecorateInfoId();
			}
			productCustoms = productService.getShoppingMallProductData(paramsMap);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		List<Integer> mchtIds = new ArrayList<Integer>();
		List<Integer> productIds = new ArrayList<Integer>();
		if(CollectionUtils.isNotEmpty(productCustoms)){
			for (ProductCustom productCustom : productCustoms) {
				mchtIds.add(productCustom.getMchtId());
				productIds.add(productCustom.getId());
			}
			//活动中的
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productIdList", productIds);
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom activityCustom : activityCustoms) {
					activityMap.put(activityCustom.getProductId().toString(), activityCustom);
				}
			}
			//店铺活动
			Map<String,String> preferentialInfoMap = shopPreferentialInfoService.getShopPreferentialInfo(mchtIds);
			for (ProductCustom productCustom : productCustoms) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				String shopPreferentialInfo = "";
				//活动类型
				String activityTypeContent = "";
				String activityType = "";
				BigDecimal saleOrMallPrice = productCustom.getMallPrice();
				BigDecimal tagPrice = productCustom.getTagPrice();
				Integer stockSum = productCustom.getStockSum();
				Integer productId = productCustom.getId();
				Integer mchtId = productCustom.getMchtId();
				if(activityMap.get(productId.toString()) != null){
					//查找到这只商品是处于活动中的商品
					ActivityCustom activityCustom = activityMap.get(productId.toString());
					if(activityCustom.getProductId() != null){
						shopPreferentialInfo = activityCustom.getBenefitPoint();
						saleOrMallPrice = new BigDecimal(activityCustom.getSalePrice());
						tagPrice = new BigDecimal(activityCustom.getTagPrice());
						stockSum = activityCustom.getStockSum();
						activityTypeContent = "特卖活动";
						activityType = "0";
					}
				}else{
					shopPreferentialInfo = preferentialInfoMap.get(mchtId.toString()) == null ? "" : preferentialInfoMap.get(mchtId.toString());
					if(!StringUtil.isBlank(shopPreferentialInfo)){
						activityTypeContent = "店铺活动";
					}
					activityType = "101";
				}
				dataMap.put("activityType", activityType);
				dataMap.put("activityTypeContent", activityTypeContent);
				dataMap.put("productId", productId);
				dataMap.put("mchtId", mchtId);
				dataMap.put("productName", productCustom.getName());
				dataMap.put("brandName", productCustom.getBrandName());
				dataMap.put("productPic", StringUtil.getPic(productCustom.getPic(), "M"));
				dataMap.put("mallPrice", saleOrMallPrice);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("discount", tagPrice.doubleValue() == 0.00?1:saleOrMallPrice.divide(tagPrice,2, BigDecimal.ROUND_HALF_UP));
				dataMap.put("stockSum", stockSum);
				dataMap.put("shopPreferentialInfo", shopPreferentialInfo);
				dataList.add(dataMap);
			}
		}
		String redictUrl = "";
		if(decorateInfoId != null){
			redictUrl = Config.getProperty("mUrl")+"/xgbuy/views/activity/nest/decorate/brand_decorate.html?infoId="+decorateInfoId+"&memberId=";
		}
		map.put("dataList", dataList);
		map.put("decorateInfoId", decorateInfoId);
		map.put("redictUrl", redictUrl);
		map.put("name", name);
		return map;
	}

	public Map<String, Object> getMchtShoppingInfo(JSONObject reqPRM, JSONObject reqDataJson, Integer memberId) {
		String system = reqPRM.getString("system");
		Integer mchtId = reqDataJson.getInt("mchtId");
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> brandRecommendMapList = new ArrayList<Map<String,Object>>();
		MchtInfoExample mchtInfoExample = new MchtInfoExample();
		mchtInfoExample.createCriteria().andIdEqualTo(mchtId).andDelFlagEqualTo("0");
		List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
		if(CollectionUtils.isNotEmpty(mchtInfos)){
			MchtInfo mchtInfo = mchtInfos.get(0);
			String shopName = mchtInfo.getShopName();
			String shopLogo = mchtInfo.getShopLogo();
			String xiaoNengId = xiaonengCustomerServiceService.getCustomerService(mchtId, "5");
			if(mchtInfo.getShopStatus().equals("1") && mchtInfo.getStatus().equals("1")){
				boolean customerServerButton = true;
				boolean collectionButton = true;
				if(StringUtil.isBlank(shopLogo)){
					SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_MCHT_SHOP_DEFULT_LOGO","");
					if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
						shopLogo = StringUtil.getPic(paramCfg.getParamValue(), "");
					}
				}else{
					shopLogo = StringUtil.getPic(shopLogo, "");
				}
				//优惠信息
				map = shopPreferentialInfoService.getMchtShopPreferInfoByMchtId(mchtId,map,memberId,"1",system);
				//客服，店铺是否收藏
				boolean isCollectuonShop = false;
				if(memberId != null){
					isCollectuonShop = DataDicUtil.getRemindExists(mchtId, Integer.valueOf(memberId), "3");
				}
				map.put("isCollectuonShop", isCollectuonShop);
				map.put("xiaoNengId", xiaoNengId);
				//封装品牌推荐
				Map<String,Object> brandParams = new HashMap<String,Object>();
				brandParams.put("mchtId", mchtId);
				brandParams.put("limit", 6);
				String brandRecommend = "店铺特卖推荐";
				brandRecommendMapList = activityService.getBrandRecommendActivityByMchtId(brandParams);
				map.put("shopStatus", mchtInfo.getShopStatus());
				map.put("shopStatusMsg", "");
				map.put("brandRecommendMapList", brandRecommendMapList);
				map.put("brandRecommend", brandRecommend);
				map.put("customerServerButton", customerServerButton);
				map.put("collectionButton", collectionButton);
				map.put("shopLogo", StringUtil.getPic(shopLogo, ""));
				map.put("shareContent", "我在醒购APP发现一个不错的商品，赶快来看看吧。");
				map.put("shareShopUrl", Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=mall/seller/index.html?id="+mchtId);
				map.put("shopName", shopName);
			}else{
				map.put("shopName", shopName);
				map.put("shopStatus", "0");
				map.put("shopStatusMsg", "来晚了，该店铺已打烊，您可以逛逛其他店铺");
			}
		}else{
			map.put("shopName", "");
			map.put("shopStatus", "0");
			map.put("shopStatusMsg", "来晚了，该店铺已打烊，您可以逛逛其他店铺");
		}
		return map;
	}
	
	public Map<String, Object> getMchtShoppingProductList(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		Integer mchtId = reqDataJson.getInt("mchtId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		String shopProductTypeId = "";
		String mallPrice = "";
		String newProduct = "";
		if(reqDataJson.containsKey("shopProductTypeId")){
			shopProductTypeId = reqDataJson.getString("shopProductTypeId");
		}
		if(reqDataJson.containsKey("newProduct")){
			newProduct = reqDataJson.getString("newProduct");
		}
		if(reqDataJson.containsKey("mallPrice")){
			mallPrice = reqDataJson.getString("mallPrice");
		}
		List<Integer> mchtIds = new ArrayList<Integer>();
		List<Integer> productIds = new ArrayList<Integer>();
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("shopProductTypeId", shopProductTypeId);
		paramsMap.put("newProduct", newProduct);
		paramsMap.put("mallPrice", mallPrice);
		paramsMap.put("mchtId", mchtId);
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("pageSize", pageSize);
		List<ProductCustom> productCustoms = productService.getMchtShoppingProductList(paramsMap);
		if(CollectionUtils.isNotEmpty(productCustoms)){
			for (ProductCustom productCustom : productCustoms) {
				mchtIds.add(productCustom.getMchtId());
				productIds.add(productCustom.getId());
			}
			//活动中的
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productIdList", productIds);
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom activityCustom : activityCustoms) {
					activityMap.put(activityCustom.getProductId().toString(), activityCustom);
				}
			}
			//店铺活动
			Map<String,String> preferentialInfoMap = shopPreferentialInfoService.getShopPreferentialInfo(mchtIds);
			for (ProductCustom productCustom : productCustoms) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Integer productId = productCustom.getId();
				BigDecimal saleOrMallPrice = productCustom.getMallPrice();
				BigDecimal tagPrice = productCustom.getTagPrice();
				String shopPreferentialInfo = "";
				String activityTypeContent = "";
				String activityType = "";
				Integer stockSum = productCustom.getStockSum();
				if(activityMap.get(productId.toString()) != null){
					//查找到这只商品是处于活动中的商品
					ActivityCustom activityCustom = activityMap.get(productId.toString());
					if(activityCustom.getProductId() != null){
						shopPreferentialInfo = activityCustom.getBenefitPoint();
						saleOrMallPrice = new BigDecimal(activityCustom.getSalePrice());
						tagPrice = new BigDecimal(activityCustom.getTagPrice());
						activityTypeContent = "特卖活动";
						activityType = "0";
					}
				}else{
					shopPreferentialInfo = preferentialInfoMap.get(mchtId.toString()) == null ? "" : preferentialInfoMap.get(mchtId.toString());
					if(!StringUtil.isBlank(shopPreferentialInfo)){
						activityTypeContent = "店铺活动";
					}
					activityType = "101";
				}
				dataMap.put("activityTypeContent", activityTypeContent);
				dataMap.put("activityType", activityType);
				dataMap.put("shopPreferentialInfo", shopPreferentialInfo);
				dataMap.put("productId", productId);
				dataMap.put("mallPrice", saleOrMallPrice);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("stockSum", stockSum);
				dataMap.put("productName", productCustom.getName());
				dataMap.put("brandName", productCustom.getBrandName());
				dataMap.put("productPic", StringUtil.getPic(productCustom.getPic(), "M"));
				dataMap.put("discount", tagPrice.doubleValue()==0?1:saleOrMallPrice.divide(tagPrice,2, BigDecimal.ROUND_HALF_UP));
				dataList.add(dataMap);
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	public List<Map<String, Object>> getMchtShoppingProductClass(JSONObject reqPRM, JSONObject reqDataJson) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Integer mchtId = reqDataJson.getInt("mchtId");
		ShopProductCustomTypeExample shopProductCustomTypeExample = new ShopProductCustomTypeExample();
		shopProductCustomTypeExample.createCriteria().andMchtIdEqualTo(mchtId).andStatusEqualTo("1").andDelFlagEqualTo("0");
		shopProductCustomTypeExample.setOrderByClause("ifnull(seq_no,99999)");
		List<ShopProductCustomType> customTypes = shopProductCustomTypeService.selectByExample(shopProductCustomTypeExample);
		if (CollectionUtils.isNotEmpty(customTypes)) {
			for (ShopProductCustomType shopProductCustomType : customTypes) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				String name = shopProductCustomType.getName();
				Integer id = shopProductCustomType.getId();
				dataMap.put("id", id);
				dataMap.put("name", name);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	public List<ProductTypeView> findMchtShopProductType(FindMchtShopProductTypeRequest request) {
		List<ProductType> list= productService.findMchtShopProductType(request.getMchtId(), request.getProductType2());
		if (CollectionUtils.isEmpty(list)) return Collections.emptyList();

		return FluentIterable.from(list).transform(new Function<ProductType, ProductTypeView>() {
			@Override
			public ProductTypeView apply(ProductType input) {
				ProductTypeView view = new ProductTypeView();
				BeanUtils.copyProperties(input, view);
				return view;
			}
		}).toList();
	}

	public Map<String, Object> findMchtShopProduct(FindMchtShopProductRequest request) {
		List<Map<String, Object>> dataList = Lists.newArrayList();
		Map<String, Object> map = Maps.newHashMap();

		List<Integer> mchtIds = Lists.newArrayList();
		List<Integer> productIds = Lists.newArrayList();

		Map<String, Object> params = Maps.newHashMap();
		params.put("mchtId", request.getMchtId());
		params.put("productType3", request.getProductType3());
		params.put("orderType", request.getOrderType());
		params.put("offset", request.getOffset());
		params.put("fetchSize", request.getPageSize());
		List<ProductCustom> productCustoms =productService.findMchtShopProduct(params);
		if(CollectionUtils.isNotEmpty(productCustoms)){
			for (ProductCustom productCustom : productCustoms) {
				mchtIds.add(productCustom.getMchtId());
				productIds.add(productCustom.getId());
			}
			//活动中的
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productIdList", productIds);
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom activityCustom : activityCustoms) {
					activityMap.put(activityCustom.getProductId().toString(), activityCustom);
				}
			}
			//店铺活动
			Map<String,String> preferentialInfoMap = shopPreferentialInfoService.getShopPreferentialInfo(mchtIds);
			for (ProductCustom productCustom : productCustoms) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Integer productId = productCustom.getId();
				BigDecimal saleOrMallPrice = productCustom.getMallPrice();
				BigDecimal tagPrice = productCustom.getTagPrice();
				String shopPreferentialInfo = "";
				String activityTypeContent = "";
				String activityType = "";
				Integer stockSum = productCustom.getStockSum();
				if(activityMap.get(productId.toString()) != null){
					//查找到这只商品是处于活动中的商品
					ActivityCustom activityCustom = activityMap.get(productId.toString());
					if(activityCustom.getProductId() != null){
						shopPreferentialInfo = activityCustom.getBenefitPoint();
						saleOrMallPrice = new BigDecimal(activityCustom.getSalePrice());
						tagPrice = new BigDecimal(activityCustom.getTagPrice());
						activityTypeContent = "特卖活动";
						activityType = "0";
					}
				}else{
					shopPreferentialInfo = preferentialInfoMap.get(request.getMchtId().toString()) == null ? "" : preferentialInfoMap.get(request.getMchtId().toString());
					if(!StringUtil.isBlank(shopPreferentialInfo)){
						activityTypeContent = "店铺活动";
					}
					activityType = "101";
				}
				dataMap.put("activityTypeContent", activityTypeContent);
				dataMap.put("activityType", activityType);
				dataMap.put("shopPreferentialInfo", shopPreferentialInfo);
				dataMap.put("productId", productId);
				dataMap.put("mallPrice", saleOrMallPrice);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("stockSum", stockSum);
				dataMap.put("productName", productCustom.getName());
				dataMap.put("brandName", productCustom.getBrandName());
				dataMap.put("productPic", StringUtil.getPic(productCustom.getPic(), "M"));
				dataMap.put("discount", tagPrice.doubleValue()==0?1:saleOrMallPrice.divide(tagPrice,2, BigDecimal.ROUND_HALF_UP));
				dataList.add(dataMap);
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	public Map<String, Object> getShoppingMallCategory() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		MallCategoryExample mallCategoryExample = new MallCategoryExample();
		mallCategoryExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		mallCategoryExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		List<MallCategory> categories = mallCategoryService.selectByExample(mallCategoryExample);
		List<String> specialHideCategoryList = sysParamCfgService.getSpecialHideCategoryList();
		boolean needHide = CollectionUtils.isNotEmpty(specialHideCategoryList);
		if(CollectionUtils.isNotEmpty(categories)){
			for (MallCategory mallCategory : categories) {
				if (needHide && specialHideCategoryList.contains(mallCategory.getCategoryName())) {
					continue; //小程序审核时要求隐藏
				}
				Map<String,Object> dataMap = new HashMap<String,Object>();
				dataMap.put("categoryId", mallCategory.getId());
				dataMap.put("categoryName", mallCategory.getCategoryName());
				dataList.add(dataMap);
			}
		}
		//推荐热词
		SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_MALL_RECOMMEND_HOT_WORD","");
		String searchName = "运动";
		if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
			searchName = paramCfg.getParamValue();
		}
		map.put("dataList", dataList);
		map.put("searchName", searchName);
		return map;
	}
	
	public Map<String, Object> getShoppingMallCategoryData(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer categoryId = null;
		String categoryName = "";
		MallCategory mallCategory = null;
		if(reqDataJson.containsKey("categoryId") && !StringUtil.isBlank(reqDataJson.getString("categoryId"))){
			categoryId = reqDataJson.getInt("categoryId");
			mallCategory = mallCategoryService.selectByPrimaryKey(categoryId);
		}else{
			//前端没有传过来就自己找，取最前面的一条数据
			MallCategoryExample mallCategoryExample = new MallCategoryExample();
			mallCategoryExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
			mallCategoryExample.setOrderByClause("ifnull(seq_no,99999),id desc");
			List<MallCategory> categories = mallCategoryService.selectByExample(mallCategoryExample);
			if(CollectionUtils.isNotEmpty(categories)){
				mallCategory = categories.get(0);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> adList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>();
		if(mallCategory != null){
			//获取顶部广告图
			Map<String,Object> adMap = new HashMap<String,Object>();
			categoryId = mallCategory.getId();
			categoryName = mallCategory.getCategoryName();
			String adPic = mallCategory.getAdPic();
			String adLinkType= mallCategory.getAdLinkType();
			String adLinkValue = mallCategory.getAdLinkValue();
			if(!StringUtil.isBlank(adPic)){
				adMap.put("pic", StringUtil.getPic(adPic, ""));
				adMap.put("linkType", adLinkType);
				adMap.put("linkValue", adLinkValue);
				adList.add(adMap);
			}
			
			//余下部分模块
			MallCategoryModuleExample moduleExample = new MallCategoryModuleExample();
			moduleExample.createCriteria().andMallCategoryIdEqualTo(categoryId).andDelFlagEqualTo("0").andStatusEqualTo("1");
			moduleExample.setOrderByClause("ifnull(seq_no,99999),id desc");
			List<MallCategoryModule> modules = mallCategoryModuleService.selectByExample(moduleExample);
			if(CollectionUtils.isNotEmpty(modules)){
				for (MallCategoryModule module : modules) {
					//类目模块
					List<Map<String, Object>> categoryItemList = new ArrayList<Map<String, Object>>();
					Map<String,Object> moduleMap = new HashMap<String,Object>();
					Integer categoryModuleId = module.getId();
					moduleMap.put("categoryModuleId", categoryModuleId);
					moduleMap.put("categoryModuleName", module.getCategoryModuleName());
					moduleMap.put("categoryModuleType", module.getModuleType());
					MallCategoryItemExample itemExample = new MallCategoryItemExample();
					itemExample.createCriteria().andMallCategoryModuleIdEqualTo(categoryModuleId).andDelFlagEqualTo("0").andStatusEqualTo("1");
					itemExample.setOrderByClause("ifnull(seq_no,99999),id desc");
					List<MallCategoryItem> items = mallCategoryItemService.selectByExample(itemExample);
					if(CollectionUtils.isNotEmpty(items)){
						for (MallCategoryItem item : items) {
							//类目模块-----条目
							Map<String,Object> itemMap = new HashMap<String,Object>();
							String categoryItemName = item.getItemName() == null ? "" : item.getItemName();
							Integer categoryItemId = item.getId();
							String categoryItemLinkValue = item.getItemLinkValue();
							String categoryItemLinkType = item.getItemLinkType();
							if("1".equals(categoryItemLinkType)){
								categoryItemName = "";
							}
							itemMap.put("categoryItemId", categoryItemId);
							itemMap.put("categoryItemName", categoryItemName);
							itemMap.put("categoryItemPic", StringUtil.getPic(item.getItemPic(), ""));
							itemMap.put("categoryItemLinkType", categoryItemLinkType);
							itemMap.put("categoryItemLinkValue", categoryItemLinkValue);
							categoryItemList.add(itemMap);
						}
					}else{
						continue;
					}
					moduleMap.put("categoryItemList", categoryItemList);
					categoryList.add(moduleMap);
				}
			}
		}
		map.put("categoryId", categoryId);
		map.put("categoryName", categoryName);
		map.put("adList", adList);
		map.put("categoryList", categoryList);
		return map;
	}

	public Map<String, Object> getShoppingMallProductListData(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Integer currentPage = reqDataJson.getInt("currentPage");
		if(currentPage > 99){
			//只取前1000个商品
			map.put("dataList", dataList);
			return map;
		}
		//信用(评价)排序
		String commentSorf = "";
		//销量排序
		String saleWeightSorf = "";
		//商城价排序
		String salePriceSorf = "";
		//品牌
		List<Integer> brandIdList = new ArrayList<Integer>();
		//季节
		List<String> seasonList = new ArrayList<String>();
		//类目id
		Integer productTypeId= null;
		List<Integer> productTypeOneIdList= new ArrayList<Integer>();
		List<Integer> productTypeTwoIdList= new ArrayList<Integer>();
		List<Integer> productTypeThreeIdList= new ArrayList<Integer>();
		//适合性别  00 都不选 11 都选  10 选男不选女   01 选女不选男
		String suitSex = "";
		List<String> suitSexList = new ArrayList<String>();
		//适合人群 100青年人   010儿童幼儿   001中老年人
		String suitGroup = "";
		//最低价格
		BigDecimal priceMin = null;;
		//最高价格
		BigDecimal priceMax = null;
		//根据搜索词拆成几个关键词
		List<String> wordList = new ArrayList<String>();
		if(reqDataJson.containsKey("commentSorf") && !StringUtil.isBlank(commentSorf)){
			commentSorf = reqDataJson.getString("commentSorf");
		}
		if(reqDataJson.containsKey("saleWeightSorf")){
			saleWeightSorf = reqDataJson.getString("saleWeightSorf");
			if(!StringUtil.isBlank(saleWeightSorf)){
				//排序两者取其一
				if("desc".equalsIgnoreCase(saleWeightSorf) || "asc".equalsIgnoreCase(saleWeightSorf)){
					salePriceSorf = "";
				}else{
					saleWeightSorf = "";
				}
			}
		}
		if(reqDataJson.containsKey("salePriceSorf")){
			salePriceSorf = reqDataJson.getString("salePriceSorf");
			if(!StringUtil.isBlank(salePriceSorf)){
				//排序两者取其一
				if("desc".equalsIgnoreCase(salePriceSorf) || "asc".equalsIgnoreCase(salePriceSorf)){
					saleWeightSorf = "";
				}else{
					salePriceSorf = "";
				}
			}
		}
		if(reqDataJson.containsKey("suitSex")){
			suitSex = reqDataJson.getString("suitSex");
			if(!StringUtil.isBlank(suitSex)){
				if(suitSex.equals("10")){//男
					suitSex = "1%";
					suitSexList.add(suitSex);
				}else if(suitSex.equals("01")){//女
					suitSex = "%1";
					suitSexList.add(suitSex);
				}
			}
		}
		if(reqDataJson.containsKey("suitGroup")){
			suitGroup = reqDataJson.getString("suitGroup");
		}
		if(reqDataJson.containsKey("priceMin") && !StringUtil.isBlank(reqDataJson.getString("priceMin"))){
			priceMin = new BigDecimal(reqDataJson.getString("priceMin"));
		}
		if(reqDataJson.containsKey("priceMax") && !StringUtil.isBlank(reqDataJson.getString("priceMax"))){
			priceMax = new BigDecimal(reqDataJson.getString("priceMax"));
		}
		if(reqDataJson.containsKey("season") && !StringUtil.isBlank(reqDataJson.getString("season"))){
			for (String id : reqDataJson.getString("season").split(",")) {
				seasonList.add(id);
			}
		}
		if(reqDataJson.containsKey("brandId") && !StringUtil.isBlank(reqDataJson.getString("brandId"))){
			for (String id : reqDataJson.getString("brandId").split(",")) {
				brandIdList.add(Integer.valueOf(id));
			}
		}
		//类目id 查询该类目是属于几级类目id
		if(reqDataJson.containsKey("productTypeId") && !StringUtil.isBlank(reqDataJson.getString("productTypeId"))){
			productTypeId = reqDataJson.getInt("productTypeId");
			ProductType productType = productTypeService.selectByPrimaryKey(productTypeId);
			if(productType != null && productType.getId() != null){
				String tLevel = productType.gettLevel().toString();
				if("1".equals(tLevel)){
					productTypeOneIdList.add(productTypeId);
				}else if("2".equals(tLevel)){
					productTypeTwoIdList.add(productTypeId);
				}else if("3".equals(tLevel)){
					productTypeThreeIdList.add(productTypeId);
				}else{
					productTypeId = null;
				}
			}else{
				productTypeId = null;
			}
		}
		if(reqDataJson.containsKey("searchName") && !StringUtil.isBlank(reqDataJson.getString("searchName"))){
			String searchName = reqDataJson.optString("searchName");
		 	if(!StringUtil.isEmpty(searchName)){
		 		searchName = searchName.trim();
		 		SysParamCfg sysParamCfg = sysParamCfgService.findByCode("FORBIDDEN_SEARCH_KEYWORD");
				if(sysParamCfg!=null){
					if(!StringUtil.isEmpty(sysParamCfg.getParamValue())){
						String[] keywordArray = sysParamCfg.getParamValue().split(",");
						for(String keyword:keywordArray){
							if(keyword.equals(searchName)){
								map.put("dataList", dataList);
								map.put("forbidden", "1");
								return map;
							}
						}
					}
				}
		 	}
			searchName = reqDataJson.getString("searchName").replace(";", "").trim();
			String homoionym = keywordMomoionymService.getKeywordHomoionym(searchName);
			if(StringUtil.isBlank(homoionym)){
				wordList = StringUtil.seg(searchName);//数据库的商品搜索域用;号作为各个字段的分隔符，所以过滤掉;
			}else{
				wordList.add(homoionym);
			}
			if(CollectionUtils.isNotEmpty(wordList)){
				if(wordList.size() > 3){
					wordList.subList(0, 3);//只取前面3个
				}
//				//查找出是否属于品牌
//				brandIdList = productBrandService.getBrandIdListByWords(brandIdList,wordList);
//				//查找出是否属于品类
//				ProductTypeExample typeExample = new ProductTypeExample();
//				typeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andNameIn(wordList);
//				List<ProductType> productTypes = productTypeService.selectByExample(typeExample);
//				if(CollectionUtils.isNotEmpty(productTypes)){
//					for (ProductType pt : productTypes) {
//						if("1".equals(pt.gettLevel().toString())){
//							productTypeOneIdList.add(pt.getId());
//						}else if("2".equals(pt.gettLevel().toString())){
//							productTypeTwoIdList.add(pt.getId());
//						}else if("3".equals(pt.gettLevel().toString())){
//							productTypeThreeIdList.add(pt.getId());
//						}
//					}
//				}
//				//查找出是否属于性别
//				if((wordList.contains("男") && wordList.contains("女")) && wordList.contains("男女")){
//					suitSex = "11";
//					suitSexList.add(suitSex);
//				}else if(wordList.contains("男")){
//					suitSex = "1%";
//					suitSexList.add(suitSex);
//				}else if(wordList.contains("女")){
//					suitSex = "%1";
//					suitSexList.add(suitSex);
//				}
			}
		}
		if(CollectionUtils.isEmpty(productTypeThreeIdList)){
			//三级类目是空的：
			//根据一级类目和二级类目找出所对应的三级类目
			if(CollectionUtils.isNotEmpty(productTypeOneIdList) || CollectionUtils.isNotEmpty(productTypeTwoIdList)){
				Map<String, Object> typeMap = new HashMap<String, Object>();
				typeMap.put("productType1IdList", productTypeOneIdList);
				typeMap.put("productType2IdList", productTypeTwoIdList);
				List<ProductTypeCustom> typeCustoms = productTypeService.getThreeIdByIds(typeMap);
				if(CollectionUtils.isNotEmpty(typeCustoms)){
					for (ProductTypeCustom productTypeCustom : typeCustoms) {
						productTypeThreeIdList.add(productTypeCustom.getId());
					}
				}
			}
		}
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("wordList", wordList);
		paramsMap.put("commentSorf", commentSorf);
		paramsMap.put("saleWeightSorf", saleWeightSorf);
		paramsMap.put("salePriceSorf", salePriceSorf);
		paramsMap.put("productTypeThreeIdList", productTypeThreeIdList);
		paramsMap.put("suitSexList", suitSexList);
		paramsMap.put("suitGroup", suitGroup);
		paramsMap.put("priceMin", priceMin);
		paramsMap.put("priceMax", priceMax);
		paramsMap.put("brandIdList", brandIdList);
		paramsMap.put("seasonList", seasonList);
		paramsMap.put("pageSize", pageSize);
		paramsMap.put("currentPage", currentPage*pageSize);
		paramsMap.put("hiddenTypeList", sysParamCfgService.getSpecialHideTypeList());
		paramsMap.put("hiddenMchtCodeList", sysParamCfgService.getSpecialHideMchtCodeList());
		List<ProductCustom> productCustoms = productService.getShopMallProductListDefaultSort(paramsMap);
		if(CollectionUtils.isNotEmpty(productCustoms)){
			List<Integer> mchtIds = new ArrayList<Integer>();
			List<Integer> productIds = new ArrayList<Integer>();
			for (ProductCustom productCustom : productCustoms) {
				mchtIds.add(productCustom.getMchtId());
				productIds.add(productCustom.getId());
			}
			//活动中的
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productIdList", productIds);
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom activityCustom : activityCustoms) {
					activityMap.put(activityCustom.getProductId().toString(), activityCustom);
				}
			}
			//店铺活动
			Map<String,String> preferentialInfoMap = shopPreferentialInfoService.getShopPreferentialInfo(mchtIds);
			for (ProductCustom productCustom : productCustoms) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Integer productId = productCustom.getId();
				BigDecimal saleOrMallPrice = productCustom.getMallPrice();
				BigDecimal tagPrice = productCustom.getTagPrice();
				String shopPreferentialInfo = "";
				String activityTypeContent = "";
				String activityType = "";
				Integer stockSum = productCustom.getStockSum();
				Integer mchtId = productCustom.getMchtId();
				String activityId = "";
				Integer brandId = productCustom.getBrandId();
				String brandName = productCustom.getBrandName();
				if(brandId == 0){
					brandName = "";
				}
				if(activityMap.get(productId.toString()) != null){
					//查找到这只商品是处于活动中的商品
					ActivityCustom activityCustom = activityMap.get(productId.toString());
					if(activityCustom.getProductId() != null){
						activityId = activityCustom.getId().toString();
						shopPreferentialInfo = activityCustom.getBenefitPoint();
						saleOrMallPrice = new BigDecimal(activityCustom.getSalePrice());
						tagPrice = new BigDecimal(activityCustom.getTagPrice());
						activityTypeContent = "特卖活动";
						activityType = "0";
					}
				}else{
					shopPreferentialInfo = preferentialInfoMap.get(mchtId.toString()) == null ? "" : preferentialInfoMap.get(mchtId.toString());
					if(!StringUtil.isBlank(shopPreferentialInfo)){
						activityTypeContent = "店铺活动";
					}
					activityType = "101";
				}
				//评价数量
				Map<String, Object> commentParamsMap = new HashMap<>();
				commentParamsMap.put("productId", productId);
				int commentCount = commentService.getProductTotalCommentCount(commentParamsMap);
				String commentCountStr = "";
				//好评度
				String goodProductScoreCountStr = "";
				if(commentCount > 9) {
					if(commentCount <= 9999) {
						commentCountStr = commentCount+"条评价";
					}else {
						commentCountStr = new BigDecimal(commentCount).setScale(1, BigDecimal.ROUND_DOWN)+"万条评价";
					}
					Integer goodProductScoreCount = productCustom.getGoodProductScoreCount()==null?0:productCustom.getGoodProductScoreCount();
					if(goodProductScoreCount == 0) {
						goodProductScoreCountStr = "0%好评";
					}else {
						goodProductScoreCountStr = new BigDecimal(goodProductScoreCount).divide(new BigDecimal(commentCount), 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(0).toString()+"%好评";
					}
				}
				dataMap.put("commentCountStr", commentCountStr);
				dataMap.put("goodProductScoreCountStr", goodProductScoreCountStr);
				dataMap.put("activityType", activityType);
				dataMap.put("activityTypeContent", activityTypeContent);
				dataMap.put("productId", productId);
				dataMap.put("mchtId", mchtId);
				dataMap.put("productName", productCustom.getName());
				dataMap.put("brandName", brandName);
				dataMap.put("productPic", StringUtil.getPic(productCustom.getPic(), "M"));
				dataMap.put("mallPrice", saleOrMallPrice);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("stockSum", stockSum);
				dataMap.put("shopPreferentialInfo", shopPreferentialInfo);
				dataMap.put("activityId", activityId);
				dataMap.put("manageSelf", productCustom.getIsManageSelf()); //是否自营
				dataList.add(dataMap);
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	public Map<String, Object> getShopProductList(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize, String memberId) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Integer mchtId = null;
		String mchtCode = "";
		String type = reqDataJson.getString("type");//1首页商品 2 商品列表 3 上新商品
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer brandId = null;
		Integer productTypeId = null;
		String classId = "";
		String repStatus = "1";//0异常 1正常
		String repMsg = "";
		String shopLogo = "";
		String shopName = "";
		String xiaoNengId = "";
		boolean isCollectuonShop = false;//客服，店铺是否收藏
		List<String> wordList = new ArrayList<String>();//根据搜索词拆成几个关键词
		MchtInfoExample mchtInfoExample = new MchtInfoExample();
		MchtInfoExample.Criteria criteria = mchtInfoExample.createCriteria();
		criteria.andStatusEqualTo("1").andShopStatusEqualTo("1").andDelFlagEqualTo("0");
		if(reqDataJson.containsKey("mchtId") && !StringUtil.isBlank(reqDataJson.getString("mchtId"))){
			mchtId = reqDataJson.getInt("mchtId");
			criteria.andIdEqualTo(mchtId);
		}else if(reqDataJson.containsKey("mchtCode") && !StringUtil.isBlank(reqDataJson.getString("mchtCode"))){
			mchtCode = reqDataJson.getString("mchtCode");	
			criteria.andMchtCodeEqualTo(mchtCode);
		}else{
			repStatus = "0";
			repMsg = "店铺未开通.";
		}
		
		if(reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))){
			memberId = reqDataJson.getString("memberId");
		}
		if(!StringUtil.isBlank(memberId) && currentPage == 0){
			isCollectuonShop = DataDicUtil.getRemindExists(mchtId, Integer.valueOf(memberId), "3");
		}
		if(reqDataJson.containsKey("brandId") && !StringUtil.isBlank(reqDataJson.getString("brandId"))){
			brandId = reqDataJson.getInt("brandId");
		}
		if(reqDataJson.containsKey("productTypeId") && !StringUtil.isBlank(reqDataJson.getString("productTypeId"))){
			productTypeId = reqDataJson.getInt("productTypeId");		
		}
		if(reqDataJson.containsKey("searchName") && !StringUtil.isBlank(reqDataJson.getString("searchName"))){
			String searchName = reqDataJson.getString("searchName");
			wordList = StringUtil.seg(searchName.replace(";", "").trim());//数据库的商品搜索域用;号作为各个字段的分隔符，所以过滤掉;
			if(CollectionUtils.isNotEmpty(wordList)){
				if(wordList.size() > 4){
					wordList.subList(0, 4);//只取前面4个
				}
			}
		}
		if(reqDataJson.containsKey("classId") && !StringUtil.isBlank(reqDataJson.getString("classId"))){
			classId = reqDataJson.getString("classId");
		}
		if(repStatus.equals("1")){
			List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
			if(CollectionUtils.isNotEmpty(mchtInfos)){
				shopLogo = mchtInfos.get(0).getShopLogo() == null ? "" : StringUtil.getPic(mchtInfos.get(0).getShopLogo(), "");
				shopName = mchtInfos.get(0).getShopName();
				mchtCode = mchtInfos.get(0).getMchtCode();
				mchtId = mchtInfos.get(0).getId();
				xiaoNengId = xiaonengCustomerServiceService.getCustomerService(mchtId, "5");
			}else{
				repStatus = "0";
				repMsg = "店铺未开通..";
			}
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("mchtId", mchtId);
			paramsMap.put("type", type);
			paramsMap.put("brandId", brandId);
			paramsMap.put("productTypeId", productTypeId);
			paramsMap.put("classId", classId);
			paramsMap.put("wordList", wordList);
			paramsMap.put("currentPage", currentPage * pageSize);
			paramsMap.put("pageSize", pageSize);
			paramsMap.put("hiddenTypeList", sysParamCfgService.getSpecialHideTypeList());
			paramsMap.put("hiddenMchtCodeList", sysParamCfgService.getSpecialHideMchtCodeList());
			List<ProductCustom> productCustoms = productService.getShopProductList(paramsMap);
			if(CollectionUtils.isNotEmpty(productCustoms)){
				List<Integer> mchtIds = new ArrayList<Integer>();
				List<Integer> productIds = new ArrayList<Integer>();
				mchtIds.add(mchtId);
				for (ProductCustom productCustom : productCustoms) {
					productIds.add(productCustom.getId());
				}
				//活动中的
				Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
				Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
				paramsActivityMap.put("productIdList", productIds);
				List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
				if(CollectionUtils.isNotEmpty(activityCustoms)){
					for (ActivityCustom activityCustom : activityCustoms) {
						activityMap.put(activityCustom.getProductId().toString(), activityCustom);
					}
				}
				//店铺活动
				Map<String,String> preferentialInfoMap = shopPreferentialInfoService.getShopPreferentialInfo(mchtIds);
				for (ProductCustom productCustom : productCustoms) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					Integer productId = productCustom.getId();
					BigDecimal saleOrMallPrice = productCustom.getMallPrice();
					BigDecimal tagPrice = productCustom.getTagPrice();
					String shopPreferentialInfo = "";
					String activityTypeContent = "";
					String activityType = "";
					String activityId = "";
					brandId = productCustom.getBrandId();
					String brandName = productCustom.getBrandName();
					if(brandId == 0){
						brandName = "";
					}
					Integer stockSum = productCustom.getStockSum();
					if(activityMap.get(productId.toString()) != null){
						//查找到这只商品是处于活动中的商品
						ActivityCustom activityCustom = activityMap.get(productId.toString());
						if(activityCustom.getProductId() != null){
							activityId = activityCustom.getId().toString();
							shopPreferentialInfo = activityCustom.getBenefitPoint();
							saleOrMallPrice = new BigDecimal(activityCustom.getSalePrice());
							tagPrice = new BigDecimal(activityCustom.getTagPrice());
							activityTypeContent = "特卖活动";
							activityType = "0";
						}
					}else{
						shopPreferentialInfo = preferentialInfoMap.get(mchtId.toString()) == null ? "" : preferentialInfoMap.get(mchtId.toString());
						if(!StringUtil.isBlank(shopPreferentialInfo)){
							activityTypeContent = "店铺活动";
						}
						activityType = "101";
					}
					//评价数量
					Map<String, Object> commentParamsMap = new HashMap<>();
					commentParamsMap.put("productId", productId);
					int commentCount = commentService.getProductTotalCommentCount(commentParamsMap);
					String commentCountStr = "";
					String goodProductScoreCountStr = "";
					//好评数量
					if(commentCount > 9) {
						commentParamsMap.put("productScore", 4);
						Integer goodProductScoreCount = commentService.getProductTotalCommentCount(commentParamsMap);
						if(commentCount <= 9999) {
							commentCountStr = commentCount+"条评价";
						}else {
							commentCountStr = new BigDecimal(commentCount).setScale(1, BigDecimal.ROUND_DOWN)+"万条评价";
						}
						if(goodProductScoreCount == 0) {
							goodProductScoreCountStr = "0%好评";
						}else {
							goodProductScoreCountStr = new BigDecimal(goodProductScoreCount).divide(new BigDecimal(commentCount), 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(0).toString()+"%好评";
						}
					}
					dataMap.put("commentCountStr", commentCountStr);
					dataMap.put("goodProductScoreCountStr", goodProductScoreCountStr);
					dataMap.put("activityTypeContent", activityTypeContent);
					dataMap.put("activityType", activityType);
					dataMap.put("shopPreferentialInfo", shopPreferentialInfo);
					dataMap.put("productId", productId);
					dataMap.put("mallPrice", saleOrMallPrice);
					dataMap.put("tagPrice", tagPrice);
					dataMap.put("stockSum", stockSum);
					dataMap.put("productName", productCustom.getName());
					dataMap.put("brandName", brandName);
					dataMap.put("productPic", StringUtil.getPic(productCustom.getPic(), "M"));
					dataMap.put("discount", tagPrice.doubleValue()==0?1:saleOrMallPrice.divide(tagPrice,2, BigDecimal.ROUND_HALF_UP));
					dataMap.put("activityId", activityId);
					dataList.add(dataMap);
				}
			}
		}
		map.put("dataList", dataList);
		map.put("mchtId", mchtId);
		map.put("mchtCode", mchtCode);
		map.put("shopLogo", shopLogo);
		map.put("shopName", shopName);
		map.put("isCollectuonShop", isCollectuonShop);
		map.put("xiaoNengId", xiaoNengId);
		map.put("repStatus", repStatus);
		map.put("repMsg", repMsg);
		return map;
	}

	public Map<String, Object> getShopActivityList(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize) {
		Map<String, Object> map = new HashMap<>();
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer mchtId = null;
		if(reqDataJson.containsKey("mchtId") && !StringUtil.isBlank(reqDataJson.getString("mchtId"))){
			mchtId = reqDataJson.getInt("mchtId");
		}else if(reqDataJson.containsKey("mchtCode") && !StringUtil.isBlank(reqDataJson.getString("mchtCode"))){
			MchtInfoExample mchtInfoExample = new MchtInfoExample();
			mchtInfoExample.createCriteria().andMchtCodeEqualTo(reqDataJson.getString("mchtCode"));
			List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
			if(CollectionUtils.isNotEmpty(mchtInfos)){
				mchtId = mchtInfos.get(0).getId();
			}else{
				throw new ArgException("店铺已关闭");
			}
		}else{
			throw new ArgException("商家id不能为空");
		}
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("mchtId", mchtId);
		paramsMap.put("orderByType", "1");
		paramsMap.put("currentPage", currentPage * pageSize);
		paramsMap.put("pageSize", pageSize);
		List<Map<String,Object>> activityList = activityService.getBrandRecommendActivityByMchtId(paramsMap);
		map.put("activityList", activityList);
		return map;
	}
	
	public Map<String, Object> getMchtShopInfo(JSONObject reqPRM, JSONObject reqDataJson, MemberInfo memberInfo) {
		Integer mchtId = null;
		String mchtCode = "";
		Integer memberId = null;
		MchtInfoExample mchtInfoExample = new MchtInfoExample();
		MchtInfoExample.Criteria criteria = mchtInfoExample.createCriteria();
		if(reqDataJson.containsKey("mchtId") && !StringUtil.isBlank(reqDataJson.getString("mchtId"))){
			mchtId = reqDataJson.getInt("mchtId");
			criteria.andIdEqualTo(mchtId);
		}else if(reqDataJson.containsKey("mchtCode") && !StringUtil.isBlank(reqDataJson.getString("mchtCode"))){
			mchtCode = reqDataJson.getString("mchtCode");	
			criteria.andMchtCodeEqualTo(mchtCode);
		}else{
			throw new ArgException("店铺id不能为空");
		}
		List<MchtInfo> mchtInfos = mchtInfoService.selectByExample(mchtInfoExample);
		if(CollectionUtils.isEmpty(mchtInfos)){
			throw new ArgException("店铺已关闭");
		}
		MchtInfo mchtInfo = mchtInfos.get(0);
		String shopLogo = StringUtil.getPic(mchtInfo.getShopLogo(), "");
		String businessLicensePic = "";
		String licenseIsMust = mchtInfo.getLicenseIsMust();
		String licenseStatus = mchtInfo.getLicenseStatus();
		String shopName = mchtInfo.getShopName();
		String companyAddress = mchtInfo.getCompanyAddress();
		String companyName = mchtInfo.getCompanyName();
		Date shopOpenDate = mchtInfo.getShopStatusDate();
		String shopOpenDateStr = "";
		if(shopOpenDate != null){
			shopOpenDateStr = DateUtil.getFormatDate(shopOpenDate, "yyyy-MM-dd HH:mm:ss");
		}
		String mchtSettledType = mchtInfo.getSettledType() == null ? "" : mchtInfo.getSettledType();//入驻类型1.企业公司2.个体工商
		String mVerfiyFlag = "";
		boolean isCollectuonShop = false;
		if(memberInfo != null){
			memberId = memberInfo.getId();
			isCollectuonShop = DataDicUtil.getRemindExists(mchtId, memberId, "3");
			mVerfiyFlag = memberInfo.getmVerfiyFlag();
		}
		if("1".equals(licenseIsMust) && "2".equals(licenseStatus)){
			businessLicensePic = StringUtil.getPic(mchtInfo.getBusinessLicensePic(), "");
		}
		//评价数量
		String goodProductScoreCountStr = "";
		Map<String, Object> commentParamsMap = new HashMap<>();
		commentParamsMap.put("mchtId", mchtId);
		int commentCount = commentService.getProductTotalCommentCount(commentParamsMap);
		commentParamsMap.put("productScore", 4);
		//好评数量
		Integer goodProductScoreCount = commentService.getProductTotalCommentCount(commentParamsMap);
		if(commentCount <= 0) {
			goodProductScoreCountStr = "店铺商品好评率 100%";
		}else {
			goodProductScoreCountStr = new BigDecimal(goodProductScoreCount).divide(new BigDecimal(commentCount), 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(0).toString()+"%";
			goodProductScoreCountStr = "店铺商品好评率 "+goodProductScoreCountStr;
		}
		Map<String, BigDecimal> shopScoreMap = commentService.getShopAvgScore(null,mchtId);
		String xiaoNengId = xiaonengCustomerServiceService.getCustomerService(mchtId, "5");
		String intelligenceUrl = "";
		//店铺优惠券
		List<Map<String, Object>> shopCouponList = couponService.getShopCouponList(memberId,mchtId);
		Map<String, Object> map = new HashMap<>();
		map.put("shopCouponList", shopCouponList);
		map.put("businessLicensePic", businessLicensePic);
		map.put("mchtSettledType", mchtSettledType);
		map.put("shopName", shopName);
		map.put("shopLogo", shopLogo);
		map.put("isCollectuonShop", isCollectuonShop);
		map.put("goodProductScoreCountStr", goodProductScoreCountStr);
		map.put("productAvgScore", shopScoreMap.get("productAvgScore").setScale(1, BigDecimal.ROUND_DOWN).toString());
		map.put("mchtAvgScore", shopScoreMap.get("mchtAvgScore").setScale(1, BigDecimal.ROUND_DOWN).toString());
		map.put("wuliuAvgScore", shopScoreMap.get("wuliuAvgScore").setScale(1, BigDecimal.ROUND_DOWN).toString());
		map.put("companyName", companyName);
		map.put("companyAddress", companyAddress);
		map.put("shopOpenDate", shopOpenDateStr);
		map.put("mVerfiyFlag", mVerfiyFlag);
		map.put("intelligenceUrl", intelligenceUrl);
		map.put("xiaoNengId", xiaoNengId);
		map.put("mchtId", mchtId);
		map.put("manageSelf", mchtInfo.getIsManageSelf()); //是否自营
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getShopProductScreeningConditions(JSONObject reqPRM, JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> brandList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> classList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> categoryList = new ArrayList<Map<String, Object>>();
		Integer mchtId = reqDataJson.getInt("mchtId");
		List<ProductCustom> brandCustoms = productService.getShopProductBrandConverge(mchtId);
		List<ProductCustom> classCustoms = productService.getShopProductClassConverge(mchtId);
		List<ProductCustom> categoryCustoms = productService.getShopProductCategoryConverge(mchtId);
		if(CollectionUtils.isNotEmpty(brandCustoms)){
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> brandMap = new HashMap<>();
			brandMap.put("name", "品牌");
			brandMap.put("id", "");
			for (ProductCustom p : brandCustoms) {
				Map<String, Object> brand1Map = new HashMap<>();
				brand1Map.put("id", p.getBrandId());
				brand1Map.put("name", p.getBrandName());
				list.add(brand1Map);
			}
			brandMap.put("dataList", list);
			brandList.add(brandMap);
		}
		if(CollectionUtils.isNotEmpty(classCustoms)){
			String shopProductCustomTypeIdGroup = "";
			for (ProductCustom p : classCustoms) {
				if(!StringUtil.isBlank(p.getShopProductCustomTypeIdGroup())){
					shopProductCustomTypeIdGroup += p.getShopProductCustomTypeIdGroup()+",";
				}
			}
			if(!StringUtil.isBlank(shopProductCustomTypeIdGroup)){
				shopProductCustomTypeIdGroup = shopProductCustomTypeIdGroup.substring(0,shopProductCustomTypeIdGroup.length()-1);
				List<Integer> sList = new ArrayList<Integer>();
				for (String str : shopProductCustomTypeIdGroup.split(",")) {
					sList.add(Integer.parseInt(str));
				}
				if(CollectionUtil.isNotEmpty(sList)){
					ShopProductCustomTypeExample shopProductCustomTypeExample = new ShopProductCustomTypeExample();
					shopProductCustomTypeExample.createCriteria().andMchtIdEqualTo(mchtId).andStatusEqualTo("1").andDelFlagEqualTo("0").andIdIn(sList);
					List<ShopProductCustomType> customTypes = shopProductCustomTypeService.selectByExample(shopProductCustomTypeExample);
					if(CollectionUtil.isNotEmpty(customTypes)){
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						Map<String, Object> classMap = new HashMap<>();
						classMap.put("name", "精选分类");
						classMap.put("id", "");
						for (ShopProductCustomType shopProductCustomType : customTypes) {
							Map<String, Object> class1Map = new HashMap<>();
							class1Map.put("id", shopProductCustomType.getId());
							class1Map.put("name", shopProductCustomType.getName());
							list.add(class1Map);
						}
						classMap.put("dataList", list);
						classList.add(classMap);
					}
				}
			}
		}
		if(CollectionUtils.isNotEmpty(categoryCustoms)){
			List<Map<String, Object>> productTypeList = new ArrayList<Map<String, Object>>();
			Map<String, Object> categoryTwoAllMap = new HashMap<>();
			for (ProductCustom p : categoryCustoms) {
				Map<String, Object> m2 = new HashMap<>();
				Integer productTypeId = p.getProductTypeId();
				String productTypeName = p.getProductTypeName();
				Integer productType2Id = p.getProductType2Id();
				String productType2Name = p.getProductType2Name();
				if(categoryTwoAllMap.containsKey(productType2Id.toString())){
					productTypeList = (List<Map<String, Object>>) categoryTwoAllMap.get(productType2Id.toString());
					m2.put("id", productTypeId);
					m2.put("name", productTypeName);
					productTypeList.add(m2);
				}else{
					Map<String, Object> categoryTwoMap = new HashMap<>();
					productTypeList = new ArrayList<Map<String, Object>>();
					m2.put("id", productTypeId);
					m2.put("name", productTypeName);
					productTypeList.add(m2);
					categoryTwoMap.put("id", productType2Id);
					categoryTwoMap.put("name", productType2Name);
					categoryTwoMap.put("dataList", productTypeList);
					categoryList.add(categoryTwoMap);
					
					categoryTwoAllMap.put(productType2Id.toString(), productTypeList);
				}
			}
		}
		map.put("brandList", brandList);
		map.put("classList", classList);
		map.put("categoryList", categoryList);
		return map;
	}

	public Map<String, Object> getMchtGSQualifications(JSONObject reqDataJson) {
		Map<String, Object> map = new HashMap<>();
		Integer mchtId = reqDataJson.getInt("mchtId");
		MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
		String regFeeType = mchtInfo.getRegFeeType();
		String companyTypeName = mchtInfo.getCompanyType();
		String regCapital = "";
		String regFeeTypeName = "";
		String yearlyInspectionDate = mchtInfo.getYearlyInspectionDate() == null ? "" : DateUtil.getFormatDate(mchtInfo.getYearlyInspectionDate(), "yyyy-MM-dd");//营业期限：
		String scopeOfBusiness = mchtInfo.getScopeOfBusiness() == null ? "" : mchtInfo.getScopeOfBusiness();//经营范围：
		if(mchtInfo.getRegCapital() != null){
			regCapital = mchtInfo.getRegCapital()+"万元";
			if(!StringUtil.isBlank(regFeeType)){
				regFeeTypeName = DataDicUtil.getStatusDesc("BU_MCHT_INFO", "REG_FEE_TYPE", regFeeType);
				regCapital += "("+regFeeTypeName+")" ;
			}
		}
		map.put("uscc", mchtInfo.getUscc());
		map.put("companyName", mchtInfo.getCompanyName());
		map.put("companyTypeName", companyTypeName);
		map.put("companyAddress", mchtInfo.getCompanyAddress());
		map.put("corporationName", mchtInfo.getCorporationName());
		map.put("foundedDate", mchtInfo.getFoundedDate() == null ? "" : DateUtil.getFormatDate(mchtInfo.getFoundedDate(), "yyyy-MM-dd"));
		map.put("regCapital", regCapital);
		map.put("yearlyInspectionDate", yearlyInspectionDate);
		map.put("scopeOfBusiness", scopeOfBusiness);
		return map;
	}
}
