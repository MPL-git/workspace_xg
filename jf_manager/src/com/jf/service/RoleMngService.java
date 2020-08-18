package com.jf.service;

import java.util.*;

import com.jf.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzs.common.beans.Menu;
import com.jf.dao.RoleMngMapper;
import com.jf.dao.WebcommonMapper;
@Service
public class RoleMngService{
	@Autowired
	RoleMngMapper operationMaper;
	
	@Autowired
	WebcommonMapper webcommonMaper;

    /**
     * 查询清单
     * @param paramMap
     * @return
     */
	public List<?> queryDataList(HashMap<String, Object> paramMap) {
		List<?> list = operationMaper.queryDataList(paramMap);
		return list;
	}
	
	public int queryDataCount(HashMap<String, Object> paramMap) {
 
		return operationMaper.queryDataCount(paramMap); 
	}
	
	public void saveData(HashMap<String, Object> paramMap) {
		
		operationMaper.saveData(paramMap);
	}
	
	public void updateData(HashMap<String, Object> paramMap) {
		operationMaper.updateData(paramMap);
		
	}
	
	public void deleteData(HashMap<String, Object> paramMap) {
		operationMaper.deleteData(paramMap);
		
	}
	
	public List<Menu> queryStaffMenusList(HashMap<String, Object> paramMap) {
		List<Menu> list = operationMaper.queryStaffMenusList(paramMap);
		return  Menu.buildTree(list, 0);
	}
	
	public List<Menu> getRoleIdMenusList(HashMap<String, Object> paramMap) {
		List<Menu> list = operationMaper.getRoleIdMenusList(paramMap);
		return  Menu.buildTree(list, 0);
	}
	
	public List<Map<String, Object>> getroletypeNameList(){
		return operationMaper.getroletypeNameList();
	}
	
	public void insertStaffMenus(HashMap<String, Object> paramMap) {
		//注销原权限
		paramMap.put("SEQUENCE_NAME", "tmpMenuSeq");
		int operatorSeq=webcommonMaper.querySysSequence(paramMap);
		paramMap.put("operatorSeq", operatorSeq);
		operationMaper.insertStaffMenusPreTmp(paramMap);
		operationMaper.insertStaffMenusPreX(paramMap);
		operationMaper.insertStaffMenusTmpDel(paramMap);
		//授权
		if(!"".equals(paramMap.get("CHECK_MENUS"))){
			//根据CHECK_MENUS里的id查出SYS_MENU表的数据,要求STATUS为A,
			List<Menu> menus = operationMaper.queryMenusList(paramMap);
			//String menuParentIds = "";//用于存放父级id
			HashSet menuParentIds = new HashSet();
			//循环遍历所有menus获取id
			for (Menu menu : menus) {
				int menuID = menu.getMenuID();
			// 再创个方法递归查询CHECK_MENUS的所有父级ID,
				menuParentIds.add(menuID);
				menuParentIds = queryMenuParentId(menuID,menuParentIds);
			}
			//把所有父级ID传入方法
			if ("".equals(menuParentIds)&& StringUtils.isEmpty(menuParentIds)){

			}else {
 			//Object newMenuParentIds = menuParentIds;
				//创建字符串拼接方便后面转成object传参;0是最上层的父ID,递归时没有加入Set,现在再加到字符串不用调整","
				String strMenuParentIds = "0";
				for (Object menuParentId : menuParentIds) {
					strMenuParentIds = strMenuParentIds + "," + menuParentId;
				}
				Object objMenuParentIds = strMenuParentIds; //自动转换转成obj
			paramMap.put("menuParentIds",objMenuParentIds);
			operationMaper.newInsertStaffMenus(paramMap);
			}
			//operationMaper.insertStaffMenus(paramMap); 问题sql出处,原来执行很慢的Sql
		}
	}

	//private String queryMenuParentId(int menuID,String menuParentIds) {
	private HashSet queryMenuParentId(int menuID,HashSet menuParentIds) {
		Menu menu = operationMaper.selectMenuByPrimaryKey(menuID);
		int parentID = menu.getParentID();
			//menuParentIds = menuParentIds + parentID + ",";
		if (parentID == 0){
			//如果父id等于0,说明已经到最上层,跳出递归
			return menuParentIds;
		}{
			menuParentIds.add(parentID);
			return queryMenuParentId(parentID,menuParentIds);
		}
	}

	public List<Menu> queryCheckedStaffMenusList(HashMap<String, Object> paramCheckedMap) {
		return operationMaper.queryCheckedStaffMenusList(paramCheckedMap);
	}
}
