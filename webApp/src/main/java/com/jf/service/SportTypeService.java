package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SportTypeMapper;
import com.jf.entity.SportType;
import com.jf.entity.SportTypeExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月22日 下午2:08:22 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SportTypeService extends BaseService<SportType, SportTypeExample> {
	@Autowired
	private SportTypeMapper sportTypeMapper;

	@Autowired
	public void setSportTypeMapper(SportTypeMapper sportTypeMapper) {
		this.setDao(sportTypeMapper);
		this.sportTypeMapper = sportTypeMapper;
	}
	
	
}
