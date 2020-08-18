package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.IntellectualPropertyRightCustom;
import com.jf.entity.IntellectualPropertyRightExample;


@Repository
public interface IntellectualPropertyRightCustomMapper{

	/**
	 * 查询列表
	 * 
	 * @param example
	 * @return
	 */
	List<IntellectualPropertyRightCustom> selectByExample(IntellectualPropertyRightExample example);
    
	IntellectualPropertyRightCustom selectByPrimaryKey(Integer id);
	
	int countByExample(IntellectualPropertyRightExample example);
}