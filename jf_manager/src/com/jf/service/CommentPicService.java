package com.jf.service;

import com.jf.dao.CommentPicCustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CommentPicMapper;
import com.jf.entity.CommentPic;
import com.jf.entity.CommentPicExample;

import java.util.List;

@Service
@Transactional
public class CommentPicService extends BaseService<CommentPic, CommentPicExample> {

	@Autowired
	private CommentPicMapper commentPicMapper;
	@Autowired
	private CommentPicCustomMapper commentPicCustomMapper;
	@Autowired
	public void setCommentPicMapper(CommentPicMapper commentPicMapper) {
		super.setDao(commentPicMapper);
		this.dao = commentPicMapper;
	}

    public void insertBatch(List<CommentPic> commentPics) {
		commentPicCustomMapper.insertBatch(commentPics);
    }
}
