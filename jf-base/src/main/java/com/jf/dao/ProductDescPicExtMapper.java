package com.jf.dao;

import com.jf.entity.ProductDescPicExt;
import com.jf.entity.ProductDescPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDescPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductDescPicExt findById(int id);

    ProductDescPicExt find(ProductDescPicExtExample example);

    List<ProductDescPicExt> list(ProductDescPicExtExample example);

    List<Integer> listId(ProductDescPicExtExample example);

    int count(ProductDescPicExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductDescPicExtExample example);

    int max(@Param("field") String field, @Param("example") ProductDescPicExtExample example);

    int min(@Param("field") String field, @Param("example") ProductDescPicExtExample example);

}

