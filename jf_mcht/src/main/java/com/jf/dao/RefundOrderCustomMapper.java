package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.RefundOrderCustom;
import com.jf.entity.RefundOrderCustomExample;
@Repository
public interface RefundOrderCustomMapper{
	public List<RefundOrderCustom>selectByRefundOrderCustomExample(RefundOrderCustomExample example);
	
}