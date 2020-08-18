package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.IntellectualPropertyRightPicMapper;
import com.jf.entity.IntellectualPropertyRightPic;
import com.jf.entity.IntellectualPropertyRightPicExample;


@Service
@Transactional
public class IntellectualPropertyRightPicService extends BaseService<IntellectualPropertyRightPic,IntellectualPropertyRightPicExample> {
	
	@Autowired
	private IntellectualPropertyRightPicMapper mapper;
	
	@Autowired
	public void setIntellectualPropertyRightPicMapper(IntellectualPropertyRightPicMapper intellectualPropertyRightPicMapper) {
		super.setDao(intellectualPropertyRightPicMapper);
		this.mapper = intellectualPropertyRightPicMapper;
	}
	
	public int countByExample(IntellectualPropertyRightPicExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<IntellectualPropertyRightPic> selectByExample(IntellectualPropertyRightPicExample example){
    	return this.mapper.selectByExample(example);
    }
}
