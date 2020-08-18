package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.FullGiveMapper;
import com.jf.entity.FullGive;
import com.jf.entity.FullGiveExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月25日 下午5:00:55 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class FullGiveService extends BaseService<FullGive, FullGiveExample> {
	
	@Autowired
	private FullGiveMapper fullGiveMapper;
	@Autowired
	public void setFullGiveMapper(FullGiveMapper fullGiveMapper) {
		this.setDao(fullGiveMapper);
		this.fullGiveMapper = fullGiveMapper;
	}
	
	
}
