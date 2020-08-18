package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.base.Page;
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

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
	private ActivityCustomMapper activityCustomMapper;
	@Resource
	private SingleProductActivityService singleProductActivityService;
	@Resource
	private ProductService productService;
	@Resource
	private BrandTeamTypeService brandTeamTypeService;
	
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
	
	public Integer getNewSingleActivityCount(Map<String, Object> params) {
		
		return activityCustomMapper.getNewSingleActivityCount(params);
	}

	public List<ActivityCustom> getNewUserEnjoyList(Map<String, Object> params) {
		
		return activityCustomMapper.getNewUserEnjoyList(params);
	}
	
	public List<ActivityCustom> getNewSingleActivityList(Map<String, Object> params) {
		
		return activityCustomMapper.getNewSingleActivityList(params);
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
		String activityType = "";
		if(type.equals("7")){
			activityType = "1";
			configCode = "APP_NEW_USER_ENJOY_TOP";
		}else{
			activityType = "2";
			configCode = "APP_EXPLOSION_TOP";
		}
		List<SysParamCfg> cfgs = DataDicUtil.getSysParamCfg(configCode);
		if(CollectionUtils.isNotEmpty(cfgs)){
			data.put("topPic", FileUtil.getImageServiceUrl()+cfgs.get(0).getParamValue());
		}else{
			data.put("topPic", "");
		}
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		params.put("activityType", activityType);
		params.put("productTypeId", productTypeId);
		Integer count = getNewSingleActivityCount(params);
		Page page = new Page(currentPage, pageSize, count);
		List<ActivityCustom> ActivityCustoms = getNewSingleActivityList(params);
		if(CollectionUtils.isNotEmpty(ActivityCustoms)){
			String entryPic = "";
			for (ActivityCustom activityCustom : ActivityCustoms) {
				Map<String,Object> dataMap = new HashMap<String,Object>();
				Integer stockSum = activityCustom.getStockSum() == null ? 0 : activityCustom.getStockSum(); 
				String pic = activityCustom.getProductPic();
				if(stockSum > 0 && activityCustom.getProductStatus().equals("1")){
					dataMap.put("stock", stockSum);
				}else{
					dataMap.put("stock", 0);
				}
				dataMap.put("activityAreaId", 0);
				dataMap.put("activityId", "");
				dataMap.put("productId", activityCustom.getProductId());
				dataMap.put("productName", activityCustom.getProductName());
				dataMap.put("pic", StringUtil.getPic(pic, "M"));
				dataMap.put("limitBuy", activityCustom.getLimitBuy());
				dataMap.put("productTypeId", activityCustom.getProductTypeId());
				
				double salePrice = activityCustom.getSalePrice() == null ? 0 :activityCustom.getSalePrice().doubleValue();
				double tagPrice = activityCustom.getSalePrice() == null ? 0 :activityCustom.getTagPrice().doubleValue();
				double discount = new BigDecimal((float)salePrice/tagPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				dataMap.put("salePrice", salePrice);
				dataMap.put("tagPrice", tagPrice);
				dataMap.put("discount", discount);//0.33 换算成3.3
				boolean isExist = false;
				if(StringUtil.isBlank(memberId)){
					//开售提醒始终显示灰色
					dataMap.put("isExist", isExist);
				}else{
					//判断是否添加开售提醒 true 存在  false 不存在
					if(type.equals("JJSX")){
						isExist = DataDicUtil.getRemindExists(activityCustom.getProductId(),Integer.valueOf(memberId),"2");
					}
					dataMap.put("isExist", isExist);
				}
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
			data.put("TotalPage", page.getTotalPage());
			data.put("entryPic", FileUtil.getImageServiceUrl()+entryPic);
		}else{
			data.put("TotalPage", page.getTotalPage());
			data.put("Rows", returnData);
		}
		return data;
	}
	


	public Map<String, Object> getSingleProductActivityCopy(String memberId, String type, String productTypeId, Integer pageSize,
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
			Integer count = getNewUserEnjoyCount(params);
			Page page = new Page(currentPage, pageSize, count);
			List<ActivityCustom> activityCustoms = getNewUserEnjoyList(params);
			String entryPic = "";
			if(activityCustoms != null && activityCustoms.size() >0){
				for (ActivityCustom activityCustom : activityCustoms) {
					Map<String,Object> dataMap = new HashMap<String,Object>();
					Integer stockSum = activityCustom.getStockSum() == null ? 0 : activityCustom.getStockSum(); 
					String pic = activityCustom.getProductPic();
					if(stockSum > 0 && activityCustom.getProductStatus().equals("1")){
						dataMap.put("stock", stockSum);
					}else{
						dataMap.put("stock", 0);
					}
					dataMap.put("activityAreaId", "");
					dataMap.put("activityId", "");
					dataMap.put("productId", activityCustom.getProductId());
					dataMap.put("productName", activityCustom.getProductName());
					dataMap.put("pic", StringUtil.getPic(pic, "M"));
					dataMap.put("limitBuy", activityCustom.getLimitBuy());
					dataMap.put("productTypeId", activityCustom.getProductTypeId());
					
					double salePrice = activityCustom.getSalePrice() == null ? 0 :activityCustom.getSalePrice().doubleValue();
					double tagPrice = activityCustom.getSalePrice() == null ? 0 :activityCustom.getTagPrice().doubleValue();
					double discount = new BigDecimal((float)salePrice/tagPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					dataMap.put("salePrice", salePrice);
					dataMap.put("tagPrice", tagPrice);
					dataMap.put("discount", discount);//0.33 换算成3.3
					boolean isExist = false;
					if(StringUtil.isBlank(memberId)){
						//开售提醒始终显示灰色
						dataMap.put("isExist", isExist);
					}else{
						//判断是否添加开售提醒 true 存在  false 不存在
						if(type.equals("JJSX")){
							isExist = DataDicUtil.getRemindExists(activityCustom.getProductId(),Integer.valueOf(memberId),"2");
						}
						dataMap.put("isExist", isExist);
					}
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
				data.put("TotalPage", page.getTotalPage());
				data.put("entryPic", FileUtil.getImageServiceUrl()+entryPic);
			}else{
				data.put("TotalPage", page.getTotalPage());
				data.put("Rows", returnData);
			}
		}else{
			data.put("Rows", returnData);
		}
		return data;
	}


	/**
	 * 类目活动信息（今日特惠）
	 */
	public List<Map<String, Object>> getTodayPreferentialActivityList(String productTypeId, String adCatalog, Integer pageSize, Integer currentPage, String memberId, String isNewVersion) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("productTypeId", productTypeId);
		params.put("adCatalog", adCatalog);
		params.put("isNewVersion", isNewVersion);
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<ActivityCustom> activityCustoms = getActivityCustomList(params);
		List<Map<String,Object>> returnData = getActivityCustomInfo(activityCustoms);
		return returnData;
	}
	
	/**
	 * 封装类目活动数据
	 */
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
				dataMap.put("posterPic", StringUtil.getPic(activityCustom.getPosterPic(), ""));
				dataMap.put("activityId", activityCustom.getId());//活动id
				dataMap.put("activityAreaId", activityCustom.getActivityAreaId());//会场id
				dataMap.put("source", activityCustom.getSource());//source会场形式(1会场 2品牌特卖)
				dataMap.put("activityAreatype", activityCustom.getType());
				dataMap.put("logo", "");
				dataMap.put("preferentialtype", "");
				dataMap.put("entryPic", FileUtil.getImageServiceUrl()+activityCustom.getAreaEntryPic());
				dataMap.put("name", activityCustom.getActivityAreaName());
				dataMap.put("groups", activityCustom.getBenefitPoint());
				returnData.add(dataMap);
			}
		}
		return returnData;
	}



	public List<Map<String, Object>> getActivityList(String productTypeId, String adCatalog, Integer pageSize, Integer currentPage, String memberId) {
		Integer ss = 1000;  
	    Integer mi = ss * 60;  
	    Integer hh = mi * 60;  
	    Integer dd = hh * 24;  
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("productTypeId", productTypeId);
		params.put("adCatalog", adCatalog);
		List<ActivityCustom> customSize = getActivityCustomList(params);
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		Page page = new Page(currentPage, pageSize, customSize.size());
		List<ActivityCustom> activityCustoms = getActivityCustomList(params);
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
				dataMap.put("areaUrl", areaUrl);
				dataMap.put("posterPic", FileUtil.getImageServiceUrl()+activityCustom.getPosterPic());
				//活动id
				dataMap.put("activityId", activityCustom.getId());
				//会场id
				dataMap.put("activityAreaId", activityCustom.getActivityAreaId());
				//source会场形式(1会场 2品牌特卖)
				dataMap.put("source", activityCustom.getSource());
				dataMap.put("activityAreatype", activityCustom.getType());
				String logo = "";
				if(activityCustom.getSource().equals("1")){
					String code = "";
					if(activityCustom.getType().equals("1")){
						code = "APP_ACTIVITY_BRAND";
					}else{
						code = "APP_ACTIVITY_SINGLE_PRODUCT";
					}
					List<SysParamCfg> sysParamCfgs = DataDicUtil.getSysParamCfg(code);
					if(CollectionUtils.isNotEmpty(sysParamCfgs)){
						logo = sysParamCfgs.get(0).getParamValue();
					}
				}else{
					logo = activityCustom.getLogo();
				}
				dataMap.put("logo", StringUtil.getPic(logo, ""));
				dataMap.put("entryPic", FileUtil.getImageServiceUrl()+activityCustom.getAreaEntryPic());
				dataMap.put("name", activityCustom.getActivityAreaName());
				long ms = activityCustom.getActivityEndTime().getTime()-DateUtil.getDate().getTime();
				
				Long day = ms / dd;  
	    	    Long hour = (ms - day * dd) / hh;  
	    	    Long minute = (ms - day * dd - hour * hh) / mi;  
	    	    Long second = (ms - day * dd - hour * hh - minute * mi) / ss; 
	    	    
	    	    //String activityTime = "";
	    	    if(day > 0){
	    	    	if(hour > 0 || minute > 0 || second > 0){
	    	    		day = day + 1;
	    	    	}
	    	    	dataMap.put("activityTime", " 剩"+day.toString()+"天");
	    	    }else if(hour > 0){
	    	    	dataMap.put("activityTime", " 剩"+hour.toString()+"小时");
	    	    }else if(minute > 0){
	    	    	dataMap.put("activityTime", " 剩"+minute.toString()+"分");
	    	    }else{
	    	    	dataMap.put("activityTime", " 剩1分");
	    	    }
				String preferentialtype = activityCustom.getPreferentialType();
				dataMap.put("preferentialtype", preferentialtype);

				//特惠类型(0无 1优惠劵 2满减 3满赠 4多买优惠)
				dataMap.put("groups", activityCustom.getBenefitPoint());
				
				dataMap.put("totalPage", page.getTotalPage());
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
		map.put("name", "正在疯抢");
		return map;
	}



	public Map<String, Object> getCategoryBrandGroup(JSONObject reqPRM, JSONObject reqDataJson, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<>();
		Date currentDate = new Date();
		String activityType = Const.PRODUCT_ACTIVITY_TYPE_AREA;
		Integer showNum = 3;
		Integer brandTeamTypeId = null;
		Integer productType1Id = null;
		Integer memberId = null;
		Integer currentPage = reqDataJson.getInt("currentPage");
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
			// story 1643 隐藏指定特卖ID
			paramsMap.put("idNotIn", Arrays.asList(42122,41961,42118,42117,42016,41992,42099,42066,41885,41982,41954,41966,42010,42069,42134,42133));
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

	public List<ActivityCustom> getActivityByModuleItem(Map<String, Object> activityParamsMap) {
		
		return activityCustomMapper.getActivityByModuleItem(activityParamsMap);
	}

	public List<ActivityCustom> getActivityByIds(Map<String, Object> paramsMap) {
		
		return activityCustomMapper.getActivityByIds(paramsMap);
	}
	
}
