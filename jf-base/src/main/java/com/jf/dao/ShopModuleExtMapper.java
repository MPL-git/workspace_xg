package com.jf.dao;

import com.jf.entity.ShopModuleExt;
import com.jf.entity.ShopModuleExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopModuleExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopModuleExt findById(int id);

    ShopModuleExt find(ShopModuleExtExample example);

    List<ShopModuleExt> list(ShopModuleExtExample example);

    List<Integer> listId(ShopModuleExtExample example);

    int count(ShopModuleExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopModuleExtExample example);

    int max(@Param("field") String field, @Param("example") ShopModuleExtExample example);

    int min(@Param("field") String field, @Param("example") ShopModuleExtExample example);

}

