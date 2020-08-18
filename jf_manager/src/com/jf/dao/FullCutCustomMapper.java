package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.FullCutCustom;

@Repository
public interface FullCutCustomMapper {
	
	/**
	 * 通过会场Id搜索满减表
	 * @param activityId
	 * @return
	 */
	public FullCutCustom selectByActivityIdList(Integer activityId);
	/**
	 * 详情
	 * @param activityId
	 * @return
	 */
	public FullCutCustom selectByActivityId(Integer activityId);

}
