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
public interface RoleMngMapper {
	public List<?> queryDataList(Map<String,Object> map);
	public List<?> queryCurUserAuth(Map<String,Object> map);
	public List<?> queryUserMenuList(Map<String,Object> map);
	public void saveData(HashMap<String, Object> paramMap);
	public void updateData(HashMap<String, Object> paramMap);
	public void deleteData(HashMap<String, Object> paramMap);
	public int queryDataCount(HashMap<String, Object> paramMap);
	public List<Menu> queryStaffMenusList(HashMap<String, Object> paramMap);
	public int insertStaffMenusPreTmp(HashMap<String, Object> paramMap);
	public void insertStaffMenusPreX(HashMap<String, Object> paramMap);
	public void insertStaffMenusTmpDel(HashMap<String, Object> paramMap);
	public void insertStaffMenus(HashMap<String, Object> paramMap);
	
	public List<Map<String, Object>> getroletypeNameList();
	
	
	public List<Menu> getRoleIdMenusList(HashMap<String, Object> paramMap);

	//新创建的查询Menu的方法
	List<Menu> queryCheckedStaffMenusList(HashMap<String, Object> paramMap);
	List<Menu> queryMenusList(HashMap<String, Object> paramMap);
	Menu selectMenuByPrimaryKey(Integer id);

	void newInsertStaffMenus(HashMap<String, Object> paramMap);
}
