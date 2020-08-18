package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.WebcommonMapper;
@Service
public class WebcommonService  {
	@Autowired
	WebcommonMapper webcommonMaper;


	
	public List<?> sysStatusList(HashMap<String, Object> paramMap) {
		List<?> list = webcommonMaper.sysStatusList(paramMap);
		return list;
	} 

	
	public List<?> sysAreaList(HashMap<String, Object> paramMap) {
		List<?> list = webcommonMaper.sysAreaList(paramMap);
		return list;
	}
	
	public List<?> selectAreaLd(HashMap<String, Object> paramMap) {
		List<?> list = webcommonMaper.selectAreaLd(paramMap);
		return list;
	}
	
	public List<?> selectProLd( ) {
		List<?> list =  webcommonMaper.selectProLd( );
		return list;
	}
	
	public int querySysSequence(HashMap<String, Object> paramMap){
		return webcommonMaper.querySysSequence(paramMap);
	}

}
