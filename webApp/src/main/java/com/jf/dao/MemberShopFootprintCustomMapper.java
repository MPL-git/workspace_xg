package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberShopFootprintCustom;

@Repository
public interface MemberShopFootprintCustomMapper {

	List<MemberShopFootprintCustom> getMemberShopFootprintList(Map<String, Object> params);

	Integer getMemberShopFootprintCount(@Param(value = "memberId") Integer memberId);

}
