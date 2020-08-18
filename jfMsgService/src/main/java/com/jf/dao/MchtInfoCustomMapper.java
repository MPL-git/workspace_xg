package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoCustomExample;
@Repository
public interface MchtInfoCustomMapper extends BaseDao<MchtInfoCustom, MchtInfoCustomExample> {
	List<MchtInfoCustom> selectByCustomExample(MchtInfoCustomExample example);
	int countByCustomExample(MchtInfoCustomExample example);
	
	List<MchtInfoCustom> findMchtDrs(List<Integer> list);
     
}