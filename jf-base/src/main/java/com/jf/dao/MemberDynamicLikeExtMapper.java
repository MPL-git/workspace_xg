package com.jf.dao;

import com.jf.entity.MemberDynamicLikeExt;
import com.jf.entity.MemberDynamicLikeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDynamicLikeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberDynamicLikeExt findById(int id);

    MemberDynamicLikeExt find(MemberDynamicLikeExtExample example);

    List<MemberDynamicLikeExt> list(MemberDynamicLikeExtExample example);

    List<Integer> listId(MemberDynamicLikeExtExample example);

    int count(MemberDynamicLikeExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberDynamicLikeExtExample example);

    int max(@Param("field") String field, @Param("example") MemberDynamicLikeExtExample example);

    int min(@Param("field") String field, @Param("example") MemberDynamicLikeExtExample example);

}

