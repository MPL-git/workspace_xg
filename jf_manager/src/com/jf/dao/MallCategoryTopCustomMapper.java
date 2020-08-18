package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MallCategoryTopCustom;
import com.jf.entity.MallCategoryTopExample;
@Repository
public interface MallCategoryTopCustomMapper{

	int countByExample(MallCategoryTopExample example);

	List<MallCategoryTopCustom> selectByExample(MallCategoryTopExample example);
	
    
}