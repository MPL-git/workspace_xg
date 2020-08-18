package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.StyleMapper;
import com.jf.entity.Style;
import com.jf.entity.StyleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年3月8日 下午4:43:30 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class StyleService extends BaseService<Style, StyleExample> {
	
	@Autowired
	private StyleMapper styleMapper;

	@Autowired
	public void setStyleMapper(StyleMapper styleMapper) {
		this.setDao(styleMapper);
		this.styleMapper = styleMapper;
	}
	
	
}
