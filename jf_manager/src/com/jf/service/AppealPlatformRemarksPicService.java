package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AppealPlatformRemarksPicMapper;
import com.jf.entity.AppealPlatformRemarksPic;
import com.jf.entity.AppealPlatformRemarksPicExample;

@Service
@Transactional
public class AppealPlatformRemarksPicService extends BaseService<AppealPlatformRemarksPic, AppealPlatformRemarksPicExample> {

	@Autowired
	private AppealPlatformRemarksPicMapper appealPlatformRemarksPicMapper;
	
	@Autowired
	public void setAppealPlatformRemarksPicMapper(AppealPlatformRemarksPicMapper appealPlatformRemarksPicMapper) {
		super.setDao(appealPlatformRemarksPicMapper);
		this.dao = appealPlatformRemarksPicMapper;
	}
	
	
	
}
