package com.jf.dao;

import com.jf.entity.MemberActivityFootprintExt;
import com.jf.entity.MemberActivityFootprintExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberActivityFootprintExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberActivityFootprintExt findById(int id);

    MemberActivityFootprintExt find(MemberActivityFootprintExtExample example);

    List<MemberActivityFootprintExt> list(MemberActivityFootprintExtExample example);

    List<Integer> listId(MemberActivityFootprintExtExample example);

    int count(MemberActivityFootprintExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberActivityFootprintExtExample example);

    int max(@Param("field") String field, @Param("example") MemberActivityFootprintExtExample example);

    int min(@Param("field") String field, @Param("example") MemberActivityFootprintExtExample example);

}

