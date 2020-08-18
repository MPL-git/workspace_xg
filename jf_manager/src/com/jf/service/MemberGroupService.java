package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberGroupCustomMapper;
import com.jf.dao.MemberGroupMapper;
import com.jf.entity.MemberGroup;
import com.jf.entity.MemberGroupExample;

@Service
@Transactional
public class MemberGroupService extends BaseService<MemberGroup,MemberGroupExample> {
	@Autowired
	private MemberGroupMapper memberGroupMapper;
	
	@Autowired
	private MemberGroupCustomMapper memberGroupCustomMapper;
	
	@Autowired
	public void setMemberGroupMapper(MemberGroupMapper memberGroupMapper) {
		super.setDao(memberGroupMapper);
		this.memberGroupMapper = memberGroupMapper;
	}
	
	/**
	 * 查出所有列表
	 * @return
	 */
	public List<MemberGroup> selectMemberGroupList(){
		return memberGroupCustomMapper.selectMemberGroupList();
	}
}
