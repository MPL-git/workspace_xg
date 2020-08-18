package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntegralTypeCustomMapper;
import com.jf.dao.IntegralTypeMapper;
import com.jf.entity.IntegralType;
import com.jf.entity.IntegralTypeCustom;
import com.jf.entity.IntegralTypeExample;

@Service
@Transactional
public class IntegralTypeService extends BaseService<IntegralType,IntegralTypeExample> {
	@Autowired
	private IntegralTypeMapper integralTypeMapper;
	@Autowired
	private IntegralTypeCustomMapper integralTypeCustomMapper;
	@Autowired
	public void setIntegralTypeMapper(IntegralTypeMapper integralTypeMapper) {
		super.setDao(integralTypeMapper);
		this.integralTypeMapper = integralTypeMapper;
	}
	
	public int countIntegralTypeCustomByExample(IntegralTypeExample example){
		return integralTypeCustomMapper.countByExample(example);
	}
    public List<IntegralTypeCustom> selectIntegralTypeCustomByExample(IntegralTypeExample example){
    	return integralTypeCustomMapper.selectByExample(example);
    }
    public IntegralTypeCustom selectIntegralTypeCustomByPrimaryKey(Integer id){
    	return integralTypeCustomMapper.selectByPrimaryKey(id);
    }
}
