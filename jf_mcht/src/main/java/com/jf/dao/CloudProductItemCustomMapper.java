package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CloudProductItemCustom;
import com.jf.entity.CloudProductItemCustomExample;
@Repository
public interface CloudProductItemCustomMapper{
	public List<CloudProductItemCustom>selectByExample(CloudProductItemCustomExample e);

	public int countByExample(CloudProductItemCustomExample e);

	public String getSupplierStatusByItemId(Integer id);

	public String getMchtSupplierStatus(HashMap<String, Object> map);
	
}