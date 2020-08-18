package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomPageMapper;
import com.jf.entity.CustomPage;
import com.jf.entity.CustomPageExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 上午11:04:00 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CustomPageService extends BaseService<CustomPage, CustomPageExample> {
	
	@Autowired
	private CustomPageMapper customPageMapper;
	
	@Autowired
	public void setCustomPageMapper(CustomPageMapper customPageMapper) {
		this.setDao(customPageMapper);
		this.customPageMapper = customPageMapper;
	}
	
	
}
