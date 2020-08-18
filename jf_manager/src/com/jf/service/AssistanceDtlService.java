package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AssistanceDtlCustomMapper;
import com.jf.dao.AssistanceDtlMapper;
import com.jf.entity.AssistanceDtl;
import com.jf.entity.AssistanceDtlCustom;
import com.jf.entity.AssistanceDtlExample;

@Service
@Transactional
public class AssistanceDtlService extends BaseService<AssistanceDtl,AssistanceDtlExample> {
	@Autowired
	private AssistanceDtlMapper assistanceDtlMapper;
	@Autowired
	private AssistanceDtlCustomMapper assistanceDtlCustomMapper;
	@Autowired
	public void setAssistanceDtlMapper(AssistanceDtlMapper assistanceDtlMapper) {
		super.setDao(assistanceDtlMapper);
		this.assistanceDtlMapper = assistanceDtlMapper;
	}
	
	public int countAssistanceDtlCustomByExample(AssistanceDtlExample example){
		return assistanceDtlCustomMapper.countByExample(example);
	}
    public List<AssistanceDtlCustom> selectAssistanceDtlCustomByExample(AssistanceDtlExample example){
    	return assistanceDtlCustomMapper.selectByExample(example);
    }
}
