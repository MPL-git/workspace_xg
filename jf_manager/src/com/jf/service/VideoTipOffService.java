package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoTipOffCustomMapper;
import com.jf.dao.VideoTipOffMapper;
import com.jf.entity.VideoTipOff;
import com.jf.entity.VideoTipOffCustom;
import com.jf.entity.VideoTipOffCustomExample;
import com.jf.entity.VideoTipOffExample;

@Service
@Transactional
public class VideoTipOffService extends BaseService<VideoTipOff,VideoTipOffExample> {
	@Autowired
	private VideoTipOffMapper videoTipOffMapper;
	@Autowired 
	private VideoTipOffCustomMapper videoTipOffCustomMapper;
	
	@Autowired
	public void setVideoTipOffMapper(VideoTipOffMapper videoTipOffMapper) {
		super.setDao(videoTipOffMapper);
		this.videoTipOffMapper = videoTipOffMapper;
	}
	
	public int countByVideoTipOffCustomExample(VideoTipOffCustomExample example){
		return videoTipOffCustomMapper.countByVideoTipOffCustomExample(example);
	}
    public List<VideoTipOffCustom> selectByVideoTipOffCustomExample(VideoTipOffCustomExample example){
    	return videoTipOffCustomMapper.selectByVideoTipOffCustomExample(example);
    }
    public VideoTipOffCustom selectByVideoTipOffCustomPrimaryKey(Integer id){
    	return videoTipOffCustomMapper.selectByVideoTipOffCustomPrimaryKey(id);
    }

}
