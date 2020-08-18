package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jf.dao.WetaoChannelProductMapper;
import com.jf.entity.WetaoChannelProduct;
import com.jf.entity.WetaoChannelProductExample;


@Service
@Transactional
public class WeitaoChannelProductService extends BaseService<WetaoChannelProduct, WetaoChannelProductExample>{
	
	@Autowired
	private WetaoChannelProductMapper wetaoChannelProductMapper;
	
	@Autowired
	public void setWetaoChannelProductMapper(WetaoChannelProductMapper wetaoChannelProductMapper) {
		super.setDao(wetaoChannelProductMapper);
		this.wetaoChannelProductMapper = wetaoChannelProductMapper;
	}

}
