package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SalesmanMapper;
import com.jf.entity.Salesman;
import com.jf.entity.SalesmanExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SalesmanService extends BaseService<Salesman, SalesmanExample> {
	@Autowired
	private SalesmanMapper salesmanMapper;
	@Autowired
	public void setSalesmanMapper(SalesmanMapper salesmanMapper) {
		this.setDao(salesmanMapper);
		this.salesmanMapper = salesmanMapper;
	}
	
	
}
