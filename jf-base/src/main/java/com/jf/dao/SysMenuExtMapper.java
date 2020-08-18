package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysMenuExt;
import com.jf.entity.SysMenuExtExample;

@Repository
public interface SysMenuExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysMenuExt findById(int id);

    SysMenuExt find(SysMenuExtExample example);

    List<SysMenuExt> list(SysMenuExtExample example);

    List<Integer> listId(SysMenuExtExample example);

    int count(SysMenuExtExample example);

    long sum(@Param("field") String field, @Param("example") SysMenuExtExample example);

    int max(@Param("field") String field, @Param("example") SysMenuExtExample example);

    int min(@Param("field") String field, @Param("example") SysMenuExtExample example);

}

