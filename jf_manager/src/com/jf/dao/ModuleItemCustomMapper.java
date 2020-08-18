package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ModuleItemCustom;
import com.jf.entity.ModuleItemExample;
@Repository
public interface ModuleItemCustomMapper{
	List<ModuleItemCustom> selectByExample(ModuleItemExample example);
	List<Integer> getIdsByModuleId(Integer decorateModuleId);
	List<Integer> getItemIdsByItemType(Integer decorateInfoId);
	List<Integer> getItemIdsByInfoId(HashMap<String, Object> paramMap);
	int getCountByProductId(HashMap<String, Object> paramMap);
	
}