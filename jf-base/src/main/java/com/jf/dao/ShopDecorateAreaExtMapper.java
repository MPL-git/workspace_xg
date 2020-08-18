package com.jf.dao;

import com.jf.entity.ShopDecorateAreaExt;
import com.jf.entity.ShopDecorateAreaExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecorateAreaExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopDecorateAreaExt findById(int id);

    ShopDecorateAreaExt find(ShopDecorateAreaExtExample example);

    List<ShopDecorateAreaExt> list(ShopDecorateAreaExtExample example);

    List<Integer> listId(ShopDecorateAreaExtExample example);

    int count(ShopDecorateAreaExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopDecorateAreaExtExample example);

    int max(@Param("field") String field, @Param("example") ShopDecorateAreaExtExample example);

    int min(@Param("field") String field, @Param("example") ShopDecorateAreaExtExample example);

}

