package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysSmsTemplateMapper;
import com.jf.entity.SysSmsTemplate;
import com.jf.entity.SysSmsTemplateExample;

@Service
@Transactional
public class SysSmsTemplateService extends BaseService<SysSmsTemplate, SysSmsTemplateExample> {

	@Autowired
	private SysSmsTemplateMapper sysSmsTemplateMapper;

	@Autowired
	public void setSysSmsTemplateService(SysSmsTemplateMapper sysSmsTemplateMapper) {
		super.setDao(sysSmsTemplateMapper);
		this.sysSmsTemplateMapper = sysSmsTemplateMapper;
	}

	
}
