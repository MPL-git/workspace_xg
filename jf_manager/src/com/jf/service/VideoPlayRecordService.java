package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoPlayRecordCustomMapper;
import com.jf.dao.VideoPlayRecordMapper;
import com.jf.entity.VideoPlayRecord;
import com.jf.entity.VideoPlayRecordCustom;
import com.jf.entity.VideoPlayRecordCustomExample;
import com.jf.entity.VideoPlayRecordExample;

@Service
@Transactional
public class VideoPlayRecordService extends BaseService<VideoPlayRecord,VideoPlayRecordExample> {
	@Autowired
	private VideoPlayRecordMapper videoPlayRecordMapper;
	@Autowired 
	private VideoPlayRecordCustomMapper videoPlayRecordCustomMapper;
	
	@Autowired
	public void setVideoPlayRecordMapper(VideoPlayRecordMapper videoPlayRecordMapper) {
		super.setDao(videoPlayRecordMapper);
		this.videoPlayRecordMapper = videoPlayRecordMapper;
	}
	
	public int countByVideoPlayRecordCustomExample(VideoPlayRecordCustomExample example){
		return videoPlayRecordCustomMapper.countByVideoPlayRecordCustomExample(example);
	}
    public List<VideoPlayRecordCustom> selectByVideoPlayRecordCustomExample(VideoPlayRecordCustomExample example){
    	return videoPlayRecordCustomMapper.selectByVideoPlayRecordCustomExample(example);
    }
    public VideoPlayRecordCustom selectByVideoPlayRecordCustomPrimaryKey(Integer id){
    	return videoPlayRecordCustomMapper.selectByVideoPlayRecordCustomPrimaryKey(id);
    }

}
