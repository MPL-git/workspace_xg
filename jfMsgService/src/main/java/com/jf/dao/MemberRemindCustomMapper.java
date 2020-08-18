package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindCustom;
@Repository
public interface MemberRemindCustomMapper{

	List<MemberRemindCustom> getProductRemindList(Integer reminId);

	List<MemberRemindCustom> getActivityRemindList(Integer reminId);

	List<MemberRemindCustom> getRemindList();

	List<MemberRemindCustom> getMemberList(Map<String, Object> params);

	List<MemberRemind> getNoSignInMemberIdList(Integer noSignDay);

}