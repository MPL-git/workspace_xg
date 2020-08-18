package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.RoleInfoExt;
import com.jf.entity.RoleInfoExtExample;

@Repository
public interface RoleInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    RoleInfoExt findById(int id);

    RoleInfoExt find(RoleInfoExtExample example);

    List<RoleInfoExt> list(RoleInfoExtExample example);

    List<Integer> listId(RoleInfoExtExample example);

    int count(RoleInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") RoleInfoExtExample example);

    int max(@Param("field") String field, @Param("example") RoleInfoExtExample example);

    int min(@Param("field") String field, @Param("example") RoleInfoExtExample example);

}

