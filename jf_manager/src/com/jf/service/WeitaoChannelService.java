package com.jf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;





import com.jf.dao.WetaoChannelCustomMapper;
import com.jf.dao.WetaoChannelMapper;
import com.jf.entity.WetaoChannel;
import com.jf.entity.WetaoChannelCustom;
import com.jf.entity.WetaoChannelExample;

@Service
@Transactional
public class WeitaoChannelService extends BaseService<WetaoChannel,WetaoChannelExample> {

	@Autowired
	private WetaoChannelMapper wetaoChannelMapper;
	
	@Autowired
	private WetaoChannelCustomMapper wetaoChannelCustomMapper;
	
	@Autowired
	public void setWetaoChannelMapper(WetaoChannelMapper wetaoChannelMapper) {
		super.setDao(wetaoChannelMapper);
		this.wetaoChannelMapper = wetaoChannelMapper;
	};
	
	public List<WetaoChannelCustom> selectWetaoChannel(WetaoChannelExample wetaoChannelExample){
		return wetaoChannelCustomMapper.selectWetaoChannel(wetaoChannelExample);
	}
	
}
