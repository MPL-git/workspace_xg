package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBrandChgMapper;
import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtBrandChgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtBrandChgService extends BaseService<MchtBrandChg, MchtBrandChgExample> {
	@Autowired
	private MchtBrandChgMapper mchtBrandChgMapper;
	
	@Autowired
	public void setMchtProductBrandMapper(MchtBrandChgMapper mchtBrandChgMapper) {
		super.setDao(mchtBrandChgMapper);
		this.mchtBrandChgMapper = mchtBrandChgMapper;
	}
}
