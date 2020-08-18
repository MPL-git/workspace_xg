package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoExtendCustomMapper;
import com.jf.dao.VideoExtendMapper;
import com.jf.entity.VideoExtend;
import com.jf.entity.VideoExtendCustom;
import com.jf.entity.VideoExtendCustomExample;
import com.jf.entity.VideoExtendExample;

@Service
@Transactional
public class VideoExtendService extends BaseService<VideoExtend,VideoExtendExample> {
	@Autowired
	private VideoExtendMapper videoExtendMapper;
	@Autowired 
	private VideoExtendCustomMapper videoExtendCustomMapper;
	
	@Autowired
	public void setVideoExtendMapper(VideoExtendMapper videoExtendMapper) {
		super.setDao(videoExtendMapper);
		this.videoExtendMapper = videoExtendMapper;
	}
	
	public int countByVideoExtendCustomExample(VideoExtendCustomExample example){
		return videoExtendCustomMapper.countByVideoExtendCustomExample(example);
	}
    public List<VideoExtendCustom> selectByVideoExtendCustomExample(VideoExtendCustomExample example){
    	return videoExtendCustomMapper.selectByVideoExtendCustomExample(example);
    }
    public VideoExtendCustom selectByVideoExtendCustomPrimaryKey(Integer id){
    	return videoExtendCustomMapper.selectByVideoExtendCustomPrimaryKey(id);
    }

}
