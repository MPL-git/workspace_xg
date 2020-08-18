package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MallCategorySubCustom;
import com.jf.entity.MallCategorySubExample;
@Repository
public interface MallCategorySubCustomMapper{

	int countByExample(MallCategorySubExample example);

	List<MallCategorySubCustom> selectByExample(MallCategorySubExample example);

	int getMaxSeqNo();

	void batchExecute(HashMap<String, Object> map);

	void batchExecute2(HashMap<String, Object> map);

	void batchExecute3(int oldSeqNo);
	
    
}