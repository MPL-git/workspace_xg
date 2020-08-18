package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CommentCustomMapper;
import com.jf.dao.CommentMapper;
import com.jf.entity.Comment;
import com.jf.entity.CommentCustom;
import com.jf.entity.CommentCustomExample;
import com.jf.entity.CommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CommentService extends BaseService<Comment,CommentExample> {
	@Autowired
	private CommentMapper dao;
	
	@Autowired
	private CommentCustomMapper commentCustomMapper;
	
	@Autowired
	public void setCommentMapper(CommentMapper commentMapper) {
		super.setDao(commentMapper);
		this.dao = commentMapper;
	}
	
	public List<CommentCustom> selectCommentCustomByExample(CommentCustomExample example) {
		return commentCustomMapper.selectByExample(example);
	}

	public List<HashMap<String, Object>> getTotalProductScoreByMchtId(Integer mchtId) {
		return commentCustomMapper.getTotalProductScoreByMchtId(mchtId);
	}

	public int countCommentCustomByExample(CommentCustomExample commentCustomExample) {
		return commentCustomMapper.countByExample(commentCustomExample);
	}

	public void deleteReply(int id) {
		commentCustomMapper.deleteReply(id);
	}

	public double countProductScoreByMhctId(Integer mchtId) {
		return commentCustomMapper.countProductScoreByMhctId(mchtId);
	}
}
