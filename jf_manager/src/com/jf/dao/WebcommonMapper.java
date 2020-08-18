package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface WebcommonMapper { 
	public List<?> sysStatusList(HashMap<String,Object> map); 
	public List<?> sysAreaList(HashMap<String,Object> map); 
	public List<?> selectAreaLd(HashMap<String,Object> map); 
	public List<?> selectProLd( );
	int querySysSequence(HashMap<String,Object> map);
}
