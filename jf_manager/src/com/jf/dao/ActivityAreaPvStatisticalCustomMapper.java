package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityAreaPvStatisticalCustom;
import com.jf.entity.ActivityAreaPvStatisticalCustomExample;

@Repository
public interface ActivityAreaPvStatisticalCustomMapper{

	//每日会场统计数量
	Integer countCustomByExample(
			ActivityAreaPvStatisticalCustomExample activityAreaPvStatisticalCustomExample);

	//每日会场统计列表
	List<ActivityAreaPvStatisticalCustom> selectActivityAreaPvStatisticalCustomByExample(
			ActivityAreaPvStatisticalCustomExample activityAreaPvStatisticalCustomExample);

	//每日会场统计会场详情列表
	List<Map<String, Object>> selectActivityAreaPvStatisticalDouble(Map<String, Object> map);
	//每日会场统计会场详情列表数量
	Integer countCustomDouble(Map<String, Object> map);
	
	//每日会场统计会场详情列表
	List<Map<String, Object>> selectActivityAreaPvStatisticalSingle(Map<String, Object> map);
	//每日会场统计会场详情列表数量
	Integer countCustomSingle(Map<String, Object> map);
  
}