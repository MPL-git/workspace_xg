package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtPlatformAuthPicChgMapper;
import com.jf.entity.MchtPlatformAuthPicChg;
import com.jf.entity.MchtPlatformAuthPicChgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtPlatformAuthPicChgServer extends BaseService<MchtPlatformAuthPicChg, MchtPlatformAuthPicChgExample> {
	@Autowired
	private MchtPlatformAuthPicChgMapper mchtPlatformAuthPicChgMapper;
	
	@Autowired
	public void setMchtProductBrandMapper(MchtPlatformAuthPicChgMapper mchtPlatformAuthPicChgMapper) {
		super.setDao(mchtPlatformAuthPicChgMapper);
		this.mchtPlatformAuthPicChgMapper = mchtPlatformAuthPicChgMapper;
	}
	
}
