package com.jf.dao;

import com.jf.entity.AndroidChannelGroupExt;
import com.jf.entity.AndroidChannelGroupExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AndroidChannelGroupExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    AndroidChannelGroupExt findById(int id);

    AndroidChannelGroupExt find(AndroidChannelGroupExtExample example);

    List<AndroidChannelGroupExt> list(AndroidChannelGroupExtExample example);

    List<Integer> listId(AndroidChannelGroupExtExample example);

    int count(AndroidChannelGroupExtExample example);

    long sum(@Param("field") String field, @Param("example") AndroidChannelGroupExtExample example);

    int max(@Param("field") String field, @Param("example") AndroidChannelGroupExtExample example);

    int min(@Param("field") String field, @Param("example") AndroidChannelGroupExtExample example);

}

