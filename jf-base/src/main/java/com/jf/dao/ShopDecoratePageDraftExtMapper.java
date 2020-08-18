package com.jf.dao;

import com.jf.entity.ShopDecoratePageDraftExt;
import com.jf.entity.ShopDecoratePageDraftExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecoratePageDraftExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopDecoratePageDraftExt findById(int id);

    ShopDecoratePageDraftExt find(ShopDecoratePageDraftExtExample example);

    List<ShopDecoratePageDraftExt> list(ShopDecoratePageDraftExtExample example);

    List<Integer> listId(ShopDecoratePageDraftExtExample example);

    int count(ShopDecoratePageDraftExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopDecoratePageDraftExtExample example);

    int max(@Param("field") String field, @Param("example") ShopDecoratePageDraftExtExample example);

    int min(@Param("field") String field, @Param("example") ShopDecoratePageDraftExtExample example);

}

