package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.CommentCustom;
import com.jf.entity.CommentCustomExample;
import com.jf.entity.CommentExample;
@Repository
public interface CommentCustomMapper{
	public List<CommentCustom>selectByExample(CommentExample commentExample);

	public List<HashMap<String, Object>> getTotalProductScoreByMchtId(Integer mchtId);

	public int countByExample(CommentCustomExample commentCustomExample);

	public void deleteReply(int id);

	public double countProductScoreByMhctId(Integer mchtId);
}