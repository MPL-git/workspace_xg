package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DouyinSprLogCustomMapper;
import com.jf.dao.DouyinSprLogMapper;
import com.jf.entity.DouyinSprLog;
import com.jf.entity.DouyinSprLogCustom;
import com.jf.entity.DouyinSprLogCustomExample;
import com.jf.entity.DouyinSprLogExample;

@Service
@Transactional
public class DouyinSprLogService extends BaseService<DouyinSprLog, DouyinSprLogExample> {

	@Autowired
	private DouyinSprLogMapper douyinSprLogMapper;
	
	@Autowired
	private DouyinSprLogCustomMapper douyinSprLogCustomMapper;
	
	@Autowired
	public void setDouyinSprLogMapper(DouyinSprLogMapper douyinSprLogMapper) {
		super.setDao(douyinSprLogMapper);
		this.douyinSprLogMapper = douyinSprLogMapper;
	}
	
	public int countByCustomExample(DouyinSprLogCustomExample example) {
		return douyinSprLogCustomMapper.countByCustomExample(example);
	}

	public List<DouyinSprLogCustom> selectByCustomExample(DouyinSprLogCustomExample example) {
    	return douyinSprLogCustomMapper.selectByCustomExample(example);
    }

	public DouyinSprLogCustom selectByCustomPrimaryKey(Integer id) {
    	return douyinSprLogCustomMapper.selectByCustomPrimaryKey(id);
    }

	public int updateByCustomExampleSelective(@Param("record") DouyinSprLog record, @Param("example") DouyinSprLogCustomExample example) {
    	return douyinSprLogCustomMapper.updateByCustomExampleSelective(record, example);
    }

	
}
