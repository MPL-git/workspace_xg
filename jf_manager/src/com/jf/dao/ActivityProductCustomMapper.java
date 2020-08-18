package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityProduct;
import com.jf.entity.ActivityProductCustom;
import com.jf.entity.ActivityProductCustomExample;
import com.jf.entity.ActivityProductCustomNew;
import com.jf.entity.ActivityProductExample;

@Repository
public interface ActivityProductCustomMapper {
	
	/*public List<ActivityProductCustom> selectByCustomExample(Map<String, Object> map);
	
	public Integer countByCustomExample(Map<String, Object> map);*/
	
	public List<ActivityProductCustom> selectByCustomExample(ActivityProductExample example);
	
	public Integer countByCustomExample(ActivityProductExample example);
	
	/**
	 * 获取活动下的商品列表
	 * @param map
	 * @return
	 */
	public List<ActivityProductCustom> getActivityIdByProductList(Map<String, Object> map);
	/**
	 * 获取活动下的商品列表总条数
	 * @param map
	 * @return
	 */
	public Integer getActivityIdByProductCount(Map<String, Object> map);
	
	/**
	 * 获得商家对接运营专员staffId
	 * @param productId
	 * @return
	 */
	public Integer getStaffId(Integer productId);
	
	/**
	 * 获得商家对接运营专员staffId
	 * @param productId
	 * @return
	 */
	public Integer getStaffIdByActivity(Integer activityId);
	
	/**
	 * 获取活动商品详情
	 * @param activityProductId
	 * @return
	 */
	public ActivityProductCustom selectByActivityProductInfo(Integer activityProductId);
	
	public List<ActivityProduct> getActivityProductList(Integer activityId);
	/**
	 * 设置更新运营专员审核商品通过
	 * @param activityId
	 */
	public void updateOperateAuditAdopt(Map<String, Object> map);
	/**
	 * 设置更新设计专员审核商品通过
	 * @param activityId
	 */
	public void updateDesignAuditAdopt(Map<String, Object> map);
	/**
	 * 设置更新法务专员审核商品通过
	 * @param activityId
	 */
	public void updateLawAuditAdopt(Map<String, Object> map);
	/**
	 * 设置更新运营总监专员审核商品通过
	 * @param activityId
	 */
	public void updateCooAuditAdopt(Map<String, Object> map);

	public List<ActivityProductCustom> getProductsByActivityAreaId(Integer activityAreaId);

	public void saveSort(HashMap<String, Object> map);
	
	public List<ActivityProductCustom> shopcustom(HashMap<String, Object> paramMap);
	
	/**
	 * 
	 * @Title selectByCustomNewExample   
	 * @Description TODO(新版品牌特卖审核商品)   
	 * @author pengl
	 * @date 2018年1月15日 下午3:28:19
	 */
	public List<ActivityProductCustomNew> selectByCustomNewExample(ActivityProductCustomExample activityProductCustomExample);
	
	
	/**
	 * 
	 * @Title updateActivityProductIdListSelective   
	 * @Description TODO(批量修改活动商品)   
	 * @author pengl
	 * @date 2018年7月25日 下午5:25:57
	 */
	public void updateActivityProductIdListSelective(List<ActivityProduct> activityProductList);
	
	
	/**
	 * 
	 * @Title selectActivityProductCustomNewExample   
	 * @Description TODO(取同商家同品牌同货号商品中历史静态活动价的最低值（不包含当前静态活动价）)   
	 * @author pengl
	 * @date 2018年7月27日 下午2:09:00
	 */
	public List<ActivityProductCustomNew> selectActivityProductCustomNewExample(ActivityProductCustomExample activityProductCustomExample);
	public Integer countActivityProductCustomNewExample(ActivityProductCustomExample activityProductCustomExample);
	
	
	
	
	
}
