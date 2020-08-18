package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.InterventionOrderLogCustom;
import com.jf.entity.InterventionOrderLogExample;

@Repository
public interface InterventionOrderLogCustomMapper {

	public int countByCustomExample(InterventionOrderLogExample example);

	public List<InterventionOrderLogCustom> selectByCustomExample(InterventionOrderLogExample example);

	public InterventionOrderLogCustom selectByPrimaryKeyCustom(Integer id);
	
}
