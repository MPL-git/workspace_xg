package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PropertyRightProsecutionLogMapper;
import com.jf.entity.PropertyRightProsecutionLog;
import com.jf.entity.PropertyRightProsecutionLogExample;


@Service
@Transactional
public class PropertyRightProsecutionLogService extends BaseService<PropertyRightProsecutionLog, PropertyRightProsecutionLogExample> {
	
	@Autowired
	private PropertyRightProsecutionLogMapper mapper;
	
	@Autowired
	public void setPropertyRightAppealLogMapper(PropertyRightProsecutionLogMapper propertyRightProsecutionLogMapper) {
		super.setDao(propertyRightProsecutionLogMapper);
		this.mapper = propertyRightProsecutionLogMapper;
	}
	
	public int countByExample(PropertyRightProsecutionLogExample example){
		return this.mapper.countByExample(example);
	}
	
//    public List<PropertyRightProsecutionLog> selectCustomByExample(PropertyRightProsecutionLogExample example){
//    	return customMapper.selectByExample(example);
//    }
}
