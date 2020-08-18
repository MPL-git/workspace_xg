package com.jf.dao;

import com.jf.entity.ShopDecorateAreaDraftExt;
import com.jf.entity.ShopDecorateAreaDraftExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecorateAreaDraftExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopDecorateAreaDraftExt findById(int id);

    ShopDecorateAreaDraftExt find(ShopDecorateAreaDraftExtExample example);

    List<ShopDecorateAreaDraftExt> list(ShopDecorateAreaDraftExtExample example);

    List<Integer> listId(ShopDecorateAreaDraftExtExample example);

    int count(ShopDecorateAreaDraftExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopDecorateAreaDraftExtExample example);

    int max(@Param("field") String field, @Param("example") ShopDecorateAreaDraftExtExample example);

    int min(@Param("field") String field, @Param("example") ShopDecorateAreaDraftExtExample example);

}

