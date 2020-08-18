package com.jf.dao;

import com.jf.entity.MemberAccountExt;
import com.jf.entity.MemberAccountExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAccountExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberAccountExt findById(int id);

    MemberAccountExt find(MemberAccountExtExample example);

    List<MemberAccountExt> list(MemberAccountExtExample example);

    List<Integer> listId(MemberAccountExtExample example);

    int count(MemberAccountExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberAccountExtExample example);

    int max(@Param("field") String field, @Param("example") MemberAccountExtExample example);

    int min(@Param("field") String field, @Param("example") MemberAccountExtExample example);

}

