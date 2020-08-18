package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.InterventionProcessCustom;
import com.jf.entity.InterventionProcessExample;


@Repository
public interface InterventionProcessCustomMapper{

	List<InterventionProcessCustom> selectByExample(InterventionProcessExample example);
	
}