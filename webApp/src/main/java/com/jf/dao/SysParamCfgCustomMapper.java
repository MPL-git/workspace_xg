package com.jf.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SysParamCfgCustomMapper {

    int increasePoolIntegral(Map<String, Object> params);

    void resetPoolIntegral(Map<String, Object> params);

}
