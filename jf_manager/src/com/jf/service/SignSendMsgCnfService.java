package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PayToMchtLogMapper;
import com.jf.dao.SignSendMsgCnfCustomMapper;
import com.jf.dao.SignSendMsgCnfMapper;
import com.jf.entity.SignSendMsgCnf;
import com.jf.entity.SignSendMsgCnfCustom;
import com.jf.entity.SignSendMsgCnfCustomExample;
import com.jf.entity.SignSendMsgCnfExample;

@Service
@Transactional
public class SignSendMsgCnfService extends BaseService<SignSendMsgCnf, SignSendMsgCnfExample> {

	@Autowired
	private SignSendMsgCnfMapper signSendMsgCnfMapper;
	
	@Autowired
	private SignSendMsgCnfCustomMapper signSendMsgCnfCustomMapper;
	
	@Autowired
	public void setSignSendMsgCnfMapper(SignSendMsgCnfMapper signSendMsgCnfMapper) {
		super.setDao(signSendMsgCnfMapper);
		this.signSendMsgCnfMapper = signSendMsgCnfMapper;
	}
	
	public int countByCustomExample(SignSendMsgCnfCustomExample example) {
		return signSendMsgCnfCustomMapper.countByCustomExample(example);
	}

	public List<SignSendMsgCnfCustom> selectByCustomExample(SignSendMsgCnfCustomExample example) {
		return signSendMsgCnfCustomMapper.selectByCustomExample(example);
	}

	public SignSendMsgCnfCustom selectByCustomPrimaryKey(Integer id) {
		return signSendMsgCnfCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(SignSendMsgCnf record, SignSendMsgCnfCustomExample example) {
		return signSendMsgCnfCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	
}
