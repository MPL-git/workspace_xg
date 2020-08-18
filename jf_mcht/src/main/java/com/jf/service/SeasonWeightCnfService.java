package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SeasonWeightCnfMapper;
import com.jf.entity.SeasonWeightCnf;
import com.jf.entity.SeasonWeightCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SeasonWeightCnfService extends BaseService<SeasonWeightCnf, SeasonWeightCnfExample> {
	
	@Autowired
	private SeasonWeightCnfMapper seasonWeightCnfMapper;
	
	@Autowired
	public void setSeasonWeightCnfMapper(SeasonWeightCnfMapper seasonWeightCnfMapper) {
		this.setDao(seasonWeightCnfMapper);
		this.seasonWeightCnfMapper = seasonWeightCnfMapper;
	}
}
