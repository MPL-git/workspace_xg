package com.jf.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.ProRecMapper;

@Service
public class ProdRecService {

	@Autowired
	private ProRecMapper proRecMapper;
	
	
	public List<Map<String,Object>> selectProdRec(Map<String, Object> param) {
		return proRecMapper.selectProdRec(param);
	}

	
	public List<Map<String, Object>> selectProducts(Map<String, Object> param) {
		return proRecMapper.selectProducts(param);
	}

	
	public List<Map<String,Object>> selectProductTypes(Map<String, Object> param) {
		return proRecMapper.selectProductTypes(param);
	}

	
	public List<Map<String, Object>> selectProductsForProductTypeId(
			Map<String, Object> param) {
		return proRecMapper.selectProductsForProductTypeId(param);
	}

	
	public void saveRecommend(Map<String, Object> param ) {
	 
	String ids = param.get("ids").toString(); 
		String _ids[] = ids.split(",");
		  ;
		for (String id : _ids) {
			Map<String,Object> _idMap = new HashMap<String, Object>();
			_idMap.put("CATALOG_ID", param.get("CATALOG_ID"));
			_idMap.put("PRODUCT_ID", id);
			_idMap.put("CREATE_STAFF_ID", param.get("CREATE_STAFF_ID"));
			proRecMapper.saveRecommend(_idMap);
		}
	}

	
	public List selectCatalogId(HashMap<String,Object> param) {
		// TODO Auto-generated method stub
		return  proRecMapper.selectCatalogId( param);
	}

	
	public void delete(Map<String, Object> param) { 
		 proRecMapper.delete(param); 
	}

	
	public String selectProductsForProductTypeIdCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.proRecMapper.selectProductsForProductTypeIdCount(param);
	}

	
	public List<Map<String, Object>> selectCatalogs() {
		// TODO Auto-generated method stub
		return proRecMapper.selectCatalogs();
	}
	
	public List querycatadataList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return proRecMapper.querycatadataList(map);
	}

	
	public int querycatadataListTotal(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return proRecMapper.querycatadataListTotal(map);
	}

	
	public void updatecatainfo(Map<String, Object> param) {
		proRecMapper.updatecatainfo(param);
		
	}


}	
