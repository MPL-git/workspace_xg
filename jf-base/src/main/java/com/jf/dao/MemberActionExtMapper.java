package com.jf.dao;

import com.jf.entity.MemberActionExt;
import com.jf.entity.MemberActionExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberActionExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberActionExt findById(int id);

    MemberActionExt find(MemberActionExtExample example);

    List<MemberActionExt> list(MemberActionExtExample example);

    List<Integer> listId(MemberActionExtExample example);

    int count(MemberActionExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberActionExtExample example);

    int max(@Param("field") String field, @Param("example") MemberActionExtExample example);

    int min(@Param("field") String field, @Param("example") MemberActionExtExample example);

}

