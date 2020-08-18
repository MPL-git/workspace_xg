package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SubDepositOrderCustom;
import com.jf.entity.SubDepositOrderCustomExample;
@Repository
public interface SubDepositOrderCustomMapper{
    public List<SubDepositOrderCustom>selectByExample(SubDepositOrderCustomExample example);
	public int countByExample(SubDepositOrderCustomExample example);
	public List<String> getBrandNamesByMchtId(Integer mchtId);
}