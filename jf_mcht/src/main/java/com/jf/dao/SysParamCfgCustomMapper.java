package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SysParamCfg;
@Repository
public interface SysParamCfgCustomMapper{

	public List<SysParamCfg> getSysParamCfgListByParamCode(String paramCode);
}