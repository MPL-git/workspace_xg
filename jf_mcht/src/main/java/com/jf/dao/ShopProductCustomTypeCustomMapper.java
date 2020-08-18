package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ShopProductCustomTypeCustom;
import com.jf.entity.ShopProductCustomTypeExample;
@Repository
public interface ShopProductCustomTypeCustomMapper{
	
	public List<ShopProductCustomTypeCustom>selectByExample(ShopProductCustomTypeExample example);

	public int getMaxSeqNo(Integer mchtId);

	public void batchExecute(HashMap<String, Object> map);

	public void batchExecute2(HashMap<String, Object> map);

	public void batchExecute3(HashMap<String, Object> map);
	
}