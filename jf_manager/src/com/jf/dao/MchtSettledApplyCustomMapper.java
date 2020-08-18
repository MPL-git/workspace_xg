package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSettledApplyCustom;
import com.jf.entity.MchtSettledApplyExample;
@Repository
public interface MchtSettledApplyCustomMapper{
    int countByExample(MchtSettledApplyExample example);
    List<MchtSettledApplyCustom> selectByExample(MchtSettledApplyExample example);
    MchtSettledApplyCustom selectByPrimaryKey(Integer id);
	List<HashMap<String, Object>> getInfoConfirmBy();
}