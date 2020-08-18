package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandOtherPicMapper;
import com.jf.entity.MchtBrandOtherPic;
import com.jf.entity.MchtBrandOtherPicExample;

@Service
@Transactional
public class MchtBrandOtherPicServer extends BaseService<MchtBrandOtherPic, MchtBrandOtherPicExample>{
	@Autowired
	private MchtBrandOtherPicMapper mchtBrandOtherPicMapper;
	
	@Autowired
	public void setMchtBrandOtherPicMapper(MchtBrandOtherPicMapper mchtBrandOtherPicMapper) {
		super.setDao(mchtBrandOtherPicMapper);
		this.mchtBrandOtherPicMapper = mchtBrandOtherPicMapper;
	}
	
}
