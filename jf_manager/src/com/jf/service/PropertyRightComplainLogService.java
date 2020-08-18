package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PropertyRightComplainLogCustomMapper;
import com.jf.dao.PropertyRightComplainLogMapper;
import com.jf.entity.PropertyRightComplainLog;
import com.jf.entity.PropertyRightComplainLogCustom;
import com.jf.entity.PropertyRightComplainLogExample;


@Service
@Transactional
public class PropertyRightComplainLogService extends BaseService<PropertyRightComplainLog, PropertyRightComplainLogExample> {
	
	@Autowired
	private PropertyRightComplainLogMapper mapper;
	
	@Autowired
	private PropertyRightComplainLogCustomMapper customMapper;
	
	@Autowired
	public void setPropertyRightAppealLogMapper(PropertyRightComplainLogMapper propertyRightComplainLogMapper) {
		super.setDao(propertyRightComplainLogMapper);
		this.mapper = propertyRightComplainLogMapper;
	}
	
	public int countByExample(PropertyRightComplainLogExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<PropertyRightComplainLogCustom> selectCustomByExample(PropertyRightComplainLogExample example){
    	return customMapper.selectByExample(example);
    }
}
