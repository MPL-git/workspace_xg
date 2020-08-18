package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.RoleMenuExt;
import com.jf.entity.RoleMenuExtExample;

@Repository
public interface RoleMenuExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    RoleMenuExt findById(int id);

    RoleMenuExt find(RoleMenuExtExample example);

    List<RoleMenuExt> list(RoleMenuExtExample example);

    List<Integer> listId(RoleMenuExtExample example);

    int count(RoleMenuExtExample example);

    long sum(@Param("field") String field, @Param("example") RoleMenuExtExample example);

    int max(@Param("field") String field, @Param("example") RoleMenuExtExample example);

    int min(@Param("field") String field, @Param("example") RoleMenuExtExample example);

}

