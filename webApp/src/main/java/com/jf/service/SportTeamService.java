package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SportTeamMapper;
import com.jf.entity.SportTeam;
import com.jf.entity.SportTeamExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月21日 下午3:16:27 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SportTeamService extends BaseService<SportTeam, SportTeamExample> {
	@Autowired
	private SportTeamMapper sportTeamMapper;

	@Autowired
	public void setSportTeamMapper(SportTeamMapper sportTeamMapper) {
		this.setDao(sportTeamMapper);
		this.sportTeamMapper = sportTeamMapper;
	}
	
	
}
