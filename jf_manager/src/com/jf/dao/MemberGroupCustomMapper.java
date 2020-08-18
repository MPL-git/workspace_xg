package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberGroup;

@Repository
public interface MemberGroupCustomMapper {
	
	/**
	 * 查出所有列表
	 * @return
	 */
	public List<MemberGroup> selectMemberGroupList();
	
}
