package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoCustomMapper;
import com.jf.dao.VideoMapper;
import com.jf.entity.Video;
import com.jf.entity.VideoCustom;
import com.jf.entity.VideoCustomExample;
import com.jf.entity.VideoExample;

@Service
@Transactional
public class VideoService extends BaseService<Video,VideoExample> {
	@Autowired
	private VideoMapper videoMapper;
	@Autowired 
	private VideoCustomMapper videoCustomMapper;
	
	@Autowired
	public void setVideoMapper(VideoMapper videoMapper) {
		super.setDao(videoMapper);
		this.videoMapper = videoMapper;
	}
	
	public int countByVideoCustomExample(VideoCustomExample example){
		return videoCustomMapper.countByVideoCustomExample(example);
	}
    public List<VideoCustom> selectByVideoCustomExample(VideoCustomExample example){
    	return videoCustomMapper.selectByVideoCustomExample(example);
    }
    public VideoCustom selectByVideoCustomPrimaryKey(Integer id){
    	return videoCustomMapper.selectByVideoCustomPrimaryKey(id);
    }

}
