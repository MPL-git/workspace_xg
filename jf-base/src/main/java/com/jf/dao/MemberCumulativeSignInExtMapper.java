package com.jf.dao;

import com.jf.entity.MemberCumulativeSignInExt;
import com.jf.entity.MemberCumulativeSignInExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCumulativeSignInExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberCumulativeSignInExt findById(int id);

    MemberCumulativeSignInExt find(MemberCumulativeSignInExtExample example);

    List<MemberCumulativeSignInExt> list(MemberCumulativeSignInExtExample example);

    List<Integer> listId(MemberCumulativeSignInExtExample example);

    int count(MemberCumulativeSignInExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberCumulativeSignInExtExample example);

    int max(@Param("field") String field, @Param("example") MemberCumulativeSignInExtExample example);

    int min(@Param("field") String field, @Param("example") MemberCumulativeSignInExtExample example);

}

