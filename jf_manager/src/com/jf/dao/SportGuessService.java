package com.jf.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.entity.SportGuess;
import com.jf.entity.SportGuessCustom;
import com.jf.entity.SportGuessCustomExample;
import com.jf.entity.SportGuessExample;
import com.jf.service.BaseService;

@Service
@Transactional
public class SportGuessService extends BaseService<SportGuess, SportGuessExample> {

	@Autowired
	private SportGuessMapper sportGuessMapper;
	
	@Autowired
	private SportGuessCustomMapper sportGuessCustomMapper;
	
	@Autowired
	public void setSportGuessMapper(SportGuessMapper sportGuessMapper) {
		super.setDao(sportGuessMapper);
		this.sportGuessMapper = sportGuessMapper;
	}
	
	public int countByCustomExample(SportGuessCustomExample example) {
		return sportGuessCustomMapper.countByCustomExample(example);
	}

	public List<SportGuessCustom> selectByCustomExample(SportGuessCustomExample example) {
		return sportGuessCustomMapper.selectByCustomExample(example);
	}

	public SportGuessCustom selectByCustomPrimaryKey(Integer id) {
		return sportGuessCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(SportGuess record, SportGuessCustomExample example) {
		return sportGuessCustomMapper.updateByCustomExampleSelective(record, example);
	}

	
}
