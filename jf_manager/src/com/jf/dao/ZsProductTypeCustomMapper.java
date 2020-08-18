package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gzs.common.beans.Menu;
import com.jf.entity.ZsProductTypeCustom;
import com.jf.entity.ZsProductTypeExample;
@Repository
public interface ZsProductTypeCustomMapper{

	public List<Menu> selectZsProductType(Map<String,Object> map);
	public List<?> selectProductTypeList(Map<String,Object> map);
	public String queryDataCount(HashMap<String, Object> paramMap);
	public List<?> getprarentList(HashMap<String, Object> paramMap);
	public String getprarentId(HashMap<String, Object> paramMap);
	public String getPrarentF(HashMap<String, Object> paramMap);
	public int getIsNotType(HashMap<String, Object> paramMap);
	public String getProdTypeName(HashMap<String, Object> paramMap);
	public int gettlevvel(Map<String,Object> map);
	public List<?> selectProductTypeListByid(HashMap<String,Object> map);
	public String getNamesByIds(String ids);
	public List<String> getZsProductTypeDetails(List<Integer> zsProductTypeIdList);
	
	int zscountByExample(ZsProductTypeExample example);
	public List<ZsProductTypeCustom> zsselectByExample(ZsProductTypeExample example);
	
	public List<?> zsselectProductTypeList(Map<String,Object> map);
	public String countdata(HashMap<String, Object> paramMap);
	
}