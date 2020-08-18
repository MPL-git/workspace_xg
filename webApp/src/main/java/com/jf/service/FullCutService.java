package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.FullCutMapper;
import com.jf.entity.FullCut;
import com.jf.entity.FullCutExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月25日 下午4:58:53 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class FullCutService extends BaseService<FullCut, FullCutExample> {
	
	@Autowired
	private FullCutMapper fullCutMapper;
	@Autowired
	public void setFullCutMapper(FullCutMapper fullCutMapper) {
		this.setDao(fullCutMapper);
		this.fullCutMapper = fullCutMapper;
	}
	
	
}
