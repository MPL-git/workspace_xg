package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysStaffRoleExt;
import com.jf.entity.SysStaffRoleExtExample;

@Repository
public interface SysStaffRoleExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysStaffRoleExt findById(int id);

    SysStaffRoleExt find(SysStaffRoleExtExample example);

    List<SysStaffRoleExt> list(SysStaffRoleExtExample example);

    List<Integer> listId(SysStaffRoleExtExample example);

    int count(SysStaffRoleExtExample example);

    long sum(@Param("field") String field, @Param("example") SysStaffRoleExtExample example);

    int max(@Param("field") String field, @Param("example") SysStaffRoleExtExample example);

    int min(@Param("field") String field, @Param("example") SysStaffRoleExtExample example);

}

