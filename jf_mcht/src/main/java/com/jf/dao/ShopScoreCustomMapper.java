package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface ShopScoreCustomMapper{
	public List<HashMap<String,Object>> getTotalShopScoreByMchtId(Integer mchtId);

	public List<HashMap<String, Object>> countShopScoreByMhctId(Integer mchtId);
}