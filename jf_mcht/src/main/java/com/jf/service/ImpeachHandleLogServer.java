package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ImpeachHandleLogMapper;
import com.jf.entity.ImpeachHandleLog;
import com.jf.entity.ImpeachHandleLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//举报处理日志
@Service
@Transactional
public class ImpeachHandleLogServer extends BaseService<ImpeachHandleLog, ImpeachHandleLogExample> {
	@Autowired
	private ImpeachHandleLogMapper dao;
	
	
	@Autowired
	public void setProvinceFreightMapper(ImpeachHandleLogMapper impeachHandleLogMapper) {
		super.setDao(impeachHandleLogMapper);
		this.dao = impeachHandleLogMapper;
	}
	

}
