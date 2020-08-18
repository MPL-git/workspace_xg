package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberBlackOperateLogCustom;
import com.jf.entity.MemberBlackOperateLogExample;

@Repository
public interface MemberBlackOperateLogCustomMapper {
	
	int countByExample(MemberBlackOperateLogExample example);

	List<MemberBlackOperateLogCustom> selectByExample(MemberBlackOperateLogExample example);

	MemberBlackOperateLogCustom selectByPrimaryKey(Integer id);
}