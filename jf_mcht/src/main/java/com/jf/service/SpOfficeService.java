package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SpOfficeCustomMapper;
import com.jf.dao.SpOfficeMapper;
import com.jf.entity.SpOffice;
import com.jf.entity.SpOfficeCustom;
import com.jf.entity.SpOfficeCustomExample;
import com.jf.entity.SpOfficeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpOfficeService extends BaseService<SpOffice,SpOfficeExample> {
	@Autowired
	private SpOfficeMapper dao;
	
	@Autowired
	private SpOfficeCustomMapper spOfficeCustomMapper;
	
	
	@Autowired
	public void setSpOfficeMapper(SpOfficeMapper spOfficeMapper) {
		super.setDao(spOfficeMapper);
		this.dao = spOfficeMapper;
	}

	public int countByCustomExample(SpOfficeCustomExample example) {
		return spOfficeCustomMapper.countByExample(example);
	}
	
	public List<SpOfficeCustom> selectByCustomExample(SpOfficeCustomExample example) {
		return spOfficeCustomMapper.selectByExample(example);
	}

}
