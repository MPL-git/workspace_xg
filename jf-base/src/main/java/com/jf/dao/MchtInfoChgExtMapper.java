package com.jf.dao;

import com.jf.entity.MchtInfoChgExt;
import com.jf.entity.MchtInfoChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtInfoChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtInfoChgExt findById(int id);

    MchtInfoChgExt find(MchtInfoChgExtExample example);

    List<MchtInfoChgExt> list(MchtInfoChgExtExample example);

    List<Integer> listId(MchtInfoChgExtExample example);

    int count(MchtInfoChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtInfoChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtInfoChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtInfoChgExtExample example);

}

