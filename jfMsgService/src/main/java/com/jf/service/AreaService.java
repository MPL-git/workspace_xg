package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.AreaMapper;
import com.jf.entity.Area;
import com.jf.entity.AreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AreaService extends BaseService<Area, AreaExample> {

	@Autowired
	private AreaMapper areaMapper;

	@Autowired
	public void setActivityAreaMapper(AreaMapper areaMapper) {
		super.setDao(areaMapper);
		this.areaMapper = areaMapper;
	}

}
