package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysStaffInfoExt;
import com.jf.entity.SysStaffInfoExtExample;

@Repository
public interface SysStaffInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysStaffInfoExt findById(int id);

    SysStaffInfoExt find(SysStaffInfoExtExample example);

    List<SysStaffInfoExt> list(SysStaffInfoExtExample example);

    List<Integer> listId(SysStaffInfoExtExample example);

    int count(SysStaffInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") SysStaffInfoExtExample example);

    int max(@Param("field") String field, @Param("example") SysStaffInfoExtExample example);

    int min(@Param("field") String field, @Param("example") SysStaffInfoExtExample example);

}

