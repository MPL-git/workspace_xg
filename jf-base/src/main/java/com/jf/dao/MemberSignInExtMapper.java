package com.jf.dao;

import com.jf.entity.MemberSignInExt;
import com.jf.entity.MemberSignInExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSignInExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberSignInExt findById(int id);

    MemberSignInExt find(MemberSignInExtExample example);

    List<MemberSignInExt> list(MemberSignInExtExample example);

    List<Integer> listId(MemberSignInExtExample example);

    int count(MemberSignInExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberSignInExtExample example);

    int max(@Param("field") String field, @Param("example") MemberSignInExtExample example);

    int min(@Param("field") String field, @Param("example") MemberSignInExtExample example);

}

