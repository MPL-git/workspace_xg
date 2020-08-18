package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.IntegralTypeCustom;
import com.jf.entity.IntegralTypeExample;
@Repository
public interface IntegralTypeCustomMapper{
    int countByExample(IntegralTypeExample example);
    List<IntegralTypeCustom> selectByExample(IntegralTypeExample example);
    IntegralTypeCustom selectByPrimaryKey(Integer id);
}