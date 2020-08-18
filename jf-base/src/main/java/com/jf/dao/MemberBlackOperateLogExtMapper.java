package com.jf.dao;

import com.jf.entity.MemberBlackOperateLogExt;
import com.jf.entity.MemberBlackOperateLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberBlackOperateLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberBlackOperateLogExt findById(int id);

    MemberBlackOperateLogExt find(MemberBlackOperateLogExtExample example);

    List<MemberBlackOperateLogExt> list(MemberBlackOperateLogExtExample example);

    List<Integer> listId(MemberBlackOperateLogExtExample example);

    int count(MemberBlackOperateLogExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberBlackOperateLogExtExample example);

    int max(@Param("field") String field, @Param("example") MemberBlackOperateLogExtExample example);

    int min(@Param("field") String field, @Param("example") MemberBlackOperateLogExtExample example);

}

