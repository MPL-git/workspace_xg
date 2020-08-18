package com.jf.dao;

import com.jf.entity.MemberBlackPicExt;
import com.jf.entity.MemberBlackPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberBlackPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberBlackPicExt findById(int id);

    MemberBlackPicExt find(MemberBlackPicExtExample example);

    List<MemberBlackPicExt> list(MemberBlackPicExtExample example);

    List<Integer> listId(MemberBlackPicExtExample example);

    int count(MemberBlackPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberBlackPicExtExample example);

    int max(@Param("field") String field, @Param("example") MemberBlackPicExtExample example);

    int min(@Param("field") String field, @Param("example") MemberBlackPicExtExample example);

}

