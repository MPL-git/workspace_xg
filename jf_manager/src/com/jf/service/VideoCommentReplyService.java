package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoCommentReplyCustomMapper;
import com.jf.dao.VideoCommentReplyMapper;
import com.jf.entity.VideoCommentReply;
import com.jf.entity.VideoCommentReplyCustom;
import com.jf.entity.VideoCommentReplyCustomExample;
import com.jf.entity.VideoCommentReplyExample;

@Service
@Transactional
public class VideoCommentReplyService extends BaseService<VideoCommentReply,VideoCommentReplyExample> {
	@Autowired
	private VideoCommentReplyMapper videoCommentReplyMapper;
	@Autowired 
	private VideoCommentReplyCustomMapper videoCommentReplyCustomMapper;
	
	@Autowired
	public void setVideoCommentReplyMapper(VideoCommentReplyMapper videoCommentReplyMapper) {
		super.setDao(videoCommentReplyMapper);
		this.videoCommentReplyMapper = videoCommentReplyMapper;
	}
	
	public int countByVideoCommentReplyCustomExample(VideoCommentReplyCustomExample example){
		return videoCommentReplyCustomMapper.countByVideoCommentReplyCustomExample(example);
	}
    public List<VideoCommentReplyCustom> selectByVideoCommentReplyCustomExample(VideoCommentReplyCustomExample example){
    	return videoCommentReplyCustomMapper.selectByVideoCommentReplyCustomExample(example);
    }
    public VideoCommentReplyCustom selectByVideoCommentReplyCustomPrimaryKey(Integer id){
    	return videoCommentReplyCustomMapper.selectByVideoCommentReplyCustomPrimaryKey(id);
    }

}
