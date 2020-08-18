package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ActivityAuditLogCustomMapper;
import com.jf.dao.ActivityAuditLogMapper;
import com.jf.entity.ActivityAuditLog;
import com.jf.entity.ActivityAuditLogCustom;
import com.jf.entity.ActivityAuditLogExample;

@Service
@Transactional
public class ActivityAuditLogService extends BaseService<ActivityAuditLog, ActivityAuditLogExample>{

	@Autowired
	private ActivityAuditLogMapper activityAuditLogMapper;
	
	@Autowired
	private ActivityAuditLogCustomMapper activityAuditLogCustomMapper;
	
	@Autowired
	public void setActivityAuditLogMapper(ActivityAuditLogMapper activityAuditLogMapper) {
		super.setDao(activityAuditLogMapper);
		this.activityAuditLogMapper = activityAuditLogMapper;
	}
	/**
	 * 根据活动id查找最新4条数据
	 * @param activityId
	 * @return
	 */
	public List<ActivityAuditLogCustom> selectActivityAuditLogList(Integer activityId){
		return activityAuditLogCustomMapper.selectByExample(activityId);
	}
	
	/**
	 * 根据活动id查找所有信息(列表)
	 * @param activityId
	 * @return
	 */
	public List<ActivityAuditLogCustom> selectActivityAuditLogCustomList(Integer activityId){
		return activityAuditLogCustomMapper.selectActivityAuditLogCustomList(activityId);
	}
	/**
	 * 根据活动id查找总条数
	 * @param activityId
	 * @return
	 */
	public Integer selectActivityAuditLogCustomCount(Integer activityId){
		return activityAuditLogCustomMapper.selectActivityAuditLogCustomCount(activityId);
	}
}
