package com.jf.service;

import com.jf.dao.ActivityPvStatisticalCustomMapper;
import com.jf.entity.ActivityPvStatistical;
import com.jf.entity.ActivityPvStatisticalCustom;
import com.jf.entity.ActivityPvStatisticalCustomExample;
import com.jf.entity.ActivityPvStatisticalExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ActivityPvStatisticalService extends BaseService<ActivityPvStatistical, ActivityPvStatisticalExample>{
	
	@Autowired
	private ActivityPvStatisticalCustomMapper activityPvStatisticalCustomMapper;
	//每日特卖统计列表查询
	public Integer countCustomByExample(
			ActivityPvStatisticalCustomExample activityPvStatisticalCustomExample) {
		// TODO Auto-generated method stub
		Integer total = activityPvStatisticalCustomMapper.countCustomByExample(activityPvStatisticalCustomExample);
		return total;
	}
	
	//每日特卖统计列表数量
	public List<ActivityPvStatisticalCustom> selectActivityPvStatisticalCustomByExample(
			ActivityPvStatisticalCustomExample activityPvStatisticalCustomExample) {
		// TODO Auto-generated method stub
		List<ActivityPvStatisticalCustom> list = activityPvStatisticalCustomMapper.selectActivityPvStatisticalCustomByExample(activityPvStatisticalCustomExample);
		return list;
	}


    public Integer countActivityPvStatistical(Map<String, Object> paraMap) {
		return activityPvStatisticalCustomMapper.countActivityPvStatistical(paraMap);
    }

	public List<ActivityPvStatisticalCustom> selectActivityPvStatistical(Map<String, Object> paraMap) {
		return activityPvStatisticalCustomMapper.selectActivityPvStatistical(paraMap);
	}

    public List<HashMap<String,Object>> selectBrowseNumberByActivityId(Map<String, Object> paraMap) {
		return activityPvStatisticalCustomMapper.selectBrowseNumberByActivityId(paraMap);
    }
}
