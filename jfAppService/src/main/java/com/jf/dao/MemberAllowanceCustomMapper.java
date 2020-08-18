package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MemberAllowanceCustomMapper {

	BigDecimal getMemberAllowanceBalance(@Param("memberId") Integer memberId);
}