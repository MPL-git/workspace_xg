package com.jf.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.ListHelper;
import com.jf.common.utils.MapHelper;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ActivityProductDepositMapper;
import com.jf.dao.ProductCustomMapper;
import com.jf.dao.ProductMapper;
import com.jf.entity.*;

import com.jf.vo.response.CouponSimpleView;
import com.jf.vo.response.ReceivedRedInfo;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

/**
  * @author  chenwf:
  * @date 创建时间：2017年4月27日 下午4:56:39
  * @version 1.0
  * @parameter
  * @return
*/
@Service
@Transactional
public class ProductService extends BaseService<Product, ProductExample> {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductCustomMapper productCustomMapper;
	@Resource
	private ProductService productService;

	@Resource
	private ProductItemService productItemService;

	@Resource
	private ProductPicService productPicService;

	@Resource
	private ProductDescPicService productDescPicService;

	@Resource
	private CouponService couponService;

	@Resource
	private MemberCouponService memberCouponService;

	@Resource
	private ActivityAreaService activityAreaService;

	@Resource
	private ActivityService activityService;

	@Resource
	private MemberAccountService memberAccountService;

	@Resource
	private IntegralDtlService integralDtlService;
	@Resource
	private ProductPropValueService productPropValueService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private SingleProductActivityCnfService singleProductActivityCnfService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private SobotCustomerServiceService sobotCustomerServiceService;
	@Resource
	private CustomAdPageService customAdPageService;
	@Resource
	private MemberInfoService memberInfoService;
	@Resource
	private MemberAddressService memberAddressService;
	@Resource
	private ActivityGroupService activityGroupService;
	@Resource
	private CommentService commentService;
	@Resource
	private ShopPreferentialInfoService shopPreferentialInfoService;
	@Resource
	private FreightTemplateService freightTemplateService;

	@Resource
	private ActivityProductDepositMapper activityProductDepositMapper;
	@Resource
	private DeliveryOvertimeCnfService deliveryOvertimeCnfService;
	@Resource
	private ThirdPlatformProductInfoService thirdPlatformProductInfoService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ProductVideoService productVideoService;
	@Resource
	private NovaPlanService novaPlanService;
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	@Resource
	private CommonService commonService;
	@Resource
	private SysParamCfgService sysParamCfgService;

	@Autowired
	public void setProductMapper(ProductMapper productMapper) {
		this.setDao(productMapper);
		this.productMapper = productMapper;
	}

	public List<ProductCustom> getProductInfoList(Map<String, Object> params) {

		return productCustomMapper.getProductInfoList(params);
	}

	public List<ProductCustom> getStandardById(Map<String, Object> params) {

		return productCustomMapper.getStandardById(params);
	}

	public List<ProductItem> getStandard(Map<String, Object> params) {

		return productCustomMapper.getStandard(params);
	}

	public List<ProductItem> getProductItemsById(Map<String, Object> params) {

		return productCustomMapper.getProductItemsById(params);
	}

	public ProductCustom getUserBuyCount(Map<String, Object> params) {

		return productCustomMapper.getUserBuyCount(params);
	}

	public Product findModelById(Integer id) {

		return productMapper.selectByPrimaryKey(id);
	}

	public ProductCustom getProductModeById(Map<String, Object> params) {

		return productCustomMapper.getProductModeById(params);
	}

	public List<ProductItem> getHasStockList(Map<String, Object> params) {

		return productCustomMapper.getHasStockList(params);
	}

	public Integer getShoppingCartProductNum(Map<String, Object> params) {

		return productCustomMapper.getShoppingCartProductNum(params);
	}

	public List<Map<String, Object>> getProductDynamicList(Map<String, Object> paramMap) {
		return productCustomMapper.getProductDynamicList(paramMap);
	}

	public Integer getShoppingCartProductNum(Map<String, Object> params, String type) {
		Integer num = 0;
		if(params.containsKey("activityId")){
			params.remove("activityId");
		}
		num = getShoppingCartProductNum(params);
		return num;
	}

	public ProductCustom getUserBuyCount(Map<String, Object> params, String type) {
		//获取用户已购买商品的件数
		ProductCustom productCustom = null;
		if(params.containsKey("activityId")){
			params.remove("activityId");
		}
		if(params.containsKey("activityAreaId")){
			params.remove("productId");
		}
		productCustom = getUserBuyCount(params);
		return productCustom;
	}



	/**
	 * 获取用户同一类型单凭活动购买数量
	 * @param params
	 * @return
	 */
	int getUserBuyCountBySingleActivityType(Map<String, Object> params){
		return productCustomMapper.getUserBuyCountBySingleActivityType(params);
	}

	/**
	 * 获取用户同一类型单凭活动购物车数量
	 * @param params
	 * @return
	 */
	int getUserShoppingCartCountBySingleActivityType(Map<String, Object> params){
		return productCustomMapper.getUserShoppingCartCountBySingleActivityType(params);
	}
	/**
	 * 获取商品的活动状态
	 * 0-闲置 1-报名中 2-待开始 3-预热中 4-活动中 5-活动暂停 6-未提审 7-未通过
	 * @return
	 */
	public String getProductactivityStatus(Integer id) {

		return productCustomMapper.getProductactivityStatus(id);
	}

	public List<ProductCustom> getShoppingMallProductData(Map<String, Object> paramsMap) {

		return productCustomMapper.getShoppingMallProductData(paramsMap);
	}

	public List<ProductCustom> getMchtShoppingProductList(Map<String, Object> paramsMap) {

		return productCustomMapper.getMchtShoppingProductList(paramsMap);
	}

	public String getProductActivity(Integer productId, Product product) {
		String activityType = "";
		//判断该商品是否在会场活动中
		int activityAreaCount = productCustomMapper.getActivityCount(productId);
		//
		int singleActivityCount = productCustomMapper.getSingleActivityCount(productId);
		if(activityAreaCount > 0){
			activityType = "1";
		}else if(singleActivityCount > 0){
			activityType = "2";
		}else{
			if(product == null){
				product = selectByPrimaryKey(productId);
			}
			if(!StringUtil.isBlank(product.getIsActivity()) && product.getIsActivity().equals("0")){
				activityType = "3";
			}else{
				activityType = "99999";
			}
		}
		return activityType;
	}

