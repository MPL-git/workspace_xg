package com.jf.dao;

import com.jf.entity.MemberGroupExt;
import com.jf.entity.MemberGroupExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberGroupExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberGroupExt findById(int id);

    MemberGroupExt find(MemberGroupExtExample example);

    List<MemberGroupExt> list(MemberGroupExtExample example);

    List<Integer> listId(MemberGroupExtExample example);

    int count(MemberGroupExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberGroupExtExample example);

    int max(@Param("field") String field, @Param("example") MemberGroupExtExample example);

    int min(@Param("field") String field, @Param("example") MemberGroupExtExample example);

}

