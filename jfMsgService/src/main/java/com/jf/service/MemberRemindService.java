package com.jf.service;

import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberRemindCustomMapper;
import com.jf.dao.MemberRemindMapper;
import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindCustom;
import com.jf.entity.MemberRemindExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月8日 下午7:47:54 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberRemindService extends BaseService<MemberRemind, MemberRemindExample> {
	
	@Autowired
	private MemberRemindMapper memberRemindMapper;
	
	@Autowired
	private MemberRemindCustomMapper memberRemindCustomMapper;
	
	@Autowired
	public void setMemberRemindMapper(MemberRemindMapper memberRemindMapper) {
		this.setDao(memberRemindMapper);
		this.memberRemindMapper = memberRemindMapper;
	}

	public List<MemberRemindCustom> getProductRemindList(Integer reminId) {
		
		return memberRemindCustomMapper.getProductRemindList(reminId);
	}


	public List<MemberRemindCustom> getActivityRemindList(Integer reminId) {
		
		return memberRemindCustomMapper.getActivityRemindList(reminId);
	}

	public List<MemberRemindCustom> getRemindList() {
		
		return memberRemindCustomMapper.getRemindList();
	}

	public List<MemberRemindCustom> getMemberList(Map<String, Object> params) {
		
		return memberRemindCustomMapper.getMemberList(params);
	}

	public List<MemberRemind> getNoSignInMemberIdList(Integer noSignDay) {
		
		return memberRemindCustomMapper.getNoSignInMemberIdList(noSignDay);
	}
	
}
