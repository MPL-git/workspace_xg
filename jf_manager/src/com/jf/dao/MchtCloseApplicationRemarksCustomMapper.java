package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtCloseApplicationRemarksCustom;
import com.jf.entity.MchtCloseApplicationRemarksExample;
@Repository
public interface MchtCloseApplicationRemarksCustomMapper{
	public List<MchtCloseApplicationRemarksCustom>selectByExample(MchtCloseApplicationRemarksExample example);
	public int countByExample(MchtCloseApplicationRemarksExample example);

}