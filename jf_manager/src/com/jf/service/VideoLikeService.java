package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoLikeCustomMapper;
import com.jf.dao.VideoLikeMapper;
import com.jf.entity.VideoLike;
import com.jf.entity.VideoLikeCustom;
import com.jf.entity.VideoLikeCustomExample;
import com.jf.entity.VideoLikeExample;

@Service
@Transactional
public class VideoLikeService extends BaseService<VideoLike,VideoLikeExample> {
	@Autowired
	private VideoLikeMapper videoLikeMapper;
	@Autowired 
	private VideoLikeCustomMapper videoLikeCustomMapper;
	
	@Autowired
	public void setVideoLikeMapper(VideoLikeMapper videoLikeMapper) {
		super.setDao(videoLikeMapper);
		this.videoLikeMapper = videoLikeMapper;
	}
	
	public int countByVideoLikeCustomExample(VideoLikeCustomExample example){
		return videoLikeCustomMapper.countByVideoLikeCustomExample(example);
	}
    public List<VideoLikeCustom> selectByVideoLikeCustomExample(VideoLikeCustomExample example){
    	return videoLikeCustomMapper.selectByVideoLikeCustomExample(example);
    }
    public VideoLikeCustom selectByVideoLikeCustomPrimaryKey(Integer id){
    	return videoLikeCustomMapper.selectByVideoLikeCustomPrimaryKey(id);
    }

}
