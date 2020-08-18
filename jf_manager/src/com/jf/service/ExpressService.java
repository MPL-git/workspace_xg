package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ExpressMapper;
import com.jf.entity.Express;
import com.jf.entity.ExpressExample;

@Service
@Transactional
public class ExpressService extends BaseService<Express,ExpressExample> {
	@Autowired
	private ExpressMapper expressMapper;
	@Autowired
	public void setExpressMapper(ExpressMapper expressMapper) {
		super.setDao(expressMapper);
		this.expressMapper = expressMapper;
	}
}
