package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gzs.common.beans.Menu;

/**
 * 部门管理
 * @author hungcp
 * 创建时间:2014-5-6
 */
@Repository
public interface OrgMngMapper {
	public List<Menu> queryStaffOrgList(HashMap<String, Object> paramMap);
	public List<?> queryDataList(Map<String,Object> map);
	public List<?> querycheckStaff(Map<String,Object> map);
	public List<?> queryCurUserAuth(Map<String,Object> map);
	public List<?> queryUserMenuList(Map<String,Object> map);
	public void saveDataStaff(HashMap<String, Object> paramMap);
	public void updateDataStaff(HashMap<String, Object> paramMap);
	public void deleteDataStaff(HashMap<String, Object> paramMap);
	public String queryDataCount(HashMap<String, Object> paramMap);
	public void resetStaffPwd(HashMap<String, Object> paramMap);
	public Map<String, Object> selectOrgInfo(HashMap<String, Object> paramMap);
	public void deleteDataOrg(HashMap<String, Object> paramMap);
	public String queryOrgStatus(HashMap<String, Object> paramMap);
	public void saveDataOrg(HashMap<String, Object> paramMap);
	public void updateDataOrg(HashMap<String, Object> paramMap);
	public void insertStaffRoles(HashMap<String, Object> paramMap);
	public void deleteStaffRoles(HashMap<String, Object> paramMap);
	
	
	public List<String> selectmenuList(Map<String,Object> map);
	public List<Map<String, Object>> selectmenucout(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectmenucout1(Map<String, Object> paramMap);
	public List<Map<String, Object>> selectmenucout2(Map<String, Object> paramMap);
}
