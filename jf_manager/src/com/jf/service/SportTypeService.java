package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SportTypeMapper;
import com.jf.entity.SportType;
import com.jf.entity.SportTypeExample;

@Service
@Transactional
public class SportTypeService extends BaseService<SportType, SportTypeExample> {

	@Autowired
	private SportTypeMapper sportTypeMapper;
	
	@Autowired
	public void setSportTypeMapper(SportTypeMapper sportTypeMapper) {
		super.setDao(sportTypeMapper);
		this.sportTypeMapper = sportTypeMapper;
	}
	
}
