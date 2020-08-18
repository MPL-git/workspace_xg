package com.jf.dao;

import com.jf.entity.ShopDecoratePageExt;
import com.jf.entity.ShopDecoratePageExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecoratePageExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopDecoratePageExt findById(int id);

    ShopDecoratePageExt find(ShopDecoratePageExtExample example);

    List<ShopDecoratePageExt> list(ShopDecoratePageExtExample example);

    List<Integer> listId(ShopDecoratePageExtExample example);

    int count(ShopDecoratePageExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopDecoratePageExtExample example);

    int max(@Param("field") String field, @Param("example") ShopDecoratePageExtExample example);

    int min(@Param("field") String field, @Param("example") ShopDecoratePageExtExample example);

}

