package com.jf.dao;

import org.springframework.stereotype.Repository;
@Repository
public interface CommonMapper{
	public Integer getSequence(String sequenceName);
}