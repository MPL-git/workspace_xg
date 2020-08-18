package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandRateChangeCustom;
import com.jf.entity.MchtBrandRateChangeExample;
@Repository
public interface MchtBrandRateChangeCustomMapper{
    int countByExample(MchtBrandRateChangeExample example);
    List<MchtBrandRateChangeCustom> selectByExample(MchtBrandRateChangeExample example);
}