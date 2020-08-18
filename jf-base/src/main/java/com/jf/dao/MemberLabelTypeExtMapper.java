package com.jf.dao;

import com.jf.entity.MemberLabelTypeExt;
import com.jf.entity.MemberLabelTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberLabelTypeExt findById(int id);

    MemberLabelTypeExt find(MemberLabelTypeExtExample example);

    List<MemberLabelTypeExt> list(MemberLabelTypeExtExample example);

    List<Integer> listId(MemberLabelTypeExtExample example);

    int count(MemberLabelTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberLabelTypeExtExample example);

    int max(@Param("field") String field, @Param("example") MemberLabelTypeExtExample example);

    int min(@Param("field") String field, @Param("example") MemberLabelTypeExtExample example);

}

