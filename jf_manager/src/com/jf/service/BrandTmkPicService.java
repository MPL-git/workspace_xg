package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.BrandTmkPicMapper;
import com.jf.entity.BrandTmkPic;
import com.jf.entity.BrandTmkPicExample;

@Service
@Transactional
public class BrandTmkPicService extends BaseService<BrandTmkPic, BrandTmkPicExample> {
	@Autowired
	private BrandTmkPicMapper brandTmkPicMapper;

	@Autowired
	public void setBrandTmkPicMapper(BrandTmkPicMapper brandTmkPicMapper) {
		super.setDao(brandTmkPicMapper);
		this.brandTmkPicMapper = brandTmkPicMapper;
	}
}
