package com.jf.service;

import com.jf.dao.PayToMchtLogCustomMapper;
import com.jf.dao.PayToMchtLogMapper;
import com.jf.entity.PayToMchtLog;
import com.jf.entity.PayToMchtLogCustom;
import com.jf.entity.PayToMchtLogCustomExample;
import com.jf.entity.PayToMchtLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PayToMchtLogService extends BaseService<PayToMchtLog,PayToMchtLogExample> {
	@Autowired
	private PayToMchtLogMapper payToMchtLogMapper;
	
	@Autowired
	private PayToMchtLogCustomMapper payToMchtLogCustomMapper;
	@Autowired
	public void setPayToMchtLogMapper(PayToMchtLogMapper payToMchtLogMapper) {
		super.setDao(payToMchtLogMapper);
		this.payToMchtLogMapper = payToMchtLogMapper;
	}
	
	public int countByExample(PayToMchtLogCustomExample example){
		return payToMchtLogCustomMapper.countByExample(example);
	}
	
	public List<PayToMchtLogCustom> selectByExample(PayToMchtLogCustomExample example){
		return payToMchtLogCustomMapper.selectByExample(example);
	}
}
