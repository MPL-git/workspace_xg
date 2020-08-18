package com.jf.dao;

import com.jf.entity.MemberPvDtlExt;
import com.jf.entity.MemberPvDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPvDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberPvDtlExt findById(int id);

    MemberPvDtlExt find(MemberPvDtlExtExample example);

    List<MemberPvDtlExt> list(MemberPvDtlExtExample example);

    List<Integer> listId(MemberPvDtlExtExample example);

    int count(MemberPvDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberPvDtlExtExample example);

    int max(@Param("field") String field, @Param("example") MemberPvDtlExtExample example);

    int min(@Param("field") String field, @Param("example") MemberPvDtlExtExample example);

}

