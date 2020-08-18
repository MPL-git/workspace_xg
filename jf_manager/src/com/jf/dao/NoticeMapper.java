package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gzs.common.beans.Menu;
@Repository
public interface NoticeMapper {
	public List queryNoticeList(HashMap<String,Object> map);
	public int queryNoticeListTotal(HashMap<String,Object> map);
	public int insertNotice(HashMap<String,Object> map);  
	public int editNotice(HashMap<String,Object> map);
	public int deleteNotice(HashMap<String,Object> map); 
	public void updateNewsBROWSE(HashMap<String,Object> map);
}
