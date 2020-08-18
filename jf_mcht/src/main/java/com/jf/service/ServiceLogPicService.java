package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ServiceLogPicCustomMapper;
import com.jf.dao.ServiceLogPicMapper;
import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicCustom;
import com.jf.entity.ServiceLogPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceLogPicService extends BaseService<ServiceLogPic,ServiceLogPicExample> {
	@Autowired
	private ServiceLogPicMapper dao;
	
	@Autowired
	private ServiceLogPicCustomMapper serviceLogPicCustomMapper;
	
	@Autowired
	public void setServiceLogPicMapper(ServiceLogPicMapper serviceLogPicCustomMapper) {
		super.setDao(serviceLogPicCustomMapper);
		this.dao = serviceLogPicCustomMapper;
	}
	
	public List<ServiceLogPicCustom> getServiceLogPicsByServiceLogId(Integer serviceLogId){
		return serviceLogPicCustomMapper.getServiceLogPicsByServiceLogId(serviceLogId);
	}
}
