package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtBrandOtherPicChgMapper;
import com.jf.entity.MchtBrandOtherPicChg;
import com.jf.entity.MchtBrandOtherPicChgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtBrandOtherPicChgServer extends BaseService<MchtBrandOtherPicChg, MchtBrandOtherPicChgExample> {
	@Autowired
	private MchtBrandOtherPicChgMapper mchtBrandOtherPicChgMapper;
	
	@Autowired
	public void setMchtBrandOtherPicChgMapper(MchtBrandOtherPicChgMapper mchtBrandOtherPicChgMapper) {
		super.setDao(mchtBrandOtherPicChgMapper);
		this.mchtBrandOtherPicChgMapper = mchtBrandOtherPicChgMapper;
	}
	
}
