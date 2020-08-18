package com.jf.dao;

import com.jf.entity.PlatformMsgExt;
import com.jf.entity.PlatformMsgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformMsgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PlatformMsgExt findById(int id);

    PlatformMsgExt find(PlatformMsgExtExample example);

    List<PlatformMsgExt> list(PlatformMsgExtExample example);

    List<Integer> listId(PlatformMsgExtExample example);

    int count(PlatformMsgExtExample example);

    long sum(@Param("field") String field, @Param("example") PlatformMsgExtExample example);

    int max(@Param("field") String field, @Param("example") PlatformMsgExtExample example);

    int min(@Param("field") String field, @Param("example") PlatformMsgExtExample example);

}

