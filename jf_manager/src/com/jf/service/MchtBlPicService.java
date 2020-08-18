package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBlPicMapper;
import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;

@Service
@Transactional
public class MchtBlPicService extends BaseService<MchtBlPic,MchtBlPicExample> {
	@Autowired
	private MchtBlPicMapper mchtBlPicMapper;
	@Autowired
	public void setMchtBlPicMapper(MchtBlPicMapper mchtBlPicMapper) {
		super.setDao(mchtBlPicMapper);
		this.mchtBlPicMapper = mchtBlPicMapper;
	}
}
