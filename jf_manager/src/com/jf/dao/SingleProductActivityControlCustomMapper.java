package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SingleProductActivityControlCustom;
import com.jf.entity.SingleProductActivityControlExample;
@Repository
public interface SingleProductActivityControlCustomMapper{
    int countByExample(SingleProductActivityControlExample example);
    List<SingleProductActivityControlCustom> selectByExample(SingleProductActivityControlExample example);
	SingleProductActivityControlCustom selectCustomByPrimaryKey(int id);
	String getMchtIdsByMchtCodes(String mchtCodes);
}