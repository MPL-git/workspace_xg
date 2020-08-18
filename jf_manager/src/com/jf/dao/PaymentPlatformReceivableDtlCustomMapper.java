package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.PaymentPlatformReceivableDtlCustom;
import com.jf.entity.PaymentPlatformReceivableDtlCustomExample;
@Repository
public interface PaymentPlatformReceivableDtlCustomMapper{
    int countByExample(PaymentPlatformReceivableDtlCustomExample example);
    List<PaymentPlatformReceivableDtlCustom> selectByExample(PaymentPlatformReceivableDtlCustomExample example);
    int getSequence(String name);
	void updateByBatchNo(int batchNo);
	List<HashMap<String, Object>> receivedEachDayCompare(Map<String, Object> paramMap);
}