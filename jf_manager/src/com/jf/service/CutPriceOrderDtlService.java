package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CutPriceOrderDtlCustomMapper;
import com.jf.dao.CutPriceOrderDtlMapper;
import com.jf.entity.CutPriceOrderDtl;
import com.jf.entity.CutPriceOrderDtlCustom;
import com.jf.entity.CutPriceOrderDtlCustomExample;
import com.jf.entity.CutPriceOrderDtlExample;

@Service
@Transactional
public class CutPriceOrderDtlService extends BaseService<CutPriceOrderDtl, CutPriceOrderDtlExample> {

	@Autowired
	private CutPriceOrderDtlMapper cutPriceOrderDtlMapper;
	
	@Autowired
	private CutPriceOrderDtlCustomMapper cutPriceOrderDtlCustomMapper;
	
	@Autowired
	public void setCutPriceOrderDtlMapper(CutPriceOrderDtlMapper cutPriceOrderDtlMapper) {
		super.setDao(cutPriceOrderDtlMapper);
		this.cutPriceOrderDtlMapper = cutPriceOrderDtlMapper;
	}
	
	public int countByCustomExample(CutPriceOrderDtlCustomExample example) {
		return cutPriceOrderDtlCustomMapper.countByCustomExample(example);
	}

	public List<CutPriceOrderDtlCustom> selectByCustomExample(CutPriceOrderDtlCustomExample example) {
		return cutPriceOrderDtlCustomMapper.selectByCustomExample(example);
	}

	public CutPriceOrderDtlCustom selectByCustomPrimaryKey(Integer id) {
		return cutPriceOrderDtlCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(CutPriceOrderDtl record, CutPriceOrderDtlCustomExample example) {
		return cutPriceOrderDtlCustomMapper.updateByCustomExampleSelective(record, example);
	}

	
}
