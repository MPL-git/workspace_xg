package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ShopPreferentialInfoCustom;
import com.jf.entity.ShopPreferentialInfoExample;
@Repository
public interface ShopPreferentialInfoCustomMapper{
	public List<ShopPreferentialInfoCustom>selectByExample(ShopPreferentialInfoExample shopPreferentialInfoExample);

	public int countUpProductCount(Integer mchtId);
	
}