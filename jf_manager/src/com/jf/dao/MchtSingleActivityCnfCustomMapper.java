package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSingleActivityCnfCustom;
import com.jf.entity.MchtSingleActivityCnfCustomExample;

@Repository
public interface MchtSingleActivityCnfCustomMapper {

	public List<MchtSingleActivityCnfCustom> selectByCustomExample(MchtSingleActivityCnfCustomExample example);
	
	public Integer countByCustomExample(MchtSingleActivityCnfCustomExample example);
	
	
}

