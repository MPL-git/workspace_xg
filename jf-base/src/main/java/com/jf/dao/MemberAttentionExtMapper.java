package com.jf.dao;

import com.jf.entity.MemberAttentionExt;
import com.jf.entity.MemberAttentionExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAttentionExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberAttentionExt findById(int id);

    MemberAttentionExt find(MemberAttentionExtExample example);

    List<MemberAttentionExt> list(MemberAttentionExtExample example);

    List<Integer> listId(MemberAttentionExtExample example);

    int count(MemberAttentionExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberAttentionExtExample example);

    int max(@Param("field") String field, @Param("example") MemberAttentionExtExample example);

    int min(@Param("field") String field, @Param("example") MemberAttentionExtExample example);

}

