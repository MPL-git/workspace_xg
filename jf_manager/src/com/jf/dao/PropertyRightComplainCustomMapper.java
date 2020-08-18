package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.PropertyRightComplainCustom;
import com.jf.entity.PropertyRightComplainCustomExample;


@Repository
public interface PropertyRightComplainCustomMapper{

	/**
	 * 查询列表
	 * 
	 * @param example
	 * @return
	 */
	List<PropertyRightComplainCustom> selectByExample(PropertyRightComplainCustomExample example);
    
	PropertyRightComplainCustom selectByPrimaryKey(Integer id);
	
	int countByExample(PropertyRightComplainCustomExample example);
}