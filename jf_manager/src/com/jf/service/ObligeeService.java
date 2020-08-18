package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ObligeeCustomMapper;
import com.jf.dao.ObligeeMapper;
import com.jf.entity.Obligee;
import com.jf.entity.ObligeeCustom;
import com.jf.entity.ObligeeExample;


@Service
@Transactional
public class ObligeeService extends BaseService<Obligee, ObligeeExample> {
	
	@Autowired
	private ObligeeMapper mapper;
	
	@Autowired
	private ObligeeCustomMapper customMapper;
	
	@Autowired
	public void setObligeeRightMapper(ObligeeMapper obligeeMapper) {
		super.setDao(obligeeMapper);
		this.mapper = obligeeMapper;
	}
	
	public int countByExample(ObligeeExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<Obligee> selectByExample(ObligeeExample example){
    	return this.mapper.selectByExample(example);
    }
    
    public Obligee selectByPrimaryKey(Integer id){
    	return this.mapper.selectByPrimaryKey(id);
    }
    
    public ObligeeCustom selectCustomByPrimaryKey(Integer id){
    	return customMapper.selectByPrimaryKey(id);
    }
	
}
