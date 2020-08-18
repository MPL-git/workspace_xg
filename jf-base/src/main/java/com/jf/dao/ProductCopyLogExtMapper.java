package com.jf.dao;

import com.jf.entity.ProductCopyLogExt;
import com.jf.entity.ProductCopyLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCopyLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductCopyLogExt findById(int id);

    ProductCopyLogExt find(ProductCopyLogExtExample example);

    List<ProductCopyLogExt> list(ProductCopyLogExtExample example);

    List<Integer> listId(ProductCopyLogExtExample example);

    int count(ProductCopyLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductCopyLogExtExample example);

    int max(@Param("field") String field, @Param("example") ProductCopyLogExtExample example);

    int min(@Param("field") String field, @Param("example") ProductCopyLogExtExample example);

}

