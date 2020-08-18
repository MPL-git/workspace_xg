package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateAreaCustom;
import com.jf.entity.DecorateAreaExample;
@Repository
public interface DecorateAreaCustomMapper{
	public List<DecorateAreaCustom>selectByExample(DecorateAreaExample example);

	public void updateSeqNo(HashMap<String, Object> paramMap);

	public int insertSelective1(DecorateArea dArea);
}