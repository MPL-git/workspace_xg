package com.jf.service;


import java.util.ArrayList;
import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.CommentPicMapper;
import com.jf.entity.CommentPic;
import com.jf.entity.CommentPicExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年7月9日 下午2:16:02 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CommentPicService extends BaseService<CommentPic, CommentPicExample> {
	@Autowired
	private CommentPicMapper commentPicMapper;

	@Autowired
	public void setCommentPicMapper(CommentPicMapper commentPicMapper) {
		this.setDao(commentPicMapper);
		this.commentPicMapper = commentPicMapper;
	}

	public List<String> getCommentPics(Integer commentId) {
		List<String> pics = new ArrayList<String>();
		CommentPicExample commentPicExample = new CommentPicExample();
		commentPicExample.createCriteria().andCommentIdEqualTo(commentId).andDelFlagEqualTo("0");
		List<CommentPic> commentPics = selectByExample(commentPicExample);
		if(CollectionUtils.isNotEmpty(commentPics)){
			for (CommentPic commentPic : commentPics) {
				pics.add(StringUtil.getPic(commentPic.getPic(), ""));
			}
		}
		return pics;
	}
	
	
}
