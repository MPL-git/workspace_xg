package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CutPriceCnfMapper;
import com.jf.entity.CutPriceCnf;
import com.jf.entity.CutPriceCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CutPriceCnfService extends BaseService<CutPriceCnf, CutPriceCnfExample> {
	@Autowired
	private CutPriceCnfMapper cutPriceCnfMapper;
	@Autowired
	public void setCutPriceCnfMapper(CutPriceCnfMapper cutPriceCnfMapper) {
		this.setDao(cutPriceCnfMapper);
		this.cutPriceCnfMapper = cutPriceCnfMapper;
	}
	
}
