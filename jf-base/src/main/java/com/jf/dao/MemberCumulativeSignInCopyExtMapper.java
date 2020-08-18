package com.jf.dao;

import com.jf.entity.MemberCumulativeSignInCopyExt;
import com.jf.entity.MemberCumulativeSignInCopyExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberCumulativeSignInCopyExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberCumulativeSignInCopyExt findById(int id);

    MemberCumulativeSignInCopyExt find(MemberCumulativeSignInCopyExtExample example);

    List<MemberCumulativeSignInCopyExt> list(MemberCumulativeSignInCopyExtExample example);

    List<Integer> listId(MemberCumulativeSignInCopyExtExample example);

    int count(MemberCumulativeSignInCopyExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberCumulativeSignInCopyExtExample example);

    int max(@Param("field") String field, @Param("example") MemberCumulativeSignInCopyExtExample example);

    int min(@Param("field") String field, @Param("example") MemberCumulativeSignInCopyExtExample example);

}

