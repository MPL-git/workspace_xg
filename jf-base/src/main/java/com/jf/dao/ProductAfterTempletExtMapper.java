package com.jf.dao;

import com.jf.entity.ProductAfterTempletExt;
import com.jf.entity.ProductAfterTempletExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAfterTempletExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductAfterTempletExt findById(int id);

    ProductAfterTempletExt find(ProductAfterTempletExtExample example);

    List<ProductAfterTempletExt> list(ProductAfterTempletExtExample example);

    List<Integer> listId(ProductAfterTempletExtExample example);

    int count(ProductAfterTempletExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductAfterTempletExtExample example);

    int max(@Param("field") String field, @Param("example") ProductAfterTempletExtExample example);

    int min(@Param("field") String field, @Param("example") ProductAfterTempletExtExample example);

}

