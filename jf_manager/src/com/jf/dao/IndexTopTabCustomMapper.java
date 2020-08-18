package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.IndexTopTab;
import com.jf.entity.IndexTopTabCustom;
import com.jf.entity.IndexTopTabExample;
@Repository
public interface IndexTopTabCustomMapper{
    int countByExample(IndexTopTabExample example);
    List<IndexTopTabCustom> selectByExample(IndexTopTabExample example);
	void update(IndexTopTab indexTopTab);
}