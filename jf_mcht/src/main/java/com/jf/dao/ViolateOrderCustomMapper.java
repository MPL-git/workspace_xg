package com.jf.dao;

import com.jf.entity.ViolateOrderCustom;
import com.jf.entity.ViolateOrderCustomExample;
import com.jf.vo.DebitRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ViolateOrderCustomMapper{
	public int countByExample(ViolateOrderCustomExample example);
	
	public List<ViolateOrderCustom>selectByExample(ViolateOrderCustomExample violateOrderCustomExample);

	public List<String> getViolateTypesByMchtId(Integer mchtId);

	public Integer getSubOrderIdByActivityId(Integer activityId);

	public List<DebitRecord> getDebitRecordList(Map<String, Object> map);
}