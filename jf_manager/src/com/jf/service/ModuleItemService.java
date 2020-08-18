package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ModuleItemCustomMapper;
import com.jf.dao.ModuleItemMapper;
import com.jf.entity.ModuleItem;
import com.jf.entity.ModuleItemCustom;
import com.jf.entity.ModuleItemExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年2月28日 下午1:54:34 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ModuleItemService extends BaseService<ModuleItem, ModuleItemExample>{
	
	@Autowired
	private ModuleItemMapper moduleItemMapper;
	@Autowired
	private ModuleItemCustomMapper moduleItemCustomMapper;

	@Autowired
	public void setModuleItemMapper(ModuleItemMapper moduleItemMapper) {
		this.setDao(moduleItemMapper);
		this.moduleItemMapper = moduleItemMapper;
	}
	
	public List<ModuleItemCustom> selectModuleItemCustomByExample(ModuleItemExample example){
		return moduleItemCustomMapper.selectByExample(example);
	}
	
	public List<Integer> getIdsByModuleId(Integer decorateModuleId) {
		return moduleItemCustomMapper.getIdsByModuleId(decorateModuleId);
	}

	public List<Integer> getItemIdsByItemType(Integer decorateInfoId) {
		return moduleItemCustomMapper.getItemIdsByItemType(decorateInfoId);
	}
	
	public List<Integer> getItemIdsByInfoId(HashMap<String, Object> paramMap) {
		return moduleItemCustomMapper.getItemIdsByInfoId(paramMap);
	}

	public int getCountByProductId(HashMap<String, Object> paramMap) {
		return moduleItemCustomMapper.getCountByProductId(paramMap);
	}
}
