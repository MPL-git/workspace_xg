package com.jf.dao;

import com.jf.entity.CommentPic;
import com.jf.entity.ComplainOrderPicCustom;
import com.jf.entity.ComplainOrderPicCustomExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentPicCustomMapper {

	void insertBatch(List<CommentPic> commentPics);
}