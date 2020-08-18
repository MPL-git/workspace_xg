package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
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
		this.setDao(cutPriceCnfDtlMapper);
		this.cutPriceCnfDtlMapper = cutPriceCnfDtlMapper;
	}
	public List<CutPriceCnfDtl> findModelByCnfId(Integer cutPriceCnfId) {
		CutPriceCnfDtlExample cutPriceCnfDtlExample = new CutPriceCnfDtlExample();
		cutPriceCnfDtlExample.createCriteria().andCutPriceCnfIdEqualTo(cutPriceCnfId).andDelFlagEqualTo("0");
		
		return selectByExample(cutPriceCnfDtlExample);
	}
	
}
