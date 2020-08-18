package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SportTeamLogCustomMapper;
import com.jf.dao.SportTeamLogMapper;
import com.jf.entity.SportTeamLog;
import com.jf.entity.SportTeamLogCustom;
import com.jf.entity.SportTeamLogCustomExample;
import com.jf.entity.SportTeamLogExample;

@Service
@Transactional
public class SportTeamLogService extends BaseService<SportTeamLog, SportTeamLogExample> {

	@Autowired
	private SportTeamLogMapper sportTeamLogMapper;
	
	@Autowired
	private SportTeamLogCustomMapper sportTeamLogCustomMapper;
	
	@Autowired
	public void setSportTeamLogMapper(SportTeamLogMapper sportTeamLogMapper) {
		super.setDao(sportTeamLogMapper);
		this.sportTeamLogMapper = sportTeamLogMapper;
	}
	
	public int countByCustomExample(SportTeamLogCustomExample example) {
		return sportTeamLogCustomMapper.countByCustomExample(example);
	}

    public List<SportTeamLogCustom> selectByCustomExample(SportTeamLogCustomExample example) {
    	return sportTeamLogCustomMapper.selectByCustomExample(example);
    }

    public SportTeamLogCustom selectByCustomPrimaryKey(Integer id) {
    	return sportTeamLogCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(SportTeamLog record, SportTeamLogCustomExample example) {
    	return sportTeamLogCustomMapper.updateByCustomExampleSelective(record, example);
    }
	
	
}
