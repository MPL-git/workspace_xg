package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityProductAuditLogCustomMapper;
import com.jf.dao.ActivityProductAuditLogMapper;
import com.jf.entity.ActivityProductAuditLog;
import com.jf.entity.ActivityProductAuditLogCustom;
import com.jf.entity.ActivityProductAuditLogExample;

@Service
@Transactional
public class ActivityProductAuditLogService extends BaseService<ActivityProductAuditLog, ActivityProductAuditLogExample>{

	@Autowired
	private ActivityProductAuditLogMapper activityProductAuditLogMapper;
	
	@Autowired
	private ActivityProductAuditLogCustomMapper ActivityProductAuditLogCustomMapper;
	
	
	@Autowired
	public void setActivityAuditLogMapper(ActivityProductAuditLogMapper activityProductAuditLogMapper) {
		super.setDao(activityProductAuditLogMapper);
		this.activityProductAuditLogMapper = activityProductAuditLogMapper;
	}
	
	/**
	 * 根据活动id查找最新4条数据
	 * @param activityId
	 * @return
	 */
	public List<ActivityProductAuditLogCustom> selectByExample(Integer activityProductId){
		return ActivityProductAuditLogCustomMapper.selectByExample(activityProductId);
	}
	
	/**
	 * 根据活动id查找所有信息
	 * @param activityId
	 * @return
	 */
	public List<ActivityProductAuditLogCustom> selectActivityProductAuditLogCustomList(Integer activityProductId){
		return ActivityProductAuditLogCustomMapper.selectActivityProductAuditLogCustomList(activityProductId);
	}
	
	/**
	 * 根据活动id查找总条数
	 * @param activityId
	 * @return
	 */
	public Integer selectActivityProductAuditLogCustomCount(Integer activityProductId){
		return ActivityProductAuditLogCustomMapper.selectActivityProductAuditLogCustomCount(activityProductId);
	}
}
