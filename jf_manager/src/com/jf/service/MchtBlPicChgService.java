package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBlPicChgMapper;
import com.jf.entity.MchtBlPicChg;
import com.jf.entity.MchtBlPicChgExample;

@Service
@Transactional
public class MchtBlPicChgService extends BaseService<MchtBlPicChg,MchtBlPicChgExample> {
	@Autowired
	private MchtBlPicChgMapper mchtBlPicChgMapper;
	@Autowired
	public void setMchtBlPicChgMapper(MchtBlPicChgMapper mchtBlPicChgMapper) {
		super.setDao(mchtBlPicChgMapper);
		this.mchtBlPicChgMapper = mchtBlPicChgMapper;
	}
}
