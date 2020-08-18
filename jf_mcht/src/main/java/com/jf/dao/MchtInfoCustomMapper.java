package com.jf.dao;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtHomeInfo;
import com.jf.entity.MchtInfoCustom;
@Repository
public interface MchtInfoCustomMapper{
    MchtInfoCustom selectByPrimaryKey(Integer id);
    MchtHomeInfo selectMchtHomeByPrimaryKey(Integer id);
}