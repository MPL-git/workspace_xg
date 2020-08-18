package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.PropertyRightProsecutionCustom;
import com.jf.entity.PropertyRightProsecutionCustomExample;


@Repository
public interface PropertyRightProsecutionCustomMapper{

	/**
	 * 查询列表
	 * 
	 * @param example
	 * @return
	 */
	List<PropertyRightProsecutionCustom> selectByExample(PropertyRightProsecutionCustomExample example);
    
	PropertyRightProsecutionCustom selectByPrimaryKey(Integer id);
	
	int countByExample(PropertyRightProsecutionCustomExample example);
}