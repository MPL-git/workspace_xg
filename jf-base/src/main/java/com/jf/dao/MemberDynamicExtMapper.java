package com.jf.dao;

import com.jf.entity.MemberDynamicExt;
import com.jf.entity.MemberDynamicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDynamicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberDynamicExt findById(int id);

    MemberDynamicExt find(MemberDynamicExtExample example);

    List<MemberDynamicExt> list(MemberDynamicExtExample example);

    List<Integer> listId(MemberDynamicExtExample example);

    int count(MemberDynamicExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberDynamicExtExample example);

    int max(@Param("field") String field, @Param("example") MemberDynamicExtExample example);

    int min(@Param("field") String field, @Param("example") MemberDynamicExtExample example);

}

