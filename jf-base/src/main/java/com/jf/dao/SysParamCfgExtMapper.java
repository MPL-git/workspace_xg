package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysParamCfgExt;
import com.jf.entity.SysParamCfgExtExample;

@Repository
public interface SysParamCfgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysParamCfgExt findById(int id);

    SysParamCfgExt find(SysParamCfgExtExample example);

    List<SysParamCfgExt> list(SysParamCfgExtExample example);

    List<Integer> listId(SysParamCfgExtExample example);

    int count(SysParamCfgExtExample example);

    long sum(@Param("field") String field, @Param("example") SysParamCfgExtExample example);

    int max(@Param("field") String field, @Param("example") SysParamCfgExtExample example);

    int min(@Param("field") String field, @Param("example") SysParamCfgExtExample example);

}

