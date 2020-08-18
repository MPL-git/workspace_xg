package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberSupplementarySignInCustomMapper;
import com.jf.dao.MemberSupplementarySignInMapper;
import com.jf.entity.MemberSupplementarySignIn;
import com.jf.entity.MemberSupplementarySignInCustom;
import com.jf.entity.MemberSupplementarySignInExample;

@Service
@Transactional
public class MemberSupplementarySignInService extends BaseService<MemberSupplementarySignIn,MemberSupplementarySignInExample> {
	@Autowired
	private MemberSupplementarySignInMapper memberSupplementarySignInMapper;
	@Autowired
	private MemberSupplementarySignInCustomMapper memberSupplementarySignInCustomMapper;
	@Autowired
	public void setMemberSupplementarySignInMapper(MemberSupplementarySignInMapper memberSupplementarySignInMapper) {
		super.setDao(memberSupplementarySignInMapper);
		this.memberSupplementarySignInMapper = memberSupplementarySignInMapper;
	}
	
	public int countMemberSupplementarySignInCustomByExample(MemberSupplementarySignInExample example){
		return memberSupplementarySignInCustomMapper.countByExample(example);
	}
    public List<MemberSupplementarySignInCustom> selectMemberSupplementarySignInCustomByExample(MemberSupplementarySignInExample example){
    	return memberSupplementarySignInCustomMapper.selectByExample(example);
    }
}
