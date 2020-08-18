package com.jf.dao;

import com.jf.entity.ShopownerExt;
import com.jf.entity.ShopownerExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopownerExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopownerExt findById(int id);

    ShopownerExt find(ShopownerExtExample example);

    List<ShopownerExt> list(ShopownerExtExample example);

    List<Integer> listId(ShopownerExtExample example);

    int count(ShopownerExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopownerExtExample example);

    int max(@Param("field") String field, @Param("example") ShopownerExtExample example);

    int min(@Param("field") String field, @Param("example") ShopownerExtExample example);

}

