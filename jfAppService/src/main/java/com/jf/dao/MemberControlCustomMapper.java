package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberControlCustom;

@Repository
public interface MemberControlCustomMapper {

	int updateLastRecSvipIntegralDateModel(MemberControlCustom custom);

}
