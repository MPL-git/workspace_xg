package com.jf.dao;

import com.jf.entity.MemberLabelGroupRelationExt;
import com.jf.entity.MemberLabelGroupRelationExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelGroupRelationExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberLabelGroupRelationExt findById(int id);

    MemberLabelGroupRelationExt find(MemberLabelGroupRelationExtExample example);

    List<MemberLabelGroupRelationExt> list(MemberLabelGroupRelationExtExample example);

    List<Integer> listId(MemberLabelGroupRelationExtExample example);

    int count(MemberLabelGroupRelationExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberLabelGroupRelationExtExample example);

    int max(@Param("field") String field, @Param("example") MemberLabelGroupRelationExtExample example);

    int min(@Param("field") String field, @Param("example") MemberLabelGroupRelationExtExample example);

}

