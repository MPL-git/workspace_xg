package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSignIn;

@Repository
public interface MemberSignInCustomMapper {

	List<MemberSignIn> getMemberSignInModels(Integer memberId);

}
