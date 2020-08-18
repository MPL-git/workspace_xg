package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PvWeightCnfMapper;
import com.jf.entity.PvWeightCnf;
import com.jf.entity.PvWeightCnfExample;


@Service
@Transactional
public class PvWeightCnfService extends BaseService<PvWeightCnf, PvWeightCnfExample> {
	
	@Autowired
	private PvWeightCnfMapper pvWeightCnfMapper;
	
	@Autowired
	public void setPvWeightCnfMapper(PvWeightCnfMapper pvWeightCnfMapper) {
		this.setDao(pvWeightCnfMapper);
		this.pvWeightCnfMapper = pvWeightCnfMapper;
	}
}
