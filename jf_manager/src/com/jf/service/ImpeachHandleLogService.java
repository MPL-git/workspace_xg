package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ImpeachHandleLogCustomMapper;
import com.jf.dao.ImpeachHandleLogMapper;
import com.jf.entity.ImpeachHandleLog;
import com.jf.entity.ImpeachHandleLogCustom;
import com.jf.entity.ImpeachHandleLogCustomExample;
import com.jf.entity.ImpeachHandleLogExample;

@Service
@Transactional
public class ImpeachHandleLogService extends BaseService<ImpeachHandleLog,ImpeachHandleLogExample> {
	@Autowired
	private ImpeachHandleLogMapper impeachHandleLogMapper;
	@Autowired
	private ImpeachHandleLogCustomMapper impeachHandleLogCustomMapper;	
	@Autowired
	public void setImpeachHandleLogMapper(ImpeachHandleLogMapper impeachHandleLogMapper) {
		super.setDao(impeachHandleLogMapper);
		this.impeachHandleLogMapper = impeachHandleLogMapper;
	}
	
	public int countByImpeachHandleLogCustomExample(ImpeachHandleLogCustomExample example){
		return impeachHandleLogCustomMapper.countByImpeachHandleLogCustomExample(example);
	}
    public List<ImpeachHandleLogCustom> selectByImpeachHandleLogCustomExample(ImpeachHandleLogCustomExample example){
    	return impeachHandleLogCustomMapper.selectByImpeachHandleLogCustomExample(example);
    }
    public ImpeachHandleLogCustom selectByImpeachHandleLogCustomPrimaryKey(Integer id){
    	return impeachHandleLogCustomMapper.selectByImpeachHandleLogCustomPrimaryKey(id);
    }
}
