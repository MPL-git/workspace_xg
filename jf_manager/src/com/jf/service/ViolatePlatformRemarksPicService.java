package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ViolatePlatformRemarksPicMapper;
import com.jf.entity.ViolatePlatformRemarksPic;
import com.jf.entity.ViolatePlatformRemarksPicExample;

@Service
@Transactional
public class ViolatePlatformRemarksPicService extends BaseService<ViolatePlatformRemarksPic,ViolatePlatformRemarksPicExample> {
	@Autowired
	private ViolatePlatformRemarksPicMapper dao;
	
	@Autowired
	public void setViolatePlatformRemarksPicMapper(ViolatePlatformRemarksPicMapper violatePlatformRemarksPicMapper) {
		super.setDao(violatePlatformRemarksPicMapper);
		this.dao = violatePlatformRemarksPicMapper;
	}

}
