package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysOrganizationExt;
import com.jf.entity.SysOrganizationExtExample;

@Repository
public interface SysOrganizationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysOrganizationExt findById(int id);

    SysOrganizationExt find(SysOrganizationExtExample example);

    List<SysOrganizationExt> list(SysOrganizationExtExample example);

    List<Integer> listId(SysOrganizationExtExample example);

    int count(SysOrganizationExtExample example);

    long sum(@Param("field") String field, @Param("example") SysOrganizationExtExample example);

    int max(@Param("field") String field, @Param("example") SysOrganizationExtExample example);

    int min(@Param("field") String field, @Param("example") SysOrganizationExtExample example);

}

