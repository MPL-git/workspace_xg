package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.ActivityAreaPvStatisticalCustomMapper;
import com.jf.entity.ActivityAreaPvStatistical;
import com.jf.entity.ActivityAreaPvStatisticalCustom;
import com.jf.entity.ActivityAreaPvStatisticalCustomExample;
import com.jf.entity.ActivityAreaPvStatisticalExample;

@Service
public class ActivityAreaPvStatisticalService extends BaseService<ActivityAreaPvStatistical, ActivityAreaPvStatisticalExample>{
	
	@Autowired
	private ActivityAreaPvStatisticalCustomMapper activityAreaPvStatisticalCustomMapper;
	//每日会场统计数量
	public Integer countCustomByExample(
			ActivityAreaPvStatisticalCustomExample activityAreaPvStatisticalCustomExample) {
		// TODO Auto-generated method stub
		return activityAreaPvStatisticalCustomMapper.countCustomByExample(activityAreaPvStatisticalCustomExample);
		
	}

	//每日会场统计列表
	public List<ActivityAreaPvStatisticalCustom> selectActivityAreaPvStatisticalCustomByExample(
			ActivityAreaPvStatisticalCustomExample activityAreaPvStatisticalCustomExample) {
		// TODO Auto-generated method stub
		return activityAreaPvStatisticalCustomMapper.selectActivityAreaPvStatisticalCustomByExample(activityAreaPvStatisticalCustomExample);
	}
	
	//每日会场统计会场详情列表数量
	public Integer countCustomDouble(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return activityAreaPvStatisticalCustomMapper.countCustomDouble(map);
	}

	//每日会场统计会场详情列表
	public List<Map<String, Object>> selectActivityAreaPvStatisticalDouble(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return activityAreaPvStatisticalCustomMapper.selectActivityAreaPvStatisticalDouble(map);
	}
	
	//每日会场统计会场详情列表数量
		public Integer countCustomSingle(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return activityAreaPvStatisticalCustomMapper.countCustomSingle(map);
		}

		//每日会场统计会场详情列表
		public List<Map<String, Object>> selectActivityAreaPvStatisticalSingle(Map<String, Object> map) {
			// TODO Auto-generated method stub
			return activityAreaPvStatisticalCustomMapper.selectActivityAreaPvStatisticalSingle(map);
		}
}
