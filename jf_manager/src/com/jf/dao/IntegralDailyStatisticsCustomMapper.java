package com.jf.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface IntegralDailyStatisticsCustomMapper {

	public Map<String, Object> statisticsIntegral(Map<String, Object> map);
	
}
