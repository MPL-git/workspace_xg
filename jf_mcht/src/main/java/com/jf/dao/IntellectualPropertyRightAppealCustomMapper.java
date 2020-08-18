package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.IntellectualPropertyRightAppealCustom;
import com.jf.entity.IntellectualPropertyRightAppealCustomExample;


@Repository
public interface IntellectualPropertyRightAppealCustomMapper{

	/**
	 * 查询列表
	 * 
	 * @param example
	 * @return
	 */
	List<IntellectualPropertyRightAppealCustom> selectByExample(IntellectualPropertyRightAppealCustomExample example);
    
	IntellectualPropertyRightAppealCustom selectByPrimaryKey(Integer id);
	
	int countByExample(IntellectualPropertyRightAppealCustomExample example);
	
}