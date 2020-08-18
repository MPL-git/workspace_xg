package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PropertyRightProsecutionPicMapper;
import com.jf.entity.PropertyRightAppealPic;
import com.jf.entity.PropertyRightAppealPicExample;
import com.jf.entity.PropertyRightProsecutionPic;
import com.jf.entity.PropertyRightProsecutionPicExample;


@Service
@Transactional
public class PropertyRightProsecutionPicService extends BaseService<PropertyRightProsecutionPic, PropertyRightProsecutionPicExample> {
	
	@Autowired
	private PropertyRightProsecutionPicMapper mapper;
	
	@Autowired
	public void setPropertyRightProsecutionPicMapper(PropertyRightProsecutionPicMapper propertyRightProsecutionPicMapper) {
		super.setDao(propertyRightProsecutionPicMapper);
		this.mapper = propertyRightProsecutionPicMapper;
	}
	
	public int countByExample(PropertyRightProsecutionPicExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<PropertyRightProsecutionPic> selectByExample(PropertyRightProsecutionPicExample example){
    	return this.mapper.selectByExample(example);
    }
}
