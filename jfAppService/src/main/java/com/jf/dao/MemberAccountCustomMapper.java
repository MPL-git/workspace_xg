package com.jf.dao;

import com.jf.entity.MemberAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAccountCustomMapper {

	int updateMemberAccountFreeze(MemberAccount memberAccount);

	int updateMemberAccountBalance(MemberAccount memberAccount);

	int updateAccountProfitInviteFreece(MemberAccount memberAccount);

	int updateAccountIntegralByPrimaryKey(@Param("id") Integer id, @Param("integral") Integer integral);

}
