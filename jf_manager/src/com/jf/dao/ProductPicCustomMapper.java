package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface ProductPicCustomMapper{
	public List<String>getPics(HashMap<String, Object> paramMap);

	public List<String> getPicsByProductIds(List<Integer> productIds);
}