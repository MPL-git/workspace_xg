package com.jf.dao;

import com.jf.entity.PlatformMsgEditExt;
import com.jf.entity.PlatformMsgEditExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformMsgEditExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    PlatformMsgEditExt findById(int id);

    PlatformMsgEditExt find(PlatformMsgEditExtExample example);

    List<PlatformMsgEditExt> list(PlatformMsgEditExtExample example);

    List<Integer> listId(PlatformMsgEditExtExample example);

    int count(PlatformMsgEditExtExample example);

    long sum(@Param("field") String field, @Param("example") PlatformMsgEditExtExample example);

    int max(@Param("field") String field, @Param("example") PlatformMsgEditExtExample example);

    int min(@Param("field") String field, @Param("example") PlatformMsgEditExtExample example);

}

