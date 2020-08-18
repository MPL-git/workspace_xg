package com.jf.dao;

import com.jf.entity.SingleProductActivityExt;
import com.jf.entity.SingleProductActivityExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleProductActivityExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SingleProductActivityExt findById(int id);

    SingleProductActivityExt find(SingleProductActivityExtExample example);

    List<SingleProductActivityExt> list(SingleProductActivityExtExample example);

    List<Integer> listId(SingleProductActivityExtExample example);

    int count(SingleProductActivityExtExample example);

    long sum(@Param("field") String field, @Param("example") SingleProductActivityExtExample example);

    int max(@Param("field") String field, @Param("example") SingleProductActivityExtExample example);

    int min(@Param("field") String field, @Param("example") SingleProductActivityExtExample example);

}

