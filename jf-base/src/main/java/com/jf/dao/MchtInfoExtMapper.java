package com.jf.dao;

import com.jf.entity.MchtInfoExt;
import com.jf.entity.MchtInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtInfoExt findById(int id);

    MchtInfoExt find(MchtInfoExtExample example);

    List<MchtInfoExt> list(MchtInfoExtExample example);

    List<Integer> listId(MchtInfoExtExample example);

    int count(MchtInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtInfoExtExample example);

    int max(@Param("field") String field, @Param("example") MchtInfoExtExample example);

    int min(@Param("field") String field, @Param("example") MchtInfoExtExample example);

}

