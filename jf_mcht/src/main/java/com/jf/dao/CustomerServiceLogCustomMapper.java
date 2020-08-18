package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServiceLogCustom;
import com.jf.entity.CustomerServiceLogCustomExample;
@Repository
public interface CustomerServiceLogCustomMapper{
	public List<CustomerServiceLogCustom>selectByExample(CustomerServiceLogCustomExample example);
}