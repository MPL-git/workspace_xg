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
public interface StatusMapper {
	public List<?> querytStatusList(HashMap<String, Object> paramMap);
}
