package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CouponExchangeCodeCustom;
import com.jf.entity.CouponExchangeCodeCustomExample;
@Repository
public interface CouponExchangeCodeCustomMapper {
	
	public List<CouponExchangeCodeCustom> selectByCustomExample(CouponExchangeCodeCustomExample example);

    public CouponExchangeCodeCustom selectByCustomPrimaryKey(Integer id);
	
    public int countByCustomExample(CouponExchangeCodeCustomExample example);
    
}