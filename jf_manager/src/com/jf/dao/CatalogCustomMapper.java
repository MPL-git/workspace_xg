package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gzs.common.beans.Menu;

@Repository
public interface CatalogCustomMapper {
	public List<Menu> selectCatalog();
	public String queryDataCount(HashMap<String, Object> paramMap);
	public List<?> selectProductTypeList(Map<String,Object> map);
	public List<?> selectProductTypeListByid(HashMap<String,Object> map);
	public String getparentByid(HashMap<String,Object> map);
}
