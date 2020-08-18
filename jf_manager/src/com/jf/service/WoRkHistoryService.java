package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.WoRkHistoryCustomMapper;
import com.jf.dao.WorkHistoryMapper;
import com.jf.entity.WoRkHistoryCustom;
import com.jf.entity.WoRkHistoryCustomExample;
import com.jf.entity.WorkHistory;
import com.jf.entity.WorkHistoryExample;

@Service
public class WoRkHistoryService extends BaseService<WorkHistory, WorkHistoryExample> {

	@Autowired
	private WorkHistoryMapper workHistoryMapper;
	
	@Autowired
	private WoRkHistoryCustomMapper woRkHistoryCustomMapper;
	
	@Autowired
	public void setworkHistoryMapper(WorkHistoryMapper workHistoryMapper) {
		super.setDao(workHistoryMapper);
		this.workHistoryMapper = workHistoryMapper;
	}
	
	public Integer countByCustomExample(WoRkHistoryCustomExample example) {
		return woRkHistoryCustomMapper.countByCustomExample(example);
	}

	public List<WoRkHistoryCustom> selectByCustomExample(WoRkHistoryCustomExample example) {
		return woRkHistoryCustomMapper.selectByCustomExample(example);
	}
	
	public WoRkHistoryCustom selectByPrimaryKeyCustom(Integer staffRoleId) {
		return woRkHistoryCustomMapper.selectByPrimaryKeyCustom(staffRoleId);
	}
	
}
