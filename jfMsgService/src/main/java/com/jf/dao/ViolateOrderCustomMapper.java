package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ViolateOrder;
import com.jf.entity.ViolateOrderCustomExample;
@Repository
public interface ViolateOrderCustomMapper{
	public List<ViolateOrder>selectByExample(ViolateOrderCustomExample violateOrderCustomExample);
}