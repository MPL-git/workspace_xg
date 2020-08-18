package com.jf.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PlatformMsgEditCustomMapper;
import com.jf.dao.PlatformMsgEditMapper;
import com.jf.entity.PlatformMsgEdit;
import com.jf.entity.PlatformMsgEditCustom;
import com.jf.entity.PlatformMsgEditExample;

@Service
@Transactional
public class PlatformMsgEditService extends BaseService<PlatformMsgEdit, PlatformMsgEditExample> {
	@Autowired
	private PlatformMsgEditMapper platformMsgEditMapper;
	
	@Resource
	private PlatformMsgEditCustomMapper platformMsgEditCustomMapper; 

	@Autowired
	public void setPlatformMsgEditMapper(PlatformMsgEditMapper platformMsgEditMapper) {
		super.setDao(platformMsgEditMapper);
		this.platformMsgEditMapper = platformMsgEditMapper;
	}

	public int countPlatformMsgEditMapperCustomByExample(PlatformMsgEditExample example){
		return platformMsgEditCustomMapper.countByExample(example);
	}

	public List<PlatformMsgEditCustom> selectPlatformMsgEditMapperCustomByExample(PlatformMsgEditExample example){
    	return platformMsgEditCustomMapper.selectByExample(example);
    }
	public PlatformMsgEditCustom selectPlatformMsgCustomByPrimaryKey(Integer id){
		return platformMsgEditCustomMapper.selectByPrimaryKey(id);
	}
}
