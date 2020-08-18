package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtRemarksLogCustom;
import com.jf.entity.MchtRemarksLogExample;
@Repository
public interface MchtRemarksLogCustomMapper{
	public int countByExample(MchtRemarksLogExample example);
	
	public List<MchtRemarksLogCustom>selectByExample(MchtRemarksLogExample example);

}