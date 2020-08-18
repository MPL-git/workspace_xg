package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberSupplementarySignInCustom;

@Repository
public interface MemberSupplementarySignInCustomMapper {

	List<MemberSupplementarySignInCustom> getMySupplementarySignTaskList(Map<String, Object> paramsMap);

}
