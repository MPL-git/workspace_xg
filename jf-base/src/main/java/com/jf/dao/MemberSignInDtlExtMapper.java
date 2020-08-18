package com.jf.dao;

import com.jf.entity.MemberSignInDtlExt;
import com.jf.entity.MemberSignInDtlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSignInDtlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberSignInDtlExt findById(int id);

    MemberSignInDtlExt find(MemberSignInDtlExtExample example);

    List<MemberSignInDtlExt> list(MemberSignInDtlExtExample example);

    List<Integer> listId(MemberSignInDtlExtExample example);

    int count(MemberSignInDtlExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberSignInDtlExtExample example);

    int max(@Param("field") String field, @Param("example") MemberSignInDtlExtExample example);

    int min(@Param("field") String field, @Param("example") MemberSignInDtlExtExample example);

}

