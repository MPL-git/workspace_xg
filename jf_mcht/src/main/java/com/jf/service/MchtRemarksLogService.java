package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtRemarksLogCustomMapper;
import com.jf.dao.MchtRemarksLogMapper;
import com.jf.entity.MchtRemarksLog;
import com.jf.entity.MchtRemarksLogCustom;
import com.jf.entity.MchtRemarksLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtRemarksLogService extends BaseService<MchtRemarksLog,MchtRemarksLogExample> {
	@Autowired
	private MchtRemarksLogMapper mchtRemarksLogMapper;
	
	@Autowired
	private MchtRemarksLogCustomMapper mchtRemarksLogCustomMapper;
	
	
	@Autowired
	public void setMchtRemarksLogMapper(MchtRemarksLogMapper mchtRemarksLogMapper) {
		super.setDao(mchtRemarksLogMapper);
		this.mchtRemarksLogMapper = mchtRemarksLogMapper;
	}
	
	public List<MchtRemarksLogCustom> selectByCustomExample(MchtRemarksLogExample example){
		return mchtRemarksLogCustomMapper.selectByExample(example);
	}
}
