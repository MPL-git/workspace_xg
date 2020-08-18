package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.HelpItemMapper;
import com.jf.entity.HelpItem;
import com.jf.entity.HelpItemExample;

@Service
@Transactional
public class HelpItemService extends BaseService<HelpItem, HelpItemExample> {
	@Autowired
	private HelpItemMapper helpItemMapper;
	@Autowired
	public void setHelpItemMapper(HelpItemMapper helpItemMapper) {
		this.setDao(helpItemMapper);
		this.helpItemMapper = helpItemMapper;
	}
	
	
}
