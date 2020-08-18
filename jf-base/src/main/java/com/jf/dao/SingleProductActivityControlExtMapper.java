package com.jf.dao;

import com.jf.entity.SingleProductActivityControlExt;
import com.jf.entity.SingleProductActivityControlExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleProductActivityControlExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SingleProductActivityControlExt findById(int id);

    SingleProductActivityControlExt find(SingleProductActivityControlExtExample example);

    List<SingleProductActivityControlExt> list(SingleProductActivityControlExtExample example);

    List<Integer> listId(SingleProductActivityControlExtExample example);

    int count(SingleProductActivityControlExtExample example);

    long sum(@Param("field") String field, @Param("example") SingleProductActivityControlExtExample example);

    int max(@Param("field") String field, @Param("example") SingleProductActivityControlExtExample example);

    int min(@Param("field") String field, @Param("example") SingleProductActivityControlExtExample example);

}

