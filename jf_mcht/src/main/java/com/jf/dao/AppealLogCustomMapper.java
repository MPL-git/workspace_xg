package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AppealLogCustom;
import com.jf.entity.AppealLogCustomExample;
@Repository
public interface AppealLogCustomMapper{
	public List<AppealLogCustom>selectByExample(AppealLogCustomExample example);
}