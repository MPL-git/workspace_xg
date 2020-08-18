package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AppealOrderCustom;
import com.jf.entity.AppealOrderCustomExample;
@Repository
public interface AppealOrderCustomMapper{
	public List<AppealOrderCustom>selectByExample(AppealOrderCustomExample example);

	public int countByExample(AppealOrderCustomExample example);

}