package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtInfoChg;
import com.jf.entity.MchtInfoChgCustom;
import com.jf.entity.MchtInfoChgExample;
@Repository
public interface MchtInfoChgCustomMapper{
    int countByExample(MchtInfoChgExample example);
    List<MchtInfoChgCustom> selectByExample(MchtInfoChgExample example);
    MchtInfoChgCustom selectByPrimaryKey(Integer id);

}