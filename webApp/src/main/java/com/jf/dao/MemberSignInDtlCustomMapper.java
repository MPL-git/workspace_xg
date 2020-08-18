package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSignInDtlCustom;

@Repository
public interface MemberSignInDtlCustomMapper{

	int updateReceiveExtraAmount(MemberSignInDtlCustom memberSignInDtlCustom);

}
