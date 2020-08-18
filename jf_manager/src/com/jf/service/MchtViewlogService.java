package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.MchtViewlogCustomMapper;
import com.jf.dao.MchtViewlogMapper;
import com.jf.entity.MchtViewlog;
import com.jf.entity.MchtViewlogCustom;
import com.jf.entity.MchtViewlogCustomExample;
import com.jf.entity.MchtViewlogExample;

@Service
public class MchtViewlogService extends BaseService<MchtViewlog, MchtViewlogExample> {

	@Autowired
	private MchtViewlogMapper mchtViewlogMapper;
	
	@Autowired
	private MchtViewlogCustomMapper mchtViewlogCustomMapper;
	
	@Autowired
	public void setmchtViewlogMapper(MchtViewlogMapper mchtViewlogMapper) {
		super.setDao(mchtViewlogMapper);
		this.mchtViewlogMapper = mchtViewlogMapper;
	}
	
	public Integer orderViewlogExamplecountByExample(MchtViewlogCustomExample example) {
		return mchtViewlogCustomMapper.mchtViewlogExamplecountByExample(example);
	}

	public List<MchtViewlogCustom> orderViewlogCustomselectByExample(MchtViewlogCustomExample example) {
		return mchtViewlogCustomMapper.mchtViewlogCustomselectByExample(example);
	}
	
	public MchtViewlogCustom orderViewlogCustomselectByPrimaryKey(Integer id) {
		return mchtViewlogCustomMapper.mchtViewlogCustomselectByPrimaryKey(id);
	}
	
}
