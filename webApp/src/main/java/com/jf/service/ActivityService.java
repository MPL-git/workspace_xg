package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.FileUtil;
import com.jf.common.utils.JsonUtils;
import com.jf.common.utils.StringUtil;
import com.jf.dao.ActivityCustomMapper;
import com.jf.dao.ActivityMapper;
import com.jf.entity.Activity;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityExample;
import com.jf.entity.BrandTeamType;
import com.jf.entity.ProductCustom;
import com.jf.entity.SysParamCfg;
import com.jf.vo.request.allowance.FindAreaProductListRequest;
import com.jf.vo.response.allowance.AllowanceProductTypeView;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月25日 下午3:14:32 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ActivityService extends BaseService<Activity, ActivityExample> {
	
	
	
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private BrandTeamTypeService brandTeamTypeService;
    @Autowired
    private SysParamCfgService sysParamCfgService;
	@Autowired
	private ActivityCustomMapper activityCustomMapper;
	
	@Autowired
	public void setActivityMapper(ActivityMapper activityMapper) {
		super.setDao(activityMapper);
		this.activityMapper = activityMapper;
	}
	
	
	
	public Integer getActivityCustomCount(Map<String, Object> params) {
		
		return activityCustomMapper.getActivityCustomCount(params);
	}
	
	public List<ActivityCustom> getActivityCustomList(Map<String, Object> params) {
		
		return activityCustomMapper.getActivityCustomList(params);
	}

	public Integer getNewUserEnjoyCount(Map<String, Object> params) {
		
		return activityCustomMapper.getNewUserEnjoyCount(params);
	}

	public List<ActivityCustom> getNewUserEnjoyList(Map<String, Object> params) {
		
		return activityCustomMapper.getNewUserEnjoyList(params);
	}

	public Integer getActivityPreheatCount(Map<String, Object> params) {
		
		return activityCustomMapper.getActivityPreheatCount(params);
	}

	public List<ActivityCustom> getActivityPreheatList(Map<String, Object> params) {
		
		return activityCustomMapper.getActivityPreheatList(params);
	}


	public List<ActivityCustom> getProductByActiviTyId(Map<String, Object> params) {
		
		return activityCustomMapper.getProductByActiviTyId(params);
	}


	public Integer getCurrentMaxActivityAreaId(Map<String, Object> params) {
		
		return activityCustomMapper.getCurrentMaxActivityAreaId(params);
	}

	public ActivityCustom getActivityInfoById(Integer activityId) {
		
		return activityCustomMapper.getActivityInfoById(activityId);
	}


	public ActivityCustom selectActivityAreaInfo(Map<String, Object> activityParams) {
		
		return activityCustomMapper.selectActivityAreaInfo(activityParams);
	}


	public List<ActivityCustom> getProductCount(Map<String, Object> params) {
		
		return activityCustomMapper.getProductCount(params);
	}

	public List<ActivityCustom> getNewUserEnjoyCategory(Map<String, Object> params) {
		
		return activityCustomMapper.getNewUserEnjoyCategory(params);
	}

	public Map<String, Object> getSingleProductActivity(String memberId, String type, String productTypeId, Integer pageSize,
			Integer currentPage) {
		List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		params.put("type", type);
		params.put("productTypeId", productTypeId);

		Map<String,Object> data = new HashMap<String,Object>();
		//会场类型(6单品爆款 7新用户专享)
		String configCode = "";
		if(type.equals("7")){
			configCode = "APP_NEW_USER_ENJOY_TOP";
		}else{
			configCode = "APP_EXPLOSION_TOP";
		}
		List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg(configCode);
		if(CollectionUtils.isNotEmpty(cfgs)){
			data.put("topPic", FileUtil.getImageServiceUrl()+cfgs.get(0).getParamValue());
		}else{
			data.put("topPic", "");
		}
		Integer activityAreaId = getCurrentMaxActivityAreaId(params);
		
		if(activityAreaId != null){
			params.put("activityAreaId", activityAreaId);
			/*Integer count = getNewUserEnjoyCount(params);
			Page page = new Page(currentPage, pageSize, count);
			System.out.println(page.getTotalPage());*/
			List<ActivityCustom> activityCustoms = getNewUserEnjoyList(params);
			String entryPic = "";
			if(activityCustoms != null && activityCustoms.size() >0){
				for (ActivityCustom activityCustom : activityCustoms) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					Integer stockSum = activityCustom.getStockSum() == null ? 0 : activityCustom.getStockSum(); 
					dataMap.put("activityAreaId", activityCustom.getActivityAreaId());
					dataMap.put("activityId", activityCustom.getId());
					dataMap.put("productId", activityCustom.getProductId());
					dataMap.put("productName", activityCustom.getProductName());
					if(stockSum > 0 && activityCustom.getProductStatus().equals("1")){
						dataMap.put("stock", stockSum);
					}else{
						dataMap.put("stock", 0);
					}
					
					dataMap.put("pic", FileUtil.getImageServiceUrl()+activityCustom.getProductPic());
					dataMap.put("limitBuy", activityCustom.getLimitBuy());
					dataMap.put("productTypeId", activityCustom.getProductTypeId());
					
					double salePrice = activityCustom.getSalePrice() == null ? 0 :activityCustom.getSalePrice().doubleValue();
					double tagPrice = activityCustom.getSalePrice() == null ? 0 :activityCustom.getTagPrice().doubleValue();
					double discount = new BigDecimal((float)salePrice/tagPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					dataMap.put("salePrice", salePrice);
					dataMap.put("tagPrice", tagPrice);
					dataMap.put("discount", discount);//0.33 换算成3.3
					dataMap.put("isExist", false);
					entryPic = activityCustom.getEntryPic();
					returnData.add(dataMap);
					data.put("Rows", returnData);
				}
				String code = "";
				if(type.equals("7")){
					code = "APP_NEW_USER_ENJOY";
				}else{
					code = "APP_EXPLOSION";
				}
				List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg(code);
				if(CollectionUtils.isNotEmpty(sysParamCfgs)){
					entryPic = sysParamCfgs.get(0).getParamValue();
				}
				//data.put("TotalPage", page.getTotalPage());
				data.put("entryPic", FileUtil.getImageServiceUrl()+entryPic);
			}else{
				//data.put("TotalPage", page.getTotalPage());
				data.put("Rows", returnData);
			}
		}else{
			data.put("Rows", returnData);
		}
		return data;
	}



	public List<Map<String, Object>> getTodayPreferentialActivityList(String productTypeId, String adCatalog, Integer pageSize, Integer currentPage, String memberId) {
		Integer ptId = null;
		if(!StringUtil.isBlank(productTypeId)){
			ptId = Integer.valueOf(productTypeId);
		}
		Map<String,Object> params = new HashMap<String,Object>();
		if(StringUtil.isBlank(adCatalog)){
			if(StringUtil.isBlank(productTypeId)){
				adCatalog = "1";//首页
			}else{
				//根据品类id查找对应的广告类目id
				SysParamCfg sysParamCfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", productTypeId);
				if(sysParamCfg != null){
					adCatalog = sysParamCfg.getParamOrder();
				}
			}
		}
		params.put("productTypeId", ptId);
		params.put("adCatalog", adCatalog);
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		params.put("specialHideAreaProductTypeIdList", sysParamCfgService.getSpecialHideAreaProductTypeIdList());
		List<ActivityCustom> activityCustoms = getActivityCustomList(params);
		List<Map<String,Object>> returnData = getActivityCustomInfo(activityCustoms);
		return returnData;
	}



	private List<Map<String, Object>> getActivityCustomInfo(List<ActivityCustom> activityCustoms) {
		List<Map<String,Object>> returnData = new ArrayList<Map<String,Object>>();
		if(activityCustoms !=null && activityCustoms.size() > 0){
			for (ActivityCustom activityCustom : activityCustoms) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				String areaUrl = "";
				if(activityCustom.getSource().equals("1")){
					if(!StringUtil.isBlank(activityCustom.getTempletSuffix())){
						areaUrl = Config.getProperty("mUrl")+activityCustom.getTempletSuffix()+"?activityAreaId="+activityCustom.getActivityAreaId()+"&currentPage=0&pageSize=10&memberId=";
					}
				}
				String activityRemainingTime = DateUtil.getActivityRemainingTime(activityCustom.getActivityEndTime());
				dataMap.put("activityTime", activityRemainingTime);
				dataMap.put("areaUrl", areaUrl);
				dataMap.put("posterPic", FileUtil.getImageServiceUrl()+activityCustom.getPosterPic());
				dataMap.put("activityId", activityCustom.getId());//活动id
				dataMap.put("activityAreaId", activityCustom.getActivityAreaId());//会场id
				dataMap.put("source", activityCustom.getSource());//source会场形式(1会场 2品牌特卖)
				dataMap.put("activityAreatype", activityCustom.getType());
				dataMap.put("logo", "");
				dataMap.put("entryPic", FileUtil.getImageServiceUrl()+activityCustom.getAreaEntryPic());
				dataMap.put("name", activityCustom.getActivityAreaName());
				dataMap.put("groups", activityCustom.getBenefitPoint());
				returnData.add(dataMap);
			}
		}
		return returnData;
	}



	public List<ActivityCustom> getOrderDataByCombineOrderId(Integer combineOrderId) {
		
		return activityCustomMapper.getOrderDataByCombineOrderId(combineOrderId);
	}
	
	/**用途：商家特卖获得列表*/
	public List<Map<String,Object>> getBrandRecommendActivityByMchtId(Map<String, Object> brandParams) {
		List<Map<String,Object>> brandRecommendMapList = new ArrayList<Map<String,Object>>();
		List<ActivityCustom> activityCustoms = activityCustomMapper.getBrandRecommendActivityByMchtId(brandParams);
		if(CollectionUtils.isNotEmpty(activityCustoms)){
			for (ActivityCustom custom : activityCustoms) {
				Map<String,Object> brandRecommendMap = new HashMap<String,Object>();
				String activityRemainingTime = DateUtil.getActivityRemainingTime(custom.getActivityEndTime());
				brandRecommendMap.put("activityId", custom.getId());
				brandRecommendMap.put("activityAreaId", custom.getActivityAreaId());
				brandRecommendMap.put("activityName", custom.getName());
				brandRecommendMap.put("benefitPoint", custom.getBenefitPoint());
				brandRecommendMap.put("entryPic", StringUtil.getPic(custom.getEntryPic(), ""));
				brandRecommendMap.put("activityTime", activityRemainingTime);
				brandRecommendMap.put("source", "2");
				brandRecommendMapList.add(brandRecommendMap);
			}
		}
		return brandRecommendMapList;
	}

	public List<ActivityCustom> getActivityByModuleItem(Map<String, Object> activityParamsMap) {
		
		return activityCustomMapper.getActivityByModuleItem(activityParamsMap);
	}

	public List<ActivityCustom> getActivityByIds(Map<String, Object> paramsMap) {
		
		return activityCustomMapper.getActivityByIds(paramsMap);
	}



	/**
	 * 方法描述 ：获取商品在活动中的信息
	 */
	public List<ActivityCustom> getActivityProductInfo(Map<String, Object> paramsMap) {
		
		return activityCustomMapper.getActivityProductInfo(paramsMap);
	}

	public List<ActivityCustom> findByProductIds(List<Integer> productIds) {
		if (CollectionUtils.isEmpty(productIds)) return Collections.emptyList();

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("productIdList", productIds);
		return getActivityProductInfo(params);
	}

	/**
	 * 方法描述 ：品牌团活动列表信息
	 */
	public Map<String, Object> getActivityBrandGroupActivityList(Integer activityBrandGroupId, Integer currentPage,Integer pageSize) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("activityBrandGroupId", activityBrandGroupId);
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<ActivityCustom> activityCustoms = activityCustomMapper.getActivityBrandGroupActivityList(params);
		List<Map<String,Object>> activityList = getActivityCustomInfo(activityCustoms);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityList", activityList);
		return map;
	}



	public Map<String, Object> getCategoryBrandGroup(JSONObject reqPRM, JSONObject reqDataJson) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<>();
		Date currentDate = new Date();
		Date cureentBeginDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
		Date cureentEndDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
		String activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
		Integer showNum = 3;
		Integer brandTeamTypeId = null;
		Integer productType1Id = null;
		Integer memberId = null;
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = reqDataJson.getInt("pageSize");
		String adCatalog = "";//广告类目(1 首页 2 运动 3 鞋包 4 服饰 5 生活)
		List<Integer> productType2IdList = new ArrayList<>();
		if(reqDataJson.containsKey("productType1Id") && !StringUtil.isBlank(reqDataJson.getString("productType1Id"))){
			productType1Id = reqDataJson.getInt("productType1Id");
		}else if(reqDataJson.containsKey("brandTeamTypeId") && !StringUtil.isBlank(reqDataJson.getString("brandTeamTypeId"))){
			brandTeamTypeId = reqDataJson.getInt("brandTeamTypeId");
			List<BrandTeamType> brandTeamTypes = brandTeamTypeService.findModelsById(brandTeamTypeId);
			if(CollectionUtils.isNotEmpty(brandTeamTypes)){
				productType1Id = brandTeamTypes.get(0).getProductTypeId();
			}
		}else if(reqDataJson.containsKey("productType2Ids") && !StringUtil.isBlank(reqDataJson.getString("productType2Ids"))){
			String productType2Ids = reqDataJson.getString("productType2Ids");
			for (String productType2Id : productType2Ids.split(",")) {
				if(!StringUtil.isBlank(productType2Id)){
					productType2IdList.add(Integer.parseInt(productType2Id));
				}
			}
		}else{
			map.put("dataList", dataList);
			return map;
		}
		
		if(!JsonUtils.isEmpty(reqDataJson, "memberId")){
			memberId = reqDataJson.getInt("memberId");
		}
		if(productType1Id != null || CollectionUtils.isNotEmpty(productType2IdList)){
			if(productType1Id != null){
				SysParamCfg cfg = DataDicUtil.getSysParamCfgModel("APP_HOME_TOP_CATALOG", productType1Id.toString());
				if(cfg != null){
					adCatalog = cfg.getParamOrder();
				}
			}
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("productType1Id", productType1Id);
			paramsMap.put("productType2IdList", productType2IdList);
			paramsMap.put("adCatalog", adCatalog);
			paramsMap.put("pageSize", pageSize);
			paramsMap.put("currentPage", currentPage*pageSize);
			List<ActivityCustom> activityCustoms = activityCustomMapper.getCategoryBrandGroup(paramsMap);
			if(CollectionUtils.isNotEmpty(activityCustoms)){
				for (ActivityCustom ac : activityCustoms) {
					List<Map<String,Object>> productDataList = new ArrayList<>();
					Map<String,Object> dataMap = new HashMap<String,Object>();
					String benefitPoint = ac.getBenefitPoint();
					Integer activityId = ac.getId();
					String activityAreaName = ac.getActivityAreaName();
					String areaEntryPic = ac.getAreaEntryPic();
					String brandTeamPic = ac.getBrandTeamPic();
					Integer activityAreaId = ac.getActivityAreaId();
					String activityRemainingTime = DateUtil.getActivityRemainingTime(ac.getActivityEndTime());
					String isOldEntryPic = "1";//是否旧版本入口图 0否 1是
					boolean isExist = false;//是否已收藏 0 否 1是
					if(!StringUtil.isBlank(brandTeamPic)){
						isOldEntryPic = "0";
						areaEntryPic = brandTeamPic;
					}
					if(memberId != null && ((productType1Id !=null && productType1Id == 0) || CollectionUtils.isNotEmpty(productType2IdList))){
						isExist = DataDicUtil.getRemindExists(ac.getActivityAreaId(),memberId,"1");
					}
					if(productType1Id !=null && productType1Id == 0){
						//预售商品
						Date activityBeginTime = ac.getActivityBeginTime();
						if(activityBeginTime.after(cureentBeginDate) && activityBeginTime.before(cureentEndDate)){
							activityRemainingTime = DateUtil.getFormatDate(activityBeginTime, "HH:mm")+"开售";
						}else{
							activityRemainingTime = DateUtil.getFormatDate(activityBeginTime, "dd日 HH:mm")+"开售";
						}
					}else{
						activityRemainingTime = DateUtil.getActivityRemainingTime(ac.getActivityEndTime());
					}
					dataMap.put("activityId", activityId);
					dataMap.put("activityAreaId", activityAreaId);
					dataMap.put("areaEntryPic", StringUtil.getPic(areaEntryPic, ""));
					dataMap.put("activityRemainingTime", activityRemainingTime);
					dataMap.put("isOldEntryPic", isOldEntryPic);
					dataMap.put("benefitPoint", benefitPoint);
					dataMap.put("activityBeginTime", ac.getActivityBeginTime().getTime());
					dataMap.put("currentTime", currentDate.getTime());
					dataMap.put("productType1Id", productType1Id);
					dataMap.put("activityAreaName", activityAreaName);
					dataMap.put("isExist", isExist);
					Map<String,Object> productParamsMap = new HashMap<String,Object>();
					productParamsMap.put("activityId", activityId);
					productParamsMap.put("showNum", showNum);
					List<ProductCustom> psCustoms = productService.getBaranGroupProducts(productParamsMap);
					if(CollectionUtils.isNotEmpty(psCustoms)){
						for (ProductCustom ps : psCustoms) {
							Map<String,Object> productDataMap = new HashMap<String,Object>();
							BigDecimal salePrice = ps.getSalePrice();
							BigDecimal tagPrice = ps.getTagPrice();
							BigDecimal svipDiscount = ps.getSvipDiscount();
							BigDecimal svipSalePrice = new BigDecimal("0");
							if(svipDiscount != null && svipDiscount.compareTo(new BigDecimal("0")) > 0){
								svipSalePrice = productService.getProductSvipSalePrice(salePrice, svipDiscount, activityType);
							}
							productDataMap.put("productId", ps.getId());
							productDataMap.put("productPic", StringUtil.getPic(ps.getPic(), ""));
							productDataMap.put("productName", ps.getName());
							productDataMap.put("salePrice", salePrice);
							productDataMap.put("tagPrice", tagPrice);
							productDataMap.put("svipSalePrice", svipSalePrice);
							productDataList.add(productDataMap);
						}
					}
					dataMap.put("productDataList", productDataList);
					dataList.add(dataMap);
				}
			}
		}
		map.put("dataList", dataList);
		return map;
	}

	public List<Activity> findModelByAreaId(Integer activityAreaId) {
		ActivityExample activityExample = new ActivityExample();
		activityExample.createCriteria().andActivityAreaIdEqualTo(activityAreaId).andDelFlagEqualTo("0");
		return selectByExample(activityExample);
	}

	public List<ActivityCustom> findAreaProductList(FindAreaProductListRequest findAreaProductListRequest) {
		return activityCustomMapper.findAreaProductList(findAreaProductListRequest);
	}

	public List<AllowanceProductTypeView> listAllowanceProductType(Integer activityAreaId) {
		return activityCustomMapper.listAllowanceProductType(activityAreaId);
	}

}
