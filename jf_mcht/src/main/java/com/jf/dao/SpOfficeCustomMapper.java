package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SpOfficeCustom;
import com.jf.entity.SpOfficeCustomExample;

@Repository
public interface SpOfficeCustomMapper {
   
	public int countByExample(SpOfficeCustomExample example);

	public List<SpOfficeCustom> selectByExample(SpOfficeCustomExample example);

}