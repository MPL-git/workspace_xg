package com.jf.dao;

import com.jf.entity.ProductPicExt;
import com.jf.entity.ProductPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductPicExt findById(int id);

    ProductPicExt find(ProductPicExtExample example);

    List<ProductPicExt> list(ProductPicExtExample example);

    List<Integer> listId(ProductPicExtExample example);

    int count(ProductPicExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductPicExtExample example);

    int max(@Param("field") String field, @Param("example") ProductPicExtExample example);

    int min(@Param("field") String field, @Param("example") ProductPicExtExample example);

}

