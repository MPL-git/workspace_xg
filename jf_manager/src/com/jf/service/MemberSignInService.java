package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberSignInCustomMapper;
import com.jf.dao.MemberSignInMapper;
import com.jf.entity.MemberSignIn;
import com.jf.entity.MemberSignInCustom;
import com.jf.entity.MemberSignInCustomExample;
import com.jf.entity.MemberSignInExample;

@Service
@Transactional
public class MemberSignInService extends BaseService<MemberSignIn, MemberSignInExample> {

	@Autowired
	private MemberSignInMapper memberSignInMapper;
	
	@Autowired
	private MemberSignInCustomMapper memberSignInCustomMapper;
	
	@Autowired
	public void setMemberSignInCustomMapper(MemberSignInMapper memberSignInMapper) {
		super.setDao(memberSignInMapper);
		this.memberSignInMapper = memberSignInMapper;
	}
	
	public int countByCustomExample(MemberSignInCustomExample example) {
		return memberSignInCustomMapper.countByCustomExample(example);
	}

	public List<MemberSignInCustom> selectByCustomExample(MemberSignInCustomExample example) {
		return memberSignInCustomMapper.selectByCustomExample(example);
	}

	public MemberSignInCustom selectByCustomPrimaryKey(Integer id) {
		return memberSignInCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(MemberSignIn record, MemberSignInCustomExample example) {
		return memberSignInCustomMapper.updateByCustomExampleSelective(record, example);
	}

	
}
