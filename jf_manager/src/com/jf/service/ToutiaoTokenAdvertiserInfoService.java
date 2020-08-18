package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ToutiaoTokenAdvertiserInfoCustomMapper;
import com.jf.dao.ToutiaoTokenAdvertiserInfoMapper;
import com.jf.entity.ToutiaoTokenAdvertiserInfo;
import com.jf.entity.ToutiaoTokenAdvertiserInfoCustom;
import com.jf.entity.ToutiaoTokenAdvertiserInfoCustomExample;
import com.jf.entity.ToutiaoTokenAdvertiserInfoExample;

@Service
@Transactional
public class ToutiaoTokenAdvertiserInfoService extends BaseService<ToutiaoTokenAdvertiserInfo, ToutiaoTokenAdvertiserInfoExample> {

	@Autowired
	private ToutiaoTokenAdvertiserInfoMapper toutiaoTokenAdvertiserInfoMapper;
	
	@Autowired
	private ToutiaoTokenAdvertiserInfoCustomMapper toutiaoTokenAdvertiserInfoCustomMapper;
	
	@Autowired
	public void setToutiaoTokenAdvertiserInfoMapper(ToutiaoTokenAdvertiserInfoMapper toutiaoTokenAdvertiserInfoMapper) {
		super.setDao(toutiaoTokenAdvertiserInfoMapper);
		this.toutiaoTokenAdvertiserInfoMapper = toutiaoTokenAdvertiserInfoMapper;
	}

	public int countByCustomExample(ToutiaoTokenAdvertiserInfoCustomExample example) {
		return toutiaoTokenAdvertiserInfoCustomMapper.countByCustomExample(example);
	}

	public List<ToutiaoTokenAdvertiserInfoCustom> selectByCustomExample(ToutiaoTokenAdvertiserInfoCustomExample example) {
		return toutiaoTokenAdvertiserInfoCustomMapper.selectByCustomExample(example);
	}

	public ToutiaoTokenAdvertiserInfoCustom selectByCustomPrimaryKey(Integer id) {
		return toutiaoTokenAdvertiserInfoCustomMapper.selectByCustomPrimaryKey(id);
	}

    public int updateByCustomExampleSelective(@Param("record") ToutiaoTokenAdvertiserInfo record, @Param("example") ToutiaoTokenAdvertiserInfoCustomExample example) {
    	return toutiaoTokenAdvertiserInfoCustomMapper.updateByCustomExampleSelective(record, example);
    }
	
	public ToutiaoTokenAdvertiserInfoCustom selectToutiaoTokenAdvertiserInfo(String advertiserId) {
		return toutiaoTokenAdvertiserInfoCustomMapper.selectToutiaoTokenAdvertiserInfo(advertiserId);
	}
	
}
