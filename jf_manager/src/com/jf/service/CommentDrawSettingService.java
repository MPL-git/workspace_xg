package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CommentDrawSettingCustomMapper;
import com.jf.dao.CommentDrawSettingMapper;
import com.jf.entity.CommentDrawSetting;
import com.jf.entity.CommentDrawSettingCustom;
import com.jf.entity.CommentDrawSettingCustomExample;
import com.jf.entity.CommentDrawSettingExample;

@Service
@Transactional
public class CommentDrawSettingService extends BaseService<CommentDrawSetting, CommentDrawSettingExample> {

	@Autowired
	private CommentDrawSettingMapper commentDrawSettingMapper;
	
	@Autowired
	private CommentDrawSettingCustomMapper commentDrawSettingCustomMapper;
	
	@Autowired
	public void setCommentDrawSettingMapper(CommentDrawSettingMapper commentDrawSettingMapper) {
		super.setDao(commentDrawSettingMapper);
		this.dao = commentDrawSettingMapper;
	}
	
	public int countByCustomExample(CommentDrawSettingCustomExample example) {
		return commentDrawSettingCustomMapper.countByCustomExample(example);
	}

	public List<CommentDrawSettingCustom> selectByCustomExample(CommentDrawSettingCustomExample example) {
		return commentDrawSettingCustomMapper.selectByCustomExample(example);
	}

	public CommentDrawSettingCustom selectByCustomPrimaryKey(Integer id) {
		return commentDrawSettingCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(CommentDrawSetting record, CommentDrawSettingCustomExample example) {
		return commentDrawSettingCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	
}
