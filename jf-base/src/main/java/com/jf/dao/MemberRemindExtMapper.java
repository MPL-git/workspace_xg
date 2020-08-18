package com.jf.dao;

import com.jf.entity.MemberRemindExt;
import com.jf.entity.MemberRemindExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRemindExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberRemindExt findById(int id);

    MemberRemindExt find(MemberRemindExtExample example);

    List<MemberRemindExt> list(MemberRemindExtExample example);

    List<Integer> listId(MemberRemindExtExample example);

    int count(MemberRemindExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberRemindExtExample example);

    int max(@Param("field") String field, @Param("example") MemberRemindExtExample example);

    int min(@Param("field") String field, @Param("example") MemberRemindExtExample example);

}

