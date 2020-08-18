package com.jf.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntegralDailyStatisticsCustomMapper;
import com.jf.dao.IntegralDailyStatisticsMapper;
import com.jf.entity.IntegralDailyStatistics;
import com.jf.entity.IntegralDailyStatisticsExample;

@Service
@Transactional
public class IntegralDailyStatisticsService extends BaseService<IntegralDailyStatistics,IntegralDailyStatisticsExample> {
	@Autowired
	private IntegralDailyStatisticsMapper integralDailyStatisticsMapper;
	
	@Autowired
	private IntegralDailyStatisticsCustomMapper integralDailyStatisticsCustomMapper;
	
	@Autowired
	public void setIntegralDailyStatisticsMapper(IntegralDailyStatisticsMapper integralDailyStatisticsMapper) {
		super.setDao(integralDailyStatisticsMapper);
		this.integralDailyStatisticsMapper = integralDailyStatisticsMapper;
	}
	
	public Map<String, Object> statisticsIntegral(Map<String, Object> map) {
		return integralDailyStatisticsCustomMapper.statisticsIntegral(map);
	}
	
}
