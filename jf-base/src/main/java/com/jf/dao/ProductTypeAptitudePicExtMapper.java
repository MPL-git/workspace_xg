package com.jf.dao;

import com.jf.entity.ProductTypeAptitudePicExt;
import com.jf.entity.ProductTypeAptitudePicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeAptitudePicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductTypeAptitudePicExt findById(int id);

    ProductTypeAptitudePicExt find(ProductTypeAptitudePicExtExample example);

    List<ProductTypeAptitudePicExt> list(ProductTypeAptitudePicExtExample example);

    List<Integer> listId(ProductTypeAptitudePicExtExample example);

    int count(ProductTypeAptitudePicExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductTypeAptitudePicExtExample example);

    int max(@Param("field") String field, @Param("example") ProductTypeAptitudePicExtExample example);

    int min(@Param("field") String field, @Param("example") ProductTypeAptitudePicExtExample example);

}

