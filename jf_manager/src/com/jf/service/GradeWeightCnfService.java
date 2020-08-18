package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.GradeWeightCnfMapper;
import com.jf.entity.GradeWeightCnf;
import com.jf.entity.GradeWeightCnfExample;


@Service
@Transactional
public class GradeWeightCnfService extends BaseService<GradeWeightCnf, GradeWeightCnfExample>{
	
	@Autowired
	private GradeWeightCnfMapper gradeWeightCnfMapper;
	
	@Autowired
	public void setGradeWeightCnfMapper(GradeWeightCnfMapper gradeWeightCnfMapper) {
		this.setDao(gradeWeightCnfMapper);
		this.gradeWeightCnfMapper = gradeWeightCnfMapper;
	}
}
