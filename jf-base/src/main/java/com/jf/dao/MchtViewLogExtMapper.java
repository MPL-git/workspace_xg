package com.jf.dao;

import com.jf.entity.MchtViewLogExt;
import com.jf.entity.MchtViewLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtViewLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtViewLogExt findById(int id);

    MchtViewLogExt find(MchtViewLogExtExample example);

    List<MchtViewLogExt> list(MchtViewLogExtExample example);

    List<Integer> listId(MchtViewLogExtExample example);

    int count(MchtViewLogExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtViewLogExtExample example);

    int max(@Param("field") String field, @Param("example") MchtViewLogExtExample example);

    int min(@Param("field") String field, @Param("example") MchtViewLogExtExample example);

}

