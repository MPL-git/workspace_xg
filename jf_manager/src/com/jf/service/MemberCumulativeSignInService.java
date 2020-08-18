package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberCumulativeSignInCustomMapper;
import com.jf.dao.MemberCumulativeSignInMapper;
import com.jf.entity.MemberCumulativeSignIn;
import com.jf.entity.MemberCumulativeSignInCustom;
import com.jf.entity.MemberCumulativeSignInExample;

@Service
@Transactional
public class MemberCumulativeSignInService extends BaseService<MemberCumulativeSignIn,MemberCumulativeSignInExample> {
	@Autowired
	private MemberCumulativeSignInMapper memberCumulativeSignInMapper;
	@Autowired
	private MemberCumulativeSignInCustomMapper memberCumulativeSignInCustomMapper;
	@Autowired
	public void setMemberCumulativeSignInMapper(MemberCumulativeSignInMapper memberCumulativeSignInMapper) {
		super.setDao(memberCumulativeSignInMapper);
		this.memberCumulativeSignInMapper = memberCumulativeSignInMapper;
	}
	
	public int countMemberCumulativeSignInCustomByExample(MemberCumulativeSignInExample example){
		return memberCumulativeSignInCustomMapper.countByExample(example);
	}
    public List<MemberCumulativeSignInCustom> selectMemberCumulativeSignInCustomByExample(MemberCumulativeSignInExample example){
    	return memberCumulativeSignInCustomMapper.selectByExample(example);
    }
}
