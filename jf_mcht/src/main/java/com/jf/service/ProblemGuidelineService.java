package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.ProblemGuidelineMapper;
import com.jf.entity.ProblemGuideline;
import com.jf.entity.ProblemGuidelineExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pengl
 * @create 2020-03-18 下午 3:25
 */
@Service
@Transactional
public class ProblemGuidelineService extends BaseService<ProblemGuideline, ProblemGuidelineExample> {

	@Autowired
	private ProblemGuidelineMapper problemGuidelineMapper;

	@Autowired
	public void setProblemGuidelineMapper(ProblemGuidelineMapper problemGuidelineMapper) {
		super.setDao(problemGuidelineMapper);
		this.dao = problemGuidelineMapper;
	}

}
