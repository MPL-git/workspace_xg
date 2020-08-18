package com.jf.dao;

import com.jf.entity.MemberStatusLogExt;
import com.jf.entity.MemberStatusLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberStatusLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberStatusLogExt findById(int id);

    MemberStatusLogExt find(MemberStatusLogExtExample example);

    List<MemberStatusLogExt> list(MemberStatusLogExtExample example);

    List<Integer> listId(MemberStatusLogExtExample example);

    int count(MemberStatusLogExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberStatusLogExtExample example);

    int max(@Param("field") String field, @Param("example") MemberStatusLogExtExample example);

    int min(@Param("field") String field, @Param("example") MemberStatusLogExtExample example);

}

