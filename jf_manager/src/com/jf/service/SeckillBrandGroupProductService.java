package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SeckillBrandGroupProductCustomMapper;
import com.jf.dao.SeckillBrandGroupProductMapper;
import com.jf.entity.SeckillBrandGroupProduct;
import com.jf.entity.SeckillBrandGroupProductCustom;
import com.jf.entity.SeckillBrandGroupProductCustomExample;
import com.jf.entity.SeckillBrandGroupProductExample;

@Service
@Transactional
public class SeckillBrandGroupProductService extends BaseService<SeckillBrandGroupProduct, SeckillBrandGroupProductExample> {

	@Autowired
	private SeckillBrandGroupProductMapper seckillBrandGroupProductMapper;
	
	@Autowired
	private SeckillBrandGroupProductCustomMapper seckillBrandGroupProductCustomMapper;
	
	@Autowired
	public void setSeckillBrandGroupProductMapper(SeckillBrandGroupProductMapper seckillBrandGroupProductMapper) {
		super.setDao(seckillBrandGroupProductMapper);
		this.seckillBrandGroupProductMapper = seckillBrandGroupProductMapper;
	}
	
	public int countByCustomExample(SeckillBrandGroupProductCustomExample example) {
		return seckillBrandGroupProductCustomMapper.countByCustomExample(example);
	}

    public List<SeckillBrandGroupProductCustom> selectByCustomExample(SeckillBrandGroupProductCustomExample example) {
    	return seckillBrandGroupProductCustomMapper.selectByCustomExample(example);
    }

    public SeckillBrandGroupProductCustom selectByPrimaryCustomKey(Integer id) {
    	return seckillBrandGroupProductCustomMapper.selectByPrimaryCustomKey(id);
    }
	
}
