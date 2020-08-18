package com.jf.dao;

import com.jf.entity.ProductExt;
import com.jf.entity.ProductExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductExt findById(int id);

    ProductExt find(ProductExtExample example);

    List<ProductExt> list(ProductExtExample example);

    List<Integer> listId(ProductExtExample example);

    int count(ProductExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductExtExample example);

    int max(@Param("field") String field, @Param("example") ProductExtExample example);

    int min(@Param("field") String field, @Param("example") ProductExtExample example);

}

