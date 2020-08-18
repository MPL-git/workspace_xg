package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ViolateOrderCustom;
import com.jf.entity.ViolateOrderCustomExample;
import com.jf.vo.DebitRecord;
@Repository
public interface ViolateOrderCustomMapper{
	public void setMinDeductionQuotaNull(Integer id);
	
	public int countByExample(ViolateOrderCustomExample example);
	
	public List<ViolateOrderCustom>selectByExample(ViolateOrderCustomExample violateOrderCustomExample);

	public List<DebitRecord> selectDebitRecordList(HashMap<String, Object> paramMap);

	public int debitRecordListCount(HashMap<String, Object> paramMap);

	public List<Map<String, Object>> getAllCreateBy();

	public List<HashMap<String,Object>> getMchtInfoBySubOrderCode(String subOrderCode);
	
	public List<Map<String, Object>> getplatformProcessorList();//平台处理人集合

	public List<ViolateOrderCustom> selectExportViolateOrderCustomByExample(ViolateOrderCustomExample violateOrderCustomExample);

}