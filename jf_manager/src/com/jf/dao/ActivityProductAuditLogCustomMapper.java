package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityProductAuditLog;
import com.jf.entity.ActivityProductAuditLogCustom;
@Repository
public interface ActivityProductAuditLogCustomMapper{
	
	/**
	 * 根据活动id查找最新4条数据
	 * @param activityId
	 * @return
	 */
	public List<ActivityProductAuditLogCustom> selectByExample(Integer activityProductId);
	
	/**
	 * 根据活动id查找所有信息
	 * @param activityId
	 * @return
	 */
	public List<ActivityProductAuditLogCustom> selectActivityProductAuditLogCustomList(Integer activityProductId);
	
	/**
	 * 根据活动id查找总条数
	 * @param activityId
	 * @return
	 */
	public Integer selectActivityProductAuditLogCustomCount(Integer activityProductId);
    
	/**
	 * 批量新增活动商品审核流水信息
	 * @param activityProductAuditLog
	 * @return
	 */
	public void insertActivityProductAuditLogs(List<ActivityProductAuditLog> activityProductAuditLogs);
}