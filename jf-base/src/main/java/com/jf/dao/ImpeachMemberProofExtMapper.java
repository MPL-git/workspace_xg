package com.jf.dao;

import com.jf.entity.ImpeachMemberProofExt;
import com.jf.entity.ImpeachMemberProofExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpeachMemberProofExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ImpeachMemberProofExt findById(int id);

    ImpeachMemberProofExt find(ImpeachMemberProofExtExample example);

    List<ImpeachMemberProofExt> list(ImpeachMemberProofExtExample example);

    List<Integer> listId(ImpeachMemberProofExtExample example);

    int count(ImpeachMemberProofExtExample example);

    long sum(@Param("field") String field, @Param("example") ImpeachMemberProofExtExample example);

    int max(@Param("field") String field, @Param("example") ImpeachMemberProofExtExample example);

    int min(@Param("field") String field, @Param("example") ImpeachMemberProofExtExample example);

}

