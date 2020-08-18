package com.jf.dao;

import com.jf.entity.ViolateOrderExt;
import com.jf.entity.ViolateOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViolateOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ViolateOrderExt findById(int id);

    ViolateOrderExt find(ViolateOrderExtExample example);

    List<ViolateOrderExt> list(ViolateOrderExtExample example);

    List<Integer> listId(ViolateOrderExtExample example);

    int count(ViolateOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") ViolateOrderExtExample example);

    int max(@Param("field") String field, @Param("example") ViolateOrderExtExample example);

    int min(@Param("field") String field, @Param("example") ViolateOrderExtExample example);

}

