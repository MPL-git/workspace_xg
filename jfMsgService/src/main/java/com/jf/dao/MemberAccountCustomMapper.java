package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberAccount;
@Repository
public interface MemberAccountCustomMapper{
    int updateAccountIntegralByPrimaryKey(@Param("id") Integer id,@Param("integral") Integer integral);

	int updateMemberAccountBalanceAndFreeze(MemberAccount account);

	int updateMemberAccountFreeze(MemberAccount account);
	
	MemberAccount selectMemberIdKey(@Param("memberId") Integer memberId);
}