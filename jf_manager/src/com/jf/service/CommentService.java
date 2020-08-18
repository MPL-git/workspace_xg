package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CommentCustomMapper;
import com.jf.dao.CommentMapper;
import com.jf.entity.Comment;
import com.jf.entity.CommentCustom;
import com.jf.entity.CommentCustomExample;
import com.jf.entity.CommentExample;

@Service
@Transactional
public class CommentService extends BaseService<Comment, CommentExample> {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private CommentCustomMapper commentCustomMapper;
	
	@Autowired
	public void setCommentMapper(CommentMapper commentMapper) {
		super.setDao(commentMapper);
		this.dao = commentMapper;
	}
	
	public int countByCustomExample(CommentCustomExample example) {
		return commentCustomMapper.countByCustomExample(example);
	}

	public List<CommentCustom> selectByCustomExample(CommentCustomExample example) {
		return commentCustomMapper.selectByCustomExample(example);
	}

	public CommentCustom selectByCustomPrimaryKey(Integer id) {
		return commentCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(Comment record, CommentCustomExample example) {
		return commentCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public List<Comment> selectByPropDescCustomExample(Integer id) {
		return commentCustomMapper.selectByPropDescCustomExample(id);
	}

	public Integer selectSaleQuantity(Integer id) {
		return commentCustomMapper.selectSaleQuantity(id);
	}

	public Integer selectPayers(Integer id) {
		return commentCustomMapper.selectPayers(id);
	}
}
