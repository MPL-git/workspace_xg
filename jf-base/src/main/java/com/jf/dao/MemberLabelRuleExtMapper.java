package com.jf.dao;

import com.jf.entity.MemberLabelRuleExt;
import com.jf.entity.MemberLabelRuleExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelRuleExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberLabelRuleExt findById(int id);

    MemberLabelRuleExt find(MemberLabelRuleExtExample example);

    List<MemberLabelRuleExt> list(MemberLabelRuleExtExample example);

    List<Integer> listId(MemberLabelRuleExtExample example);

    int count(MemberLabelRuleExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberLabelRuleExtExample example);

    int max(@Param("field") String field, @Param("example") MemberLabelRuleExtExample example);

    int min(@Param("field") String field, @Param("example") MemberLabelRuleExtExample example);

}

