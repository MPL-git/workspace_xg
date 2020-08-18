package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtBrandOtherPicChgMapper;
import com.jf.entity.MchtBrandOtherPicChg;
import com.jf.entity.MchtBrandOtherPicChgExample;

@Service
@Transactional
public class MchtBrandOtherPicChgServer extends BaseService<MchtBrandOtherPicChg, MchtBrandOtherPicChgExample>{
	@Autowired
	private MchtBrandOtherPicChgMapper mchtBrandOtherPicChgMapper;
	
	@Autowired
	public void setMchtBrandOtherPicChgMapper(MchtBrandOtherPicChgMapper mchtBrandOtherPicChgMapper) {
		super.setDao(mchtBrandOtherPicChgMapper);
		this.mchtBrandOtherPicChgMapper = mchtBrandOtherPicChgMapper;
	}
	
}
