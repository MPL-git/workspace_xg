package com.jf.dao;

import com.jf.entity.ImpeachMemberExt;
import com.jf.entity.ImpeachMemberExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpeachMemberExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ImpeachMemberExt findById(int id);

    ImpeachMemberExt find(ImpeachMemberExtExample example);

    List<ImpeachMemberExt> list(ImpeachMemberExtExample example);

    List<Integer> listId(ImpeachMemberExtExample example);

    int count(ImpeachMemberExtExample example);

    long sum(@Param("field") String field, @Param("example") ImpeachMemberExtExample example);

    int max(@Param("field") String field, @Param("example") ImpeachMemberExtExample example);

    int min(@Param("field") String field, @Param("example") ImpeachMemberExtExample example);

}

