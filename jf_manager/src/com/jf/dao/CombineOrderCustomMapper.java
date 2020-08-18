package com.jf.dao;

import com.jf.common.ext.query.QueryObject;
import com.jf.entity.CombineOrderCustom;
import com.jf.entity.CombineOrderCustomExample;
import com.jf.entity.CombineOrderExample;

import com.jf.vo.MemberPayDataVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public interface CombineOrderCustomMapper{
	public int countByExample(CombineOrderExample example);
    public List<CombineOrderCustom> selectByExample(CombineOrderExample example);
    public CombineOrderCustom selectByPrimaryKey(Integer id);
    public List<CombineOrderCustom> receivablesCountEachDayList(HashMap<String, Object> paramMap);

	public int countByExampleGroupByMemberId(CombineOrderExample combineOrderExample);
    public void updateCombineOrders(HashMap<String, Object> paramMap);
	public List<Map<String, Object>> receivablesCount(Map<String, Object> paramMap);
	
	public Integer countByCustomExample(CombineOrderCustomExample example);
	public List<CombineOrderCustom> selectByCustomExample(CombineOrderCustomExample example);
	public List<HashMap<String, Object>> androidCount(HashMap<String, Object> paramMap);
	public List<HashMap<String, Object>> totalAndroidCount(HashMap<String, Object> paramMap);
	public List<HashMap<String, Object>> iosCount(HashMap<String, Object> paramMap);
	public List<HashMap<String, Object>> totalIosCount(HashMap<String, Object> paramMap);
	
	public List<HashMap<String, Object>> androidCountNew(HashMap<String, Object> paramMap);
	
	public List<HashMap<String, Object>> androidCountNewNull(HashMap<String, Object> paramMap);
	
	public List<HashMap<String, Object>> iosCountNew(HashMap<String, Object> paramMap);
	
	public List<HashMap<String, Object>> iosCountNewNull(HashMap<String, Object> paramMap);
	
	public List<HashMap<String, Object>> androidCountReturn(HashMap<String, Object> paramMap);

	public List<HashMap<String, Object>> totalAndroidCountReturn(HashMap<String, Object> paramMap);
	
	public List<HashMap<String, Object>> iosCountReturn(HashMap<String, Object> paramMap);
	
	public List<HashMap<String, Object>> totalIosCountReturn(HashMap<String, Object> paramMap);
	
    public List<CombineOrderCustom> paymentIncomeCountEachDayList(HashMap<String, Object> paramMap);

    public Integer paymentIncomeCountEachDayCount(Map<String, Object> paramMap);

	List<MemberPayDataVo> statMemberPayData(QueryObject queryObject);
	List<MemberPayDataVo> statSvipMemberPayData(HashMap<String, Object> paramMap);

	public List<Map<String, Object>> androidChannelGroupList(Map<String, Object> paramMap);
	public List<Map<String, Object>> androidChannelGroupNull(Map<String, Object> paramMap);

	public List<Map<String, Object>> spreadActivityGroupList(Map<String, Object> paramMap);
	public List<Map<String, Object>> spreadActivityGroupNull(Map<String, Object> paramMap);

	public Map<String, Object> iosSpreadChannelDataTotal(Map<String, Object> paramMap);
	public Map<String, Object> androidSpreadChannelDataTotal(Map<String, Object> paramMap);

	public List<Map<String, Object>> iosSpreadChannelDataList(Map<String, Object> paramMap);
	public List<Map<String, Object>> androidSpreadChannelDataList(Map<String, Object> paramMap);

	public Map<String, Object> getTotalPayAmountSum(Map<String, Object> paramMap);

}