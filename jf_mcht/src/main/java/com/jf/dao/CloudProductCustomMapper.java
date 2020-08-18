package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CloudProductCustom;
@Repository
public interface CloudProductCustomMapper{

	public List<CloudProductCustom>selectByExample(HashMap<String,Object> map);
	
	public int countByExample(HashMap<String,Object> map);

	public List<HashMap<String, Object>> getRelatedProduct(HashMap<String,Object> map);

	public CloudProductCustom selectCustomById(int id);
	
}