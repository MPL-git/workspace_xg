package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MallCategoryItemCustomMapper;
import com.jf.dao.MallCategoryItemMapper;
import com.jf.entity.MallCategoryItem;
import com.jf.entity.MallCategoryItemCustom;
import com.jf.entity.MallCategoryItemCustomExample;
import com.jf.entity.MallCategoryItemExample;

@Service
@Transactional
public class MallCategoryItemService extends BaseService<MallCategoryItem, MallCategoryItemExample> {
	
	@Autowired
	private MallCategoryItemMapper mallCategoryItemMapper;
	
	@Autowired
	private MallCategoryItemCustomMapper mallCategoryItemCustomMapper;
	
	@Autowired
	public void setMallCategoryItemMapper(MallCategoryItemMapper mallCategoryItemMapper) {
		super.setDao(mallCategoryItemMapper);
		this.mallCategoryItemMapper = mallCategoryItemMapper;
	}
	
	public int countByCustomExample(MallCategoryItemCustomExample example) {
		return mallCategoryItemCustomMapper.countByCustomExample(example);
	}

	public List<MallCategoryItemCustom> selectByCustomExample(MallCategoryItemCustomExample example) {
		return mallCategoryItemCustomMapper.selectByCustomExample(example);
	}

	public MallCategoryItemCustom selectByCustomPrimaryKey(Integer id) {
		return mallCategoryItemCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") MallCategoryItem record, @Param("example") MallCategoryItemCustomExample example) {
		return mallCategoryItemCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
}
