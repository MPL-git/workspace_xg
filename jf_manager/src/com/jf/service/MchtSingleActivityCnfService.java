package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtSingleActivityCnfCustomMapper;
import com.jf.dao.MchtSingleActivityCnfMapper;
import com.jf.dao.SingleProductActivityCnfMapper;
import com.jf.entity.MchtSingleActivityCnf;
import com.jf.entity.MchtSingleActivityCnfCustom;
import com.jf.entity.MchtSingleActivityCnfCustomExample;
import com.jf.entity.MchtSingleActivityCnfExample;
import com.jf.entity.SingleProductActivityCnf;
import com.jf.entity.SingleProductActivityCnfExample;

/**
 * 
 * @ClassName MchtSingleActivityCnfService
 * @Description TODO(这里用一句话描述这个方法的作用)
 * @author pengl
 * @date 2017年11月28日 下午6:45:43
 */
@Service
@Transactional
public class MchtSingleActivityCnfService extends BaseService<MchtSingleActivityCnf, MchtSingleActivityCnfExample> {

	@Autowired
	private MchtSingleActivityCnfMapper mchtSingleActivityCnfMapper;

	@Autowired
	private MchtSingleActivityCnfCustomMapper mchtSingleActivityCnfCustomMapper;
	
	@Autowired
	private SingleProductActivityCnfMapper singleProductActivityCnfMapper;
	
	@Autowired
	public void setMchtSingleActivityCnfMapper(MchtSingleActivityCnfMapper mchtSingleActivityCnfMapper) {
		super.setDao(mchtSingleActivityCnfMapper);
		this.mchtSingleActivityCnfMapper = mchtSingleActivityCnfMapper;
	}
	
	public List<MchtSingleActivityCnfCustom> selectByCustomExample(MchtSingleActivityCnfCustomExample example) {
		return mchtSingleActivityCnfCustomMapper.selectByCustomExample(example);
	}
	
	public Integer countByCustomExample(MchtSingleActivityCnfCustomExample example) {
		return mchtSingleActivityCnfCustomMapper.countByCustomExample(example);
	}
	
	public List<SingleProductActivityCnf> selectByExample(SingleProductActivityCnfExample example) {
		return singleProductActivityCnfMapper.selectByExample(example);
	}
	
}
