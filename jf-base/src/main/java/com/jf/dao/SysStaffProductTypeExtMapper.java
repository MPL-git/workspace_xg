package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysStaffProductTypeExt;
import com.jf.entity.SysStaffProductTypeExtExample;

@Repository
public interface SysStaffProductTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysStaffProductTypeExt findById(int id);

    SysStaffProductTypeExt find(SysStaffProductTypeExtExample example);

    List<SysStaffProductTypeExt> list(SysStaffProductTypeExtExample example);

    List<Integer> listId(SysStaffProductTypeExtExample example);

    int count(SysStaffProductTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") SysStaffProductTypeExtExample example);

    int max(@Param("field") String field, @Param("example") SysStaffProductTypeExtExample example);

    int min(@Param("field") String field, @Param("example") SysStaffProductTypeExtExample example);

}

