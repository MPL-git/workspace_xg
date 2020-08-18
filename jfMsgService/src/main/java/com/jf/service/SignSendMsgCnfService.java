package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SignSendMsgCnfMapper;
import com.jf.entity.SignSendMsgCnf;
import com.jf.entity.SignSendMsgCnfExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月25日 下午1:15:42 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SignSendMsgCnfService extends BaseService<SignSendMsgCnf, SignSendMsgCnfExample> {
	@Autowired
	private SignSendMsgCnfMapper signSendMsgCnfMapper;

	@Autowired
	public void setSignSendMsgCnfMapper(SignSendMsgCnfMapper signSendMsgCnfMapper) {
		this.setDao(signSendMsgCnfMapper);
		this.signSendMsgCnfMapper = signSendMsgCnfMapper;
	}
	
	
}
