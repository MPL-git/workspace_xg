package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberExtendCustom;

@Repository
public interface MemberExtendCustomMapper {

	List<MemberExtendCustom> findSignInRemindMember(Map<String, Integer> paramsMap);

}
