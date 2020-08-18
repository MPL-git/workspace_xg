package com.jf.service;

import com.jf.dao.VideoProductCustomMapper;
import com.jf.dao.VideoProductMapper;
import com.jf.entity.VideoProduct;
import com.jf.entity.VideoProductCustom;
import com.jf.entity.VideoProductCustomExample;
import com.jf.entity.VideoProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VideoProductService extends BaseService<VideoProduct, VideoProductExample> {
	@Autowired
	private VideoProductMapper videoProductMapper;
	@Autowired
	private VideoProductCustomMapper videoProductCustomMapper;
	
	@Autowired
	public void setVideoProductMapper(VideoProductMapper videoProductMapper) {
		super.setDao(videoProductMapper);
		this.videoProductMapper = videoProductMapper;
	}
	
	public int countByVideoProductCustomExample(VideoProductCustomExample example){
		return videoProductCustomMapper.countByVideoProductCustomExample(example);
	}
    public List<VideoProductCustom> selectByVideoProductCustomExample(VideoProductCustomExample example){
    	return videoProductCustomMapper.selectByVideoProductCustomExample(example);
    }
    public VideoProductCustom selectByVideoProductCustomPrimaryKey(Integer id){
    	return videoProductCustomMapper.selectByVideoProductCustomPrimaryKey(id);
    }

    public List<Map<String, Object>> selectCurrentTimeStatistical(Map<String, Object> paramMap) {
		return videoProductCustomMapper.selectCurrentTimeStatistical(paramMap);
	}
    public List<Map<String, Object>> selectHistoryStatistical(Map<String, Object> paramMap) {
		return videoProductCustomMapper.selectHistoryStatistical(paramMap);
	}

}
