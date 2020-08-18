package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.IntegralGiveCustom;
import com.jf.entity.IntegralGiveExample;
@Repository
public interface IntegralGiveCustomMapper{
    int countByExample(IntegralGiveExample example);
    List<IntegralGiveCustom> selectByExample(IntegralGiveExample example);
    IntegralGiveCustom selectByPrimaryKey(Integer id);
}