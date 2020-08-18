package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.PropertyRightComplainMapper;
import com.jf.entity.PropertyRightComplain;
import com.jf.entity.PropertyRightComplainExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PropertyRightComplainService extends BaseService<PropertyRightComplain, PropertyRightComplainExample> {
	
	@Autowired
	private PropertyRightComplainMapper mapper;
	

	@Autowired
	public void setPropertyRightComplainMapper(PropertyRightComplainMapper propertyRightComplainMapper) {
		super.setDao(propertyRightComplainMapper);
		this.mapper = propertyRightComplainMapper;
	}
	
}
