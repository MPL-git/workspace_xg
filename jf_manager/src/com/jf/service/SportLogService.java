package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SportLogCustomMapper;
import com.jf.dao.SportLogMapper;
import com.jf.entity.SportLog;
import com.jf.entity.SportLogCustom;
import com.jf.entity.SportLogCustomExample;
import com.jf.entity.SportLogExample;

@Service
@Transactional
public class SportLogService extends BaseService<SportLog, SportLogExample> {

	@Autowired
	private SportLogMapper sportLogMapper;
	
	@Autowired
	private SportLogCustomMapper sportLogCustomMapper;
	
	@Autowired
	public void setSportLogMapper(SportLogMapper sportLogMapper) {
		super.setDao(sportLogMapper);
		this.sportLogMapper = sportLogMapper;
	}
	
	public int countByCustomExample(SportLogCustomExample example) {
		return sportLogCustomMapper.countByCustomExample(example);
	}

    public List<SportLogCustom> selectByCustomExample(SportLogCustomExample example) {
    	return sportLogCustomMapper.selectByCustomExample(example);
    }

    public SportLogCustom selectByCustomPrimaryKey(Integer id) {
    	return sportLogCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(SportLog record, SportLogCustomExample example) {
    	return sportLogCustomMapper.updateByCustomExampleSelective(record, example);
    }
	
	
}
