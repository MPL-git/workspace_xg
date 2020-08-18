package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.ViolateComplainOrderCustom;
import com.jf.entity.ViolateComplainOrderExample;
@Repository
public interface ViolateComplainOrderCustomMapper{
	public List<ViolateComplainOrderCustom>selectByExample(ViolateComplainOrderExample violateOrderExample);

	public List<Map<String, Object>> getAllProcesBy();
}