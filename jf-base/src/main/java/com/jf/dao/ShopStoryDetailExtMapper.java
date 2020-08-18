package com.jf.dao;

import com.jf.entity.ShopStoryDetailExt;
import com.jf.entity.ShopStoryDetailExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopStoryDetailExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopStoryDetailExt findById(int id);

    ShopStoryDetailExt find(ShopStoryDetailExtExample example);

    List<ShopStoryDetailExt> list(ShopStoryDetailExtExample example);

    List<Integer> listId(ShopStoryDetailExtExample example);

    int count(ShopStoryDetailExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopStoryDetailExtExample example);

    int max(@Param("field") String field, @Param("example") ShopStoryDetailExtExample example);

    int min(@Param("field") String field, @Param("example") ShopStoryDetailExtExample example);

}

