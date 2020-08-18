package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AppAccessTokenMapper;
import com.jf.entity.AppAccessToken;
import com.jf.entity.AppAccessTokenExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppAccessTokenService extends BaseService<AppAccessToken, AppAccessTokenExample> {
	@Autowired
	private AppAccessTokenMapper appAccessTokenMapper;
	
	@Autowired
	public void setAppAccessTokenMapper(AppAccessTokenMapper appAccessTokenMapper) {
		super.setDao(appAccessTokenMapper);
		this.appAccessTokenMapper = appAccessTokenMapper;
	}
	
}
