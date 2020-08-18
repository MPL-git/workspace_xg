package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gzs.common.beans.Menu;
import com.jf.dao.CatalogCustomMapper;
import com.jf.dao.CatalogMapper;
import com.jf.entity.Catalog;
import com.jf.entity.CatalogExample;

@Service
public class CatalogService extends BaseService<Catalog, CatalogExample>{
	
	@Autowired
	private CatalogMapper catalogMapper;
	
	@Resource
	private CatalogCustomMapper catalogCustomMapper;
	
	@Autowired
	public void setMchtInfoMapper(CatalogMapper catalogMapper) {
		super.setDao(catalogMapper);
		this.catalogMapper = catalogMapper;
	}
	
	
	public List<Menu> selectCatalog(){
		List<Menu> list = catalogCustomMapper.selectCatalog();
		return Menu.buildTree(list, 0);
	}
	
	public String queryDataCount(HashMap<String, Object> paramMap){
		String tatalNum=catalogCustomMapper.queryDataCount(paramMap);
		return tatalNum;
	}
	
	public List<?> selectProductTypeList(Map<String, Object> paramMap){
		List<?> list = catalogCustomMapper.selectProductTypeList(paramMap);
		return list;
	}
	
	public List<?> selectProductTypeListByid(HashMap<String, Object> map) {
		List<?> list =catalogCustomMapper.selectProductTypeListByid(map);
		return list;
	}
	
	public String getparentByid(HashMap<String, Object> map){
		return catalogCustomMapper.getparentByid(map);
	}
}
