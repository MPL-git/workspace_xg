package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ViolateComplainOrder;
import com.jf.entity.ViolateComplainOrderCustom;
import com.jf.entity.ViolateComplainOrderExample;
@Repository
public interface ViolateComplainOrderCustomMapper{
	public List<ViolateComplainOrderCustom>selectByExample(ViolateComplainOrderExample violateOrderExample);

	public ViolateComplainOrder getViolateComplainOrderByViolateOrderId(Integer violateOrderId);
}