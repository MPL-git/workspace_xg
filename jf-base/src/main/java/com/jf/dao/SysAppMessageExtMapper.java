package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysAppMessageExt;
import com.jf.entity.SysAppMessageExtExample;

@Repository
public interface SysAppMessageExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysAppMessageExt findById(int id);

    SysAppMessageExt find(SysAppMessageExtExample example);

    List<SysAppMessageExt> list(SysAppMessageExtExample example);

    List<Integer> listId(SysAppMessageExtExample example);

    int count(SysAppMessageExtExample example);

    long sum(@Param("field") String field, @Param("example") SysAppMessageExtExample example);

    int max(@Param("field") String field, @Param("example") SysAppMessageExtExample example);

    int min(@Param("field") String field, @Param("example") SysAppMessageExtExample example);

}

