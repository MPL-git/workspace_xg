package com.jf.dao;

import com.jf.entity.MemberExtendExt;
import com.jf.entity.MemberExtendExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberExtendExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberExtendExt findById(int id);

    MemberExtendExt find(MemberExtendExtExample example);

    List<MemberExtendExt> list(MemberExtendExtExample example);

    List<Integer> listId(MemberExtendExtExample example);

    int count(MemberExtendExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberExtendExtExample example);

    int max(@Param("field") String field, @Param("example") MemberExtendExtExample example);

    int min(@Param("field") String field, @Param("example") MemberExtendExtExample example);

}

