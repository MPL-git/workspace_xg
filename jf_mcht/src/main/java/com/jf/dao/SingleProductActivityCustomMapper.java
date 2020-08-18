package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;
import com.jf.entity.SingleProductActivityExample;
@Repository
public interface SingleProductActivityCustomMapper{
	public List<SingleProductActivityCustom>selectByExample(SingleProductActivityExample singleProductActivityExample);

	public int countByExample(SingleProductActivityCustomExample example);

	public int getEnrollCount1(Map<String, Object> paramMap);

	public int getEnrollCount2(Map<String, Object> paramMap);
}