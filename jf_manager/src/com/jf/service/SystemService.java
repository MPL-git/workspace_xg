package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzs.common.beans.Menu;
import com.jf.dao.SystemMapper;

@Service
public class SystemService{
	@Autowired
	SystemMapper operationMaper;

	
	public List<?> queryUserPwd(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<?> list = operationMaper.queryUserPwd(paramMap);
		return list;
	}

	
	public List<?> queryUserMenuList(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<?> list = operationMaper.queryUserMenuList(paramMap);
		return list;
	}
	
	
	public List<Menu> getMenuTree(HashMap<String, Object> paramMap) {
		List<Menu> list = operationMaper.selectMenuTree(paramMap);
		return Menu.buildTree(list, 0);
	}
	
	public String selectCwOrgStatus(Integer orgId) {
		return operationMaper.selectCwOrgStatus(orgId);
	}
	
}
