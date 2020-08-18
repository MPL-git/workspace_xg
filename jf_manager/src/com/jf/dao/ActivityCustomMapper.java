package com.jf.dao;

import com.jf.entity.Activity;
import com.jf.entity.ActivityCustom;
import com.jf.entity.ActivityCustomExample;
import com.jf.entity.ActivityExample;
import com.jf.entity.ActivityNewCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface ActivityCustomMapper{
    int countByExample(ActivityExample example);

    List<ActivityCustom> selectByExample(ActivityExample example);

    ActivityCustom selectByPrimaryKey(Integer id);
    
    public Integer selectByProductIdList(Integer activityId);
    
    public List<ActivityCustom> selectMchtActivityList(Map<String, Object> map);
    
    public List<Integer> getActivityByIdList(Integer activityAreaId);
    
    public List<Map<String, Object>> getAuditByList(Map<String, Object> map);
    
    public List<ActivityNewCustom> selectByCustomExample(ActivityCustomExample activityCustomExample);
    
    public ActivityNewCustom selectByCustomPrimaryKey(Integer id);

	List<Integer> getProductIdsByActivityIds(String activityIds);

	List<String> getEntryPics(HashMap<String, Object> paramMap);
	
	public Integer updateByCustomExampleSelective(@Param("record") Activity record, @Param("example") ActivityCustomExample example);

    Integer countActivityTrafficStatisticsRealTime(HashMap<String, Object> paraMap);

    List<HashMap<String,Object>> selectActivityTrafficStatisticsRealTime(HashMap<String, Object> paraMap);
}