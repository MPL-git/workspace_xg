package com.jf.dao;

import com.jf.entity.SingleActivityWatermarkExt;
import com.jf.entity.SingleActivityWatermarkExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleActivityWatermarkExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SingleActivityWatermarkExt findById(int id);

    SingleActivityWatermarkExt find(SingleActivityWatermarkExtExample example);

    List<SingleActivityWatermarkExt> list(SingleActivityWatermarkExtExample example);

    List<Integer> listId(SingleActivityWatermarkExtExample example);

    int count(SingleActivityWatermarkExtExample example);

    long sum(@Param("field") String field, @Param("example") SingleActivityWatermarkExtExample example);

    int max(@Param("field") String field, @Param("example") SingleActivityWatermarkExtExample example);

    int min(@Param("field") String field, @Param("example") SingleActivityWatermarkExtExample example);

}

