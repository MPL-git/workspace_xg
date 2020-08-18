package com.jf.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CommonMapper;

@Service
@Transactional
public class CommonService {
@Resource
private CommonMapper commonMapper;
public Integer getSequence(String sequenceName){
	return commonMapper.getSequence(sequenceName);
}
}
