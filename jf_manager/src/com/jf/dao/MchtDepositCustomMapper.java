package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtDepositCustom;
import com.jf.entity.MchtDepositCustomExample;
@Repository
public interface MchtDepositCustomMapper{
	public List<MchtDepositCustom> selectMchtDepositList(Map<String,Object> map);
	public int mchtDepositListCount(Map<String,Object> map);
	
	public List<MchtDepositCustom> selectMchtDepositMchtInfoList(Map<String,Object> map);
	public int mchtDepositMchtInfoListCount(Map<String,Object> map);
	
	public List<MchtDepositCustom> selectByCustomExample(MchtDepositCustomExample mchtDepositCustomExample);
	public int countByCustomExample(MchtDepositCustomExample mchtDepositCustomExample);
	
}