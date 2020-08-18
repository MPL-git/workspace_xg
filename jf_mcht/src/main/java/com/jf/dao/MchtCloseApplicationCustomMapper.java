package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtCloseApplicationCustom;
import com.jf.entity.MchtCloseApplicationCustomExample;
@Repository
public interface MchtCloseApplicationCustomMapper{
	public List<MchtCloseApplicationCustom>selectByExample(MchtCloseApplicationCustomExample example);

	public int countByExample(MchtCloseApplicationCustomExample example);

	public MchtCloseApplicationCustom selectCustomByPrimaryKey(Integer id);

}