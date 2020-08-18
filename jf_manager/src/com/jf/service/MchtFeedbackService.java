package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MchtFeedbackCustomMapper;
import com.jf.dao.MchtFeedbackMapper;
import com.jf.entity.MchtFeedback;
import com.jf.entity.MchtFeedbackCustom;
import com.jf.entity.MchtFeedbackExample;

@Service
@Transactional
public class MchtFeedbackService extends BaseService<MchtFeedback,MchtFeedbackExample> {
	@Autowired
	private MchtFeedbackMapper mchtFeedbackMapper;
	@Autowired
	private MchtFeedbackCustomMapper mchtFeedbackCustomMapper;	
	@Autowired
	public void setMchtFeedbackMapper(MchtFeedbackMapper mchtFeedbackMapper) {
		super.setDao(mchtFeedbackMapper);
		this.mchtFeedbackMapper = mchtFeedbackMapper;
	}
	
	public int countMchtFeedbackCustomByExample(MchtFeedbackExample example){
		return mchtFeedbackCustomMapper.countByExample(example);
	}
    public List<MchtFeedbackCustom> selectmchtFeedbackCustomByExample(MchtFeedbackExample example){
    	return mchtFeedbackCustomMapper.selectByExample(example);
    }
    public MchtFeedbackCustom selectmchtFeedbackCustomByPrimaryKey(Integer id){
    	return mchtFeedbackCustomMapper.selectByPrimaryKey(id);
    }
    public List<Map<String, Object>> getMchtfeedbackList() {
		return mchtFeedbackCustomMapper.getMchtfeedbackList();
	}
}
