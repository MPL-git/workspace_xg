package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PropertyRightAppealPicMapper;
import com.jf.entity.PropertyRightAppealPic;
import com.jf.entity.PropertyRightAppealPicExample;


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
	
	public int countByExample(PropertyRightAppealPicExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<PropertyRightAppealPic> selectByExample(PropertyRightAppealPicExample example){
    	return this.mapper.selectByExample(example);
    }
}
