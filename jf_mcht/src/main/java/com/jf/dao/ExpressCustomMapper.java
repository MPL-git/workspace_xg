package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.Express;
@Repository
public interface ExpressCustomMapper{
	public List<Express>getExpressByName(String name);
}