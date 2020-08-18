package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtCloseApplicationPicMapper;
import com.jf.entity.MchtCloseApplicationPic;
import com.jf.entity.MchtCloseApplicationPicExample;


@Service
@Transactional
public class MchtCloseApplicationPicService extends BaseService<MchtCloseApplicationPic, MchtCloseApplicationPicExample>{

	@Autowired
	private MchtCloseApplicationPicMapper mchtCloseApplicationPicMapper;
	
	@Autowired
	public void setMchtCloseApplicationPicMapper(MchtCloseApplicationPicMapper mchtCloseApplicationPicMapper) {
		super.setDao(mchtCloseApplicationPicMapper);
		this.dao = mchtCloseApplicationPicMapper;
	}
	
	
}
