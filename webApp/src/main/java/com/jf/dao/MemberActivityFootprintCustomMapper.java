package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberActivityFootprintCustom;

@Repository
public interface MemberActivityFootprintCustomMapper {

	List<MemberActivityFootprintCustom> getMemberActivityFootprintList(Map<String, Object> params);

	Integer getMemberActivityFootprintCount(@Param(value = "memberId") Integer memberId);

}
