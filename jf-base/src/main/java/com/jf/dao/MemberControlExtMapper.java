package com.jf.dao;

import com.jf.entity.MemberControlExt;
import com.jf.entity.MemberControlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberControlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberControlExt findById(int id);

    MemberControlExt find(MemberControlExtExample example);

    List<MemberControlExt> list(MemberControlExtExample example);

    List<Integer> listId(MemberControlExtExample example);

    int count(MemberControlExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberControlExtExample example);

    int max(@Param("field") String field, @Param("example") MemberControlExtExample example);

    int min(@Param("field") String field, @Param("example") MemberControlExtExample example);

}

