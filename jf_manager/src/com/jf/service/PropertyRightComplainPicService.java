package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntellectualPropertyRightPicMapper;
import com.jf.dao.PropertyRightComplainPicMapper;
import com.jf.entity.IntellectualPropertyRightPic;
import com.jf.entity.IntellectualPropertyRightPicExample;
import com.jf.entity.PropertyRightComplainPic;
import com.jf.entity.PropertyRightComplainPicExample;


@Service
@Transactional
public class PropertyRightComplainPicService extends BaseService<PropertyRightComplainPic, PropertyRightComplainPicExample> {
	
	@Autowired
	private PropertyRightComplainPicMapper mapper;
	
	@Autowired
	public void setPropertyRightComplainPicMapper(PropertyRightComplainPicMapper propertyRightComplainPicMapper) {
		super.setDao(propertyRightComplainPicMapper);
		this.mapper = propertyRightComplainPicMapper;
	}
	
	public int countByExample(PropertyRightComplainPicExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<PropertyRightComplainPic> selectByExample(PropertyRightComplainPicExample example){
    	return this.mapper.selectByExample(example);
    }
}
