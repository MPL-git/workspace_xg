package com.jf.dao;

import com.jf.entity.MemberInfoExt;
import com.jf.entity.MemberInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberInfoExt findById(int id);

    MemberInfoExt find(MemberInfoExtExample example);

    List<MemberInfoExt> list(MemberInfoExtExample example);

    List<Integer> listId(MemberInfoExtExample example);

    int count(MemberInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberInfoExtExample example);

    int max(@Param("field") String field, @Param("example") MemberInfoExtExample example);

    int min(@Param("field") String field, @Param("example") MemberInfoExtExample example);

}

