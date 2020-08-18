package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.DesignTaskOrderPicCustom;
import com.jf.entity.DesignTaskOrderPicCustomExample;
@Repository
public interface DesignTaskOrderPicCustomMapper{
	
	public List<DesignTaskOrderPicCustom>designTaskOrderPicSelectByExample(DesignTaskOrderPicCustomExample example);

	public int designTaskOrderPicCountByExample(DesignTaskOrderPicCustomExample example);
	
}