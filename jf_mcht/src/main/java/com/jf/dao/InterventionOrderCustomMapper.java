package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.InterventionOrderCustom;
import com.jf.entity.InterventionOrderCustomExample;
@Repository
public interface InterventionOrderCustomMapper{

	int countByExample(InterventionOrderCustomExample ioce);

	List<InterventionOrderCustom> selectByExample(InterventionOrderCustomExample ioce);
	
}