	public Map<String, Object> getProductSkuInfo(JSONObject reqPRM, JSONObject reqDataJson) {
		String system = reqPRM.getString("system");
		Integer version = reqPRM.getInt("version");
		//商品id
		Integer id = reqDataJson.getInt("id");
		String isSvip = "0";
		//商品的所有大规格id
		String propIds = "";
		if(reqDataJson.containsKey("propIds")){
			propIds = reqDataJson.getString("propIds");
		}else{
			throw new ArgException("已售罄");
		}
		String openType = "";
		if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
			Integer memberId = reqDataJson.getInt("memberId");
			MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
			isSvip = memberInfoService.getIsSvip(memberInfo, reqDataJson.getInt("memberId"));
			openType = novaPlanService.getNewNovaPlanOpenType(memberInfo, memberInfo.getId());
		}
		Date date = new Date();
		//规格ids
		String propValIds = reqDataJson.getString("propValIds");
		List<Integer> propIdList = new ArrayList<Integer>();
		List<Integer> propValIdsList = new ArrayList<Integer>();
		Map<String,String> propValIdsMap = new HashMap<String,String>();
		if(!StringUtil.isBlank(propValIds)){
			for (String str : propValIds.split(",")) {
				propValIdsList.add(Integer.valueOf(str));
			}
		}
		if(CollectionUtils.isNotEmpty(propValIdsList)){
			ProductPropValueExample productPropValueExample = new ProductPropValueExample();
			productPropValueExample.createCriteria().andIdIn(propValIdsList).andDelFlagEqualTo("0");
			List<ProductPropValue> productPropValues = productPropValueService.selectByExample(productPropValueExample);
			for (ProductPropValue productPropValue : productPropValues) {
				if(!propValIdsMap.containsKey(productPropValue.getPropId().toString())){
					String value = productPropValue.getId().toString();
					if(StringUtil.isBlank(value)){
						value = "";
					}
					propValIdsMap.put(productPropValue.getPropId().toString(), productPropValue.getId().toString());
				}
			}
		}
		Map<String,Object> params = new HashMap<String,Object>();
		String item = "";
		for (String str : propIds.split(",")) {
			List<Integer> list = new ArrayList<Integer>();
			for (Entry<String, String> entry : propValIdsMap.entrySet()){
				String key = entry.getKey();
				String value = entry.getValue();
				if(!StringUtil.isBlank(value)){
					if(!key.equals(str)){
						list.add(Integer.valueOf(value));
					}
				}
			}
			params.put("id", id);
			params.put("propValIdsList", list);
			List<Integer> sList = new ArrayList<Integer>();
			List<ProductItem> productItems = productService.getProductItemsById(params);
			String item2 = "";
			for (ProductItem productItem : productItems) {
				item2 += productItem.getPropValId()+",";
			}
			if(!StringUtil.isBlank(item2)){
				String[] itemsStr = item2.split(",");
				if(itemsStr.length>0){
					for (String s : itemsStr) {
						sList.add(Integer.valueOf(s));
					}
				}
			}
			if(CollectionUtils.isNotEmpty(sList)){
				ProductPropValueExample productPropValueExample = new ProductPropValueExample();
				productPropValueExample.createCriteria().andIdIn(sList).andPropIdEqualTo(Integer.valueOf(str)).andDelFlagEqualTo("0");
				List<ProductPropValue> productPropValues = productPropValueService.selectByExample(productPropValueExample);
				for (ProductPropValue productPropValue : productPropValues) {
					propIdList.add(productPropValue.getId());
				}
			}
		}
		//封装规格详情集合
		List<Map<String,Object>> standardMapList = new ArrayList<Map<String,Object>>();
		if(CollectionUtils.isNotEmpty(propIdList)){
			ProductPropValueExample productPropValueExample = new ProductPropValueExample();
			productPropValueExample.createCriteria().andIdIn(propIdList).andDelFlagEqualTo("0");
			List<ProductPropValue> productPropValues = productPropValueService.selectByExample(productPropValueExample);
			if(CollectionUtils.isNotEmpty(productPropValues)){
				for (String str : propIds.split(",")) {
					Map<String,Object> propMap = new HashMap<String,Object>();
					propMap.put("propName", "");
					propMap.put("propId", str);
					List<Map<String,Object>> propValueMapList = new ArrayList<Map<String,Object>>();
					for (ProductPropValue productPropValue1 : productPropValues) {
						if(Integer.valueOf(str) == productPropValue1.getPropId()){
							Map<String,Object> specPicMap = new HashMap<String,Object>();
							Map<String,Object> propValueMap = new HashMap<String,Object>();
							specPicMap.put("productId", id);
							specPicMap.put("propValId", productPropValue1.getId());
							String specPic = "";
							//根据规格id和商品id去查找商品的sku图片(只有当规格有颜色的时候才去找)  1
							if(str.equals("1")){
								ProductItem itemMode = productItemService.getSpecPic(specPicMap);
								if(itemMode != null && !StringUtil.isBlank(itemMode.getPic())){
									specPic = FileUtil.getImageServiceUrl()+itemMode.getPic();
								}
							}
							propValueMap.put("propValue", productPropValue1.getPropValue());
							propValueMap.put("propValId", productPropValue1.getId());
							propValueMap.put("specPic", specPic);
							propValueMap.put("status", true);
							propValueMapList.add(propValueMap);
						}
					}
					propMap.put("propValueMapList", propValueMapList);
					standardMapList.add(propMap);
				}
			}
		}else{
			params.put("id", id);
			//params.put("propValIdsList", propValIdsList);
			List<Integer> sList2 = new ArrayList<Integer>();
			List<ProductItem> productItemsOne = productService.getProductItemsById(params);
			for (ProductItem productItem : productItemsOne) {
				item += productItem.getPropValId()+",";
			}
			if(!StringUtil.isBlank(item)){
				String[] itemsStr = item.split(",");
				if(itemsStr.length>0){
					for (String str : itemsStr) {
						sList2.add(Integer.valueOf(str));
					}
				}
			}
			if(CollectionUtils.isNotEmpty(sList2)){
				ProductPropValueExample productPropValueExample = new ProductPropValueExample();
				productPropValueExample.createCriteria().andIdIn(sList2).andDelFlagEqualTo("0");
				List<ProductPropValue> productPropValues = productPropValueService.selectByExample(productPropValueExample);
				if(CollectionUtils.isNotEmpty(productPropValues)){
					for (String str : propIds.split(",")) {
						Map<String,Object> propMap = new HashMap<String,Object>();
						propMap.put("propName", "");
						propMap.put("propId", str);
						List<Map<String,Object>> propValueMapList = new ArrayList<Map<String,Object>>();
						for (ProductPropValue productPropValue1 : productPropValues) {
							if(Integer.valueOf(str) == productPropValue1.getPropId()){
								Map<String,Object> specPicMap = new HashMap<String,Object>();
								Map<String,Object> propValueMap = new HashMap<String,Object>();
								specPicMap.put("productId", id);
								specPicMap.put("propValId", productPropValue1.getId());
								String specPic = "";
								//根据规格id和商品id去查找商品的sku图片(只有当规格有颜色的时候才去找)  1
								if(str.equals("1")){
									ProductItem itemMode = productItemService.getSpecPic(specPicMap);
									if(itemMode != null && !StringUtil.isBlank(itemMode.getPic())){
										specPic = FileUtil.getImageServiceUrl()+itemMode.getPic();
									}
								}
								propValueMap.put("propValue", productPropValue1.getPropValue());
								propValueMap.put("propValId", productPropValue1.getId());
								propValueMap.put("specPic", specPic);
								propValueMap.put("status", true);
								propValueMapList.add(propValueMap);
							}
						}
						propMap.put("propValueMapList", propValueMapList);
						standardMapList.add(propMap);
					}
				}
			}

		}
		//获取商品sku详情
		Product product = productService.selectByPrimaryKey(id);
		BigDecimal svipDiscount = product.getSvipDiscount()== null ? new BigDecimal("0") : product.getSvipDiscount();
		String isActivity = "1";
		String activityType = "";
		Integer activityId = null;
		if("1".equals(product.getSaleType())){
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();//活动中
			Map<String,ActivityCustom> notActivityMap = new HashMap<String,ActivityCustom>();//活动未开始
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productId", id);
			if((system.equals(Const.ANDROID) && version >= 45) || (system.equals(Const.IOS) && version >= 48)){
				paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
			}
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom activityCustom : activityCustoms) {
					if(activityCustom.getActivityBeginTime().compareTo(date) <= 0){ //活动中
						activityMap.put(activityCustom.getProductId().toString(), activityCustom);
						activityType = "0";
						activityId = activityCustom.getId();
					}else { //活动未开始
						notActivityMap.put(activityCustom.getProductId().toString(), activityCustom);
						activityType = "101";
					}
				}
			}
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				ActivityCustom activityCustom = activityCustoms.get(0);
				if(!activityMap.containsKey(id.toString())){
					//活动未开始，判断是否是预售活动
					activityCustom = notActivityMap.get(id.toString());
					if(activityCustom != null && "2".equals(activityCustom.getPreSellAuditStatus())) { //预售状态为通过
						ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
						activityProductDepositExample.createCriteria().andDelFlagEqualTo("0")
							.andActivityIdEqualTo(activityCustom.getId()).andProductIdEqualTo(id);
						List<ActivityProductDeposit> activityProductDepositList = activityProductDepositMapper.selectByExample(activityProductDepositExample);
						if(CollectionUtils.isEmpty(activityProductDepositList)) {
							isActivity = "0";
							activityType = "101";
						}else{
							activityType = "0";
							activityId = activityCustom.getId();
						}
					}else{
						isActivity = "0";
						activityType = "101";
					}
				}else{
					activityType = "0";
					activityId = activityCustom.getId();
				}
			}else{
				activityType = "101";
				isActivity = "0";
			}
		}


		//店长权益
		BigDecimal popFeeRate = new BigDecimal("0");
		BigDecimal spreadAmountRate = new BigDecimal("0");
		if("3".equals(openType)){
			List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_DISTRIBUTION_RATE");
			if(CollectionUtils.isNotEmpty(cfgs)){
				spreadAmountRate = new BigDecimal(cfgs.get(0).getParamValue());
			}
			try {
				//预防没有技术服务费，导致抛出异常
				Map<String, Object> popFeeRateParamsMap = new HashMap<>();
				popFeeRateParamsMap.put("productId", id);
				popFeeRateParamsMap.put("activityId", activityId);
				popFeeRateParamsMap.put("mchtId", product.getMchtId());
				popFeeRateParamsMap.put("brandId", product.getBrandId());
				popFeeRateParamsMap.put("productType1Id", product.getProductType1Id());
				popFeeRateParamsMap.put("productType2Id", product.getProductType2Id());
				popFeeRateParamsMap.put("productType3Id", product.getProductTypeId());
				popFeeRate = mchtProductBrandService.getPopFeeRate(popFeeRateParamsMap);
			} catch (Exception e) {
				popFeeRate = new BigDecimal("0");
			}
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		params.put("id", id);
		params.put("propValIdsList", propValIdsList);
		params.put("isActivity", isActivity);
		params.put("isHasStock", "1"); //必须有库存
		List<ProductItem> productItemsOne = productService.getProductItemsById(params);
		if (CollectionUtils.isEmpty(productItemsOne)) {
			params.put("isHasStock", "0"); //不判断库存
			productItemsOne = productService.getProductItemsById(params);
		}
		if(CollectionUtils.isNotEmpty(productItemsOne)){
			ProductItem productItemOne = productItemsOne.get(0);
			BigDecimal tagPrice = productItemOne.getTagPrice();
			BigDecimal salePrice = new BigDecimal("0");
			BigDecimal svipSalePrice = new BigDecimal("0");
			BigDecimal activityPrice = new BigDecimal("0");
			BigDecimal platformPreferential = new BigDecimal("0");
			String svipContent = "";
			String shopwnerEquityStr = "";
			BigDecimal shopwnerEquityAmount = new BigDecimal("0");
			if("1".equals(product.getSaleType())){
				if("0".equals(activityType)){
					salePrice = productItemOne.getSalePrice();
				}else{
					salePrice = productItemOne.getMallPrice();
					activityPrice = productItemOne.getSalePrice();
				}
			}else{
				salePrice = productItemOne.getSalePrice();
				SingleProductActivity singleProductActivity = singleProductActivityService.findModelByProductId(id);
				if(singleProductActivity != null){
					activityType = singleProductActivity.getType();
					platformPreferential = singleProductActivity.getPlatformPreferential() == null ? new BigDecimal("0") : singleProductActivity.getPlatformPreferential();
				}else{
					activityType = "99999";
				}
			}
			if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
				svipSalePrice = getProductSvipSalePrice(salePrice, svipDiscount, activityType);
				if(svipSalePrice.compareTo(new BigDecimal("0")) > 0){
					if("1".equals(isSvip)){
						svipContent = "您可享"+(svipDiscount.multiply(new BigDecimal("10"))).stripTrailingZeros().toPlainString()+"折 约省"+(salePrice.subtract(svipSalePrice)).stripTrailingZeros().toPlainString()+"元";
					}else{
						svipContent = "开通后可享"+(svipDiscount.multiply(new BigDecimal("10"))).stripTrailingZeros().toPlainString()+"折 约省"+(salePrice.subtract(svipSalePrice)).stripTrailingZeros().toPlainString()+"元";
					}
				}
			}
			BigDecimal spreadAmount = salePrice.multiply(popFeeRate).multiply(new BigDecimal(spreadAmountRate+"")).setScale(2, BigDecimal.ROUND_DOWN);
			if("3".equals(openType)){
				if(spreadAmount.compareTo(new BigDecimal("0")) > 0 ){
					shopwnerEquityAmount = salePrice.subtract(spreadAmount).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros();
					shopwnerEquityStr = "（升级店长约省"+spreadAmount.setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()+"元）";
				}
			}
			salePrice = getSaleOrMallPrice(activityType, version, system, salePrice, platformPreferential, reqPRM);
			dataMap.put("shopwnerEquityAmount", shopwnerEquityAmount);
			dataMap.put("shopwnerEquityStr", shopwnerEquityStr);
			dataMap.put("spreadAmountStr", 0);
			dataMap.put("tagPrice", tagPrice);
			dataMap.put("salePrice", salePrice);
			dataMap.put("activityPrice", activityPrice);
			dataMap.put("discount", 0);
			dataMap.put("svipSalePrice", svipSalePrice.stripTrailingZeros().toPlainString());
			dataMap.put("svipContent", svipContent);
			dataMap.put("skuPic", StringUtil.getPic(productItemOne.getPic(), ""));
			dataMap.put("skuPic_S", StringUtil.getPic(productItemOne.getPic(), "S"));
			Integer stock = productItemOne.getStock() - productItemOne.getLockedAmount();
			if(stock > 0 && product.getStatus().equals("1")){
				dataMap.put("stock", stock);
				dataMap.put("skuId", productItemOne.getId());
			}else{
				dataMap.put("stock", 0);
				dataMap.put("skuId", "");
			}
		}
		dataMap.put("standardMapList", standardMapList);
		return dataMap;
	}

	public Map<String, Object> getProductRelativeCouponInfo(Integer productId, Integer memberId) {
		Map<String, Object> result = Maps.newHashMap();

		List<ProductCustom> pCustoms = productCustomMapper.getProductBaseInfo(productId);
		ProductCustom product = pCustoms.get(0);
		Integer mchtId = product.getMchtId();
		Date date = new Date();
		ActivityCustom activityCustom = null;
		SingleProductActivity singleProductActivity = null;
		String activityAreaId = "";
		boolean hasNotStartActivity = false;//是否是未开始的活动
		long activityBeginTime = 0;
		long activityEndTime = 0;
		String preSellAuditStatus = "0"; //预售审核状态
		String activityType = "";
		Map<String, String> preferentialInfoMap = new HashMap<String, String>();
		preferentialInfoMap.put("preferentialInfo", "");
		preferentialInfoMap.put("preferentialType", "");

		if ("1".equals(product.getSaleType())) {
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productId", productId);
			paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				activityCustom = activityCustoms.get(0);
				activityBeginTime = activityCustom.getActivityBeginTime().getTime()/1000;
				activityEndTime = activityCustom.getActivityEndTime().getTime()/1000;
				if(activityCustom.getActivityBeginTime().compareTo(date) <= 0){
					activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
				}else{
					//活动还未开始
					hasNotStartActivity = true;
					//预售审核状态审核通过
					if(activityCustom.getPreSellAuditStatus() != null && "2".equals(activityCustom.getPreSellAuditStatus())) {
						ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
						activityProductDepositExample.createCriteria().andDelFlagEqualTo("0")
								.andActivityIdEqualTo(activityCustom.getId()).andProductIdEqualTo(productId);
						List<ActivityProductDeposit> activityProductDepositList = activityProductDepositMapper.selectByExample(activityProductDepositExample);
						//是否是预售商品
						if(CollectionUtils.isNotEmpty(activityProductDepositList)) {
							preSellAuditStatus = activityCustom.getPreSellAuditStatus();
							activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
						}else {
							activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
						}
					}else {
						activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
					}
				}
			}else{
				activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
			}
		}else{
			//单品活动
			singleProductActivity = singleProductActivityService.findModelByProductId(productId);
			if(singleProductActivity == null){
				activityType = "99999";//单品活动已结束
			}else {
				activityType = singleProductActivity.getType();
			}
		}

		if (activityCustom != null && activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA) && !hasNotStartActivity) {
			String preType = activityCustom.getPreferentialType();
			String preIdGroup = activityCustom.getPreferentialIdGroup();
			activityAreaId = activityCustom.getActivityAreaId().toString();
			preferentialInfoMap = memberCouponService.getPreferentialInfo(preType, preIdGroup, date);
		} else if (activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)) {
			ShopPreferentialInfoExample preferentialInfoExample = new ShopPreferentialInfoExample();
			preferentialInfoExample.createCriteria().andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThan(date)
					.andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtIdEqualTo(mchtId);
			preferentialInfoExample.setOrderByClause("id desc");
			preferentialInfoExample.setLimitStart(0);
			preferentialInfoExample.setLimitSize(1);
			List<ShopPreferentialInfo> preferentialInfos = shopPreferentialInfoService.selectByExample(preferentialInfoExample);
			if (CollectionUtils.isNotEmpty(preferentialInfos)) {
				ShopPreferentialInfo preferentialInfo = preferentialInfos.get(0);
				String preType = preferentialInfo.getType();
				String preIdGroup = "";
				if ("1".equals(preType)) {
					preIdGroup = preferentialInfo.getCouponIdGroup();
				} else {
					preIdGroup = preferentialInfo.getPreferentialId() == null ? "" : preferentialInfos.get(0).getPreferentialId().toString();
				}
				preferentialInfoMap = memberCouponService.getPreferentialInfo(preType, preIdGroup, date);
				activityBeginTime = preferentialInfo.getBeginDate().getTime()/1000;
				activityEndTime = preferentialInfo.getEndDate().getTime()/1000;
			}
		}

		long currentTime = new Date().getTime()/1000;
		if(!activityType.equals("99999")){
			if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
				if(StringUtil.isBlank(activityAreaId) && !"2".equals(preSellAuditStatus)){
					//时间找不对，直接设置为商品结束
					activityBeginTime = currentTime-2;
					activityEndTime = currentTime-1;
				}
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION)
					|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
					|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING)
					|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT)){
				if(singleProductActivity != null){
					activityBeginTime = singleProductActivity.getBeginTime().getTime()/1000;
					activityEndTime = singleProductActivity.getEndTime().getTime()/1000;
				}else{
					//时间找不对，直接设置为商品结束
					activityBeginTime = currentTime-2;
					activityEndTime = currentTime-1;
				}
			}else if(activityType.equals("7") || activityType.equals("8")){
				//砍价，邀请免费拿商品，原生的app没开发
				activityBeginTime = currentTime-2;
				activityEndTime = currentTime-1;
			}else{
				//走到这边的就属于日常销售
				//1如果活动未开始的，前端要展示距离开始时间多久
				//2如果活动已结束的，不展示时间
			}
		}else{
			//时间找不对，直接设置为商品结束
			activityBeginTime = currentTime-2;
			activityEndTime = currentTime-1;
		}

		String preferentialType = preferentialInfoMap.get("preferentialType");
		String preferentialTypeDesc = "";
		if ("1".equals(preferentialType)) {
			preferentialTypeDesc = "优惠券";
		} else if ("2".equals(preferentialType)) {
			preferentialTypeDesc = "满减";
		} else if ("3".equals(preferentialType)) {
			preferentialTypeDesc = "满赠";
		} else if ("4".equals(preferentialType)) {
			preferentialTypeDesc = "多买优惠";
		}

		result.put("activityType", activityType);
		result.put("activityBeginTime", activityBeginTime);
		result.put("activityEndTime", activityEndTime);
		result.put("preferentialTypeDesc", preferentialTypeDesc);
		result.put("preferentialInfo", preferentialInfoMap.get("preferentialInfo"));
		fillWithPreferentialCouponInfo(result, preferentialInfoMap.get("preferentialCouponIds"), memberId); //填充 活动优惠券
		fillWithCouponInfo(result, mchtId, memberId, productId); //填充 商品券、店铺券、新人券

		int showFlag = 1; //1活动中（非优惠券促销） 2活动中（优惠券促销） 3非活动中
		if (!CollectionUtil.isEmpty((List) result.get("preferentialCouponList"))) {
			showFlag = 2;
		}
		if (StringUtil.isBlank(preferentialInfoMap.get("preferentialInfo"))) {
			showFlag = 3;
		}
		result.put("showFlag", showFlag);
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getProductBaseInfo(JSONObject reqPRM, JSONObject reqDataJson) {

		String system = reqPRM.getString("system");
		Integer version = reqPRM.getInt("version");

		Integer id = reqDataJson.getInt("id");
		Integer memberId = null;
		if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
			memberId = Integer.valueOf(reqDataJson.getString("memberId"));
		}
		String auditStatus = "0";
		String auditMsg = "您浏览的商品已下架，换个试试哦~";
		String activityType = "";
		//类型 1 首页主题管 2 日常营销入口 3 商品主题馆 4 现金签到 5 砍价免费拿 6 购物车 7 消息 8 商品未上架
		Map<String,Object> adPageMap = customAdPageService.getCustomAdPage("8",null);
		String redictUrl = adPageMap.get("customAdPageUrl").toString();
		Date date = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> preferentialInfoMap = new HashMap<String, String>();
		preferentialInfoMap.put("preferentialInfo", "");
		preferentialInfoMap.put("preferentialType", "");
		List<ProductCustom> pCustoms = productCustomMapper.getProductBaseInfo(id);
		if(CollectionUtils.isNotEmpty(pCustoms)){
			ProductCustom p = pCustoms.get(0);
			String saleType = p.getSaleType();//销售类型1 品牌库 2 单品款
			String artNo = p.getArtNo();
			String brandName = p.getBrandName();
			Integer mchtId = p.getMchtId();
			Integer brandId = p.getBrandId();
			String productName = p.getName();
			String code = p.getCode();
			String status = p.getStatus();
			Integer limitBuy = p.getLimitBuy();
			Integer productTypeId = p.getProductTypeId();
			String isSingleProp = p.getIsSingleProp() == null ? "" : p.getIsSingleProp();//是否单规格 0 否 1 是
			Integer freightTemplateId = p.getFreightTemplateId();
			BigDecimal tagPrice = new BigDecimal("0");
			BigDecimal salePrice = new BigDecimal("0");//醒购价
			BigDecimal mallPrice = new BigDecimal("0");//商城价
			BigDecimal saleOrMallPrice = new BigDecimal("0");//醒购价或是商城价
			BigDecimal activityPrice = new BigDecimal("0");//活动价格
			String propValId = "";
			Integer stockSum = 0;
			Integer lockedAmount = 0;
			String itemPic = "";
			String activityAreaId = "";
			String signPic = "";//入口彩标图
			String productWkPic = "";//商品彩标图
			String priceWkPic = "";//价格彩标图
			String priceFontColor = "";//价格字体颜色
			Integer productWkLinkId = null;//商品彩标链接id（会场id）
			String productWkPosition = "";//商品彩标位置1 左上 2 右上 3 右下 4 左下
			ActivityCustom activityCustom = null;
			SingleProductActivity singleProductActivity = null;
			boolean hasNotStartActivity = false;//是否是未开始的活动
			long activityBeginTime = 0;
			long activityEndTime = 0;
			String isWaitermark = "";
			Boolean isSingleActivityWatermark=false;
			String source = "";
			String areaLabel = "";//会场标签
			String areaLabelPic = "";//会场标签图片
			Integer activityId = null;
			ActivityGroup activityGroup = null;
			BigDecimal platformPreferential = new BigDecimal("0");//平台补贴
			double deposit = 0;//定金
			double deductAmount = 0;//抵扣金额
			String preSellAuditStatus = "0"; //预售审核状态
			String isSvip = "0";//0不是vip 1是vip
			BigDecimal svipSalePrice = new BigDecimal("0");
			BigDecimal svipDiscount = p.getSvipDiscount();
			String svipContent = "";
			String shelfLife = p.getShelfLife();
			String cccNo = p.getCccNo();
			int productBuyNum = 0;
			String limitErrorMsg = "";
			String openType = "";
			String spreadAmountStr = "";
			String novaPlanAmountRuleH5 = "";
			String shopwnerEquityStr = "";
			BigDecimal shopwnerEquityAmount = new BigDecimal("0");
			MemberInfo memberInfo = null;
			String isPreSell = "";
			Date preheatTime = null;
			if(memberId!=null){
				memberInfo = memberInfoService.selectByPrimaryKey(memberId);
				openType = novaPlanService.getNewNovaPlanOpenType(memberInfo, memberInfo.getId());
			}
			if(brandId == 0 || brandId == 1182 || brandId == 1633 || StringUtil.isBlank(brandName)){
				brandName = "";
			}else{
				brandName = "品牌："+brandName;
			}
			Map<String,ActivityCustom> activityMap = new HashMap<String,ActivityCustom>();
			if("1".equals(saleType)){
				//判断会场是否正常
				//正常：1会场处于活动中&&会场启用 2 活动通过 3活动商品没有被驳回&&总监审核通过
				//获取商品是否处于活动中\
				Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
				paramsActivityMap.put("productId", id);
				paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
				List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
				if(CollectionUtils.isNotEmpty(activityCustoms)){
					activityCustom = activityCustoms.get(0);
					isPreSell = activityCustom.getIsPreSell();//是否预售:0.否1.是
					preheatTime = activityCustom.getPreheatTime();
					activityMap.put(id.toString().toString(), activityCustom);
					activityBeginTime = activityCustom.getActivityBeginTime().getTime()/1000;
					activityEndTime = activityCustom.getActivityEndTime().getTime()/1000;
					areaLabel = activityCustom.getAreaLabel();
					if(!StringUtil.isEmpty(activityCustom.getAreaLabelPic())){
						areaLabelPic = StringUtil.getPic(activityCustom.getAreaLabelPic(), "");
					}
					source = activityCustom.getSource();
					if(activityCustom.getActivityBeginTime().compareTo(date) <= 0){
						activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
						activityId = activityCustom.getId();
					}else{
						//活动还未开始
						hasNotStartActivity = true;
						//版本兼容
						if((system.equals(Const.ANDROID) && version >= 45) || (system.equals(Const.IOS) && version >= 48)){
							//预售审核状态审核通过
							if(activityCustom.getPreSellAuditStatus() != null && "2".equals(activityCustom.getPreSellAuditStatus())) {
								ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
								activityProductDepositExample.createCriteria().andDelFlagEqualTo("0")
									.andActivityIdEqualTo(activityCustom.getId()).andProductIdEqualTo(id);
								List<ActivityProductDeposit> activityProductDepositList = activityProductDepositMapper.selectByExample(activityProductDepositExample);
								//是否是预售商品
								if(CollectionUtils.isNotEmpty(activityProductDepositList)) {
									deposit = activityProductDepositList.get(0).getDeposit().doubleValue();
									deductAmount = activityProductDepositList.get(0).getDeductAmount().doubleValue();
									preSellAuditStatus = activityCustom.getPreSellAuditStatus();
									activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
									activityId = activityCustom.getId();
								}else {
									activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
								}
							}else {
								activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
							}
						}else {
							activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
						}
					}
				}else{
					activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
				}
			}else{
				//单品活动
				singleProductActivity = singleProductActivityService.findModelByProductId(Integer.valueOf(id));
				if(singleProductActivity != null){
					activityType=singleProductActivity.getType();
					if(singleProductActivity.getPlatformPreferential() != null && activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
						platformPreferential = singleProductActivity.getPlatformPreferential();
					}
					if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
						if("0".equals(singleProductActivity.getStatus())){
							auditStatus = "1";
						}
					}
					isSingleActivityWatermark="1".equals(singleProductActivity.getIsWatermark());
				}else{
					activityType = "99999";//单品活动已结束
				}
			}
			p.setActivityType(activityType);
			limitBuy = getProductLimitBuy(limitBuy, activityType, activityCustom);
			ProductItemExample itemExample = new ProductItemExample();
			itemExample.createCriteria().andProductIdEqualTo(id).andDelFlagEqualTo("0");
			if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
				itemExample.setOrderByClause("mall_price,id desc");
			}else if(activityType.equals("7") || activityType.equals("8")){
				itemExample.setOrderByClause("tag_price desc,id desc");
			}else{
				itemExample.setOrderByClause("sale_price,id desc");
			}
			if(!StringUtil.isBlank(shelfLife)){
				shelfLife = "保质期："+shelfLife;
			}
			if(!StringUtil.isBlank(cccNo)){
				cccNo = "3C认证证书号："+cccNo;
			}
			List<ProductItem> items = productItemService.selectByExample(itemExample);
			if(CollectionUtils.isNotEmpty(items)){
				boolean isMinSalePrice = true;
				for (ProductItem item : items) {
					propValId += item.getPropValId()+",";
					Integer stock = item.getStock()-item.getLockedAmount();
					if(stock > 0 && isMinSalePrice){
						isMinSalePrice = false;
						salePrice = item.getSalePrice();
						mallPrice = item.getMallPrice();
						saleOrMallPrice = salePrice;
						tagPrice = item.getTagPrice();
						itemPic = item.getPic();
						if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
							saleOrMallPrice = mallPrice;
							activityPrice = salePrice;
						}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
							activityPrice = mallPrice;
						}
					}
					stockSum = stockSum+stock;
					lockedAmount = lockedAmount+item.getLockedAmount();
				}
				if(isMinSalePrice){//商品总库存<=0,取最低
					salePrice = items.get(0).getSalePrice();
					mallPrice = items.get(0).getMallPrice();
					saleOrMallPrice = salePrice;
					tagPrice = items.get(0).getTagPrice();
					itemPic = items.get(0).getPic();
					if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
						saleOrMallPrice = mallPrice;
						activityPrice = salePrice;
					}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
						activityPrice = mallPrice;
					}
				}
			}else{
				auditStatus = "1";
			}
			saleOrMallPrice = getSaleOrMallPrice(activityType,version,system,saleOrMallPrice,platformPreferential,reqPRM);

			//判断是否正常商品
			//指定客服软件
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			//店铺
			String shopStatus = mchtInfo.getShopStatus();
			String shopLogo = "";
			String shopName = "";
			Integer shopProductSum = 0;
			boolean isOpenShop = false;
			Map<String,BigDecimal> shopScoreMap = new HashMap<String,BigDecimal>();
			if(mchtInfo.getStatus().equals("1")){
				if(shopStatus.equals("1")){//开通店铺
					isOpenShop = true;
					shopLogo = mchtInfo.getShopLogo();
					if(StringUtil.isBlank(shopLogo)){
						SysParamCfg paramCfg = DataDicUtil.getSysParamCfgModel("APP_MCHT_SHOP_DEFULT_LOGO","");
						if(paramCfg != null && !StringUtil.isBlank(paramCfg.getParamValue())){
							shopLogo = StringUtil.getPic(paramCfg.getParamValue(), "");
						}
					}else{
						shopLogo = StringUtil.getPic(shopLogo, "");
					}
					shopName = mchtInfo.getShopName();
					ProductExample productExample = new ProductExample();
					productExample.createCriteria().andMchtIdEqualTo(mchtId).andStatusEqualTo("1")
					.andAuditStatusEqualTo("2").andDelFlagEqualTo("0").andSaleTypeEqualTo("1");
					shopProductSum = productService.countByExample(productExample);
					shopScoreMap = commentService.getShopAvgScore(id,mchtId);
				}
				if(!shopStatus.equals("1") && activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
					auditStatus = "1";
				}
			}else{
				auditStatus = "1";
			}
			if(auditStatus.equals("1")){
				map.put("auditStatus", auditStatus);
				map.put("auditMsg", auditMsg);
				map.put("redictUrl", redictUrl);
				return map;
			}
			//限购
			if(memberId!=null){
				Map<String, String> invalidPMap = productService.isInvalidProduct(id, activityType, memberId, date, null, null, activityMap, null,"1",1);
				productBuyNum = Integer.valueOf(invalidPMap.get("productBuyNum"));
				String isInvalidProduct = invalidPMap.get("isInvalidProduct");
				if("1".equals(isInvalidProduct) && Const.PRODUCT_ACTIVITY_TYPE_AREA.equals(activityType) && "1".equals(source)){
					limitErrorMsg = "本会场限购"+limitBuy+"件,您购买已达上限";
				}
			}
			if(stockSum <= 0 || !status.equals("1")){
				stockSum = 0;
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
					if(singleProductActivity.getBeginTime().after(date)){
						stockSum = 1;
					}
				}
			}
			//获取商品活动状态
			//返回值说明： 0-闲置 1-报名中 2-待开始 3-预热中 4-活动中 5-活动暂停 6-未提审 7-未通过
			String productActivityStatus = productService.getProductactivityStatus(id);
			if(!activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
				if(!productActivityStatus.equals("3")
						&& !productActivityStatus.equals("4")
						&& !productActivityStatus.equals("5")){
					stockSum = 0;
				}
			}
			//新星计划分润余额
			if("1".equals(openType) || "3".equals(openType)){
				List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg("APP_DISTRIBUTION_RATE");
				Double spreadAmountRate = 0.00;
				if(CollectionUtils.isNotEmpty(cfgs)){
					spreadAmountRate = Double.valueOf(cfgs.get(0).getParamValue());
				}
				BigDecimal popFeeRate = new BigDecimal("0");
				try {
					//预防没有技术服务费，导致抛出异常
					Map<String, Object> popFeeRateParamsMap = new HashMap<>();
					popFeeRateParamsMap.put("productId", id);
					popFeeRateParamsMap.put("activityId", activityId);
					popFeeRateParamsMap.put("mchtId", mchtId);
					popFeeRateParamsMap.put("brandId", brandId);
					popFeeRateParamsMap.put("productType1Id", p.getProductType1Id());
					popFeeRateParamsMap.put("productType2Id", p.getProductType2Id());
					popFeeRateParamsMap.put("productType3Id", p.getProductTypeId());
					popFeeRate = mchtProductBrandService.getPopFeeRate(popFeeRateParamsMap);
				} catch (Exception e) {
					popFeeRate = new BigDecimal("0");
				}
				BigDecimal spreadAmount = saleOrMallPrice.multiply(popFeeRate).multiply(new BigDecimal(spreadAmountRate+"")).setScale(2, BigDecimal.ROUND_DOWN);
				if((Const.ANDROID.equals(system) && version >= 57) || (Const.IOS.equals(system) && version >= 58)){
					spreadAmountStr = spreadAmount.stripTrailingZeros().toPlainString();
				}else{
					spreadAmountStr = "预估赚"+spreadAmount.stripTrailingZeros().toPlainString()+"元";
				}
				novaPlanAmountRuleH5 = commonService.buildH5Page("1");
				if("3".equals(openType)){
					if(spreadAmount.compareTo(new BigDecimal("0")) > 0 ){
						shopwnerEquityAmount = saleOrMallPrice.subtract(spreadAmount).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros();
						shopwnerEquityStr = "（升级店长约省"+spreadAmount.setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString()+"元）";
					}
				}
			}
			//商品信息
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("shopwnerEquityAmount", shopwnerEquityAmount);
			dataMap.put("shopwnerEquityStr", shopwnerEquityStr);
			dataMap.put("spreadAmountStr", spreadAmountStr);
			dataMap.put("novaPlanAmountRuleH5", novaPlanAmountRuleH5);
			dataMap.put("limitErrorMsg", limitErrorMsg);
			dataMap.put("productBuyNum", productBuyNum);
			dataMap.put("limitBuy", limitBuy);
			dataMap.put("id", id);
			dataMap.put("name", productName);
			dataMap.put("productTypeId", productTypeId);
			dataMap.put("mchtId", mchtId);
			dataMap.put("stock", stockSum);
			dataMap.put("lockedAmount", lockedAmount);
			dataMap.put("tagPrice", tagPrice);
			dataMap.put("salePrice", saleOrMallPrice);
			dataMap.put("activityPrice", activityPrice);
			dataMap.put("hasNotStartActivity", hasNotStartActivity);
			dataMap.put("discount", 0);
			dataMap.put("skuPic", StringUtil.getPic(itemPic, ""));
			dataMap.put("skuPic_S", StringUtil.getPic(itemPic, "S"));
			dataMap.put("isSingleProp", isSingleProp);
			dataMap.put("preSellAuditStatus", preSellAuditStatus);
			dataMap.put("deposit", deposit);
			dataMap.put("deductAmount", deductAmount);
			DecimalFormat df = new DecimalFormat();
			df.applyPattern("#.##");
			dataMap.put("arrivalPrice", df.format(saleOrMallPrice.subtract(new BigDecimal(deductAmount)).add(new BigDecimal(deposit))));
			//封装规格详情集合
			List<Map<String,Object>> standardMapList = new ArrayList<Map<String,Object>>();
			if(!isSingleProp.equals("1")){
				standardMapList = productItemService.getProductStandardList(propValId,id,null);
			}
			//封装产品参数
			Map<String,Object> productParamsMap = getProductParams(p);
			//封装服务说明
			List<String> serviceList = new ArrayList<String>();
			List<Map<String, Object>> productServiceList = new ArrayList<Map<String, Object>>();
			String serviceDec = "";
			//公告牌
			String bulletinBoardPic = "";
			if (!activityType.equals("7") && !activityType.equals("8")) {
				Map<String, Object> productServiceMap = getProductService(p,id,null);
				serviceDec = productServiceMap.get("serviceDec").toString();
				productServiceList =  (List<Map<String, Object>>) productServiceMap.get("productServiceList");
				serviceList = (List<String>) productServiceMap.get("serviceList");//已废弃，暂时保留旧版本使用
				// 优先显示商家运费公告，没有就发货超时设置公告，没有就显示技术上传公告
				if (freightTemplateId != null) {
					FreightTemplate freightTemplate = freightTemplateService.findModelById(freightTemplateId);
					if (freightTemplate != null) {
						bulletinBoardPic = StringUtil.getPic(freightTemplate.getProductNoticePic(), "");
					}
				}
				if (StringUtil.isBlank(bulletinBoardPic)) {
					DeliveryOvertimeCnfExample overtimeCnfExample = new DeliveryOvertimeCnfExample();
					overtimeCnfExample.createCriteria().andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThanOrEqualTo(date).andDelFlagEqualTo("0");
					List<DeliveryOvertimeCnf> overtimeCnfs = deliveryOvertimeCnfService.selectByExample(overtimeCnfExample);
					if (CollectionUtils.isNotEmpty(overtimeCnfs)) {
						bulletinBoardPic = StringUtil.getPic(overtimeCnfs.get(0).getProductNoticePic(), "");
					}

				}
				if (StringUtil.isBlank(bulletinBoardPic)) {
					List<SysParamCfg> bulletinBoardPics = DataDicUtil.getSysParamCfg("APP_PRODUCT_BULEETIN_BOARD_PIC");
					if (CollectionUtils.isNotEmpty(bulletinBoardPics)) {
						bulletinBoardPic = StringUtil.getPic(bulletinBoardPics.get(0).getParamValue(), "");
					}
				}
			}

				//公告图，由于ios前端图片高度写死，所以做ios兼容，将公告图放详情里面
				if(system.equals(Const.IOS)&& version <= 51){
					bulletinBoardPic = "";
				}

			//封装优惠券Map
			Map<String,Object> couponMap = new HashMap<String,Object>();
			if(activityCustom != null && !hasNotStartActivity && activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
				isWaitermark = activityCustom.getIsWatermark();
				activityBeginTime = activityCustom.getActivityBeginTime().getTime()/1000;
				activityEndTime = activityCustom.getActivityEndTime().getTime()/1000;
				activityAreaId = activityCustom.getActivityAreaId().toString();
				Integer activityGroupId = null;
				//彩印图标
				if(source.equals("1")){
					activityGroupId = activityCustom.getAreaGroupId();
				}else if(source.equals("2")){
					activityGroupId = activityCustom.getActivityGroupId();
				}
				if(activityGroupId != null){
					activityGroup = activityGroupService.getActivityGroupModelByGroupId(activityGroupId);
				}
				List<Integer> couponIds = new ArrayList<Integer>();
				String preferentialType = activityCustom.getPreferentialType() == null ? "" : activityCustom.getPreferentialType();
				if(preferentialType.equals("1")){
					String preferentialIdGroup = activityCustom.getPreferentialIdGroup();
					if(!StringUtil.isBlank(preferentialIdGroup)){
						String[] preferentialIdGroups = preferentialIdGroup.split(",");
						for (String str : preferentialIdGroups) {
							couponIds.add(Integer.valueOf(str));
						}
					}
					if(CollectionUtils.isNotEmpty(couponIds)){
						CouponExample couponExample = new CouponExample();
						couponExample.createCriteria().andActivityAreaIdEqualTo(Integer.valueOf(activityAreaId))
						.andDelFlagEqualTo("0").andStatusEqualTo("1").andIdIn(couponIds)
						.andRecBeginDateLessThanOrEqualTo(date).andRecEndDateGreaterThanOrEqualTo(date)
						.andRecTypeNotEqualTo("3");
						couponExample.setOrderByClause("money desc");
						List<Coupon> couponList = couponService.selectByExample(couponExample);
						if(couponList != null && couponList.size() > 0){
							couponMap = new HashMap<String,Object>();
							Coupon coupon = couponList.get(0);
							couponMap.put("couponId", coupon.getId());
							couponMap.put("couponName", coupon.getName());
							couponMap.put("recType", coupon.getRecType());
							couponMap.put("needIntegral", coupon.getNeedIntegral() == null ? 0 : coupon.getNeedIntegral());
						}
					}
				}
				String preType = activityCustom.getPreferentialType();
				String preIdGroup = activityCustom.getPreferentialIdGroup();
				preferentialInfoMap = memberCouponService.getPreferentialInfo(preType,preIdGroup,date);
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
				ShopPreferentialInfoExample preferentialInfoExample = new ShopPreferentialInfoExample();
				preferentialInfoExample.createCriteria().andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThan(date)
				.andDelFlagEqualTo("0").andStatusEqualTo("1").andMchtIdEqualTo(mchtId);
				preferentialInfoExample.setOrderByClause("id desc");
				preferentialInfoExample.setLimitStart(0);
				preferentialInfoExample.setLimitSize(1);
				List<ShopPreferentialInfo> preferentialInfos = shopPreferentialInfoService.selectByExample(preferentialInfoExample);
				if(CollectionUtils.isNotEmpty(preferentialInfos)){
					String preType = preferentialInfos.get(0).getType();
					String preIdGroup = "";
					if("1".equals(preType)){
						preIdGroup = preferentialInfos.get(0).getCouponIdGroup();
					}else{
						preIdGroup = preferentialInfos.get(0).getPreferentialId() == null ? "" : preferentialInfos.get(0).getPreferentialId().toString();
					}
					preferentialInfoMap = memberCouponService.getPreferentialInfo(preType,preIdGroup,date);
				}
			}

			//封装主图
			List<Map<String,Object>> picMapList = productPicService.getProductDefaultPic(id,activityAreaId,isWaitermark,isSingleActivityWatermark);
			//开售提醒是否点亮
			boolean isExist = true;
			String memberStatus = "A";
			if(memberInfo == null){
				//未登入用户提醒都存在
				isExist = false;
			}else{
				//判断是否添加开售提醒 true 存在  false 不存在
				memberInfo = memberInfoService.selectByPrimaryKey(memberId);
				memberStatus = memberInfo.getStatus();
				isExist = DataDicUtil.getRemindExists(id,memberId,"2");
				isSvip = memberInfoService.getIsSvip(memberInfo, memberId);
			}
			//开始与结束时间
			String singleProductActivityId = "";
			long currentTime = new Date().getTime()/1000;
			if(!activityType.equals("99999")){
				if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
					if(StringUtil.isBlank(activityAreaId) && !"2".equals(preSellAuditStatus)){
						//时间找不对，直接设置为商品结束
						activityBeginTime = currentTime-2;
						activityEndTime = currentTime-1;
					}
				}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION)
						|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
						|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING)
						|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT)){
					if(singleProductActivity != null){
						singleProductActivityId = singleProductActivity.getId().toString();
						activityBeginTime = singleProductActivity.getBeginTime().getTime()/1000;
						activityEndTime = singleProductActivity.getEndTime().getTime()/1000;
					}else{
						//时间找不对，直接设置为商品结束
						activityBeginTime = currentTime-2;
						activityEndTime = currentTime-1;
					}
				}else if(activityType.equals("7") || activityType.equals("8")){
					//砍价，邀请免费拿商品，原生的app没开发
					activityBeginTime = currentTime-2;
					activityEndTime = currentTime-1;
				}else{
					//走到这边的就属于日常销售
					//1如果活动未开始的，前端要展示距离开始时间多久
					//2如果活动已结束的，不展示时间
				}
			}else{
				//时间找不对，直接设置为商品结束
				activityBeginTime = currentTime-2;
				activityEndTime = currentTime-1;
			}

			//封装品牌推荐
			String brandRecommend = "";
			List<Map<String,Object>> brandRecommendMapList = new ArrayList<Map<String,Object>>();
			String activityRemainingTime = "";
			if(activityCustom != null && activityCustom.getSource().equals("1") &&
					(activityCustom.getType().equals("1")|| activityCustom.getType().equals("2"))){
				brandRecommend = "活动推荐";
				Map<String,Object> brandRecommendMap = new HashMap<String,Object>();
				activityRemainingTime = DateUtil.getActivityRemainingTime(activityCustom.getActivityEndTime());
				brandRecommendMap.put("activityId", activityCustom.getId());
				brandRecommendMap.put("activityAreaId", activityCustom.getActivityAreaId());
				brandRecommendMap.put("activityName", activityCustom.getName());
				brandRecommendMap.put("benefitPoint", activityCustom.getBenefitPoint());
				brandRecommendMap.put("entryPic", StringUtil.getPic(activityCustom.getEntryPic(), ""));
				brandRecommendMap.put("activityTime", activityRemainingTime);
				brandRecommendMap.put("source", activityCustom.getSource());
				brandRecommendMapList.add(brandRecommendMap);
			}else{
				brandRecommend = "店铺活动推荐";
				Map<String,Object> brandParams = new HashMap<String,Object>();
				brandParams.put("mchtId", mchtId);
				brandParams.put("brandId", brandId);
				if(!activityType.equals("7") && ! activityType.equals("8")){
					brandParams.put("limit", 1);
				}
				brandRecommendMapList = activityService.getBrandRecommendActivityByMchtId(brandParams);
			}
			//积分优惠不做处理
			String deductibleAmount = "";
			//活动类型
			String activityTypeContent = "";
			String jumpFlow = "0";//0立即抢购->购物车->结算页面  1立即抢购->结算页面
			if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
				activityTypeContent = "品牌特卖";
				if("1".equals(source) && !StringUtil.isBlank(areaLabel)){
					activityTypeContent = areaLabel;
				}
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY)){
				activityTypeContent = "新人专享";
				jumpFlow = "1";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION)){
				activityTypeContent = "单品爆款";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)){
				activityTypeContent = "限时抢购";
				jumpFlow = "1";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
				activityTypeContent = "新人专享";
				jumpFlow = "1";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)){
				activityTypeContent = "积分商城";
				jumpFlow = "1";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING)){
				activityTypeContent = "清仓特卖";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BARGAIN)){
				activityTypeContent = "砍价";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INVITE_FREE_CHARGE)){
				activityTypeContent = "邀请免费拿";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT)){
				activityTypeContent = "优惠券";
				jumpFlow = "1";
			}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)){
				activityTypeContent = "日常销售";
				if(hasNotStartActivity){//活动处于预热中，显示品牌特卖
					activityTypeContent = "品牌特卖";
				}
			}else{
				activityTypeContent = "活动结束";
			}
			//客服，判断版本，ios
			String customerServiceSoftType = sobotCustomerServiceService.getCustomerServiceSoftType(reqPRM,mchtInfo);
			//商品详情主题馆
			Map<String,Object> adPageMap3 = customAdPageService.getCustomAdPage(Const.PAGE_TYPE_3,null);
			String productUrl = adPageMap3.get("customAdPageUrl").toString();
			//放置彩标图
			if(activityGroup != null){
				signPic = activityGroup.getSignPic();
				productWkPic = activityGroup.getProductWkPic();
				priceWkPic = activityGroup.getPriceWkPic();
				priceFontColor = activityGroup.getPriceFontColor();
				productWkLinkId = activityGroup.getProductWkLinkId();
				productWkPosition = activityGroup.getProductWkPosition();
			}
			//评价数量
			Map<String, Object> commentParamsMap = new HashMap<>();
			commentParamsMap.put("productId", id);
			int commentCount = commentService.getProductTotalCommentCount(commentParamsMap);
			//商品总销售量
			Integer productSaleQuantity = p.getProductSaleQuantity()==null?0:p.getProductSaleQuantity();
			String productSaleQuantityStr = "";
			//好评度
			Integer goodProductScoreCount = p.getGoodProductScoreCount()==null?0:p.getGoodProductScoreCount();
			String goodProductScoreCountStr = "";
			//评价
			List<Map<String, Object>> commentInfoList = new ArrayList<Map<String, Object>>();
			if(commentCount > 0){
				Map<String, Object> commentInfoMap = commentService.getUserProductAllComment(id, "1", 0, 1,new ArrayList<Integer>());
				commentInfoList = (List<Map<String, Object>>) commentInfoMap.get("dataList");
				if(goodProductScoreCount.intValue() == 0) {
					goodProductScoreCountStr = "0%";
				}else {
					goodProductScoreCountStr = new BigDecimal(goodProductScoreCount).divide(new BigDecimal(commentCount), 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(0).toString()+"%";
				}
			}else{
				productSaleQuantityStr = "0件";
				goodProductScoreCountStr = "100%";
			}
			if(productSaleQuantity.intValue() <= 9999) {
				productSaleQuantityStr = productSaleQuantity+"件";
			}else {
				BigDecimal saleQuantity = new BigDecimal(productSaleQuantity/10000);
				productSaleQuantityStr = saleQuantity.setScale(1, BigDecimal.ROUND_DOWN)+"万";
			}
			//版本判断，用来兼容优惠券活动类型，可以直接跳转到结算页面
			if(Const.PRODUCT_ACTIVITY_TYPE_COUPON_PRODUCT.equals(activityType)){
				if((system.equals(Const.ANDROID) && version <= 45) || (system.equals(Const.IOS) && version <= 49)){
					activityType = "1";//1代表新用户专享，该类型可以直接跳转到结算页面
				}
			}
			//会员价格
			if(!"p".equalsIgnoreCase(memberStatus)){
				svipSalePrice = getProductSvipSalePrice(saleOrMallPrice, svipDiscount,activityType);
				if(svipSalePrice.compareTo(new BigDecimal("0")) > 0){
					if("1".equals(isSvip)){
						svipContent = "您可享"+(svipDiscount.multiply(new BigDecimal("10"))).stripTrailingZeros().toPlainString()+"折 约省"+(saleOrMallPrice.subtract(svipSalePrice)).stripTrailingZeros().toPlainString()+"元";
					}else{
						svipContent = "开通后可享"+(svipDiscount.multiply(new BigDecimal("10"))).stripTrailingZeros().toPlainString()+"折 约省"+(saleOrMallPrice.subtract(svipSalePrice)).stripTrailingZeros().toPlainString()+"元";
					}
				}
			}
			//商品券列表展示
			Map<String, Object> productCouponMap = couponService.getProductCouponList(memberId,id);
			map.put("saleType", saleType);
			map.put("productCouponMap", productCouponMap);
			map.put("productVideos", productVideoService.getProductVideos(id, "1"));
			map.put("productServiceList", productServiceList);
			map.put("serviceDec", serviceDec);
			map.put("shelfLife", shelfLife);
			map.put("cccNo", cccNo);
			map.put("svipSalePrice", svipSalePrice.stripTrailingZeros().toPlainString());
			map.put("svipContent", svipContent);
			map.put("isSvip", isSvip);
			map.put("goodProductScore", goodProductScoreCountStr);
			map.put("productSaleQuantity", productSaleQuantityStr);
			map.put("source", source);
			map.put("commentCount", commentCount);
			map.put("commentInfoList", commentInfoList);
			map.put("signPic", StringUtil.getPic(signPic, ""));
			map.put("productWkPic", StringUtil.getPic(productWkPic, ""));
			map.put("priceWkPic", StringUtil.getPic(priceWkPic, ""));
			map.put("priceFontColor", priceFontColor);
			map.put("productWkLinkId", productWkLinkId);
			map.put("jumpType", "0");
			map.put("productWkPosition", productWkPosition);
			map.put("customerServiceSoftType", customerServiceSoftType);
			map.put("userLevel", "0");
			map.put("brandContent", brandName);
			map.put("productArtNo", "货号："+(artNo == null ? "" : artNo));
			map.put("activityTypeContent", activityTypeContent);
			map.put("areaLabelPic", areaLabelPic);
			map.put("dataMap", dataMap);
			map.put("standardMap", standardMapList);
			map.put("picMapList", picMapList);
			map.put("shareMainTitle", productName);
			map.put("productParams", productParamsMap.get("productParams").toString());
			map.put("productParamList", productParamsMap.get("productParamList"));
			map.put("shareTitle", "我在醒购发现了一个不错的商品，赶快来看看吧。");
			map.put("shareUrl", Config.getProperty("mUrl")+"/xgbuy/views/index.html?redirect_url=goods/detail.html?code="+code);
			map.put("serviceList", serviceList);
			map.put("couponMap", couponMap);
			map.put("activityAreaId", activityAreaId);
			map.put("activityId", activityId);
			map.put("currentTime", currentTime);
			map.put("bulletinBoardPic", bulletinBoardPic);
			if("1".equals(isPreSell)){
				map.put("activityType", Const.PRODUCT_ACTIVITY_TYPE_PRE_SELL);//预付会场
				map.put("preheatTime", preheatTime.getTime()/1000);
			}else{
				if("2".equals(preSellAuditStatus)){//品牌特卖的预售
					map.put("activityType", activityType);
					map.put("preheatTime", preheatTime.getTime()/1000);
				}else{
					map.put("activityType", activityType);
					map.put("preheatTime", 0);
				}
			}
			map.put("singleProductActivityId", singleProductActivityId);
			map.put("brandRecommend", brandRecommend);
			map.put("brandRecommendMapList", brandRecommendMapList);
			map.put("deductibleAmount", deductibleAmount);
			map.put("preferentialInfoMap", preferentialInfoMap);
			map.put("shopLogo", shopLogo);
			map.put("shopName", shopName);
			map.put("shopProductSum", shopProductSum);
			map.put("isOpenShop", isOpenShop);
			map.put("activityBeginTime", activityBeginTime);
			map.put("activityEndTime", activityEndTime);
			map.put("isExist", isExist);
			map.put("auditStatus", auditStatus);
			map.put("auditMsg", "");
			map.put("redictUrl", "");
			map.put("productUrl", productUrl);
			map.put("productAvgScore", shopScoreMap.get("productAvgScore"));
			map.put("mchtAvgScore", shopScoreMap.get("mchtAvgScore"));
			map.put("wuliuAvgScore", shopScoreMap.get("wuliuAvgScore"));
			map.put("productCode", p.getCode());
			map.put("jumpFlow", jumpFlow);
			map.put("platformPreferential", platformPreferential);
			map.put("manageSelf", p.getIsManageSelf()); //是否自营
			//STORY #1664
			InputStream stream = ProductService.class.getResourceAsStream("/config.properties");
	        try {
	        	Properties properties = new Properties();
	        	properties.load(stream);
	        	String originalId = properties.getProperty("originalId");
	        	String xcxShareType = properties.getProperty("xcxShareType");
	        	map.put("originalId", originalId);//小程序原始ID
	        	map.put("xcxShareType", xcxShareType);//分享小程序的版本（0-正式，1-开发，2-体验）
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("wxPath", "page/product/details/index?id=" + id + "&memberId=" + (memberId == null ? "" : memberId));//分享小程序页面的具体路径

			//STORY #1889
			ReceivedRedInfo receivedRedInfo = memberCouponService.isReceivedRed(memberId);
			List<String> productRelativeCouponList = Lists.newArrayList();
			List<Coupon> couponList = couponService.findProductRelativeCoupon(mchtId, id); // 1、商品券、店铺券
			if (!CollectionUtil.isEmpty(couponList)) {
				for (Coupon coupon : couponList) {
                    String preferentialType = coupon.getPreferentialType(); //1 固定面额 2 折扣
					if ("1".equals(preferentialType)) {
                        String denomination = coupon.getMoney().stripTrailingZeros().toPlainString();
						if (coupon.getMinimum() != null) {
							productRelativeCouponList.add(StringUtil.buildMsg("满{}减{}", coupon.getMinimum().stripTrailingZeros().toPlainString(), denomination));
						} else {
							productRelativeCouponList.add(StringUtil.buildMsg("{}元无门槛", denomination));
						}
                    } else if ("2".equals(preferentialType)) {
						BigDecimal discount = coupon.getDiscount().multiply(BigDecimal.TEN).stripTrailingZeros();
						if (coupon.getMinimum() != null) {
							productRelativeCouponList.add(StringUtil.buildMsg("满{}元{}折", coupon.getMinimum().stripTrailingZeros(), discount));
						} else if (coupon.getMinicount() != null) {
							productRelativeCouponList.add(StringUtil.buildMsg("满{}件{}折", coupon.getMinicount(), discount));
						}else{
							productRelativeCouponList.add(StringUtil.buildMsg("{}折扣无门槛", discount));
						}
					}

				}
			}
			map.put("showRedReceiveGate", !receivedRedInfo.isReceived()); //是否显示新人礼包领取入口
			map.put("redReceiveContent", "新人礼包"); //新人礼包领取提示内容
			map.put("productRelativeCouponList", productRelativeCouponList); //商品相关优惠券信息列表

		}else{
			map.put("auditStatus", "1");
			map.put("auditMsg", auditMsg);
			map.put("redictUrl", redictUrl);
		}
		return map;
	}

	private void fillWithPreferentialCouponInfo(Map<String, Object> result, String preferentialCouponIds, Integer memberId) {
		if (StringUtil.isBlank(preferentialCouponIds)) {
			result.put("preferentialCouponList", Collections.emptyList());
			return;
		}
		List<CouponSimpleView> preferentialCouponList = Lists.newArrayList();

		List<Integer> couponIds = Lists.newArrayList(StringUtil.stringToIntegers(preferentialCouponIds.split(",")));
		CouponExample couponExample = new CouponExample();
		couponExample.createCriteria().andIdIn(couponIds);
		couponExample.setOrderByClause("money desc");
		List<Coupon> couponList = couponService.selectByExample(couponExample);
		Map<Integer, Integer> recQuantityMap = memberCouponService.countMemberRecQuantity(memberId, couponIds);
		for (Coupon coupon : couponList) {
			CouponSimpleView view = buildCouponSimpleView(coupon, null, false, null);
			Integer recQuantity = recQuantityMap.get(coupon.getId());
			String recLimitType=coupon.getRecLimitType(); // 限领类型 1每人每天限领1张 2每人限领指定数量 3不限4.每人每月限领1张
			int recLimit = 1;
			if (coupon.getRecQuantity() >= coupon.getGrantQuantity()) {
				view.setStatus(3); //领完
			}else{
				if ("2".equals(recLimitType)) {
					recLimit = coupon.getRecEach();
				} else if ("3".equals(recLimitType)) {
					recLimit = 9999;
				}
				if (recQuantity == null || recQuantity < recLimit) {
					view.setStatus(1); //可领
				}else{
					view.setStatus(2); //已领
				}
			}
			preferentialCouponList.add(view);
		}
		result.put("preferentialCouponList", preferentialCouponList);
	}

	private void fillWithCouponInfo(Map<String, Object> result, Integer mchtId, Integer memberId, Integer productId) {
		ReceivedRedInfo receivedRedInfo = memberCouponService.isReceivedRed(memberId);

		List<CouponSimpleView> receiveAbleCouponList = Lists.newArrayList(); //可领取
		List<CouponSimpleView> receivedCouponList = Lists.newArrayList(); //已领取

		// 1、商品券、店铺券
		List<Coupon> couponList = couponService.findProductRelativeCoupon(mchtId, productId);
		List<Integer> couponIdList = Lists.newArrayList();
		if (!CollectionUtil.isEmpty(couponList)) {
			Map<Integer, Integer> recQuantityMap = Collections.emptyMap(); //用户已领取的券数量Map（商品券、店铺券）
			if (memberId != null) {
				List<Integer> couponIds = ListHelper.extractIds(couponList, new ListHelper.IdExtractor<Coupon>() {
					@Override
					public Integer getId(Coupon source) {
						return source.getId();
					}
				});
				recQuantityMap = memberCouponService.countMemberRecQuantity(memberId, couponIds);
			}
			if (!CollectionUtil.isEmpty(couponList)) {
				for (Coupon coupon : couponList) {
					couponIdList.add(coupon.getId());
					Integer recQuantity = recQuantityMap.get(coupon.getId());

					String recLimitType=coupon.getRecLimitType(); // 限领类型 1每人每天限领1张 2每人限领指定数量 3不限4.每人每月限领1张
					int recLimit = 1;
					if ("2".equals(recLimitType)) {
						recLimit = coupon.getRecEach();
					} else if ("3".equals(recLimitType)) {
						recLimit = 9999;
					}
					if (coupon.getRecQuantity() < coupon.getGrantQuantity() && (recQuantity == null || recQuantity < recLimit)) {
						receiveAbleCouponList.add(buildCouponSimpleView(coupon, 1, false, null));
					}
				}
			}
		}
		List<MemberCoupon> memberCouponList = memberCouponService.findMemberActiveCoupon(memberId, couponIdList, null);
		if (!CollectionUtils.isEmpty(memberCouponList)) {
			Map<Integer, Coupon> couponMap = MapHelper.toMap(couponList, new MapHelper.MapKey<Coupon, Integer>() {
				@Override
				public Integer key(Coupon source) {
					return source.getId();
				}
			});
			for (MemberCoupon memberCoupon : memberCouponList) {
				receivedCouponList.add(buildCouponSimpleView(couponMap.get(memberCoupon.getCouponId()), 2, false, memberCoupon)); //已领取
			}
		}

		// 2、已领取的新人券（取面额最高的两张）
		if (memberId != null && receivedRedInfo.isReceived() && receivedRedInfo.getReceivedCouponIds().size() > 0) {
			List<MemberCoupon> newUserMemberCouponList = memberCouponService.findMemberActiveCoupon(memberId, receivedRedInfo.getReceivedCouponIds(), 2);
			if (!CollectionUtils.isEmpty(newUserMemberCouponList)) {
				CouponExample example = new CouponExample();
				example.createCriteria().andIdIn(receivedRedInfo.getReceivedCouponIds()).andDelFlagEqualTo(StateConst.FALSE);
				List<Coupon> newUserCouponList = couponService.selectByExample(example);
				Map<Integer, Coupon> couponMap = MapHelper.toMap(newUserCouponList, new MapHelper.MapKey<Coupon, Integer>() {
					@Override
					public Integer key(Coupon source) {
						return source.getId();
					}
				});
				for (MemberCoupon memberCoupon : newUserMemberCouponList) {
					receivedCouponList.add(buildCouponSimpleView(couponMap.get(memberCoupon.getCouponId()), 2, true, memberCoupon));
				}
			}
		}

		result.put("showRedReceiveGate", !receivedRedInfo.isReceived()); //是否显示新人礼包领取入口
		result.put("redReceiveContent", "新人专享500元大礼包"); //新人礼包领取提示内容
		result.put("receiveAbleCouponList", receiveAbleCouponList); //可领取的券列表
		result.put("receivedCouponList", receivedCouponList); //已领取的券列表
	}

	private CouponSimpleView buildCouponSimpleView(Coupon coupon, Integer status, boolean newUserCoupon, MemberCoupon memberCoupon) {
		CouponSimpleView view = new CouponSimpleView();
		view.setId(coupon.getId());
		view.setMinimum(coupon.getMinimum());
		String expiryDesc = "";
		if (status != null) {
			view.setStatus(status);
			if (1 == status) { //可领取
				if (coupon.getExpiryType().equals("2")) { //相对时间
					expiryDesc = StringUtil.buildMsg("领取后{}天内有效", coupon.getExpiryDays());
				} else { //取绝对时间
					if (coupon.getExpiryBeginDate() != null && coupon.getExpiryEndDate() != null) {
						String begin = DateUtil.getFormatDate(coupon.getExpiryBeginDate(), "yyyy.MM.dd");
						String end = DateUtil.getFormatDate(coupon.getExpiryEndDate(), "yyyy.MM.dd");
						expiryDesc = StringUtil.buildMsg("{}-{}", begin, end);
					}
				}
			} else if (2 == status) { //已领取
				if (memberCoupon != null && memberCoupon.getExpiryBeginDate() != null && memberCoupon.getExpiryEndDate() != null) {
					String begin = DateUtil.getFormatDate(memberCoupon.getExpiryBeginDate(), "yyyy.MM.dd");
					String end = DateUtil.getFormatDate(memberCoupon.getExpiryEndDate(), "yyyy.MM.dd");
					expiryDesc = StringUtil.buildMsg("{}-{}", begin, end);
				}
			}
		}

		view.setExpiryDesc(expiryDesc);
		if (newUserCoupon) {
			view.setCouponTag("新人券");
			view.setLimitDesc("限时抢购、新人专享、积分抵扣等特惠商品不可用，不可与其他平台券叠加");
		} else {
			if ("4".equals(coupon.getCouponType())) {
				view.setCouponTag("商品券");
				view.setLimitDesc("限本商品使用");
			} else if ("1".equals(coupon.getCouponType())) {
				view.setCouponTag("店铺券");
				view.setLimitDesc("限本店铺使用");
			}
		}

		if (coupon.getMinimum() != null && coupon.getMinimum().compareTo(BigDecimal.ZERO) > 0) {
			view.setUseDesc(StringUtil.buildMsg("满{}元可用", coupon.getMinimum().stripTrailingZeros().toPlainString()));
		} else if (coupon.getMinicount() != null && coupon.getMinicount() > 0) {
			view.setUseDesc(StringUtil.buildMsg("满{}件可用", coupon.getMinicount()));
		} else {
			view.setUseDesc("无门槛");
		}
		view.setPreferentialType(coupon.getPreferentialType()); // 1固定面额 2 折扣
		if ("1".equals(coupon.getPreferentialType())) {
			view.setDenomination(coupon.getMoney().stripTrailingZeros());
		} else if ("2".equals(coupon.getPreferentialType())) {
			view.setDiscount(coupon.getDiscount().multiply(BigDecimal.TEN));
		}
		view.setDenomination(coupon.getMoney());
		return view;
	}

	/**
	 * 获取商品限购数量
	 * @param limitBuy 商品限购数量
	 * @param activityType 活动类型
	 * @param activityCustom
	 * @return
	 */
	public Integer getProductLimitBuy(Integer limitBuy, String activityType, ActivityCustom activityCustom) {
		if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
			String purchaseLimits = activityCustom.getPurchaseLimits();
			Integer activityAreaLimitBuy = activityCustom.getPurchaseLimitsQuantity() == null ? 0 : activityCustom.getPurchaseLimitsQuantity();
			String source = activityCustom.getSource();
			if("1".equals(source) && "1".equals(purchaseLimits)){
				if(activityAreaLimitBuy <= 0){

				}else if(activityAreaLimitBuy < (limitBuy == 0 ? 999999 : limitBuy)){
					limitBuy = activityAreaLimitBuy;
				}
			}
		}else{
			//新用户秒杀 限购配置查询
			QueryObject queryObject = new QueryObject();
			queryObject.addQuery("activityType", activityType);
			List<SingleProductActivityCnf> singleProductActivityCnfs = singleProductActivityCnfService.findListQuery(queryObject);
			Integer limitBuyCnf = 0;
			if(CollectionUtils.isNotEmpty(singleProductActivityCnfs)){
				SingleProductActivityCnf singleProductActivityCnf = singleProductActivityCnfs.get(0);
				limitBuyCnf = singleProductActivityCnf.getLimitBuy();
				if(limitBuyCnf <= 0){

				}else if(limitBuyCnf < (limitBuy == 0 ? 999999 : limitBuy)){
					limitBuy = limitBuyCnf;
				}
			}
		}
		return limitBuy;
	}

	public Map<String, Object> getProductParams(ProductCustom p) {
		Map<String, Object> map = new HashMap<String, Object>();
		String storageConditions = p.getStorageConditions();
		String placeOfOrigin = p.getPlaceOfOrigin();
		String producerInformation = p.getProducerInformation();
		String approvalNumber = p.getApprovalNumber();
		String artNo = p.getArtNo();
		String suitSex = p.getSuitSex();
		String suitGroup = p.getSuitGroup();
		String season = p.getSeason();
		String brandName = p.getBrandName();
		String productDescStr = p.getProductDesc() == null ? "" : p.getProductDesc();
		String productType3Name = p.getProductTypeName();
		String productType2Name = p.getProductType2Name();
		String productType1Name = p.getProductType1Name();
		String ptName = productType1Name+">"+productType2Name+">"+productType3Name;
		String shelfLife = p.getShelfLife();
		String cccNo = p.getCccNo();
		String activityType = p.getActivityType();
		Integer brandId = p.getBrandId();
		String yearOfListing = p.getYearOfListing();
		String sexName = "";
		if(brandId == 0 || brandId == 1182 || brandId == 1633){
			brandName = "";
		}
		String activityTypeName = "";
		if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL) ||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY)
				 || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL) ||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
			activityTypeName = "抢购商品";
		}
		StringBuffer shareTitle = new StringBuffer();
		shareTitle.append(productDescStr.replace("&&&", ",")+",");
		StringBuffer productParams = new StringBuffer();
		productParams.append("<HTML>");
		productParams.append("<style>");
		productParams.append(".product-params {");
		productParams.append("	line-height: 46px;");
		productParams.append("	padding: 0 1px;");
		productParams.append("	font-size: 14px;");
		productParams.append("	font-family: SimHei;");
		productParams.append("}");
		productParams.append(".product-params li {");
		productParams.append("list-style: none;");
		productParams.append("	border-top: 1px solid #eee;");
		productParams.append("}");
		productParams.append(".product-params li:first-child {");
		productParams.append("	border: none;");
		productParams.append("}");
		productParams.append("</style>");
		productParams.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
		productParams.append("<BODY>");
		productParams.append("<ul class='product-params'>");
		if(!StringUtil.isBlank(activityTypeName)){
			productParams.append("<li>");
			productParams.append("类型:");
			productParams.append(activityTypeName);
			productParams.append("</li>");
		}
		if(!StringUtil.isBlank(ptName)){
			productParams.append("<li>");
			productParams.append("品类:");
			productParams.append(ptName);
			productParams.append("</li>");
		}
		if(!StringUtil.isBlank(brandName)){
			productParams.append("<li>");
			productParams.append("品牌:");
			productParams.append(brandName);
			productParams.append("</li>");
			shareTitle.append("品牌："+brandName+",");
		}
		if(!StringUtil.isBlank(yearOfListing)){
			productParams.append("<li>");
			productParams.append("上市年份:");
			productParams.append(yearOfListing);
			productParams.append("</li>");
		}
		if(!StringUtil.isBlank(artNo)){
			productParams.append("<li>");
			productParams.append("商品货号:");
			productParams.append(artNo);
			productParams.append("</li>");
		}
		if(!StringUtil.isBlank(suitSex)){
			SysStatus sysStatus = DataDicUtil.getStatus("BU_PRODUCT", "SUIT_SEX", suitSex);
			if(sysStatus != null){
				sexName = sysStatus.getStatusDesc();
				productParams.append("<li>");
				productParams.append(sysStatus.getRemark()+":");
				productParams.append(sexName);
				productParams.append("</li>");
				shareTitle.append("适合性别："+sexName+",");
			}
		}
		if(!StringUtil.isBlank(suitGroup)){
			String suitGroupStr = "";
			productParams.append("<li>");
			productParams.append("适合人群:");
			if(suitGroup.equals("001")){
				suitGroupStr = "老人";
			}else if(suitGroup.equals("010")){
				suitGroupStr = "儿童";
			}else if(suitGroup.equals("011")){
				suitGroupStr = "儿童,老人";
			}else if(suitGroup.equals("100")){
				suitGroupStr = "青年";
			}else if(suitGroup.equals("101")){
				suitGroupStr = "青年,老人";
			}else if(suitGroup.equals("110")){
				suitGroupStr = "青年,儿童";
			}else if(suitGroup.equals("111")){
				suitGroupStr = "青年,儿童,老人";
			}
			productParams.append(suitGroupStr);
			productParams.append("</li>");
			shareTitle.append("适合人群："+suitGroupStr+",");
		}
		if(!StringUtil.isBlank(season)){
			SysStatus sysStatus = DataDicUtil.getStatus("BU_PRODUCT", "SEASON", season);
			if(sysStatus != null){
				productParams.append("<li>");
				productParams.append(sysStatus.getRemark()+":");
				productParams.append(sysStatus.getStatusDesc());
				productParams.append("</li>");
				shareTitle.append("季节："+sysStatus.getStatusDesc());
			}
		}
		if(!StringUtil.isBlank(shelfLife)){
			productParams.append("<li>");
			productParams.append("保质期:");
			productParams.append(shelfLife);
			productParams.append("</li>");
		}
		if(!StringUtil.isBlank(cccNo)){
			productParams.append("<li>");
			productParams.append("3C认证证书号:");
			productParams.append(cccNo);
			productParams.append("</li>");
		}
		Integer productTypeId = 0;
		SysParamCfgExample e = new SysParamCfgExample();
		e.createCriteria().andParamCodeEqualTo("APP_HOME_TOP_CATALOG").andParamNameLike("食品");
		List<SysParamCfg> sysParamCfgList = sysParamCfgService.selectByExample(e);
		if(sysParamCfgList!=null && sysParamCfgList.size()>0){
			productTypeId = Integer.parseInt(sysParamCfgList.get(0).getParamValue());
		}
		//当类目为“食品超市”类目下
		if(p.getProductType1Id().equals(productTypeId)){
			if(!StringUtil.isBlank(storageConditions)){
				productParams.append("<li>");
				productParams.append("贮存条件:");
				productParams.append(storageConditions);
				productParams.append("</li>");
			}
			if(!StringUtil.isBlank(placeOfOrigin)){
				productParams.append("<li>");
				productParams.append("产地：");
				productParams.append(placeOfOrigin);
				productParams.append("</li>");
			}
			if(!StringUtil.isBlank(producerInformation)){
				productParams.append("<li>");
				productParams.append("生产者信息：");
				productParams.append(producerInformation);
				productParams.append("</li>");
			}
			if(!StringUtil.isBlank(approvalNumber)){
				productParams.append("<li>");
				productParams.append("批准文号：");
				productParams.append(approvalNumber);
				productParams.append("</li>");
			}
		}
		if(!StringUtil.isBlank(productDescStr)){
			String[] productDescs = productDescStr.split("&&&");
			for (String productDesc : productDescs) {
				productParams.append("<li>");
				productParams.append(productDesc);
				productParams.append("</li>");
			}
		}
		productParams.append("</ul>");
		productParams.append("</BODY>");
		productParams.append("</HTML>");

		List<Map<String, String>> productParamList = new ArrayList<>();
		Map<String, String> productParamMap = new HashMap<>();
		productParamMap.put("title", "商品ID：");
		productParamMap.put("content", p.getCode());
		productParamList.add(productParamMap);
		productParamMap = new HashMap<>();
		productParamMap.put("title", "品类：");
		productParamMap.put("content", ptName);
		productParamList.add(productParamMap);
		if(!StringUtil.isBlank(brandName)){
			productParamMap = new HashMap<>();
			productParamMap.put("title", "品牌：");
			productParamMap.put("content", brandName);
			productParamList.add(productParamMap);
		}
		productParamMap = new HashMap<>();
		productParamMap.put("title", "商品货号：");
		productParamMap.put("content", artNo);
		productParamList.add(productParamMap);
		if(!StringUtil.isBlank(sexName)){
			productParamMap = new HashMap<>();
			productParamMap.put("title", "适用性别：");
			productParamMap.put("content", sexName);
			productParamList.add(productParamMap);
		}
		//当类目为“食品超市”类目下
		if(p.getProductType1Id().equals(productTypeId)){
			if(!StringUtil.isBlank(shelfLife)){
				productParamMap = new HashMap<>();
				productParamMap.put("title", "保质期:");
				productParamMap.put("content", shelfLife);
				productParamList.add(productParamMap);
			}
			if(!StringUtil.isBlank(storageConditions)){
				productParamMap = new HashMap<>();
				productParamMap.put("title", "贮存条件：");
				productParamMap.put("content", storageConditions);
				productParamList.add(productParamMap);
			}
			if(!StringUtil.isBlank(placeOfOrigin)){
				productParamMap = new HashMap<>();
				productParamMap.put("title", "产地：");
				productParamMap.put("content", placeOfOrigin);
				productParamList.add(productParamMap);
			}
			if(!StringUtil.isBlank(approvalNumber)){
				productParamMap = new HashMap<>();
				productParamMap.put("title", "批准文号：");
				productParamMap.put("content", approvalNumber);
				productParamList.add(productParamMap);
			}
			SysParamCfgExample example = new SysParamCfgExample();
			example.createCriteria().andParamCodeEqualTo("PRODUCT_TYPE2_HEALTH_IDS");
			List<SysParamCfg> sysParamCfgs = sysParamCfgService.selectByExample(example);
			List<Integer> productType2IdList = new ArrayList<Integer>();
			if(sysParamCfgs!=null && sysParamCfgs.size()>0){
				String productType2Ids = sysParamCfgs.get(0).getParamValue();
				String[] productType2IdsArray = productType2Ids.split(",");
				for(String productType2Id:productType2IdsArray){
					if(!StringUtil.isEmpty(productType2Id)){
						productType2IdList.add(Integer.parseInt(productType2Id));
					}
				}
			}
			if(productType2IdList.contains(p.getProductType2Id())){
				productParamMap = new HashMap<>();
				productParamMap.put("title", "温馨提示：");
				productParamMap.put("content", "本产品不能代替药物");
				productParamList.add(productParamMap);
			}
		}

		map.put("productParams", productParams.toString());
		map.put("shareTitle", shareTitle.toString());
		map.put("productParamList", productParamList);
		return map;
	}
	/**
	 *
	 * @param productId
	 * @param activityType
	 * @param memberId
	 * @param date
	 * @param pMap 用于储存每个商品的数量的总数量
	 * @param pTypeMap 用于储存每个单品活动的商品总数量
	 * @param activityMap
	 * @param areaMap 用于储存每个会场活动的商品总数量
	 * @param reqType 1商品详情 2结算页面 3提交订单 4添加购物车 5编辑购物车
	 * @return
	 */
	public Map<String, String> isInvalidProduct(Integer productId, String activityType, Integer memberId, Date date, Map<String, Integer> pMap, Map<String, Integer> pTypeMap, Map<String, ActivityCustom> activityMap, Map<String, Integer> areaMap,String reqType,Integer shopCarQuantity) {
		Map<String, String> map = new HashMap<String, String>();
		String isInvalidProduct = "0";
		String limitBuyNum = "";
		String invalidReason = "";
		String limitErrorMsg = "";
		Integer productBuyNum = 0;
		Map<String, Object> params = new HashMap<String, Object>();
		Product product = selectByPrimaryKey(productId);
		Integer productLimitBuy = product.getLimitBuy();
		ActivityCustom activityCustom = activityMap.get(productId.toString());
		Integer activityAreaId = null;
		Integer limitBuy = getProductLimitBuy(productLimitBuy, activityType, activityMap.get(productId.toString()));
		if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA)){
			if("1".equals(activityCustom.getSource())){
				activityAreaId = activityCustom.getActivityAreaId();
				params.put("activityAreaId", activityAreaId);
				params.put("productId", productId);
				params.put("memberId", memberId);
				//商品的总购买数量
				ProductCustom productCustom = productService.getUserBuyCount(params,activityType);
				productBuyNum = productCustom.getProductBuyNum();
				String purchaseLimits = activityCustom.getPurchaseLimits();
				Integer purchaseLimitsQuantity = activityCustom.getPurchaseLimitsQuantity();
				if("1".equals(purchaseLimits) && purchaseLimitsQuantity > 0){//判断会场的限购数量
					if(areaMap != null){
						shopCarQuantity = areaMap.get(activityAreaId.toString());
					}
					if(purchaseLimitsQuantity-productBuyNum < shopCarQuantity){
						isInvalidProduct = "1";
						invalidReason = "您输入的商品数量超过了该会场的限购数量";
						limitBuyNum = "该会场每人限购"+purchaseLimitsQuantity+"件";
						limitErrorMsg = "该会场每人限购"+purchaseLimitsQuantity+"件";
					}
				}
			}
		}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEW_ENJOY)||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION)
				||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)
				||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_INTEGRAL_MALL)||activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING)){
			////单品活动限购控制
			SingleProductActivityCnfExample singleProductActivityCnfExample=new SingleProductActivityCnfExample();
			singleProductActivityCnfExample.createCriteria().andDelFlagEqualTo("0").andActivityTypeEqualTo(activityType);
			List<SingleProductActivityCnf> singleProductActivityCnfs=singleProductActivityCnfService.selectByExample(singleProductActivityCnfExample);
			if(singleProductActivityCnfs!=null&&singleProductActivityCnfs.size()>0){
				SingleProductActivityCnf singleProductActivityCnf=singleProductActivityCnfs.get(0);
				Integer singleLimitBuy = singleProductActivityCnf.getLimitBuy();
				if(singleLimitBuy!=null&&singleLimitBuy>0){
					//获取用户在此类型活动已下订单（除了取消状态，关闭外的所有订单）的数量
					Map<String, Object> p = new HashMap<String, Object>();
					//查出用户id（包括同一设备号的用户）
					MemberInfo member=memberInfoService.selectByPrimaryKey(memberId);
					List<Integer> memberIds=new ArrayList<Integer>();
					memberIds.add(member.getId());
					String regImei=member.getReqImei();
					if(regImei!=null&&!"00000000-0000-0000-0000-000000000000".equals(regImei)&&!"".equals(regImei)&&!"unknown".equals(regImei)){
						MemberInfoExample memberInfoExample=new MemberInfoExample();
						memberInfoExample.createCriteria().andDelFlagEqualTo("0").andReqImeiEqualTo(regImei);
						List<MemberInfo> memberInfos=memberInfoService.selectByExample(memberInfoExample);
						if(memberInfos!=null&&memberInfos.size()>0){
							for(MemberInfo m:memberInfos){
								memberIds.add(m.getId());
							}
						}
					}

					//收货地址中手机号跟此会员绑定的手机号相同的会员id
					if(!StringUtil.isEmpty(member.getMobile())){
						MemberAddressExample memberAddressExample=new MemberAddressExample();
						memberAddressExample.createCriteria().andDelFlagEqualTo("0").andPhoneEqualTo(member.getMobile());
						List<MemberAddress> memberAddressList=memberAddressService.selectByExample(memberAddressExample);
						for(MemberAddress memberAddress:memberAddressList){
							memberIds.add(memberAddress.getMemberId());
						}
					}


					//查出收货地址中有相同手机号的用户id
					MemberAddressExample memberAddressExample=new MemberAddressExample();
					memberAddressExample.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberId);
					List<MemberAddress> memberAddressList=memberAddressService.selectByExample(memberAddressExample);
					if(memberAddressList!=null&&memberAddressList.size()>0){
						List<String> mobiles=new ArrayList<String>();
						for(MemberAddress memberAddress:memberAddressList){
							mobiles.add(memberAddress.getPhone());
						}

						memberAddressExample=new MemberAddressExample();
						memberAddressExample.createCriteria().andDelFlagEqualTo("0").andPhoneIn(mobiles);
						memberAddressList=memberAddressService.selectByExample(memberAddressExample);
						for(MemberAddress memberAddress:memberAddressList){
							memberIds.add(memberAddress.getMemberId());
						}
					}

					p.put("memberIds", memberIds);
					p.put("activityType", activityType);
					if(singleProductActivityCnf.getLimitTime()!=null&&singleProductActivityCnf.getLimitTime()>0){
				    	Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DATE, -singleProductActivityCnf.getLimitTime());
						p.put("limitDate", calendar.getTime());
					}
					productBuyNum=productService.getUserBuyCountBySingleActivityType(p);
					if(pTypeMap != null){
						shopCarQuantity = pTypeMap.get(activityType);
					}
					if(singleLimitBuy-productBuyNum < shopCarQuantity){
						isInvalidProduct = "1";
						invalidReason = "您输入的商品数量超过了该商品的限购数量";
						limitBuyNum = "该商品每人限购"+singleLimitBuy+"件";
						if(reqType.equals("3")){
							if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_SECKILL)){
								limitErrorMsg="秒杀专区每人限购"+singleLimitBuy+"件";
							}else if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL)){
								limitErrorMsg="新用户秒杀专区每人限购"+singleLimitBuy+"件";
							}else{
								limitErrorMsg="每人限购"+singleLimitBuy+"件";
							}
						}else if(reqType.equals("4")){
							limitErrorMsg = "每人限购"+singleLimitBuy+"件";
							if(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL.equals(activityType)){
								limitErrorMsg = "《新人秒杀专区》每人限购"+singleLimitBuy+"件，不可重复购买哦~";
							}
						}else{
							limitErrorMsg = "每人限购"+singleLimitBuy+"件";
						}
					}
				}
			}
		}
		if(!"1".equals(isInvalidProduct)){
			if(productLimitBuy > 0){
				params.clear();
				params.put("productId", productId);
				params.put("memberId", memberId);
				ProductCustom productCustom = productService.getUserBuyCount(params,activityType);
				productBuyNum = productCustom.getProductBuyNum();
				if(pMap != null){
					shopCarQuantity = pMap.get(productId.toString());
				}
				if(productLimitBuy-productBuyNum < shopCarQuantity){
					isInvalidProduct = "1";
					invalidReason = "您输入的商品数量超过了该商品的限购数量";
					limitBuyNum = "该商品每人限购"+productLimitBuy+"件";
					limitErrorMsg = "该商品每人限购"+productLimitBuy+"件";
				}
			}
		}
		map.put("isInvalidProduct", isInvalidProduct);
		map.put("invalidReason", invalidReason);
		map.put("limitBuyNum", limitBuyNum);
		map.put("limitErrorMsg", limitErrorMsg);
		map.put("productBuyNum", productBuyNum.toString());
		map.put("limitBuy", limitBuy.toString());
		return map;
	}

	public List<ProductCustom> getShopMallProductListDefaultSort(Map<String, Object> paramsMap) {

		return productCustomMapper.getShopMallProductListDefaultSort(paramsMap);
	}

	public List<ProductCustom> getShopMallProductBrandList(Map<String, Object> paramsMap) {

		return productCustomMapper.getShopMallProductBrandList(paramsMap);
	}

	/**
	 * 获取商品的正常运行状态（可以复用）
	 * 商家的合作状态=正常 且 商家的品牌的运营状态 =正常 且 商家的品牌资质状态=通过  且 商品的上架状态 =上架 且 商品的法务审核状态=通过
	 * @return
	 */
	public ProductCustom getNormalOperationProduct(Integer productId) {

		return productCustomMapper.getNormalOperationProduct(productId);
	}

	public List<ProductCustom> getShopMallProductTypeList(Map<String, Object> paramsMap) {

		return productCustomMapper.getShopMallProductTypeList(paramsMap);
	}
	/**
	 * 根据商品skuId获取商品的基础信息（通用）
	 * @param goodsMap
	 * @return
	 */
	public List<ProductCustom> getGoodsBasicsInfo(Map<String, Object> goodsMap) {

		return productCustomMapper.getGoodsBasicsInfo(goodsMap);
	}

	/**用来兼容旧版本星用户秒杀的价格，以后不用可以直接删除整个方法体
	 * @param reqPRM */
	public BigDecimal getSaleOrMallPrice(String activityType, Integer version, String system, BigDecimal saleOrMallPrice, BigDecimal platformPreferential, JSONObject reqPRM) {
		if(StringUtil.isBlank(system)){
			system = reqPRM.getString("system");
			version = reqPRM.getInt("version");
		}
		if(Const.PRODUCT_ACTIVITY_TYPE_NEWUSERSECKILL.equals(activityType)){
			if((version <= 46 && system.equals(Const.ANDROID)) || (version <= 49 && system.equals(Const.IOS))){
				saleOrMallPrice = saleOrMallPrice.subtract(platformPreferential);
			}
		}
		return saleOrMallPrice;
	}

	public Map<String, Object> getSkuInfo(JSONObject reqPRM, JSONObject reqDataJson) {
		Integer id = null;
		Integer productItemId = null;
		String code = "";
		String pic = "";
		String propValId = "";
		String memberId = "";
		String activityType = "";
		Product p = null;
		List<Map<String,Object>> standardMapList = new ArrayList<Map<String,Object>>();
		if(reqDataJson.containsKey("id") && !StringUtil.isBlank(reqDataJson.getString("id"))){
			id = reqDataJson.getInt("id");
			p = selectByPrimaryKey(id);
		}else if(reqDataJson.containsKey("code") && !StringUtil.isBlank(reqDataJson.getString("code"))){
			code = reqDataJson.getString("code");
			ProductExample productExample = new ProductExample();
			productExample.createCriteria().andCodeEqualTo(code).andDelFlagEqualTo("0");
			List<Product> products = selectByExample(productExample);
			p = products.get(0);
		}
		if(reqDataJson.containsKey("memberId") && !StringUtil.isBlank(reqDataJson.getString("memberId"))){
			memberId = reqDataJson.getString("memberId");
		}
		if(reqDataJson.containsKey("productItemId") && !StringUtil.isBlank(reqDataJson.getString("productItemId"))){
			productItemId = reqDataJson.getInt("productItemId");
		}
		if(p == null ){
			throw new ArgException("商品不能为空");
		}
		id = p.getId();
		String isSingleProp = p.getIsSingleProp();
		ProductPicExample productPicExample = new ProductPicExample();
		productPicExample.createCriteria().andProductIdEqualTo(id).andIsDefaultEqualTo("1").andDelFlagEqualTo("0");
		List<ProductPic> productPics = productPicService.selectByExample(productPicExample);
		if(CollectionUtils.isNotEmpty(productPics)){
			pic = productPics.get(0).getPic();
		}
		if(!isSingleProp.equals("1")){
			ProductItemExample productItemExample = new ProductItemExample();
			productItemExample.createCriteria().andProductIdEqualTo(id).andDelFlagEqualTo("0");
			List<ProductItem> items = productItemService.selectByExample(productItemExample);
			if(CollectionUtils.isNotEmpty(items)){
				for (ProductItem productItem : items) {
					propValId += productItem.getPropValId()+",";
				}
				//封装规格详情集合
				standardMapList = productItemService.getProductStandardList(propValId,id,productItemId);
			}
		}
		//用户已购买商品数量
		if("1".equals(p.getSaleType())){
			//判断会场是否正常
			//正常：1会场处于活动中&&会场启用 2 活动通过 3活动商品没有被驳回&&总监审核通过
			//获取商品是否处于活动中
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productId", id);
			paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				ActivityCustom activityCustom = activityCustoms.get(0);
				if(activityCustom.getActivityBeginTime().compareTo(new Date()) <= 0){
					activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
				}else{
					//预售审核状态审核通过
					if(activityCustom.getPreSellAuditStatus() != null && "2".equals(activityCustom.getPreSellAuditStatus())) {
						ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
						activityProductDepositExample.createCriteria().andDelFlagEqualTo("0")
							.andActivityIdEqualTo(activityCustom.getId()).andProductIdEqualTo(id);
						Integer activityProductDepositSize = activityProductDepositMapper.countByExample(activityProductDepositExample);
						//是否是预售商品
						if(activityProductDepositSize > 0) {
							activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
						}else {
							activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
						}
					}else {
						activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
					}
				}
			}else{
				activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
			}
		}else{
			//单品活动
			SingleProductActivity singleProductActivity = singleProductActivityService.findModelByProductId(Integer.valueOf(id));
			if(singleProductActivity != null){
				activityType=singleProductActivity.getType();
			}else{
				activityType = "99999";//单品活动已结束
			}
		}
		int productBuyNum = 0;
		ProductCustom productCustom;
		if(p.getLimitBuy() > 0 && !StringUtil.isBlank(memberId)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("productId", id);
			params.put("memberId", Integer.valueOf(memberId));
			productCustom = productService.getUserBuyCount(params, activityType);
			productBuyNum = productCustom.getProductBuyNum();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", id);
		map.put("pic", StringUtil.getPic(pic, ""));
		map.put("standardMapList", standardMapList);
		map.put("productBuyNum", productBuyNum);
		return map;
	}
	/**
	 * 新版本店铺商品列表2019-01-03
	 */
	public List<ProductCustom> getShopProductList(Map<String, Object> paramsMap) {

		return productCustomMapper.getShopProductList(paramsMap);
	}
	/**
	 * 新版本店铺商品品牌聚合2019-01-03
	 */
	public List<ProductCustom> getShopProductBrandConverge(Integer mchtId) {

		return productCustomMapper.getShopProductBrandConverge(mchtId);
	}
	/**
	 * 新版本店铺商品类目聚合2019-01-03
	 */
	public List<ProductCustom> getShopProductCategoryConverge(Integer mchtId) {

		return productCustomMapper.getShopProductCategoryConverge(mchtId);
	}
	/**
	 * 新版本店铺商品分类聚合019-01-03
	 */
	public List<ProductCustom> getShopProductClassConverge(Integer mchtId) {

		return productCustomMapper.getShopProductClassConverge(mchtId);
	}

	public Product findModelByCode(String code) {
		Product p = null;
		ProductExample productExample = new ProductExample();
		productExample.createCriteria().andCodeEqualTo(code);
		List<Product> products = selectByExample(productExample);
		if(CollectionUtils.isNotEmpty(products)){
			p = products.get(0);
		}
		return p;
	}

	public List<ProductCustom> getWeiTaoProductList(Map<String, Object> paramsMap) {

		return productCustomMapper.getWeiTaoProductList(paramsMap);
	}

	public BigDecimal getProductSvipSalePrice(BigDecimal saleOrMallPrice, BigDecimal svipDiscount,String activityType) {
		BigDecimal zero = new BigDecimal("0");
		BigDecimal svipSalePrice = zero;
		if(activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_AREA) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP)
				|| activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_EXPLOSION) || activityType.equals(Const.PRODUCT_ACTIVITY_TYPE_BROKEN_CODE_CLEARING)){
			if(svipDiscount != null && svipDiscount.compareTo(zero) > 0){
				svipSalePrice = saleOrMallPrice.multiply(svipDiscount).setScale(2,BigDecimal.ROUND_DOWN);
				if(svipSalePrice.compareTo(saleOrMallPrice) == 0){
					svipSalePrice = zero;
				}
			}
		}
		return svipSalePrice;
	}

	public Map<String, Object> getProductActivityType(Product product,Integer id) {
		String activityType = "";
		if(product == null){
			product = selectByPrimaryKey(id);
		}
		BigDecimal zero = new BigDecimal("0");
		BigDecimal salePrice = zero;
		BigDecimal tagPrice = zero;
		String activityId = "";
		if("1".equals(product.getSaleType())){
			//判断会场是否正常
			//正常：1会场处于活动中&&会场启用 2 活动通过 3活动商品没有被驳回&&总监审核通过
			//获取商品是否处于活动中
			Map<String,Object> paramsActivityMap = new HashMap<String,Object>();
			paramsActivityMap.put("productId", id);
			paramsActivityMap.put("type", "1");//这个字段用来获取未开始的活动数据
			List<ActivityCustom> activityCustoms = activityService.getActivityProductInfo(paramsActivityMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				ActivityCustom activityCustom = activityCustoms.get(0);
				if(activityCustom.getActivityBeginTime().compareTo(new Date()) <= 0){
					activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
					activityId = activityCustom.getId().toString();
				}else{
					//预售审核状态审核通过
					if(activityCustom.getPreSellAuditStatus() != null && "2".equals(activityCustom.getPreSellAuditStatus())) {
						ActivityProductDepositExample activityProductDepositExample = new ActivityProductDepositExample();
						activityProductDepositExample.createCriteria().andDelFlagEqualTo("0")
							.andActivityIdEqualTo(activityCustom.getId()).andProductIdEqualTo(id);
						List<ActivityProductDeposit> activityProductDepositList = activityProductDepositMapper.selectByExample(activityProductDepositExample);
						//是否是预售商品
						if(CollectionUtils.isNotEmpty(activityProductDepositList)) {
							activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
							activityId = activityCustom.getId().toString();
						}else {
							activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
						}
					}else {
						activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
					}
				}
			}else{
				activityType = Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP;
			}
		}else{
			//单品活动
			SingleProductActivity singleProductActivity = singleProductActivityService.findModelByProductId(Integer.valueOf(id));
			if(singleProductActivity != null){
				activityType=singleProductActivity.getType();
			}else{
				activityType = "99999";//单品活动已结束
			}
		}
		if (!Const.PRODUCT_ACTIVITY_TYPE_MCHT_SHOP.equals(activityType) && !"99999".equals(activityType)) {
			ProductItem productItem = productItemService.selectByPrimaryKey(product.getMinSalePriceItemId());
			salePrice = productItem.getSalePrice();
			tagPrice = productItem.getTagPrice();
		} else {
			salePrice = product.getMinMallPrice();
			tagPrice = product.getMinTagPrice();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("activityType", activityType);
		map.put("activityId", activityId);
		map.put("salePrice", salePrice);
		map.put("tagPrice", tagPrice);
		return map;
	}

	public Map<String, Object> getSvipProductList(List<Integer> productIdList) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Integer> productIds = new ArrayList<>();
		Map<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("productIdList", productIdList);
		List<ProductCustom> productCustoms = productCustomMapper.getSvipProductList(paramsMap);
		if(CollectionUtils.isNotEmpty(productCustoms)){
			for (ProductCustom p : productCustoms) {
				Map<String, Object> dataMap = new HashMap<>();
				Integer productId = p.getId();
				Map<String, Object> activityTypeMap = getProductActivityType(null, productId);
				BigDecimal salePrice = new BigDecimal(activityTypeMap.get("salePrice").toString());
				BigDecimal svipDiscount = p.getSvipDiscount();
				String productName = p.getName();
				String productPic = StringUtil.getPic(p.getPic(), "");
				BigDecimal svipSalePrice = salePrice.multiply(svipDiscount);
				dataMap.put("salePrice", salePrice.stripTrailingZeros().toPlainString());
				dataMap.put("svipSalePrice", svipSalePrice.stripTrailingZeros().toPlainString());
				dataMap.put("svipDiscount", svipDiscount.multiply(new BigDecimal("10")).stripTrailingZeros().toPlainString());
				dataMap.put("productName", productName);
				dataMap.put("productId", productId);
				dataMap.put("productPic", productPic);
				dataList.add(dataMap);
				productIds.add(productId);
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("dataList", dataList);
		map.put("productIdList", productIds);
		return map;
	}

	public Map<String, Object> getProductService(ProductCustom p, Integer id, List<SysParamCfg> sysParamCfgs) {
		List<Map<String, Object>> productServiceList = new ArrayList<>();
		List<String> serviceList = new ArrayList<>();
		String serviceDec = "";
		boolean isReturn7days = getProductIsReturn7days(p, id);
		if(CollectionUtils.isEmpty(sysParamCfgs)){
			sysParamCfgs = DataDicUtil.getSysParamCfg("APP_SERVICE_DESCRIPTION");
		}
		if (CollectionUtils.isNotEmpty(sysParamCfgs)) {
			for (SysParamCfg sysParamCfg : sysParamCfgs) {
				Map<String, Object> serviceMap = new HashMap<>();
				String servictType = sysParamCfg.getParamDesc();
				if(isReturn7days){
					if("0".equals(servictType)){
						continue;
					}
				}else{
					if("1".equals(servictType)){
						continue;
					}
				}
				serviceMap.put("title", sysParamCfg.getParamName());
				serviceMap.put("content", sysParamCfg.getParamValue());
				productServiceList.add(serviceMap);
				serviceDec += sysParamCfg.getParamName() + ",";
				serviceList.add(sysParamCfg.getParamName());
			}
		}
		if(!StringUtil.isBlank(serviceDec)){
			serviceDec = "服务："+serviceDec.substring(0,serviceDec.length()-1);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("serviceDec", serviceDec);
		map.put("productServiceList", productServiceList);
		map.put("serviceList", serviceList);
		return map;
	}

	public boolean getProductIsReturn7days(Product p, Integer id) {
		boolean b = false;
		if(p == null){
			p = selectByPrimaryKey(id);
		}
		b = productTypeService.getProductTypeIsReturn7days(p);

		return b;
	}

	public List<ProductCustom> getEveryDayProductList(Map<String, Object> paramsMap) {

		return productCustomMapper.getEveryDayProductList(paramsMap);
	}

	public List<ProductCustom> getEveryDayShopProduct(Map<String, Object> productParamsMap) {

		return productCustomMapper.getEveryDayShopProduct(productParamsMap);
	}

	List<ProductCustom> findHotRecommendProduct(Map<String, Object> params){

		return productCustomMapper.findHotRecommendProduct(params);
	};

	public List<ProductCustom> getBaranGroupProducts(Map<String, Object> productParamsMap) {

		return productCustomMapper.getBaranGroupProducts(productParamsMap);
	}

	public List<ProductCustom> getBannerProducts(Integer bannerId) {

		return productCustomMapper.getBannerProducts(bannerId);
	}

	public List<ProductCustom> getCodeBreakingPreferenceProductList(Map<String, Object> paramsMap) {

		return productCustomMapper.getCodeBreakingPreferenceProductList(paramsMap);
	}

	public List<ProductCustom> getProductByModuleItem(Map<String, Object> productParamsMap) {

		return productCustomMapper.getProductByModuleItem(productParamsMap);
	}

	public List<ProductCustom> getProductByParamsMap(Map<String, Object> paramsMap) {
		return productCustomMapper.getProductByParamsMap(paramsMap);
	}

	public List<ProductCustom> getEveryDayShopProducts(Map<String, Object> productParamsMap) {
		return productCustomMapper.getEveryDayShopProducts(productParamsMap);
	}

	public Map<String, Object> getNotLoginRecommendProductListMap(Map<String, Object> paramMap) {
		List<Integer> productType1IdList = productTypeService.findProductType1Ids();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isNotEmpty(productType1IdList)) {
			paramMap.put("productType1IdList", productType1IdList);
			List<ProductCustom> productCustoms = productCustomMapper.getNotLoginRecommendProductList(paramMap);
			if (CollectionUtils.isNotEmpty(productCustoms)) {
				getProductCustomsMap(dataList, productCustoms);
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	public Map<String, Object> getLoginRecommendProductListMap(Map<String, Object> paramMap, List<Integer> productType3IdList) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		if (CollectionUtil.isNotEmpty(productType3IdList)) {
			paramMap.put("productType3IdList", productType3IdList);
			List<ProductCustom> productCustoms = productCustomMapper.getLoginRecommendProductList(paramMap);
			if (CollectionUtils.isNotEmpty(productCustoms)) {
				getProductCustomsMap(dataList, productCustoms);
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	private List<Map<String, Object>> getProductCustomsMap(List<Map<String, Object>> dataList,List<ProductCustom> productCustoms) {
		for (ProductCustom productCustom : productCustoms) {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			Integer productId = productCustom.getId();
			BigDecimal saleOrMallPrice = productCustom.getMallPrice();
			BigDecimal tagPrice = productCustom.getTagPrice();
			String source = productCustom.getSource() == null ? "0" : productCustom.getSource();
			String shopPreferentialInfo = "";
			String activityTypeContent = "";
			String activityType = "";
			String activityId = "";
			Integer brandId = productCustom.getBrandId();
			String brandName = productCustom.getBrandName();
			Integer stockSum = productCustom.getStockSum();
			if(brandId == 0){
				brandName = "";
			}
			if(!StringUtil.isEmpty(productCustom.getActivityInfo())){
				String[] activityInfoArray = productCustom.getActivityInfo().split(",");
				activityId = activityInfoArray[3];
				shopPreferentialInfo = activityInfoArray[4];
				saleOrMallPrice = new BigDecimal(activityInfoArray[0]);
				tagPrice = new BigDecimal(activityInfoArray[2]);
				activityTypeContent = "特卖活动";
				activityType = "0";
			}else{
				String fullCutRule = productCustom.getFullCutRule();
				if(!StringUtil.isEmpty(fullCutRule)){
					String[] fullCutRuleArray = fullCutRule.split("\\|");
					BigDecimal dis = new BigDecimal("0");
					for (int i = 0; i < fullCutRuleArray.length; i++) {
						String[] rsi = fullCutRuleArray[i].split(",");
						BigDecimal min = new BigDecimal(rsi[1]);
						if(min.compareTo(dis) > 0){
							dis = min;
							shopPreferentialInfo += "满"+rsi[0]+"减"+rsi[1]+"";
						}
					}
				}else{
					activityTypeContent = "店铺活动";
				}
				activityType = "101";
			}
			BigDecimal svipDiscount = productCustom.getSvipDiscount();
			BigDecimal svipSalePrice = this.getProductSvipSalePrice(saleOrMallPrice,svipDiscount,activityType);
			//评价数量
			int commentCount = productCustom.getTotalCommentCount();
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
			dataMap.put("mchtId", productCustom.getMchtId());
			dataMap.put("source", source);
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
			dataMap.put("svipSalePrice", svipSalePrice);
			if(productCustom.getCouponAmount()!=null && productCustom.getCouponAmount().compareTo(new BigDecimal(0))>0){
				DecimalFormat df = new DecimalFormat();
				df.applyPattern("#.##");
				dataMap.put("couponAmountInfo", "券"+df.format(productCustom.getCouponAmount())+"元");
			}else{
				dataMap.put("couponAmountInfo", "");
			}
			dataList.add(dataMap);
		}
		return dataList;
	}
}
