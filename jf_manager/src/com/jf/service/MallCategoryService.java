package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.HotSearchWordMapper;
import com.jf.dao.MallCategoryCustomMapper;
import com.jf.dao.MallCategoryMapper;
import com.jf.entity.MallCategory;
import com.jf.entity.MallCategoryCustom;
import com.jf.entity.MallCategoryCustomExample;
import com.jf.entity.MallCategoryExample;

@Service
@Transactional
public class MallCategoryService extends BaseService<MallCategory, MallCategoryExample> {

	@Autowired
	private MallCategoryMapper mallCategoryMapper;
	
	@Autowired
	private MallCategoryCustomMapper mallCategoryCustomMapper;
	
	@Autowired
	public void setMallCategoryMapper(MallCategoryMapper mallCategoryMapper) {
		super.setDao(mallCategoryMapper);
		this.mallCategoryMapper = mallCategoryMapper;
	}
	
	public int countByCustomExample(MallCategoryCustomExample example) {
		return mallCategoryCustomMapper.countByCustomExample(example);
	}

	public List<MallCategoryCustom> selectByCustomExample(MallCategoryCustomExample example) {
		return mallCategoryCustomMapper.selectByCustomExample(example);
	}

	public MallCategoryCustom selectByCustomPrimaryKey(Integer id) {
		return mallCategoryCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") MallCategory record, @Param("example") MallCategoryCustomExample example) {
		return mallCategoryCustomMapper.updateByCustomExampleSelective(record, example);
	}

	
}
