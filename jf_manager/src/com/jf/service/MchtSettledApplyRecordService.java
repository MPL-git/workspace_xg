package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtSettledApplyRecordCustomMapper;
import com.jf.dao.MchtSettledApplyRecordMapper;
import com.jf.entity.MchtSettledApplyRecord;
import com.jf.entity.MchtSettledApplyRecordCustom;
import com.jf.entity.MchtSettledApplyRecordExample;

@Service
@Transactional
public class MchtSettledApplyRecordService extends BaseService<MchtSettledApplyRecord,MchtSettledApplyRecordExample> {
	@Autowired
	private MchtSettledApplyRecordMapper mchtSettledApplyRecordMapper;
	
	@Autowired
	private MchtSettledApplyRecordCustomMapper mchtSettledApplyRecordcustomMapper;
	
	@Autowired
	public void setMchtSettledApplyRecordMapper(MchtSettledApplyRecordMapper mchtSettledApplyRecordMapper) {
		super.setDao(mchtSettledApplyRecordMapper);
		this.mchtSettledApplyRecordMapper = mchtSettledApplyRecordMapper;
	}
	
	public int countMchtSettledApplyRecordCustomExample(MchtSettledApplyRecordExample example){
		return mchtSettledApplyRecordcustomMapper.countByExample(example);
	}
    public List<MchtSettledApplyRecordCustom> selectMchtSettledApplyRecordCustomExample(MchtSettledApplyRecordExample example){
    	return mchtSettledApplyRecordcustomMapper.selectByExample(example);
    }
    public MchtSettledApplyRecordCustom selectMchtSettledApplyRecordCustomPrimaryKey(Integer id){
    	return mchtSettledApplyRecordcustomMapper.selectByPrimaryKey(id);
    }
}
