package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityAuditLogCustom;
@Repository
public interface ActivityAuditLogCustomMapper{
	
	/**
	 * 根据活动id查找最新4条数据
	 * @param activityId
	 * @return
	 */
	public List<ActivityAuditLogCustom> selectByExample(Integer activityId);
	
	/**
	 * 根据活动id查找所有信息
	 * @param activityId
	 * @return
	 */
	public List<ActivityAuditLogCustom> selectActivityAuditLogCustomList(Integer activityId);
	
	/**
	 * 根据活动id查找总条数
	 * @param activityId
	 * @return
	 */
	public Integer selectActivityAuditLogCustomCount(Integer activityId);
    
}