package com.jf.dao;

import com.jf.entity.MchtInfoHisExt;
import com.jf.entity.MchtInfoHisExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtInfoHisExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtInfoHisExt findById(int id);

    MchtInfoHisExt find(MchtInfoHisExtExample example);

    List<MchtInfoHisExt> list(MchtInfoHisExtExample example);

    List<Integer> listId(MchtInfoHisExtExample example);

    int count(MchtInfoHisExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtInfoHisExtExample example);

    int max(@Param("field") String field, @Param("example") MchtInfoHisExtExample example);

    int min(@Param("field") String field, @Param("example") MchtInfoHisExtExample example);

}

