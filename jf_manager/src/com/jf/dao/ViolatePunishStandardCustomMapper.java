package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.ViolatePunishStandardCustom;
import com.jf.entity.ViolatePunishStandardCustomExample;
@Repository
public interface ViolatePunishStandardCustomMapper{
	public int countByExample(ViolatePunishStandardCustomExample example);
	
	public List<ViolatePunishStandardCustom>selectByExample(ViolatePunishStandardCustomExample violatePunishStandardCustomExample);

}