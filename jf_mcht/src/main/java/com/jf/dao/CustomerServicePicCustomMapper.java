package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CustomerServicePicCustom;
@Repository
public interface CustomerServicePicCustomMapper{

	List<CustomerServicePicCustom> getPicsByServiceOrderId(Integer serviceOrderId);
	
}