package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBrandAptitudePicChgMapper;
import com.jf.entity.MchtBrandAptitudePicChg;
import com.jf.entity.MchtBrandAptitudePicChgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtBrandAptitudePicChgServer extends BaseService<MchtBrandAptitudePicChg, MchtBrandAptitudePicChgExample> {
	@Autowired
	private MchtBrandAptitudePicChgMapper mchtBrandAptitudePicChgMapper;
	
	@Autowired
	public void setMchtProductBrandMapper(MchtBrandAptitudePicChgMapper mchtBrandAptitudePicChgMapper) {
		super.setDao(mchtBrandAptitudePicChgMapper);
		this.mchtBrandAptitudePicChgMapper = mchtBrandAptitudePicChgMapper;
	}
	
}
