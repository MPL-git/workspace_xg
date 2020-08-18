package com.jf.dao;

import com.jf.entity.ShopDecorateInfoDraftExt;
import com.jf.entity.ShopDecorateInfoDraftExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopDecorateInfoDraftExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopDecorateInfoDraftExt findById(int id);

    ShopDecorateInfoDraftExt find(ShopDecorateInfoDraftExtExample example);

    List<ShopDecorateInfoDraftExt> list(ShopDecorateInfoDraftExtExample example);

    List<Integer> listId(ShopDecorateInfoDraftExtExample example);

    int count(ShopDecorateInfoDraftExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopDecorateInfoDraftExtExample example);

    int max(@Param("field") String field, @Param("example") ShopDecorateInfoDraftExtExample example);

    int min(@Param("field") String field, @Param("example") ShopDecorateInfoDraftExtExample example);

}

