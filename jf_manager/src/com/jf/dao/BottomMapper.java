package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BottomMapper {
	public List<Map<String,Object>> selectBottom(Map<String,Object> paramMap);
	public void updateBottom (Map<String,Object> paramMap);
}
