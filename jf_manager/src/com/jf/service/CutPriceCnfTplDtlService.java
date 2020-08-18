package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CutPriceCnfTplDtlMapper;
import com.jf.entity.CutPriceCnfTplDtl;
import com.jf.entity.CutPriceCnfTplDtlExample;

@Service
@Transactional
public class CutPriceCnfTplDtlService extends BaseService<CutPriceCnfTplDtl, CutPriceCnfTplDtlExample> {

	@Autowired
	private CutPriceCnfTplDtlMapper cutPriceCnfTplDtlMapper;
	
	@Autowired
	public void setCutPriceCnfTplDtlMapper(CutPriceCnfTplDtlMapper cutPriceCnfTplDtlMapper) {
		super.setDao(cutPriceCnfTplDtlMapper);
		this.cutPriceCnfTplDtlMapper = cutPriceCnfTplDtlMapper;
	}
	
}
