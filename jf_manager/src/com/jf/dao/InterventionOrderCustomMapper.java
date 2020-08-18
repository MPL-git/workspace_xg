package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;

@Repository
public interface InterventionOrderCustomMapper {

	public int countByCustomExample(InterventionOrderCustomExample example);

	public List<InterventionOrderCustom> selectByCustomExample(InterventionOrderCustomExample example);

	public InterventionOrderCustom selectByPrimaryKeyCustom(Integer id);
	
	public List<Map<String, Object>> getPlatformProcessorList();

}
