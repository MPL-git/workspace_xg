package com.jf.dao;

import com.jf.entity.ActivityCustom;
import com.jf.vo.request.allowance.FindAreaProductListRequest;
import com.jf.vo.response.allowance.AllowanceProductTypeView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ActivityCustomMapper{

	List<ActivityCustom> getActivityCustomList(Map<String, Object> params);

	Integer getActivityCustomCount(Map<String, Object> params);

	Integer getNewUserEnjoyCount(Map<String, Object> params);

	List<ActivityCustom> getNewUserEnjoyList(Map<String, Object> params);

	Integer getCurrentMaxActivityAreaId(Map<String, Object> params);

	Integer getActivityPreheatCount(Map<String, Object> params);

	List<ActivityCustom> getActivityPreheatList(Map<String, Object> params);

	List<ActivityCustom> getProductByActiviTyId(Map<String, Object> params);

	ActivityCustom getActivityInfoById(Integer activityId);

	ActivityCustom selectActivityAreaInfo(Map<String, Object> activityParams);

	List<ActivityCustom> getProductCount(Map<String, Object> params);

	List<ActivityCustom> getNewUserEnjoyCategory(Map<String, Object> params);

	List<ActivityCustom> getOrderDataByCombineOrderId(Integer combineOrderId);

	List<ActivityCustom> getBrandRecommendActivityByMchtId(Map<String, Object> brandParams);

	List<ActivityCustom> getActivityByModuleItem(Map<String, Object> activityParamsMap);

	List<ActivityCustom> getActivityByIds(Map<String, Object> paramsMap);

	List<ActivityCustom> getActivityProductInfo(Map<String, Object> paramsMap);

	List<ActivityCustom> getActivityBrandGroupActivityList(Map<String, Object> params);
	/**
	 * 类目品牌团
	 */
	List<ActivityCustom> getCategoryBrandGroup(Map<String, Object> paramsMap);

    List<ActivityCustom> findAreaProductList(FindAreaProductListRequest findAreaProductListRequest);

    List<AllowanceProductTypeView> listAllowanceProductType(@Param("activityAreaId") Integer activityAreaId);

}