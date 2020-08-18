package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SpreadNameCustom;
import com.jf.entity.SpreadNameExample;

@Repository
public interface SpreadNameCustomMapper {
    int countByExample(SpreadNameExample example);


    List<SpreadNameCustom> selectByExample(SpreadNameExample example);

    SpreadNameCustom selectByPrimaryKey(Integer id);
}