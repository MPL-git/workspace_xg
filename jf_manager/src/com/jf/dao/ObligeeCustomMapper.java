package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.ObligeeCustom;


@Repository
public interface ObligeeCustomMapper{

	ObligeeCustom selectByPrimaryKey(Integer id);
}