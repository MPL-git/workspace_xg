package com.jf.dao;

import com.jf.entity.ShopScoreExt;
import com.jf.entity.ShopScoreExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopScoreExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopScoreExt findById(int id);

    ShopScoreExt find(ShopScoreExtExample example);

    List<ShopScoreExt> list(ShopScoreExtExample example);

    List<Integer> listId(ShopScoreExtExample example);

    int count(ShopScoreExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopScoreExtExample example);

    int max(@Param("field") String field, @Param("example") ShopScoreExtExample example);

    int min(@Param("field") String field, @Param("example") ShopScoreExtExample example);

}

