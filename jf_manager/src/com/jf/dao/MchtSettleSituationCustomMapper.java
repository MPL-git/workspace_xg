package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSettleSituationCustom;
import com.jf.entity.MchtSettleSituationCustomExample;
@Repository
public interface MchtSettleSituationCustomMapper{

	int countByExample(MchtSettleSituationCustomExample example);

	List<MchtSettleSituationCustom> selectByExample(MchtSettleSituationCustomExample example);

	List<MchtSettleSituationCustom> currentSituationData(Map<String, String> paramMap);

}