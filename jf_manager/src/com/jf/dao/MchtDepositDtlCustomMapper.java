package com.jf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtDepositDtlCustom;
import com.jf.entity.MchtDepositDtlCustomExample;
@Repository
public interface MchtDepositDtlCustomMapper{
	public List<MchtDepositDtlCustom> selectMchtDepositDtlList(Map<String,Object> map);
	public int mchtDepositDtlListCount(Map<String,Object> map);
	public List<MchtDepositDtlCustom> selectViolateDepositDtlList(HashMap<String, Object> paramMap);
	public int violateDepositDtlListCount(HashMap<String, Object> paramMap);
	public int countByExample(MchtDepositDtlCustomExample example);
	public List<MchtDepositDtlCustom> selectByExample(MchtDepositDtlCustomExample example);
	public List<MchtDepositDtlCustom> selectMchtDepositDtlCountList(HashMap<String, Object> paramMap);
	public Integer mchtDepositDtlCountCount(HashMap<String, Object> paramMap);
	public Integer mchtDepositDtlCountEachDayCount(HashMap<String, Object> paramMap);
	public List<MchtDepositDtlCustom> selectMchtDepositDtlCountEachDayList(HashMap<String, Object> paramMap);
}