package com.jf.dao;

import com.jf.entity.MemberLabelGroupExt;
import com.jf.entity.MemberLabelGroupExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelGroupExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberLabelGroupExt findById(int id);

    MemberLabelGroupExt find(MemberLabelGroupExtExample example);

    List<MemberLabelGroupExt> list(MemberLabelGroupExtExample example);

    List<Integer> listId(MemberLabelGroupExtExample example);

    int count(MemberLabelGroupExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberLabelGroupExtExample example);

    int max(@Param("field") String field, @Param("example") MemberLabelGroupExtExample example);

    int min(@Param("field") String field, @Param("example") MemberLabelGroupExtExample example);

}

