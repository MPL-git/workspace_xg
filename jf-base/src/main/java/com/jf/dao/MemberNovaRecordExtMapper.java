package com.jf.dao;

import com.jf.entity.MemberNovaRecordExt;
import com.jf.entity.MemberNovaRecordExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberNovaRecordExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberNovaRecordExt findById(int id);

    MemberNovaRecordExt find(MemberNovaRecordExtExample example);

    List<MemberNovaRecordExt> list(MemberNovaRecordExtExample example);

    List<Integer> listId(MemberNovaRecordExtExample example);

    int count(MemberNovaRecordExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberNovaRecordExtExample example);

    int max(@Param("field") String field, @Param("example") MemberNovaRecordExtExample example);

    int min(@Param("field") String field, @Param("example") MemberNovaRecordExtExample example);

}

