package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SportGuessMapper;
import com.jf.entity.SportGuess;
import com.jf.entity.SportGuessExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月21日 下午3:16:27 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SportGuessService extends BaseService<SportGuess, SportGuessExample> {
	@Autowired
	private SportGuessMapper sportGuessMapper;

	@Autowired
	public void setSportGuessMapper(SportGuessMapper sportGuessMapper) {
		this.setDao(sportGuessMapper);
		this.sportGuessMapper = sportGuessMapper;
	}
	
	
}
