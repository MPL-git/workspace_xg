package com.jf.dao;

import com.jf.entity.PlatformMsgEditLogExt;
import com.jf.entity.PlatformMsgEditLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformMsgEditLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PlatformMsgEditLogExt findById(int id);

    PlatformMsgEditLogExt find(PlatformMsgEditLogExtExample example);

    List<PlatformMsgEditLogExt> list(PlatformMsgEditLogExtExample example);

    List<Integer> listId(PlatformMsgEditLogExtExample example);

    int count(PlatformMsgEditLogExtExample example);

    long sum(@Param("field") String field, @Param("example") PlatformMsgEditLogExtExample example);

    int max(@Param("field") String field, @Param("example") PlatformMsgEditLogExtExample example);

    int min(@Param("field") String field, @Param("example") PlatformMsgEditLogExtExample example);

}

