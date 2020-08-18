package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MsgTplCustomMapper;
import com.jf.dao.MsgTplMapper;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplCustom;
import com.jf.entity.MsgTplExample;

@Service
@Transactional
public class MsgTplService extends BaseService<MsgTpl, MsgTplExample> {
	@Autowired
	private MsgTplMapper msgTplMapper;
	@Autowired
	private MsgTplCustomMapper msgTplCustomMapper;

	@Autowired
	public void setMsgTplMapper(MsgTplMapper msgTplMapper) {
		super.setDao(msgTplMapper);
		this.msgTplMapper = msgTplMapper;
	}
	
	public int countMsgTplCustomByExample(MsgTplExample example){
		return msgTplCustomMapper.countByExample(example);
	}
    public List<MsgTplCustom> selectMsgTplCustomByExample(MsgTplExample example){
    	return msgTplCustomMapper.selectByExample(example);
    }
    public MsgTplCustom selectMsgTplCustomByPrimaryKey(Integer id){
    	return msgTplCustomMapper.selectByPrimaryKey(id);
    }
	
}
