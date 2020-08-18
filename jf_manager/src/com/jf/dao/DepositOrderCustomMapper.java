package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.DepositOrderCustom;
import com.jf.entity.DepositOrderCustomExample;
@Repository
public interface DepositOrderCustomMapper{
	public List<DepositOrderCustom>selectByExample(DepositOrderCustomExample example);

	public int countByExample(DepositOrderCustomExample example);
}