package com.jf.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jf.common.base.ArgException;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ProductPropValue;
import com.jf.entity.ProductType;
import com.jf.entity.ProductTypeExample;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysStatus;
import com.jf.service.ActivityService;
import com.jf.service.ProductBrandService;
import com.jf.service.ProductPropValueService;
import com.jf.service.ProductTypeService;
import com.jf.service.ShoppingMallService;
import com.jf.service.SingleProductActivityService;
import com.jf.service.SysParamCfgService;

import net.sf.json.JSONObject;

/**
  * 商品筛选 
  * @author  chenwf: 
  * @date 创建时间：2017年10月10日 上午10:46:24 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Controller
public class ScreenController extends BaseController{
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private ActivityService activityService;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private ProductPropValueService productPropValueService;
	@Resource
	private SysParamCfgService sysParamCfgService;
	@Resource
	private ProductBrandService productBrandService;
	@Resource
	private ShoppingMallService shoppingMallService;
	
	/**
	 * 
	 * 方法描述 ：获取二级品类下商品的尺码
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月18日 下午5:09:11 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getTwoCategoryProductSize")
	@ResponseBody
	public ResponseMsg getTwoCategoryProductSize(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			String productTypeIdTwo = reqDataJson.getString("productTypeIdTwo");
			String type = reqDataJson.getString("type");
			List<Map<String,Object>> sizeList = new ArrayList<Map<String,Object>>();
			Map<String, Object> repMap = new HashMap<String, Object>();
			if(!StringUtil.isBlank(productTypeIdTwo)){
				List<Integer> propValIdList = new ArrayList<Integer>();
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("productTypeId", productTypeIdTwo);
				paramsMap.put("type", type);
				String size = "";
				List<SingleProductActivityCustom> singleProductActivityCustoms = singleProductActivityService.getTwoCategoryProductSize(paramsMap);
				if(CollectionUtil.isNotEmpty(singleProductActivityCustoms)){
					for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivityCustoms) {
						size += singleProductActivityCustom.getPropValId() + ",";
					}
				}
				if(!StringUtil.isBlank(size)){
					List<String> result = Arrays.asList(size.split(","));
					for (String str : result) {
						if(!propValIdList.contains(Integer.valueOf(str))){
							propValIdList.add(Integer.valueOf(str));
						}
					}
				}
				if(CollectionUtil.isNotEmpty(propValIdList)){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("propValIdList", propValIdList);
					List<ProductPropValue> productPropValues = productPropValueService.getProductPropValueModelByIds(map);
					if(CollectionUtil.isNotEmpty(productPropValues)){
						for (ProductPropValue productPropValue : productPropValues) {
							Map<String, Object> sizeMap = new HashMap<String, Object>();
							//放置别名
							String alias = productPropValue.getAlias();
							sizeMap.put("propValue", alias);
							sizeMap.put("propValId", alias);
							sizeList.add(sizeMap);
						}
					}
				}
			}
			repMap.put("sizeList", sizeList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,repMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	/**
	 * 
	 * 方法描述 ：获取断码清仓筛选
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月18日 下午5:09:11 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSingleScreeningConditions6")
	@ResponseBody
	public ResponseMsg getSingleScreeningConditions6(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			String type = reqDataJson.getString("type");
			Integer version = reqPRM.getInt("version");
			String system = reqPRM.getString("system");
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			List<Integer> propValIdList = new ArrayList<Integer>();
			Map<String, Object> repMap = new HashMap<String, Object>();
			String size = "";
				Map<String,Object> paramsMap = new HashMap<String,Object>();
				paramsMap.put("type", type);
				List<SingleProductActivityCustom> singleProductActivityCustoms = singleProductActivityService.getSingleScreeningConditions6(paramsMap);
					Map<String,Object> categoryMap = new HashMap<String,Object>();
					if(CollectionUtil.isNotEmpty(singleProductActivityCustoms)){
						Map<String,Object> dataMap = new HashMap<String,Object>();
						List<Integer> productType2IdList = new ArrayList<>();
						for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivityCustoms) {
							Integer productTypeIdTwo = singleProductActivityCustom.getProductTypeIdTwo();
							String productTypeName = singleProductActivityCustom.getProductTypeName();
							if(singleProductActivityCustoms.get(0).getProductTypeIdTwo().equals(productTypeIdTwo)){
								size += singleProductActivityCustom.getPropValId()+",";
							}
							if(!productType2IdList.contains(productTypeIdTwo)){
								productType2IdList.add(productTypeIdTwo);
								dataMap = new HashMap<String,Object>();
								dataMap.put("productTypeName", productTypeName);
								dataMap.put("productTypeIdTwo", productTypeIdTwo);
								dataList.add(dataMap);
							}
						}
					}
					categoryMap.put("categoryName","");
					categoryMap.put("categoryId","");
					categoryMap.put("categoryTabList",dataList);
					list.add(categoryMap);
//				}
//			}
			//尺码
			List<Map<String,Object>> sizeList = new ArrayList<Map<String,Object>>();
			if(!StringUtil.isBlank(size)){
				List<String> result = Arrays.asList(size.split(","));
				for (String str : result) {
					propValIdList.add(Integer.valueOf(str));
				}
			}
			if(CollectionUtil.isNotEmpty(propValIdList)){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("propValIdList", propValIdList);
				List<ProductPropValue> productPropValues = productPropValueService.getProductPropValueModelByIds(map);
				if(CollectionUtil.isNotEmpty(productPropValues)){
					for (ProductPropValue productPropValue : productPropValues) {
						Map<String, Object> sizeMap = new HashMap<String, Object>();
						String alias = productPropValue.getAlias();
						if(system.equals(Const.ANDROID) && version <= 28){
							sizeMap.put("propValue", productPropValue.getPropValue());
							sizeMap.put("propValId", productPropValue.getId());
							sizeList.add(sizeMap);
						}else{
							sizeMap.put("propValue", alias);
							sizeMap.put("propValId", alias);
							sizeList.add(sizeMap);
						}
					}
				}
			}
			repMap.put("categoryList", list);
			repMap.put("name", "尺码");
			repMap.put("sizeList", sizeList);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,repMap);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取商品筛选条件(适用于会场)
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月18日 下午5:09:11 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductScreeningConditionsByActivityId")
	@ResponseBody
	public ResponseMsg getProductScreeningConditionsByActivityId(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"activityId"};
			this.requiredStr(obj,request);
			//活动id
			String activityId = reqDataJson.getString("activityId");//TODO 注意！！这个不是活动id，是会场id（bu_activity_area的主键id！！！这是个坑！时间：2019年10月18日14:14:29，by 叶江城）
			Map<String,Object> params = new HashMap<String,Object>();
//			params.put("activityId", activityId);
			params.put("activityAreaId", activityId);
			List<ActivityCustom> activityCustoms = activityService.getProductByActiviTyId(params);
			List<Integer> productTypeIdList = new ArrayList<Integer>();
			if(activityCustoms != null && activityCustoms.size() > 0){
				for (ActivityCustom activityCustom : activityCustoms) {
					//获取所有的商品品类id
					productTypeIdList.add(activityCustom.getProductTypeId());
				}
			}
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			Map<String,Object> productTypeNameMap = new HashMap<String,Object>();
			productTypeNameMap.put("name", "商品品类");
			productTypeNameMap.put("type", "APP_PRODUCT_TYPE");
			if(productTypeIdList != null && productTypeIdList.size() > 0){
				//获取商品品类名称
				ProductTypeExample productTypeExample = new ProductTypeExample();
				productTypeExample.createCriteria().andIdIn(productTypeIdList).andDelFlagEqualTo("0");
				List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
				if(productTypes != null && productTypes.size() > 0){
					List<Map<String,Object>> productTypeMapList = new ArrayList<Map<String,Object>>();
					for (ProductType productType : productTypes) {
						//获取所有的商品品类id和名称
						Map<String,Object> productTypeMap = new HashMap<String,Object>();
						productTypeMap.put("value", productType.getId());
						productTypeMap.put("name", productType.getName());
						productTypeMapList.add(productTypeMap);
					}
					productTypeNameMap.put("productList", productTypeMapList);
				}
			}
			
			//获取适合人群
			Map<String,Object> productSuitPeopleNameMap = getProductSuitPeopleNameMap();
			//获取商品价格区间
			Map<String,Object> priceRangeNameMap = getPriceRangeNameMap();
			//男女
			Map<String,Object> sexNameMap = getSexNameMap();
			returnData.add(productTypeNameMap);
			returnData.add(productSuitPeopleNameMap);
			returnData.add(priceRangeNameMap);
			returnData.add(sexNameMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取商品筛选条件(适用与活动商品)
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月18日 下午5:09:11 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getProductScreeningConditions")
	@ResponseBody
	public ResponseMsg getProductScreeningConditions(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"activityId"};
			this.requiredStr(obj,request);
			//活动id
			String activityId = reqDataJson.getString("activityId");
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("activityId", activityId);
			List<ActivityCustom> activityCustoms = activityService.getProductByActiviTyId(params);
			List<Integer> productTypeIdList = new ArrayList<Integer>();
			if(activityCustoms != null && activityCustoms.size() > 0){
				for (ActivityCustom activityCustom : activityCustoms) {
					//获取所有的商品品类id
					productTypeIdList.add(activityCustom.getProductTypeId());
				}
			}
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			Map<String,Object> productTypeNameMap = new HashMap<String,Object>();
			productTypeNameMap.put("name", "商品品类");
			productTypeNameMap.put("type", "APP_PRODUCT_TYPE");
			if(productTypeIdList != null && productTypeIdList.size() > 0){
				//获取商品品类名称
				ProductTypeExample productTypeExample = new ProductTypeExample();
				productTypeExample.createCriteria().andIdIn(productTypeIdList).andDelFlagEqualTo("0");
				List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
				if(productTypes != null && productTypes.size() > 0){
					List<Map<String,Object>> productTypeMapList = new ArrayList<Map<String,Object>>();
					for (ProductType productType : productTypes) {
						//获取所有的商品品类id和名称
						Map<String,Object> productTypeMap = new HashMap<String,Object>();
						productTypeMap.put("value", productType.getId());
						productTypeMap.put("name", productType.getName());
						productTypeMapList.add(productTypeMap);
					}
					productTypeNameMap.put("productList", productTypeMapList);
				}
			}
			//获取适合人群
			Map<String,Object> productSuitPeopleNameMap = getProductSuitPeopleNameMap();
			//获取商品价格区间
			Map<String,Object> priceRangeNameMap = getPriceRangeNameMap();
			//男女
			Map<String,Object> sexNameMap = getSexNameMap();
			returnData.add(productTypeNameMap);
			returnData.add(productSuitPeopleNameMap);
			returnData.add(priceRangeNameMap);
			returnData.add(sexNameMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 
	 * 方法描述 ：获取商品筛选条件(适用于单品活动商品列表)
	 * @author  chenwf: 
	 * @date 创建时间：2017年5月18日 下午5:09:11 
	 * @version
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getSingleActivityScreen")
	@ResponseBody
	public ResponseMsg getSingleActivityScreen(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"type"};
			this.requiredStr(obj,request);
			//类型
			String type = reqDataJson.getString("type");
			List<SingleProductActivityCustom> singleProductActivitys = singleProductActivityService.getSingleActivityScreen(type);
			List<Integer> productTypeIdList = new ArrayList<Integer>();
			if(CollectionUtils.isNotEmpty(singleProductActivitys)){
				for (SingleProductActivityCustom singleProductActivityCustom : singleProductActivitys) {
					//获取所有的商品品类id
					productTypeIdList.add(singleProductActivityCustom.getProductTypeId());
				}
			}
			List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
			Map<String,Object> productTypeNameMap = new HashMap<String,Object>();
			productTypeNameMap.put("name", "商品品类");
			productTypeNameMap.put("type", "APP_PRODUCT_TYPE");
			if(productTypeIdList != null && productTypeIdList.size() > 0){
				//获取商品品类名称
				ProductTypeExample productTypeExample = new ProductTypeExample();
				productTypeExample.createCriteria().andIdIn(productTypeIdList).andDelFlagEqualTo("0");
				List<ProductType> productTypes = productTypeService.selectByExample(productTypeExample);
				if(productTypes != null && productTypes.size() > 0){
					List<Map<String,Object>> productTypeMapList = new ArrayList<Map<String,Object>>();
					for (ProductType productType : productTypes) {
						//获取所有的商品品类id和名称
						Map<String,Object> productTypeMap = new HashMap<String,Object>();
						productTypeMap.put("value", productType.getId());
						productTypeMap.put("name", productType.getName());
						productTypeMapList.add(productTypeMap);
					}
					productTypeNameMap.put("productList", productTypeMapList);
				}
			}
			//获取适合人群
			Map<String,Object> productSuitPeopleNameMap = getProductSuitPeopleNameMap();
			//获取商品价格区间
			Map<String,Object> priceRangeNameMap = getPriceRangeNameMap();
			//男女
			Map<String,Object> sexNameMap = getSexNameMap();
			returnData.add(productTypeNameMap);
			returnData.add(productSuitPeopleNameMap);
			returnData.add(priceRangeNameMap);
			returnData.add(sexNameMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,returnData);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}

	public Map<String, Object> getSexNameMap() {
		List<SysParamCfg> sexParams = DataDicUtil.getSysParamCfg("APP_SEX");
		Map<String,Object> sexNameMap = new HashMap<String,Object>();
		if(sexParams != null && sexParams.size() > 0){
			List<Map<String,Object>> sexMapList = new ArrayList<Map<String,Object>>();
			sexNameMap.put("name", "性别");
			sexNameMap.put("type", "APP_SEX");
			for (SysParamCfg sysParamCfg : sexParams) {
				Map<String,Object> sexMap = new HashMap<String,Object>();
				sexMap.put("name", sysParamCfg.getParamDesc());
				sexMap.put("value", sysParamCfg.getParamValue());
				sexMapList.add(sexMap);
			}
			sexNameMap.put("productList", sexMapList);
		}
		return sexNameMap;
	}

	public Map<String, Object> getPriceRangeNameMap() {
		List<SysParamCfg> priceRangeParams = DataDicUtil.getSysParamCfg("APP_PRICE_RANGE");
		Map<String,Object> priceRangeNameMap = new HashMap<String,Object>();
		if(priceRangeParams != null && priceRangeParams.size() > 0){
			List<Map<String,Object>> priceRangeMapList = new ArrayList<Map<String,Object>>();
			priceRangeNameMap.put("name", "价格区间");
			priceRangeNameMap.put("type", "APP_PRICE_RANGE");
			for (SysParamCfg sysParamCfg : priceRangeParams) {
				Map<String,Object> priceRangeMap = new HashMap<String,Object>();
				priceRangeMap.put("name", sysParamCfg.getParamValue());
				priceRangeMap.put("value", sysParamCfg.getParamValue());
				priceRangeMapList.add(priceRangeMap);
			}
			priceRangeNameMap.put("productList", priceRangeMapList);
		}
		return priceRangeNameMap;
	}

	public Map<String, Object> getProductSuitPeopleNameMap() {
		List<SysParamCfg> suitPeopleParams = DataDicUtil.getSysParamCfg("APP_SUIT_PEOPLE");
		Map<String,Object> productSuitPeopleNameMap = new HashMap<String,Object>();
		if(suitPeopleParams != null && suitPeopleParams.size() > 0){
			List<Map<String,Object>> suitPeopleMapList = new ArrayList<Map<String,Object>>();
			productSuitPeopleNameMap.put("name", "适用人群");
			productSuitPeopleNameMap.put("type", "APP_SUIT_PEOPLE");
			for (SysParamCfg sysParamCfg : suitPeopleParams) {
				Map<String,Object> suitPeopleMap = new HashMap<String,Object>();
				String value = sysParamCfg.getParamValue();
				if(value.equals("100")){
					value = "1__";
				}else if(value.equals("010")){
					value = "_1_";
				}else if(value.equals("001")){
					value = "__1";
				}
				suitPeopleMap.put("value", value);
				suitPeopleMap.put("name", sysParamCfg.getParamDesc());
				suitPeopleMapList.add(suitPeopleMap);
			}
			productSuitPeopleNameMap.put("productList", suitPeopleMapList);
		}
		return productSuitPeopleNameMap;
	}
	
	/**
	 * 获取商城筛选项
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShopMallProductScreeningConditions")
	@ResponseBody
	public ResponseMsg getShopMallProductScreeningConditions(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			//根据搜索词拆成几个关键词
			List<String> wordList = new ArrayList<String>();
			//类目id
			Integer productTypeId= 1;
			List<Integer> productTypeOneIdList= new ArrayList<Integer>();
			List<Integer> productTypeTwoIdList= new ArrayList<Integer>();
			List<Integer> productTypeThreeIdList= new ArrayList<Integer>();
			//适合性别  00 都不选 11 都选  10 选男不选女   01 选女不选男
			String suitSex = "";
			//品牌
			List<Integer> brandIdList = new ArrayList<Integer>();
			//类目id 查询该类目是属于几级类目id
			ProductType productType = null;
			if(reqDataJson.containsKey("productTypeId") && !StringUtil.isBlank(reqDataJson.getString("productTypeId"))){
				productTypeId = reqDataJson.getInt("productTypeId");
				productType = productTypeService.selectByPrimaryKey(productTypeId);
				if(productType != null && productType.getId() != null){
					if("1".equals(productType.gettLevel().toString())){
						productTypeOneIdList.add(productTypeId);
					}else if("2".equals(productType.gettLevel().toString())){
						productTypeTwoIdList.add(productTypeId);
					}else if("3".equals(productType.gettLevel().toString())){
						productTypeThreeIdList.add(productTypeId);
					}
				}
			}
				
			if(reqDataJson.containsKey("brandId") && !StringUtil.isBlank(reqDataJson.getString("brandId"))){
				for (String id : reqDataJson.getString("brandId").split(",")) {
					brandIdList.add(Integer.valueOf(id));
				}
			}
			if(reqDataJson.containsKey("searchName") && !StringUtil.isBlank(reqDataJson.getString("searchName"))){
				wordList = StringUtil.seg(reqDataJson.getString("searchName"));
				if(CollectionUtil.isNotEmpty(wordList)){
					if(wordList.size() > 3){
						wordList.subList(0, 3);//只取前面3个
					}
					//查找出是否属于品牌
					brandIdList = productBrandService.getBrandIdListByWords(brandIdList,wordList);
					//查找出是否属于品类
					ProductTypeExample typeExample = new ProductTypeExample();
					typeExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andNameIn(wordList);
					List<ProductType> productTypes = productTypeService.selectByExample(typeExample);
					if(CollectionUtils.isNotEmpty(productTypes)){
						for (ProductType pt : productTypes) {
							if("1".equals(pt.gettLevel().toString())){
								productTypeOneIdList.add(pt.getId());
							}else if("2".equals(pt.gettLevel().toString())){
								productTypeTwoIdList.add(pt.getId());
							}else if("3".equals(pt.gettLevel().toString())){
								productTypeThreeIdList.add(pt.getId());
							}
						}
					}
					//查找出是否属于性别
					if((wordList.contains("男") && wordList.contains("女")) && wordList.contains("男女")){
						suitSex = "11";
					}else if(wordList.contains("男")){
						suitSex = "1%";
					}else if(wordList.contains("女")){
						suitSex = "%1";
					}
				}
			}
			paramsMap.put("wordList", wordList);
			paramsMap.put("productTypeOneIdList", productTypeOneIdList);
			paramsMap.put("productTypeTwoIdList", productTypeTwoIdList);
			paramsMap.put("productTypeThreeIdList", productTypeThreeIdList);
			paramsMap.put("brandIdList", brandIdList);
			paramsMap.put("suitSex", suitSex);
			paramsMap.put("pageSize", 9);
			paramsMap.put("currentPage", 0);
			//获取商城品牌
			Map<String,Object> brandMap = productBrandService.getShopMallProductBrandMap(paramsMap,"1");
			//男女
			Map<String,Object> sexNameMap = getSexNameMap();
			//获取适合人群
			Map<String,Object> productSuitPeopleNameMap = getProductSuitPeopleNameMap();
			//季节
			Map<String,Object> seasonMap = getProductSeasonNameMap();
			//类目
			Map<String,Object> productTypeMap = productTypeService.getCategoryTypeList(productType,productTypeId,"1");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("priceSectionName", "价格区间");
			map.put("allCategory", "全部分类");
			map.put("productTypeMap", productTypeMap);
			map.put("brandMap", brandMap);
			map.put("productSuitPeopleNameMap", productSuitPeopleNameMap);
			map.put("sexNameMap", sexNameMap);
			map.put("seasonMap", seasonMap);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		} catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,e.getMessage());
		}
	}
	
	public Map<String, Object> getProductSeasonNameMap() {
		List<SysStatus> seasonParams = DataDicUtil.getStatusListByType("BU_PRODUCT", "SEASON","");
		Map<String,Object> seasonMap = new HashMap<String,Object>();
		seasonMap.put("春季", "8");
		seasonMap.put("夏季", "8");
		seasonMap.put("秋季", "8");
		seasonMap.put("冬季", "8");
		if(CollectionUtil.isNotEmpty(seasonParams)){
			for (SysStatus sysStatus : seasonParams) {
				String name = sysStatus.getStatusDesc();
				Integer value = Integer.valueOf(sysStatus.getStatusValue());
				if(name.contains("春")){
					seasonMap.put("春季", seasonMap.get("春季")+","+value);
				}
				if(name.contains("夏")){
					seasonMap.put("夏季", seasonMap.get("夏季")+","+value);
				}
				if(name.contains("秋")){
					seasonMap.put("秋季", seasonMap.get("秋季")+","+value);
				}
				if(name.contains("冬")){
					seasonMap.put("冬季", seasonMap.get("冬季")+","+value);
				}
			}
		}
		Map<String,Object> seasonNameMap = new HashMap<String,Object>();
		List<Map<String,Object>> seasonMapList = new ArrayList<Map<String,Object>>();
		seasonNameMap.put("name", "适合季节");
		seasonNameMap.put("type", "SEASON");
		for (Entry<String, Object> map : seasonMap.entrySet()) {
			Map<String, Object> m = new HashMap<String,Object>();
			m.put("name", map.getKey());
			m.put("value", map.getValue());	
			seasonMapList.add(m);
		}
		seasonNameMap.put("productList", seasonMapList);
		return seasonNameMap;
	}
	
	/**
	 * 店铺——店铺商品筛选
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/n/getShopProductScreeningConditions")
	@ResponseBody
	public ResponseMsg getMchtShopInfo(HttpServletRequest request){
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			JSONObject reqDataJson = reqPRM.getJSONObject("reqData");// 获取请求参数
			Object[] obj = {"mchtId"};
			this.requiredStr(obj,request);
			Map<String, Object> map = shoppingMallService.getShopProductScreeningConditions(reqPRM,reqDataJson);
			return new ResponseMsg(ResponseMsg.SUCCESS,ResponseMsg.SUCCESS_MSG,map);
		}catch (ArgException args){
			return new ResponseMsg(ResponseMsg.ERROR,args.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR,"数据请求失败");
		}
	}
}
