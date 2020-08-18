package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.PropertyRightComplainLogCustom;
import com.jf.entity.PropertyRightComplainLogExample;


@Repository
public interface PropertyRightComplainLogCustomMapper{

	/**
	 * 查询列表
	 * 
	 * @param example
	 * @return
	 */
	List<PropertyRightComplainLogCustom> selectByExample(PropertyRightComplainLogExample example);
}