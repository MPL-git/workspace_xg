package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandAptitudePicMapper;
import com.jf.entity.MchtBrandAptitudePic;
import com.jf.entity.MchtBrandAptitudePicExample;

@Service
@Transactional
public class MchtBrandAptitudePicService extends BaseService<MchtBrandAptitudePic, MchtBrandAptitudePicExample> {
	@Autowired
	private MchtBrandAptitudePicMapper mchtBrandAptitudePicMapper;
	
	@Autowired
	public void setMchtBrandAptitudePicMapper(MchtBrandAptitudePicMapper mchtBrandAptitudePicMapper) {
		super.setDao(mchtBrandAptitudePicMapper);
		this.mchtBrandAptitudePicMapper = mchtBrandAptitudePicMapper;
	}
}
