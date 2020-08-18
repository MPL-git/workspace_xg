package com.jf.dao;

import com.jf.entity.MemberFootprintExt;
import com.jf.entity.MemberFootprintExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberFootprintExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberFootprintExt findById(int id);

    MemberFootprintExt find(MemberFootprintExtExample example);

    List<MemberFootprintExt> list(MemberFootprintExtExample example);

    List<Integer> listId(MemberFootprintExtExample example);

    int count(MemberFootprintExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberFootprintExtExample example);

    int max(@Param("field") String field, @Param("example") MemberFootprintExtExample example);

    int min(@Param("field") String field, @Param("example") MemberFootprintExtExample example);

}

