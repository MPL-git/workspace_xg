package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtProductTypeCustom;
import com.jf.entity.MchtProductTypeExample;

@Repository
public interface MchtProductTypeCustomMapper{
    int countByExample(MchtProductTypeExample example);

    List<MchtProductTypeCustom> selectByExample(MchtProductTypeExample example);

    MchtProductTypeCustom selectByPrimaryKey(Integer id);

}