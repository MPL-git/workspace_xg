package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinOauthRedirectUrlMapper;
import com.jf.entity.WeixinOauthRedirectUrl;
import com.jf.entity.WeixinOauthRedirectUrlExample;

@Service
@Transactional
public class WeixinOauthRedirectUrlService extends BaseService<WeixinOauthRedirectUrl, WeixinOauthRedirectUrlExample> {
	@Autowired
	private WeixinOauthRedirectUrlMapper weixinOauthRedirectUrlMapper;

	@Autowired
	public void setWeixinOauthRedirectUrlMapper(WeixinOauthRedirectUrlMapper weixinOauthRedirectUrlMapper) {
		this.setDao(weixinOauthRedirectUrlMapper);
		this.weixinOauthRedirectUrlMapper = weixinOauthRedirectUrlMapper;
	}
	
}
