package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AreaMapper;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午6:32:37 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class AreaService extends BaseService<Area, AreaExample> {
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Autowired
	public void setAreaMapper(AreaMapper areaMapper) {
		super.setDao(areaMapper);
		this.areaMapper = areaMapper;
	}
}
