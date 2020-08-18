package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ObligeePicMapper;
import com.jf.entity.ObligeePic;
import com.jf.entity.ObligeePicExample;


@Service
@Transactional
public class ObligeePicService extends BaseService<ObligeePic, ObligeePicExample> {
	
	@Autowired
	private ObligeePicMapper mapper;
	
	@Autowired
	public void setObligeePicMapper(ObligeePicMapper obligeePicMapper) {
		super.setDao(obligeePicMapper);
		this.mapper = obligeePicMapper;
	}
	
	public int countByExample(ObligeePicExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<ObligeePic> selectByExample(ObligeePicExample example){
    	return this.mapper.selectByExample(example);
    }
    
    public ObligeePic selectByPrimaryKey(Integer id){
    	return this.mapper.selectByPrimaryKey(id);
    }
	
}
