package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ServiceLogPicCustom;
@Repository
public interface ServiceLogPicCustomMapper{

	List<ServiceLogPicCustom> getServiceLogPicsByServiceLogId(Integer serviceLogId);
	
}