package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MallCategoryModuleCustomMapper;
import com.jf.dao.MallCategoryModuleMapper;
import com.jf.entity.MallCategoryModule;
import com.jf.entity.MallCategoryModuleCustom;
import com.jf.entity.MallCategoryModuleCustomExample;
import com.jf.entity.MallCategoryModuleExample;

@Service
@Transactional
public class MallCategoryModuleService extends BaseService<MallCategoryModule, MallCategoryModuleExample> {

	@Autowired
	private MallCategoryModuleMapper mallCategoryModuleMapper;
	
	@Autowired
	private MallCategoryModuleCustomMapper mallCategoryModuleCustomMapper;
	
	@Autowired
	public void setMallCategoryModuleMapper(MallCategoryModuleMapper mallCategoryModuleMapper) {
		super.setDao(mallCategoryModuleMapper);
		this.mallCategoryModuleMapper = mallCategoryModuleMapper;
	}
	
	public int countByCustomExample(MallCategoryModuleCustomExample example) {
		return mallCategoryModuleCustomMapper.countByCustomExample(example);
	}

	public List<MallCategoryModuleCustom> selectByCustomExample(MallCategoryModuleCustomExample example) {
		return mallCategoryModuleCustomMapper.selectByCustomExample(example);
	}

	public MallCategoryModuleCustom selectByCustomPrimaryKey(Integer id) {
		return mallCategoryModuleCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") MallCategoryModule record, @Param("example") MallCategoryModuleCustomExample example) {
		return mallCategoryModuleCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
}
