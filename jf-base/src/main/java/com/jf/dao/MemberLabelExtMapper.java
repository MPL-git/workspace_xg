package com.jf.dao;

import com.jf.entity.MemberLabelExt;
import com.jf.entity.MemberLabelExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberLabelExt findById(int id);

    MemberLabelExt find(MemberLabelExtExample example);

    List<MemberLabelExt> list(MemberLabelExtExample example);

    List<Integer> listId(MemberLabelExtExample example);

    int count(MemberLabelExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberLabelExtExample example);

    int max(@Param("field") String field, @Param("example") MemberLabelExtExample example);

    int min(@Param("field") String field, @Param("example") MemberLabelExtExample example);

}

