package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtInfo;

@Repository
public interface MchtMonthTradeCustomMapper {
    
	public List<MchtInfo> selectNoMchtMonthTradeMchtList(String tradeMonth);
	
	public List<Map<String, Object>> selectNoMchtMonthTradeOrderDtlList(Map<String, Object> map);
	
	public List<Map<String, Object>> selectNoMchtMonthTradePayToMchtLogList(Map<String, Object> map);
	
	public List<Map<String, Object>> selectNoMchtMonthTradeMchtDepositDtlList(Map<String, Object> map);
	
	
	
}