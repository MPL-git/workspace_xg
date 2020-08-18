package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoAttentionCustomMapper;
import com.jf.dao.VideoAttentionMapper;
import com.jf.entity.VideoAttention;
import com.jf.entity.VideoAttentionCustom;
import com.jf.entity.VideoAttentionCustomExample;
import com.jf.entity.VideoAttentionExample;

@Service
@Transactional
public class VideoAttentionService extends BaseService<VideoAttention,VideoAttentionExample> {
	@Autowired
	private VideoAttentionMapper videoAttentionMapper;
	@Autowired 
	private VideoAttentionCustomMapper videoAttentionCustomMapper;
	
	@Autowired
	public void setVideoAttentionMapper(VideoAttentionMapper videoAttentionMapper) {
		super.setDao(videoAttentionMapper);
		this.videoAttentionMapper = videoAttentionMapper;
	}
	
	public int countByVideoAttentionCustomExample(VideoAttentionCustomExample example){
		return videoAttentionCustomMapper.countByVideoAttentionCustomExample(example);
	}
    public List<VideoAttentionCustom> selectByVideoAttentionCustomExample(VideoAttentionCustomExample example){
    	return videoAttentionCustomMapper.selectByVideoAttentionCustomExample(example);
    }
    public VideoAttentionCustom selectByVideoAttentionCustomPrimaryKey(Integer id){
    	return videoAttentionCustomMapper.selectByVideoAttentionCustomPrimaryKey(id);
    }

}
