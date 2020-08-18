package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandAptitudePicChgMapper;
import com.jf.dao.MchtBrandChgCustomMapper;
import com.jf.dao.MchtBrandChgMapper;
import com.jf.dao.MchtPlatformAuthPicChgMapper;
import com.jf.entity.MchtBrandAptitudePicChg;
import com.jf.entity.MchtBrandAptitudePicChgExample;
import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtBrandChgExample;
import com.jf.entity.MchtPlatformAuthPicChg;
import com.jf.entity.MchtPlatformAuthPicChgExample;
import com.jf.entity.MchtProductBrandCustom;

@Service
@Transactional
public class MchtPlatformAuthPicChgServer extends BaseService<MchtPlatformAuthPicChg, MchtPlatformAuthPicChgExample>{
	@Autowired
	private MchtPlatformAuthPicChgMapper mchtPlatformAuthPicChgMapper;
	
	@Autowired
	public void setMchtProductBrandMapper(MchtPlatformAuthPicChgMapper mchtPlatformAuthPicChgMapper) {
		super.setDao(mchtPlatformAuthPicChgMapper);
		this.mchtPlatformAuthPicChgMapper = mchtPlatformAuthPicChgMapper;
	}
	
}
