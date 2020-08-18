package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.NoticeMapper;

@Service
public class NoticeService{ 
	@Autowired
	NoticeMapper noticeMapper;
	
	public List queryNoticeList(HashMap<String, Object> paramMap){
		List list = noticeMapper.queryNoticeList(paramMap); 
    	return list;
	}
	
	public int queryNoticeListTotal(HashMap<String, Object> paramMap) {
		 
		return noticeMapper.queryNoticeListTotal(paramMap);
	}
	 
	
	public int insertNotice(HashMap<String, Object> paramMap) { 
		
		return   noticeMapper.insertNotice(paramMap);
 
	} 

	
	public int editNotice(HashMap<String, Object> paramMap) {
	 
		return  noticeMapper.editNotice(paramMap);
	}
	
	
	public int deleteNotice(HashMap<String, Object> paramMap) {
		 
		return noticeMapper.deleteNotice(paramMap);
	}
	 
}
