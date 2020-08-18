package com.jf.dao;

import com.jf.entity.ActivityPvStatisticalCustom;
import com.jf.entity.ActivityPvStatisticalCustomExample;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ActivityPvStatisticalCustomMapper {

	//每日特卖统计列表查询
	Integer countCustomByExample(
			ActivityPvStatisticalCustomExample activityPvStatisticalCustomExample);

	//每日特卖统计列表数量
	List<ActivityPvStatisticalCustom> selectActivityPvStatisticalCustomByExample(
			ActivityPvStatisticalCustomExample activityPvStatisticalCustomExample);

    Integer countActivityPvStatistical(Map<String, Object> paraMap);

	List<ActivityPvStatisticalCustom> selectActivityPvStatistical(Map<String, Object> paraMap);

    List<HashMap<String,Object>> selectBrowseNumberByActivityId(Map<String, Object> paraMap);
}