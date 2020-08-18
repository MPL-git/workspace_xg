package com.jf.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.jf.entity.SysAppMessageMemberExt;
import com.jf.entity.SysAppMessageMemberExtExample;

@Repository
public interface SysAppMessageMemberExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SysAppMessageMemberExt findById(int id);

    SysAppMessageMemberExt find(SysAppMessageMemberExtExample example);

    List<SysAppMessageMemberExt> list(SysAppMessageMemberExtExample example);

    List<Integer> listId(SysAppMessageMemberExtExample example);

    int count(SysAppMessageMemberExtExample example);

    long sum(@Param("field") String field, @Param("example") SysAppMessageMemberExtExample example);

    int max(@Param("field") String field, @Param("example") SysAppMessageMemberExtExample example);

    int min(@Param("field") String field, @Param("example") SysAppMessageMemberExtExample example);

}

