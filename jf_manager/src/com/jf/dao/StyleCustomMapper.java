package com.jf.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.Style;
import com.jf.entity.StyleExample;
@Repository
public interface StyleCustomMapper{
    int countByExample(StyleExample example);
    List<Style> selectByExample(StyleExample example);
    Style selectByPrimaryKey(Integer id);
	int updateStyleSeqNo(HashMap<String,Object> map);
}