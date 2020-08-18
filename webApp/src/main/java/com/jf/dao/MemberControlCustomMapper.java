package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberControlCustom;

@Repository
public interface MemberControlCustomMapper {

	int updateLastSignInDateModel(MemberControlCustom custom);

	int updateSignBeHelpedDateModel(MemberControlCustom custom);

	int updateLastHelpCutPriceDateModel(MemberControlCustom custom);

	int updateLastCutPriceDateModel(MemberControlCustom custom);

	int updateLastInviteDateModel(MemberControlCustom custom);

}
