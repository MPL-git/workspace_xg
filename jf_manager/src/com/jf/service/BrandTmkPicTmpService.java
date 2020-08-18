package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.BrandTmkPicTmpMapper;
import com.jf.entity.BrandTmkPicTmp;
import com.jf.entity.BrandTmkPicTmpExample;

@Service
@Transactional
public class BrandTmkPicTmpService extends BaseService<BrandTmkPicTmp,BrandTmkPicTmpExample> {
	@Autowired
	private BrandTmkPicTmpMapper brandTmkPicTmpMapper;
	@Autowired
	public void setBrandTmkPicTmpMapper(BrandTmkPicTmpMapper brandTmkPicTmpMapper) {
		super.setDao(brandTmkPicTmpMapper);
		this.brandTmkPicTmpMapper = brandTmkPicTmpMapper;
	}
}
