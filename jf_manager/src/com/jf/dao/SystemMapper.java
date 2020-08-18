package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gzs.common.beans.Menu;

/**
 * 登陆服务
 * @author sandy
 * 创建时间:2014-5-6
 */
@Repository
public interface SystemMapper {
	public List<?> queryUserPwd(Map<String,Object> map);
	public List<?> queryCurUserAuth(Map<String,Object> map);
	public List<?> queryUserMenuList(Map<String,Object> map);
	public List<Menu> selectMenuTree(HashMap<String, Object> paramMap);
	
	/**
	 * 
	 * @Title selectCwOrgStatus   
	 * @Description TODO(是否为钟表运营部状态)   
	 * @author pengl
	 * @date 2018年5月4日 下午4:12:06
	 */
	public String selectCwOrgStatus(Integer orgId);
	
}
