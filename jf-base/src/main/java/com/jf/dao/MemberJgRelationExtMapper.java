package com.jf.dao;

import com.jf.entity.MemberJgRelationExt;
import com.jf.entity.MemberJgRelationExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberJgRelationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberJgRelationExt findById(int id);

    MemberJgRelationExt find(MemberJgRelationExtExample example);

    List<MemberJgRelationExt> list(MemberJgRelationExtExample example);

    List<Integer> listId(MemberJgRelationExtExample example);

    int count(MemberJgRelationExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberJgRelationExtExample example);

    int max(@Param("field") String field, @Param("example") MemberJgRelationExtExample example);

    int min(@Param("field") String field, @Param("example") MemberJgRelationExtExample example);

}

