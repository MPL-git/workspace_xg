package com.jf.dao;

import com.jf.entity.ShopModuleDraftExt;
import com.jf.entity.ShopModuleDraftExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopModuleDraftExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopModuleDraftExt findById(int id);

    ShopModuleDraftExt find(ShopModuleDraftExtExample example);

    List<ShopModuleDraftExt> list(ShopModuleDraftExtExample example);

    List<Integer> listId(ShopModuleDraftExtExample example);

    int count(ShopModuleDraftExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopModuleDraftExtExample example);

    int max(@Param("field") String field, @Param("example") ShopModuleDraftExtExample example);

    int min(@Param("field") String field, @Param("example") ShopModuleDraftExtExample example);

}

