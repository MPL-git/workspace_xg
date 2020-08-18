package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SourceNicheProductCustomMapper;
import com.jf.dao.SourceNicheProductMapper;
import com.jf.entity.SourceNicheCustom;
import com.jf.entity.SourceNicheCustomExample;
import com.jf.entity.SourceNicheProduct;
import com.jf.entity.SourceNicheProductCustom;
import com.jf.entity.SourceNicheProductExample;


@Service
@Transactional
public class SourceNicheProductService extends BaseService<SourceNicheProduct,SourceNicheProductExample>{

	@Autowired
	private SourceNicheProductMapper sourceNicheProductMapper;
	
	@Autowired
	private SourceNicheProductCustomMapper sourceNicheProductCustomMapper;
	
	@Autowired
	public void setSourceNicheMapper(SourceNicheProductMapper sourceNicheProductMapper) {
		super.setDao(sourceNicheProductMapper);
		this.sourceNicheProductMapper = sourceNicheProductMapper;
	}
	
	public List<SourceNicheProductCustom> selectCustomByExample(SourceNicheProductExample example) {
		// TODO Auto-generated method stub
		return sourceNicheProductCustomMapper.selectCustomByExample(example);
	}
	
	
}
