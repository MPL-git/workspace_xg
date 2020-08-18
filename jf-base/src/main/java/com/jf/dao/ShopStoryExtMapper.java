package com.jf.dao;

import com.jf.entity.ShopStoryExt;
import com.jf.entity.ShopStoryExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopStoryExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopStoryExt findById(int id);

    ShopStoryExt find(ShopStoryExtExample example);

    List<ShopStoryExt> list(ShopStoryExtExample example);

    List<Integer> listId(ShopStoryExtExample example);

    int count(ShopStoryExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopStoryExtExample example);

    int max(@Param("field") String field, @Param("example") ShopStoryExtExample example);

    int min(@Param("field") String field, @Param("example") ShopStoryExtExample example);

}

