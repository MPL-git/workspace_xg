package com.jf.dao;

import com.jf.entity.MemberSupplementarySignInExt;
import com.jf.entity.MemberSupplementarySignInExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberSupplementarySignInExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberSupplementarySignInExt findById(int id);

    MemberSupplementarySignInExt find(MemberSupplementarySignInExtExample example);

    List<MemberSupplementarySignInExt> list(MemberSupplementarySignInExtExample example);

    List<Integer> listId(MemberSupplementarySignInExtExample example);

    int count(MemberSupplementarySignInExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberSupplementarySignInExtExample example);

    int max(@Param("field") String field, @Param("example") MemberSupplementarySignInExtExample example);

    int min(@Param("field") String field, @Param("example") MemberSupplementarySignInExtExample example);

}

