package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.RemarksLogCustomMapper;
import com.jf.dao.RemarksLogMapper;
import com.jf.entity.RemarksLog;
import com.jf.entity.RemarksLogCustom;
import com.jf.entity.RemarksLogExample;

@Service
@Transactional
public class RemarksLogService extends BaseService<RemarksLog,RemarksLogExample> {
	@Autowired
	private RemarksLogMapper remarksLogMapper;
	@Autowired
	private RemarksLogCustomMapper remarksLogCustomMapper;
	@Autowired
	public void setRemarksLogMapper(RemarksLogMapper remarksLogMapper) {
		super.setDao(remarksLogMapper);
		this.remarksLogMapper = remarksLogMapper;
	}
	
	public int countRemarksLogCustomByExample(RemarksLogExample example){
		return remarksLogCustomMapper.countByExample(example);
	}
    public List<RemarksLogCustom> selectRemarksLogCustomByExample(RemarksLogExample example){
    	return remarksLogCustomMapper.selectByExample(example);
    }
    public RemarksLogCustom selectRemarksLogCustomByPrimaryKey(Integer id){
    	return remarksLogCustomMapper.selectByPrimaryKey(id);
    }
}
