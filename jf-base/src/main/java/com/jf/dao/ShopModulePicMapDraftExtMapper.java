package com.jf.dao;

import com.jf.entity.ShopModulePicMapDraftExt;
import com.jf.entity.ShopModulePicMapDraftExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopModulePicMapDraftExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopModulePicMapDraftExt findById(int id);

    ShopModulePicMapDraftExt find(ShopModulePicMapDraftExtExample example);

    List<ShopModulePicMapDraftExt> list(ShopModulePicMapDraftExtExample example);

    List<Integer> listId(ShopModulePicMapDraftExtExample example);

    int count(ShopModulePicMapDraftExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopModulePicMapDraftExtExample example);

    int max(@Param("field") String field, @Param("example") ShopModulePicMapDraftExtExample example);

    int min(@Param("field") String field, @Param("example") ShopModulePicMapDraftExtExample example);

}

