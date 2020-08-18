package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface ProRecMapper {

	/**
	 * 查询推荐产品列表
	 * @return
	 */
	public List<Map<String,Object>> selectProdRec(Map<String,Object> param);
	
	/**
	 * 获取所有产品信息
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> selectProducts(Map<String,Object> param);
	
	/***
	 * 获取产品类型信息
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> selectProductTypes(Map<String,Object> param);
	
	/**
	 * 根据产品类型Id获取该类型下的所有产品
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> selectProductsForProductTypeId(Map<String,Object> param);

	/**
	 * 保存首页推荐信息
	 * @param param
	 * @return
	 */
	public void saveRecommend(Map<String,Object> param);
	
	/**
	 * 根据栏目编码查询栏目标识
	 * @return
	 */
	public  List selectCatalogId(HashMap<String,Object> map);
	
	/**
	 * 删除推荐产品
	 * @param param
	 */
	public void delete(Map<String,Object> param);
	public String selectProductsForProductTypeIdCount(Map<String,Object> param);
	 
	public List<Map<String,Object>> selectCatalogs(); 
	public List querycatadataList(HashMap<String,Object> map);
	public int querycatadataListTotal(HashMap<String,Object> map); 
	public void updatecatainfo(Map<String,Object> param);
}
