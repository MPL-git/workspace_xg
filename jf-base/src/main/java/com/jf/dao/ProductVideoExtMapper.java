package com.jf.dao;

import com.jf.entity.ProductVideoExt;
import com.jf.entity.ProductVideoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVideoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductVideoExt findById(int id);

    ProductVideoExt find(ProductVideoExtExample example);

    List<ProductVideoExt> list(ProductVideoExtExample example);

    List<Integer> listId(ProductVideoExtExample example);

    int count(ProductVideoExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductVideoExtExample example);

    int max(@Param("field") String field, @Param("example") ProductVideoExtExample example);

    int min(@Param("field") String field, @Param("example") ProductVideoExtExample example);

}

