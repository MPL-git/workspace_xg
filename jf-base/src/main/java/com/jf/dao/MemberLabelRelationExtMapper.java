package com.jf.dao;

import com.jf.entity.MemberLabelRelationExt;
import com.jf.entity.MemberLabelRelationExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelRelationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberLabelRelationExt findById(int id);

    MemberLabelRelationExt find(MemberLabelRelationExtExample example);

    List<MemberLabelRelationExt> list(MemberLabelRelationExtExample example);

    List<Integer> listId(MemberLabelRelationExtExample example);

    int count(MemberLabelRelationExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberLabelRelationExtExample example);

    int max(@Param("field") String field, @Param("example") MemberLabelRelationExtExample example);

    int min(@Param("field") String field, @Param("example") MemberLabelRelationExtExample example);

}

