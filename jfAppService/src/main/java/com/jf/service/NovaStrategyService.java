package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.NovaStrategyMapper;
import com.jf.entity.NovaStrategy;
import com.jf.entity.NovaStrategyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NovaStrategyService extends BaseService<NovaStrategy, NovaStrategyExample> {
	@Autowired
	private NovaStrategyMapper novaStrategyMapper;
	@Autowired
	public void setNovaStrategyMapper(NovaStrategyMapper novaStrategyMapper) {
		this.setDao(novaStrategyMapper);
		this.novaStrategyMapper = novaStrategyMapper;
	}
	
	
}
