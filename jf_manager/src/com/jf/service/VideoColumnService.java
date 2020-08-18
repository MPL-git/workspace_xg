package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.VideoColumnCustomMapper;
import com.jf.dao.VideoColumnMapper;
import com.jf.entity.VideoColumn;
import com.jf.entity.VideoColumnCustom;
import com.jf.entity.VideoColumnCustomExample;
import com.jf.entity.VideoColumnExample;

@Service
@Transactional
public class VideoColumnService extends BaseService<VideoColumn,VideoColumnExample> {
	@Autowired
	private VideoColumnMapper videoColumnMapper;
	@Autowired
	private VideoColumnCustomMapper videoColumnCustomMapper;
	
	@Autowired
	public void setVideoColumnMapper(VideoColumnMapper videoColumnMapper) {
		super.setDao(videoColumnMapper);
		this.videoColumnMapper = videoColumnMapper;
	}
	
	public int countByVideoColumnCustomExample(VideoColumnCustomExample example){
		return videoColumnCustomMapper.countByVideoColumnCustomExample(example);
	}
    public List<VideoColumnCustom> selectByVideoColumnCustomExample(VideoColumnCustomExample example){
    	return videoColumnCustomMapper.selectByVideoColumnCustomExample(example);
    }
    public VideoColumnCustom selectByVideoColumnCustomPrimaryKey(Integer id){
    	return videoColumnCustomMapper.selectByVideoColumnCustomPrimaryKey(id);
    }

}
