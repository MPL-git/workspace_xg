package com.jf.dao;

import com.jf.entity.MemberPvMiddleExt;
import com.jf.entity.MemberPvMiddleExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPvMiddleExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberPvMiddleExt findById(int id);

    MemberPvMiddleExt find(MemberPvMiddleExtExample example);

    List<MemberPvMiddleExt> list(MemberPvMiddleExtExample example);

    List<Integer> listId(MemberPvMiddleExtExample example);

    int count(MemberPvMiddleExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberPvMiddleExtExample example);

    int max(@Param("field") String field, @Param("example") MemberPvMiddleExtExample example);

    int min(@Param("field") String field, @Param("example") MemberPvMiddleExtExample example);

}

