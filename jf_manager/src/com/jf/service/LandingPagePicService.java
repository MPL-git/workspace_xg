package com.jf.service;

import com.jf.dao.LandingPagePicMapper;
import com.jf.entity.LandingPagePic;
import com.jf.entity.LandingPagePicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LandingPagePicService extends BaseService<LandingPagePic, LandingPagePicExample>{

	@Autowired
	private LandingPagePicMapper landingPagePicMapper;

	@Autowired
	public void setlandingPagePicMapper(LandingPagePicMapper landingPagePicMapper) {
		super.setDao(landingPagePicMapper);
		this.landingPagePicMapper = landingPagePicMapper;
	}

}
