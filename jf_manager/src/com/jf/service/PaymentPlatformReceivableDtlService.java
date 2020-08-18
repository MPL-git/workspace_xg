package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.PaymentPlatformReceivableDtlCustomMapper;
import com.jf.dao.PaymentPlatformReceivableDtlMapper;
import com.jf.entity.PaymentPlatformReceivableDtl;
import com.jf.entity.PaymentPlatformReceivableDtlCustom;
import com.jf.entity.PaymentPlatformReceivableDtlCustomExample;
import com.jf.entity.PaymentPlatformReceivableDtlExample;
@Service
public class PaymentPlatformReceivableDtlService extends BaseService<PaymentPlatformReceivableDtl,PaymentPlatformReceivableDtlExample> {
	@Autowired
	private PaymentPlatformReceivableDtlMapper paymentPlatformReceivableDtlMapper;
	
	@Autowired
	private PaymentPlatformReceivableDtlCustomMapper paymentPlatformReceivableDtlCustomMapper;
	
	@Autowired
	public void setPaymentPlatformReceivableDtlMapper(PaymentPlatformReceivableDtlMapper paymentPlatformReceivableDtlMapper) {
		super.setDao(paymentPlatformReceivableDtlMapper);
		this.paymentPlatformReceivableDtlMapper = paymentPlatformReceivableDtlMapper;
	}
	
	public int countByExample(PaymentPlatformReceivableDtlCustomExample example){
		return paymentPlatformReceivableDtlCustomMapper.countByExample(example);
	}
	
	public List<PaymentPlatformReceivableDtlCustom> selectByExample(PaymentPlatformReceivableDtlCustomExample example){
		return paymentPlatformReceivableDtlCustomMapper.selectByExample(example);
	}
	
	public int getSequence(String name){
		return paymentPlatformReceivableDtlCustomMapper.getSequence(name);
	}

	public void saveAndUpdate(List<PaymentPlatformReceivableDtl> paymentPlatformReceivableDtlList,int batchNo) {
		for(PaymentPlatformReceivableDtl paymentPlatformReceivableDtl:paymentPlatformReceivableDtlList){
			this.insertSelective(paymentPlatformReceivableDtl);
		}
		paymentPlatformReceivableDtlCustomMapper.updateByBatchNo(batchNo);
	}

	public List<HashMap<String, Object>> receivedEachDayCompare(Map<String, Object> paramMap) {
		return paymentPlatformReceivableDtlCustomMapper.receivedEachDayCompare(paramMap);
	}
}
