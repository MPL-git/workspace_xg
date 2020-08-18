package com.jf.dao;

import com.jf.entity.MemberPvExt;
import com.jf.entity.MemberPvExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPvExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberPvExt findById(int id);

    MemberPvExt find(MemberPvExtExample example);

    List<MemberPvExt> list(MemberPvExtExample example);

    List<Integer> listId(MemberPvExtExample example);

    int count(MemberPvExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberPvExtExample example);

    int max(@Param("field") String field, @Param("example") MemberPvExtExample example);

    int min(@Param("field") String field, @Param("example") MemberPvExtExample example);

}

