package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProvinceFreightCustom;
import com.jf.entity.ProvinceFreightExample;
@Repository
public interface ProvinceFreightCustomMapper{
	public List<ProvinceFreightCustom>selectByExample(ProvinceFreightExample example);

	public String getAreaIds(HashMap<String, Object> map);
}