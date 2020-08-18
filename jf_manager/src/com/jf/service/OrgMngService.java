package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzs.common.beans.Menu;
import com.gzs.common.utils.StringUtil;
import com.jf.dao.OrgMngMapper;

@Service
public class OrgMngService  {
	@Autowired
	OrgMngMapper operationMaper;

	
	public List<Menu> queryStaffOrgList(HashMap<String, Object> paramMap) {
		List<Menu> list = operationMaper.queryStaffOrgList(paramMap);
		return  Menu.buildTree(list, 0);
	}
    /**
     * 查询清单
     * @param paramMap
     * @return
     */
	public List<?> queryDataList(HashMap<String, Object> paramMap) {
		List<?> list = operationMaper.queryDataList(paramMap);
		return list;
	}
	
	public String queryDataCount(HashMap<String, Object> paramMap) {
		String tatalNum=operationMaper.queryDataCount(paramMap);
		return tatalNum;
	}
	
	public void saveDataStaff(HashMap<String, Object> paramMap) {
		
		operationMaper.saveDataStaff(paramMap); 
	}
	
	public void updateDataStaff(HashMap<String, Object> paramMap) {
		operationMaper.updateDataStaff(paramMap);
		
	}
	
	public void deleteDataStaff(HashMap<String, Object> paramMap) {
		operationMaper.deleteDataStaff(paramMap);
		
	}
	
	public void resetStaffPwd(HashMap<String, Object> paramMap) {
		operationMaper.resetStaffPwd(paramMap);
		
	}
	
	public void insertStaffRoles(HashMap<String, Object> paramMap) {
		
		String operType=StringUtil.valueOf(paramMap.get("OPER_FLAG"));
		if("add".equals(operType)){//授权
		operationMaper.insertStaffRoles(paramMap);
		}else if ("update".equals(operType)){//删除授权
			operationMaper.deleteStaffRoles(paramMap);
		}
	}
	
	public Map<String, Object> selectOrgInfo(HashMap<String, Object> paramMap) {
		return operationMaper.selectOrgInfo(paramMap);
	}
	
	public void deleteDataOrg(HashMap<String, Object> paramMap) {
		operationMaper.deleteDataOrg(paramMap);
		
	}
	
	public String queryOrgStatus(HashMap<String, Object> paramMap) {
		return operationMaper.queryOrgStatus(paramMap);
		
	}
	
	public void saveDataOrg(HashMap<String, Object> paramMap) {
		operationMaper.saveDataOrg(paramMap); 
	}
	
	public void updateDataOrg(HashMap<String, Object> paramMap) {
		operationMaper.updateDataOrg(paramMap);
		
	} 
 
	
	public List<?> querycheckStaff(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return operationMaper.querycheckStaff(map);
	}
	
	
	public List<String> selectmenuList(HashMap<String, Object> paramMap) {
		return operationMaper.selectmenuList(paramMap);
	}
	
	public List<Map<String, Object>> selectmenucout(Map<String, Object> paramMap) {
		return operationMaper.selectmenucout(paramMap);
	}
	public List<Map<String, Object>> selectmenucout1(Map<String, Object> paramMap) {
		return operationMaper.selectmenucout1(paramMap);
	}
	public List<Map<String, Object>> selectmenucout2(Map<String, Object> paramMap) {
		return operationMaper.selectmenucout2(paramMap);
	}
}
