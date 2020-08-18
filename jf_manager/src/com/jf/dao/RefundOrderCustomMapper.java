package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.RefundOrderCustom;
import com.jf.entity.RefundOrderCustomExample;
@Repository
public interface RefundOrderCustomMapper{
	public List<RefundOrderCustom>selectByRefundOrderCustomExample(RefundOrderCustomExample example);

	public int countByExample(RefundOrderCustomExample example);

	public List<RefundOrderCustom> selectByExample(RefundOrderCustomExample example);

	public List<RefundOrderCustom> refundOrderCountEachDayList(HashMap<String, Object> paramMap);

	public List<Integer> getIdsByRefundCode(HashMap<String, Object> paramMap);

	public List<Map<String, Object>> refundOrderCount(Map<String, Object> paramMap);

	public List<RefundOrderCustom> depositOrderRefundOrderCountEachDayList(HashMap<String, Object> paramMap);
	
	public List<RefundOrderCustom> paymenExpenditureCountEachDayList(HashMap<String, Object> paramMap);
	
	public Integer paymenExpenditureCountEachDayCount(HashMap<String, Object> paramMap);

	public List<Map<String, Object>> refundOrderStatistics(Map<String, Object> paramMap);
	
}