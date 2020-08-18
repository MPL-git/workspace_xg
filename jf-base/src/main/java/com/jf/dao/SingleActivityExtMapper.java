package com.jf.dao;

import com.jf.entity.SingleActivityExt;
import com.jf.entity.SingleActivityExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleActivityExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SingleActivityExt findById(int id);

    SingleActivityExt find(SingleActivityExtExample example);

    List<SingleActivityExt> list(SingleActivityExtExample example);

    List<Integer> listId(SingleActivityExtExample example);

    int count(SingleActivityExtExample example);

    long sum(@Param("field") String field, @Param("example") SingleActivityExtExample example);

    int max(@Param("field") String field, @Param("example") SingleActivityExtExample example);

    int min(@Param("field") String field, @Param("example") SingleActivityExtExample example);

}

