package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.StatusMapper;
@Service
public class StatusService{
	@Autowired
	StatusMapper operationMaper;

	public List<?> querytStatusList(String tableName,String colName) {
		HashMap<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("TABLE_NAME", tableName);
		paramMap.put("COL_NAME", colName);
		return operationMaper.querytStatusList(paramMap);
	}

  
	

}
