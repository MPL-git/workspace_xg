package com.jf.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface IntegralGiveCustomMapper{
    int batchInsertIntegralDtl(Map<String, Object> dataMap);
    int batchUpdateMemberAccoutIntegral(Map<String, Object> dataMap);
}