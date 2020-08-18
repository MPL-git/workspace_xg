package com.jf.dao;

import com.jf.entity.MchtInfoChangeLogExt;
import com.jf.entity.MchtInfoChangeLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtInfoChangeLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtInfoChangeLogExt findById(int id);

    MchtInfoChangeLogExt find(MchtInfoChangeLogExtExample example);

    List<MchtInfoChangeLogExt> list(MchtInfoChangeLogExtExample example);

    List<Integer> listId(MchtInfoChangeLogExtExample example);

    int count(MchtInfoChangeLogExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtInfoChangeLogExtExample example);

    int max(@Param("field") String field, @Param("example") MchtInfoChangeLogExtExample example);

    int min(@Param("field") String field, @Param("example") MchtInfoChangeLogExtExample example);

}

