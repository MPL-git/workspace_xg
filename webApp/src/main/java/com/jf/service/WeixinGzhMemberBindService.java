package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinGzhMemberBindMapper;
import com.jf.entity.WeixinGzhMemberBind;
import com.jf.entity.WeixinGzhMemberBindExample;

@Service
@Transactional
public class WeixinGzhMemberBindService extends BaseService<WeixinGzhMemberBind, WeixinGzhMemberBindExample> {
	@Autowired
	private WeixinGzhMemberBindMapper weixinGzhMemberBindMapper;

	@Autowired
	public void setWeixinGzhMemberBindMapper(WeixinGzhMemberBindMapper weixinGzhMemberBindMapper) {
		this.setDao(weixinGzhMemberBindMapper);
		this.weixinGzhMemberBindMapper = weixinGzhMemberBindMapper;
	}
	
}
