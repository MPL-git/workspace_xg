package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBlPicMapper;
import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtBlPicService extends BaseService<MchtBlPic, MchtBlPicExample> {
	@Autowired
	private MchtBlPicMapper mchtBlPicMapper;
	@Autowired
	public void setMchtBlPicMapper(MchtBlPicMapper mchtBlPicMapper) {
		this.setDao(mchtBlPicMapper);
		this.mchtBlPicMapper = mchtBlPicMapper;
	}
	
	
}
