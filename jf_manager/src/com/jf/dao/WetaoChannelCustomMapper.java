package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.WetaoChannelCustom;
import com.jf.entity.WetaoChannelCustomExample;
import com.jf.entity.WetaoChannelExample;

@Repository
public interface WetaoChannelCustomMapper {
	public List<WetaoChannelCustom> selectWetaoChannel(WetaoChannelExample wetaoChannelExample);
}
