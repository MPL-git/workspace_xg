package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SpOfficeMapper;
import com.jf.entity.SpOffice;
import com.jf.entity.SpOfficeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SpOfficeService extends BaseService<SpOffice, SpOfficeExample> {
	@Autowired
	private SpOfficeMapper spOfficeMapper;
	@Autowired
	public void setSpOfficeMapper(SpOfficeMapper spOfficeMapper) {
		this.setDao(spOfficeMapper);
		this.spOfficeMapper = spOfficeMapper;
	}
	
	
}
