package com.jf.dao;

import com.jf.entity.ShopModulePicMapExt;
import com.jf.entity.ShopModulePicMapExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopModulePicMapExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopModulePicMapExt findById(int id);

    ShopModulePicMapExt find(ShopModulePicMapExtExample example);

    List<ShopModulePicMapExt> list(ShopModulePicMapExtExample example);

    List<Integer> listId(ShopModulePicMapExtExample example);

    int count(ShopModulePicMapExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopModulePicMapExtExample example);

    int max(@Param("field") String field, @Param("example") ShopModulePicMapExtExample example);

    int min(@Param("field") String field, @Param("example") ShopModulePicMapExtExample example);

}

