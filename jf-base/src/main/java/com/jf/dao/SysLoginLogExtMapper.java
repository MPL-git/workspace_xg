package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysLoginLogExt;
import com.jf.entity.SysLoginLogExtExample;

@Repository
public interface SysLoginLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysLoginLogExt findById(int id);

    SysLoginLogExt find(SysLoginLogExtExample example);

    List<SysLoginLogExt> list(SysLoginLogExtExample example);

    List<Integer> listId(SysLoginLogExtExample example);

    int count(SysLoginLogExtExample example);

    long sum(@Param("field") String field, @Param("example") SysLoginLogExtExample example);

    int max(@Param("field") String field, @Param("example") SysLoginLogExtExample example);

    int min(@Param("field") String field, @Param("example") SysLoginLogExtExample example);

}

