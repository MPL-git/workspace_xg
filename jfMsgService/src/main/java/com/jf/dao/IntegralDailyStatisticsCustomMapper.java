package com.jf.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface IntegralDailyStatisticsCustomMapper{
	public int statisticsIntegral(Map paramMap);
	public int statisticsNotOrderIntegral(Map paramMap);
}