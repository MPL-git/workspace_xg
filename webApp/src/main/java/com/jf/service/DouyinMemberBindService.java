package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DouyinMemberBindMapper;
import com.jf.entity.DouyinMemberBind;
import com.jf.entity.DouyinMemberBindExample;

@Service
@Transactional
public class DouyinMemberBindService extends BaseService<DouyinMemberBind, DouyinMemberBindExample> {
	@Autowired
	private DouyinMemberBindMapper douyinMemberBindMapper;
	@Autowired
	public void setDouyinMemberBindMapper(DouyinMemberBindMapper douyinMemberBindMapper) {
		this.setDao(douyinMemberBindMapper);
		this.douyinMemberBindMapper = douyinMemberBindMapper;
	}
	public DouyinMemberBind addDouYinAccount(Integer memberId, String thirdPartyId, String unionId) {
		DouyinMemberBind bind = new DouyinMemberBind();
		bind.setMemberId(memberId);
		bind.setOpenId(thirdPartyId);
		bind.setUnionId(unionId);
		bind.setCreateDate(new Date());
		bind.setDelFlag("0");
		insertSelective(bind);
		return bind;
	}
	public List<DouyinMemberBind> findModels(String thirdPartyId) {
		DouyinMemberBindExample bindExample = new DouyinMemberBindExample();
		bindExample.createCriteria().andOpenIdEqualTo(thirdPartyId).andDelFlagEqualTo("0");
		return selectByExample(bindExample);
	}
	
	
}
