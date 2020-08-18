package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.StyleMapper;
import com.jf.entity.Style;
import com.jf.entity.StyleExample;

@Service
@Transactional
public class StyleService extends BaseService<Style,StyleExample> {
	@Autowired
	private StyleMapper styleMapper;
/*	@Autowired
	private StyleCustomMapper styleCustomMapper;*/
	@Autowired
	public void setStylesMapper(StyleMapper styleMapper) {
		super.setDao(styleMapper);
		this.styleMapper = styleMapper;
	}
	
	public int countStylesByExample(StyleExample example){
		return styleMapper.countByExample(example);
	}
    public List<Style> selectStyleByExample(StyleExample example){
    	return styleMapper.selectByExample(example);
    }
    public Style selectStyleByPrimaryKey(Integer id){
    	return styleMapper.selectByPrimaryKey(id);
    }
    
    public void insertsale(Style style){
    	styleMapper.insertSelective(style);
		
	}
	
	public void updatesale(Style style) {
		styleMapper.updateByPrimaryKeySelective(style);
}  
}
