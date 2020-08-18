package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SpUserCustomMapper;
import com.jf.dao.SpUserMapper;
import com.jf.entity.SpUser;
import com.jf.entity.SpUserCustom;
import com.jf.entity.SpUserCustomExample;
import com.jf.entity.SpUserExample;

@Service
@Transactional
public class SpUserService extends BaseService<SpUser,SpUserExample> {
	@Autowired
	private SpUserMapper dao;
	
	@Autowired
	private SpUserCustomMapper spUserCustomMapper;
	
	
	@Autowired
	public void setSpUserMapper(SpUserMapper spUserMapper) {
		super.setDao(spUserMapper);
		this.dao = spUserMapper;
	}

	public int countByCustomExample(SpUserCustomExample example) {
		return spUserCustomMapper.countByExample(example);
	}
	
	public List<SpUserCustom> selectByCustomExample(SpUserCustomExample example) {
		return spUserCustomMapper.selectByExample(example);
	}

}
