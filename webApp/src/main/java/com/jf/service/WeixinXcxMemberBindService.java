package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinXcxMemberBindMapper;
import com.jf.entity.WeixinXcxMemberBind;
import com.jf.entity.WeixinXcxMemberBindExample;

@Service
@Transactional
public class WeixinXcxMemberBindService extends BaseService<WeixinXcxMemberBind, WeixinXcxMemberBindExample> {
	@Autowired
	private WeixinXcxMemberBindMapper weixinXcxMemberBindMapper;

	@Autowired
	public void setWeixinXcxMemberBindMapper(WeixinXcxMemberBindMapper weixinXcxMemberBindMapper) {
		this.setDao(weixinXcxMemberBindMapper);
		this.weixinXcxMemberBindMapper = weixinXcxMemberBindMapper;
	}
	
}
