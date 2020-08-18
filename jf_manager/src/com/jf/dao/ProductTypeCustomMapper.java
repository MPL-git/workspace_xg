package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gzs.common.beans.Menu;
import com.jf.entity.ProductTypeCustom;
@Repository
public interface ProductTypeCustomMapper{
	public List<Menu> selectProductType(Map<String,Object> map);
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
	
	/**
	 * 
	 * @Title getProductTypeList   
	 * @Description TODO(分类模块——三级分类)   
	 * @author pengl
	 * @date 2018年7月23日 下午5:49:07
	 */
	public List<Map<String, Object>> getProductTypeList(Map<String, Object> map);
	public Integer getProductTypeCount(Map<String, Object> map);
	public String getProductTypeIds(Map<String, Object> map);
	public String getProductCount(Map<String, Object> map);
	public Integer countListByFirstProductTypeIds(HashMap<String, Object> map);
	public List<HashMap<String,Object>> getListByFirstProductTypeIds(HashMap<String, Object> map);
	public List<ProductTypeCustom> getProductTypeListByStaffId(Integer staffId);

	//查询类目流量报表数据
    List<ProductTypeCustom> getcategoryFlowReportData(Map<String, Object> paraMap);
}