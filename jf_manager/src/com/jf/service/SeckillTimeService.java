package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SeckillTimeMapper;
import com.jf.entity.SeckillTime;
import com.jf.entity.SeckillTimeExample;

/**
 * 
 * @ClassName SeckillTimeService
 * @Description TODO(限时秒杀时间)
 * @author pengl
 * @date 2017年9月29日 上午10:56:50
 */
@Service
@Transactional
public class SeckillTimeService extends BaseService<SeckillTime, SeckillTimeExample> {

	@Autowired
	private SeckillTimeMapper seckillTimeMapper;
	
	@Autowired
	public void setSeckillTimeMapper(SeckillTimeMapper seckillTimeMapper) {
		super.setDao(seckillTimeMapper);
		this.seckillTimeMapper = seckillTimeMapper;
	}
	
}
