package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ProblemGuidelineCustom;
import com.jf.entity.ProblemGuidelineCustomExample;
@Repository
public interface ProblemGuidelineCustomMapper{
	
	public List<ProblemGuidelineCustom>problemGuidelineSelectByExample(ProblemGuidelineCustomExample example);

	public int problemGuidelineCountByExample(ProblemGuidelineCustomExample example);
}