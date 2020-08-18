package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.AppealLogPicCustom;
import com.jf.entity.AppealLogPicCustomExample;
@Repository
public interface AppealLogPicCustomMapper{
	public List<AppealLogPicCustom>selectByExample(AppealLogPicCustomExample example);
	
	public List<String> getPicsByAppealLogId(Integer appealLogId);
}