package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface MemberDynamicCustomMapper {

	int getMemberReleaseDynamicsCount(Map<String, Object> paramsMap);

	public int updateReadCount(Integer id);

	public Map<String, Object> getMemberDynamic(Map<String, Object> map);
	
	public List<Map<String, Object>> getMchtShopDynamic(Map<String, Object> map);
	
}
