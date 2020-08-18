package com.jf.service;

import com.jf.dao.VideoAuthorizedAccessoriesMapper;
import com.jf.entity.VideoAuthorizedAccessories;
import com.jf.entity.VideoAuthorizedAccessoriesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VideoAuthorizedAccessoriesService extends BaseService<VideoAuthorizedAccessories,VideoAuthorizedAccessoriesExample> {

	@Autowired 
	private VideoAuthorizedAccessoriesMapper videoAuthorizedAccessoriesMapper;
	
	@Autowired
	public void setVideoAttentionMapper(VideoAuthorizedAccessoriesMapper videoAuthorizedAccessoriesMapper) {
		super.setDao(videoAuthorizedAccessoriesMapper);
		this.videoAuthorizedAccessoriesMapper = videoAuthorizedAccessoriesMapper;
	}

}
