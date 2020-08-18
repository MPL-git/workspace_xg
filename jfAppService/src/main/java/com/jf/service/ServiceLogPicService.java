package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ServiceLogPicMapper;
import com.jf.entity.ServiceLogPic;
import com.jf.entity.ServiceLogPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月18日 下午5:41:51 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class ServiceLogPicService extends BaseService<ServiceLogPic, ServiceLogPicExample> {
	
	@Autowired
	private ServiceLogPicMapper serviceLogPicMapper;

	@Autowired
	public void setServiceLogPicMapper(ServiceLogPicMapper serviceLogPicMapper) {
		this.setDao(serviceLogPicMapper);
		this.serviceLogPicMapper = serviceLogPicMapper;
	}

	public List<ServiceLogPic> findListByServiceLogId(Integer serviceLogId) {
		ServiceLogPicExample serviceLogPicExample = new ServiceLogPicExample();
		serviceLogPicExample.createCriteria().andServiceLogIdEqualTo(serviceLogId).andDelFlagEqualTo("0");
		return serviceLogPicMapper.selectByExample(serviceLogPicExample);
	}

	public ServiceLogPic add(Integer id, String str, Integer memberId, Date date, String remarks) {
		ServiceLogPic serviceLogPic = new ServiceLogPic();
		serviceLogPic.setServiceLogId(id);
		serviceLogPic.setPic(str);
		serviceLogPic.setCreateBy(memberId);
		serviceLogPic.setCreateDate(date);
		serviceLogPic.setRemarks(remarks);
		serviceLogPic.setDelFlag("0");
		
		return saveModel(serviceLogPic);
	}

	public ServiceLogPic saveModel(ServiceLogPic serviceLogPic) {
		serviceLogPicMapper.insertSelective(serviceLogPic);
		return serviceLogPic;
	}
	
}
