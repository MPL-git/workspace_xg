package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AdInfoCustom;
import com.jf.entity.AdInfoExample;
@Repository
public interface AdInfoCustomMapper{
    int countByExample(AdInfoExample example);
    List<AdInfoCustom> selectByExample(AdInfoExample example);
    AdInfoCustom selectByPrimaryKey(Integer id);
	int updateAdInfoSeqNo(HashMap<String,Object> map);
	
	String countIndexAdInfo(HashMap<String, Object> map);
	int countTodayIndexUp(HashMap<String, Object> map);
	int countTodayIndexDown(HashMap<String, Object> map);
	
	String countTypeAdInfo(HashMap<String, Object> map);
	int countTodayTypeUp(HashMap<String, Object> map);
	int countTodayTypeDown(HashMap<String, Object> map);
}