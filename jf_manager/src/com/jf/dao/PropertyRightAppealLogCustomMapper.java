package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.PropertyRightAppealLogCustom;
import com.jf.entity.PropertyRightAppealLogExample;


@Repository
public interface PropertyRightAppealLogCustomMapper{

	/**
	 * 查询列表
	 * 
	 * @param example
	 * @return
	 */
	List<PropertyRightAppealLogCustom> selectByExample(PropertyRightAppealLogExample example);
}