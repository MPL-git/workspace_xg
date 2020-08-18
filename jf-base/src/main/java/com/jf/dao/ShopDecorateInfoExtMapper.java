package com.jf.dao;

import com.jf.entity.ShopDecorateInfoExt;
import com.jf.entity.ShopDecorateInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecorateInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopDecorateInfoExt findById(int id);

    ShopDecorateInfoExt find(ShopDecorateInfoExtExample example);

    List<ShopDecorateInfoExt> list(ShopDecorateInfoExtExample example);

    List<Integer> listId(ShopDecorateInfoExtExample example);

    int count(ShopDecorateInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopDecorateInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ShopDecorateInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ShopDecorateInfoExtExample example);

}

