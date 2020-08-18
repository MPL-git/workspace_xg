package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtPlatformAuthPicMapper;
import com.jf.entity.MchtPlatformAuthPic;
import com.jf.entity.MchtPlatformAuthPicExample;

@Service
@Transactional
public class MchtPlatformAuthPicService extends BaseService<MchtPlatformAuthPic, MchtPlatformAuthPicExample> {
	@Autowired
	private MchtPlatformAuthPicMapper mchtPlatformAuthPicMapper;
	
	@Autowired
	public void setMchtPlatformAuthPicMapper(MchtPlatformAuthPicMapper mchtPlatformAuthPicMapper) {
		super.setDao(mchtPlatformAuthPicMapper);
		this.mchtPlatformAuthPicMapper = mchtPlatformAuthPicMapper;
	}
}
