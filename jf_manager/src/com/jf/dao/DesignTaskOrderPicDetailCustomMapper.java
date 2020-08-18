package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.DesignTaskOrderPicDetailCustom;
import com.jf.entity.DesignTaskOrderPicDetailCustomExample;
@Repository
public interface DesignTaskOrderPicDetailCustomMapper{
	
	public List<DesignTaskOrderPicDetailCustom>designTaskOrderPicDetailSelectByExample(DesignTaskOrderPicDetailCustomExample example);

	public int designTaskOrderPicDetailCountByExample(DesignTaskOrderPicDetailCustomExample example);
}