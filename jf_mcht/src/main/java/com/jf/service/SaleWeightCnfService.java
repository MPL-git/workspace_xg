package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SaleWeightCnfMapper;
import com.jf.entity.SaleWeightCnf;
import com.jf.entity.SaleWeightCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SaleWeightCnfService extends BaseService<SaleWeightCnf, SaleWeightCnfExample> {
	
	@Autowired
	private SaleWeightCnfMapper saleWeightCnfMapper;
	
	@Autowired
	public void setSaleWeightCnfMapper(SaleWeightCnfMapper saleWeightCnfMapper) {
		this.setDao(saleWeightCnfMapper);
		this.saleWeightCnfMapper = saleWeightCnfMapper;
	}
}
