package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.BgModuleCategoryCustom;
import com.jf.entity.BgModuleCategoryExample;
@Repository
public interface BgModuleCategoryCustomMapper{
    List<BgModuleCategoryCustom> selectByExample(BgModuleCategoryExample example);

	Integer getMaxSeqNo(Integer decorateModuleId);

	void batchUpdateSeqNo(HashMap<String, Object> map);
}