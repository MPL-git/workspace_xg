package com.jf.dao;

import com.jf.entity.MemberMonthSignInExt;
import com.jf.entity.MemberMonthSignInExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberMonthSignInExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberMonthSignInExt findById(int id);

    MemberMonthSignInExt find(MemberMonthSignInExtExample example);

    List<MemberMonthSignInExt> list(MemberMonthSignInExtExample example);

    List<Integer> listId(MemberMonthSignInExtExample example);

    int count(MemberMonthSignInExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberMonthSignInExtExample example);

    int max(@Param("field") String field, @Param("example") MemberMonthSignInExtExample example);

    int min(@Param("field") String field, @Param("example") MemberMonthSignInExtExample example);

}

