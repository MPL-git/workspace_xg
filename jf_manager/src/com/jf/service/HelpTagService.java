package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.HelpTagMapper;
import com.jf.entity.HelpTag;
import com.jf.entity.HelpTagExample;

@Service
@Transactional
public class HelpTagService extends BaseService<HelpTag, HelpTagExample> {

	@Autowired
	private HelpTagMapper helpTagMapper;
	
	@Autowired
	public void setHelpTagMapper(HelpTagMapper helpTagMapper) {
		super.setDao(helpTagMapper);
		this.helpTagMapper = helpTagMapper;
	}
	
}
