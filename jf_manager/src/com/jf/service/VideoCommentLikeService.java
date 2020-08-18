package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoCommentLikeCustomMapper;
import com.jf.dao.VideoCommentLikeMapper;
import com.jf.entity.VideoCommentLike;
import com.jf.entity.VideoCommentLikeCustom;
import com.jf.entity.VideoCommentLikeCustomExample;
import com.jf.entity.VideoCommentLikeExample;

@Service
@Transactional
public class VideoCommentLikeService extends BaseService<VideoCommentLike,VideoCommentLikeExample> {
	@Autowired
	private VideoCommentLikeMapper videoCommentLikeMapper;
	@Autowired 
	private VideoCommentLikeCustomMapper videoCommentLikeCustomMapper;
	
	@Autowired
	public void setVideoCommentLikeMapper(VideoCommentLikeMapper videoCommentLikeMapper) {
		super.setDao(videoCommentLikeMapper);
		this.videoCommentLikeMapper = videoCommentLikeMapper;
	}
	
	public int countByVideoCommentLikeCustomExample(VideoCommentLikeCustomExample example){
		return videoCommentLikeCustomMapper.countByVideoCommentLikeCustomExample(example);
	}
    public List<VideoCommentLikeCustom> selectByVideoCommentLikeCustomExample(VideoCommentLikeCustomExample example){
    	return videoCommentLikeCustomMapper.selectByVideoCommentLikeCustomExample(example);
    }
    public VideoCommentLikeCustom selectByVideoCommentLikeCustomPrimaryKey(Integer id){
    	return videoCommentLikeCustomMapper.selectByVideoCommentLikeCustomPrimaryKey(id);
    }

}
