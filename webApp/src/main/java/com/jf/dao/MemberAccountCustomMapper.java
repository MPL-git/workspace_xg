package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberAccount;

import java.util.Map;

@Repository
public interface MemberAccountCustomMapper {

	int updateMemberAccountFreeze(MemberAccount memberAccount);

	int updateMemberAccountBalance(MemberAccount memberAccount);

	int updateAccountProfitInviteFreece(MemberAccount memberAccount);

	int increaseIntegral(Map<String, Object> params);

	int decreaseIntegral(Map<String, Object> params);
}
