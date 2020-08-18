package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandChgCustom;
import com.jf.entity.MchtBrandChgExample;
@Repository
public interface MchtBrandChgCustomMapper  {
    int countByExample(MchtBrandChgExample example);

    List<MchtBrandChgCustom> selectByExample(MchtBrandChgExample example);

    MchtBrandChgCustom selectByPrimaryKey(Integer id);
}