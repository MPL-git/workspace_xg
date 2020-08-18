package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoCommentCustomMapper;
import com.jf.dao.VideoCommentMapper;
import com.jf.entity.VideoComment;
import com.jf.entity.VideoCommentCustom;
import com.jf.entity.VideoCommentCustomExample;
import com.jf.entity.VideoCommentExample;

@Service
@Transactional
public class VideoCommentService extends BaseService<VideoComment,VideoCommentExample> {
	@Autowired
	private VideoCommentMapper videoCommentMapper;
	@Autowired
	private VideoCommentCustomMapper videoCommentCustomMapper;
	
	@Autowired
	public void setVideoCommentMapper(VideoCommentMapper videoCommentMapper) {
		super.setDao(videoCommentMapper);
		this.videoCommentMapper = videoCommentMapper;
	}
	
	public int countByVideoCommentCustomExample(VideoCommentCustomExample example){
		return videoCommentCustomMapper.countByVideoCommentCustomExample(example);
	}
    public List<VideoCommentCustom> selectByVideoCommentCustomExample(VideoCommentCustomExample example){
    	return videoCommentCustomMapper.selectByVideoCommentCustomExample(example);
    }
    public VideoCommentCustom selectByVideoCommentCustomPrimaryKey(Integer id){
    	return videoCommentCustomMapper.selectByVideoCommentCustomPrimaryKey(id);
    }

}
