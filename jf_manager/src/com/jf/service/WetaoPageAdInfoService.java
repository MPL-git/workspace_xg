package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WetaoPageAdInfoCustomMapper;
import com.jf.dao.WetaoPageAdInfoMapper;
import com.jf.entity.WetaoPageAdInfo;
import com.jf.entity.WetaoPageAdInfoCustom;
import com.jf.entity.WetaoPageAdInfoExample;

@Service
@Transactional
public class WetaoPageAdInfoService extends BaseService<WetaoPageAdInfo,WetaoPageAdInfoExample> {
	@Autowired
	private WetaoPageAdInfoMapper wetaoPageAdInfoMapper;
	@Autowired
	private WetaoPageAdInfoCustomMapper wetaoPageAdInfoCustomMapper;
	@Autowired
	public void setWetaoPageAdInfoMapper(WetaoPageAdInfoMapper wetaoPageAdInfoMapper) {
		super.setDao(wetaoPageAdInfoMapper);
		this.wetaoPageAdInfoMapper = wetaoPageAdInfoMapper;
	}
	
	public int countCustomByExample(WetaoPageAdInfoExample example){
		return wetaoPageAdInfoCustomMapper.countByExample(example);
	}
    public List<WetaoPageAdInfoCustom> selectCustomByExample(WetaoPageAdInfoExample example){
    	return wetaoPageAdInfoCustomMapper.selectByExample(example);
    }
}
