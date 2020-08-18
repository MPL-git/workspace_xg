package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtPvStatisticalCustom;
import com.jf.entity.MchtPvStatisticalCustomExample;

@Repository
public interface MchtPvStatisticalCustomMapper{

	//商家流量统计列表数量
	Integer countCustomByExample(
			MchtPvStatisticalCustomExample mchtPvStatisticalCustomExample);

	//商家流量统计列表
	List<MchtPvStatisticalCustom> selectMchtPvStatisticalCustomByExample(
			MchtPvStatisticalCustomExample mchtPvStatisticalCustomExample);
	
	public Map<String, Object> getMchtPvStatisticalSumUp(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtPvPayAmountRank(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtPvPayAmountAvg(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtPvTotalVisitorCountRank(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtPvTotalVisitorCountAvg(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtPvPayMemberCountRank(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtPvPayMemberCountAvg(Map<String, Object> paramMap);
	
	public Map<String, Object> getFlowMchtProductPvMap(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtProductPvMap(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> getMchtProductPvList(Map<String, Object> paramMap);
	
	public Integer getMchtProductPvCount(Map<String, Object> paramMap);

	public Map<String, Object> getMchtVisitorMap(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtProductVisitorMap(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtVisitorPvMap(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtPvProductVisitorMap(Map<String, Object> paramMap);
	public Map<String, Object> getMchtMemberRemindCount(Map<String, Object> paramMap);
	
	public Map<String, Object> getMchtPvVisitorMap(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> getMchtPvStatisticalSourceList(Map<String, Object> paramMap);
	
	
}