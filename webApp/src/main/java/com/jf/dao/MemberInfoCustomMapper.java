package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MemberInfoCustom;

@Repository
public interface MemberInfoCustomMapper {

	List<MemberInfoCustom> getMemberFollowUser(Map<String, Object> paramsMap);

	List<MemberInfoCustom> getMemberFansUser(Map<String, Object> paramsMap);

	List<MemberInfoCustom> getMemberRecommendUser(Map<String, Object> paramsMap);

}
