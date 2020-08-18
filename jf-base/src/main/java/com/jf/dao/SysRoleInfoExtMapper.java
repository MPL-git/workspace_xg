package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysRoleInfoExt;
import com.jf.entity.SysRoleInfoExtExample;

@Repository
public interface SysRoleInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysRoleInfoExt findById(int id);

    SysRoleInfoExt find(SysRoleInfoExtExample example);

    List<SysRoleInfoExt> list(SysRoleInfoExtExample example);

    List<Integer> listId(SysRoleInfoExtExample example);

    int count(SysRoleInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") SysRoleInfoExtExample example);

    int max(@Param("field") String field, @Param("example") SysRoleInfoExtExample example);

    int min(@Param("field") String field, @Param("example") SysRoleInfoExtExample example);

}

