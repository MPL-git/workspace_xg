package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.PropertyRightAppealPicMapper;
import com.jf.entity.PropertyRightAppealPic;
import com.jf.entity.PropertyRightAppealPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PropertyRightAppealPicService extends BaseService<PropertyRightAppealPic, PropertyRightAppealPicExample> {
	
	@Autowired
	private PropertyRightAppealPicMapper mapper;
	
	@Autowired
	public void setPropertyRightAppealPicMapper(PropertyRightAppealPicMapper propertyRightAppealPicMapper) {
		super.setDao(propertyRightAppealPicMapper);
		this.mapper = propertyRightAppealPicMapper;
	}
}
