package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysStatusExt;
import com.jf.entity.SysStatusExtExample;

@Repository
public interface SysStatusExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysStatusExt findById(int id);

    SysStatusExt find(SysStatusExtExample example);

    List<SysStatusExt> list(SysStatusExtExample example);

    List<Integer> listId(SysStatusExtExample example);

    int count(SysStatusExtExample example);

    long sum(@Param("field") String field, @Param("example") SysStatusExtExample example);

    int max(@Param("field") String field, @Param("example") SysStatusExtExample example);

    int min(@Param("field") String field, @Param("example") SysStatusExtExample example);

}

