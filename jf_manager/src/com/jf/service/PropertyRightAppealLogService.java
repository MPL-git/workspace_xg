package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PropertyRightAppealLogCustomMapper;
import com.jf.dao.PropertyRightAppealLogMapper;
import com.jf.entity.PropertyRightAppealLog;
import com.jf.entity.PropertyRightAppealLogCustom;
import com.jf.entity.PropertyRightAppealLogExample;


@Service
@Transactional
public class PropertyRightAppealLogService extends BaseService<PropertyRightAppealLog, PropertyRightAppealLogExample> {
	
	@Autowired
	private PropertyRightAppealLogMapper mapper;
	
	@Autowired
	private PropertyRightAppealLogCustomMapper customMapper;
	
	@Autowired
	public void setPropertyRightAppealLogMapper(PropertyRightAppealLogMapper propertyRightAppealLogMapper) {
		super.setDao(propertyRightAppealLogMapper);
		this.mapper = propertyRightAppealLogMapper;
	}
	
	public int countByExample(PropertyRightAppealLogExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<PropertyRightAppealLogCustom> selectCustomByExample(PropertyRightAppealLogExample example){
    	return customMapper.selectByExample(example);
    }
}
