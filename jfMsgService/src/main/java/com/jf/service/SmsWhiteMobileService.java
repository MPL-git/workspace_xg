package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SmsWhiteMobileMapper;
import com.jf.entity.SmsWhiteMobile;
import com.jf.entity.SmsWhiteMobileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**

  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SmsWhiteMobileService extends BaseService<SmsWhiteMobile, SmsWhiteMobileExample> {
	@Autowired
	private SmsWhiteMobileMapper smsWhiteMobileMapper;

	@Autowired
	public void setSmsBlackMobileMapper(SmsWhiteMobileMapper smsWhiteMobileMapper) {
		this.setDao(smsWhiteMobileMapper);
		this.smsWhiteMobileMapper = smsWhiteMobileMapper;
	}
	
}
