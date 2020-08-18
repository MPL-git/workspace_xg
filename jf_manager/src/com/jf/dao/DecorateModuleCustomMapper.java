package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleCustom;
import com.jf.entity.DecorateModuleExample;
@Repository
public interface DecorateModuleCustomMapper{
	public List<DecorateModuleCustom>selectByExample(DecorateModuleExample example);
	public Integer getModuleCount(HashMap<String, Object> map);
	public int insertSelective(DecorateModule dModule);	 
    void updateOtherModuleSeqNo(List<Integer> arr);
}