package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntegralGiveCustomMapper;
import com.jf.dao.IntegralGiveMapper;
import com.jf.entity.IntegralGive;
import com.jf.entity.IntegralGiveCustom;
import com.jf.entity.IntegralGiveExample;

@Service
@Transactional
public class IntegralGiveService extends BaseService<IntegralGive,IntegralGiveExample> {
	@Autowired
	private IntegralGiveMapper integralGiveMapper;
	@Autowired
	private IntegralGiveCustomMapper integralGiveCustomMapper;
	
	@Autowired
	public void setIntegralGiveMapper(IntegralGiveMapper integralGiveMapper) {
		super.setDao(integralGiveMapper);
		this.integralGiveMapper = integralGiveMapper;
	}
	
	public int countIntegralGiveCustomByExample(IntegralGiveExample example){
		return integralGiveCustomMapper.countByExample(example);
	}
    public List<IntegralGiveCustom> selectIntegralGiveCustomByExample(IntegralGiveExample example){
    	return integralGiveCustomMapper.selectByExample(example);
    }
    public IntegralGiveCustom selectIntegralGiveCustomByPrimaryKey(Integer id){
    	return integralGiveCustomMapper.selectByPrimaryKey(id);
    }
    
}
