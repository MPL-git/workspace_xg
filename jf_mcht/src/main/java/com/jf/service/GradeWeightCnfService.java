package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.GradeWeightCnfMapper;
import com.jf.entity.GradeWeightCnf;
import com.jf.entity.GradeWeightCnfExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GradeWeightCnfService extends BaseService<GradeWeightCnf, GradeWeightCnfExample> {
	
	@Autowired
	private GradeWeightCnfMapper gradeWeightCnfMapper;
	
	@Autowired
	public void setGradeWeightCnfMapper(GradeWeightCnfMapper gradeWeightCnfMapper) {
		this.setDao(gradeWeightCnfMapper);
		this.gradeWeightCnfMapper = gradeWeightCnfMapper;
	}
}
