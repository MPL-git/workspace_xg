package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.HelpItemCustomMapper;
import com.jf.dao.HelpItemMapper;
import com.jf.entity.HelpItem;
import com.jf.entity.HelpItemCustom;
import com.jf.entity.HelpItemCustomExample;
import com.jf.entity.HelpItemExample;

@Service
@Transactional
public class HelpItemService extends BaseService<HelpItem, HelpItemExample> {

	@Autowired
	private HelpItemMapper helpItemMapper;
	
	@Autowired
	private HelpItemCustomMapper helpItemCustomMapper;
	
	@Autowired
	public void setHelpItemMapper(HelpItemMapper helpItemMapper) {
		super.setDao(helpItemMapper);
		this.helpItemMapper = helpItemMapper;
	}
	
	public int countByCustomExample(HelpItemCustomExample example) {
		return helpItemCustomMapper.countByCustomExample(example);
	}

	public List<HelpItemCustom> selectByCustomExample(HelpItemCustomExample example) {
		return helpItemCustomMapper.selectByCustomExample(example);
	}

	public HelpItem selectByCustomPrimaryKey(Integer id) {
		return helpItemCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(HelpItem record, HelpItemCustomExample example) {
		return helpItemCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
}
