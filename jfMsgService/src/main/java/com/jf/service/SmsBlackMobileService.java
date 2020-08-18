package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SmsBlackMobileMapper;
import com.jf.entity.SmsBlackMobile;
import com.jf.entity.SmsBlackMobileExample;

/**

  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SmsBlackMobileService extends BaseService<SmsBlackMobile, SmsBlackMobileExample> {
	@Autowired
	private SmsBlackMobileMapper smsBlackMobileMapper;

	@Autowired
	public void setSmsBlackMobileMapper(SmsBlackMobileMapper smsBlackMobileMapper) {
		this.setDao(smsBlackMobileMapper);
		this.smsBlackMobileMapper = smsBlackMobileMapper;
	}
	
}
