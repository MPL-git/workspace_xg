package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ServiceLogPicMapper;
import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicExample;

@Service
@Transactional
public class ServiceLogPicService extends BaseService<ServiceLogPic,ServiceLogPicExample> {
	@Autowired
	private ServiceLogPicMapper serviceLogPicMapper;
	@Autowired
	public void setServiceLogPicMapper(ServiceLogPicMapper serviceLogPicMapper) {
		super.setDao(serviceLogPicMapper);
		this.serviceLogPicMapper = serviceLogPicMapper;
	}
}
