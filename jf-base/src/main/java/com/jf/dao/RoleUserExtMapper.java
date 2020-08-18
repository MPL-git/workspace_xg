package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.RoleUserExt;
import com.jf.entity.RoleUserExtExample;

@Repository
public interface RoleUserExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    RoleUserExt findById(int id);

    RoleUserExt find(RoleUserExtExample example);

    List<RoleUserExt> list(RoleUserExtExample example);

    List<Integer> listId(RoleUserExtExample example);

    int count(RoleUserExtExample example);

    long sum(@Param("field") String field, @Param("example") RoleUserExtExample example);

    int max(@Param("field") String field, @Param("example") RoleUserExtExample example);

    int min(@Param("field") String field, @Param("example") RoleUserExtExample example);

}

