package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CutPriceCnfDtlMapper;
import com.jf.entity.CutPriceCnfDtl;
import com.jf.entity.CutPriceCnfDtlExample;

@Service
@Transactional
public class CutPriceCnfDtlService extends BaseService<CutPriceCnfDtl, CutPriceCnfDtlExample> {

	@Autowired
	private CutPriceCnfDtlMapper cutPriceCnfDtlMapper;
	
	@Autowired
	public void setCutPriceCnfDtlMapper(CutPriceCnfDtlMapper cutPriceCnfDtlMapper) {
		super.setDao(cutPriceCnfDtlMapper);
		this.cutPriceCnfDtlMapper = cutPriceCnfDtlMapper;
	}
	
}
