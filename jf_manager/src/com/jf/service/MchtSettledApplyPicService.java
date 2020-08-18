package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtSettledApplyPicMapper;
import com.jf.entity.MchtSettledApplyPic;
import com.jf.entity.MchtSettledApplyPicExample;

@Service
@Transactional
public class MchtSettledApplyPicService extends BaseService<MchtSettledApplyPic,MchtSettledApplyPicExample> {
	@Autowired
	private MchtSettledApplyPicMapper mchtSettledApplyPicMapper;
	
	@Autowired
	public void setMchtSettledApplyPicMapper(MchtSettledApplyPicMapper mchtSettledApplyPicMapper) {
		super.setDao(mchtSettledApplyPicMapper);
		this.mchtSettledApplyPicMapper = mchtSettledApplyPicMapper;
	}
}
