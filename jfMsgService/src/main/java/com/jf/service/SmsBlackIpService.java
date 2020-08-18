package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SmsBlackIpMapper;
import com.jf.entity.SmsBlackIp;
import com.jf.entity.SmsBlackIpExample;

/**

  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SmsBlackIpService extends BaseService<SmsBlackIp, SmsBlackIpExample> {
	@Autowired
	private SmsBlackIpMapper smsBlackIpMapper;

	@Autowired
	public void setSmsBlackIpMapper(SmsBlackIpMapper smsBlackIpMapper) {
		this.setDao(smsBlackIpMapper);
		this.smsBlackIpMapper = smsBlackIpMapper;
	}
	
}
