package com.jf.dao;

import com.jf.entity.ProductWeightExt;
import com.jf.entity.ProductWeightExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductWeightExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductWeightExt findById(int id);

    ProductWeightExt find(ProductWeightExtExample example);

    List<ProductWeightExt> list(ProductWeightExtExample example);

    List<Integer> listId(ProductWeightExtExample example);

    int count(ProductWeightExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductWeightExtExample example);

    int max(@Param("field") String field, @Param("example") ProductWeightExtExample example);

    int min(@Param("field") String field, @Param("example") ProductWeightExtExample example);

}

