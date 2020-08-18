package com.jf.dao;

import com.jf.entity.ShoppingCartExt;
import com.jf.entity.ShoppingCartExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShoppingCartExt findById(int id);

    ShoppingCartExt find(ShoppingCartExtExample example);

    List<ShoppingCartExt> list(ShoppingCartExtExample example);

    List<Integer> listId(ShoppingCartExtExample example);

    int count(ShoppingCartExtExample example);

    long sum(@Param("field") String field, @Param("example") ShoppingCartExtExample example);

    int max(@Param("field") String field, @Param("example") ShoppingCartExtExample example);

    int min(@Param("field") String field, @Param("example") ShoppingCartExtExample example);

}

