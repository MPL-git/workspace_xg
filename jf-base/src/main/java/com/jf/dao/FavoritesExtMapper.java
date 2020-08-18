package com.jf.dao;

import com.jf.entity.FavoritesExt;
import com.jf.entity.FavoritesExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    FavoritesExt findById(int id);

    FavoritesExt find(FavoritesExtExample example);

    List<FavoritesExt> list(FavoritesExtExample example);

    List<Integer> listId(FavoritesExtExample example);

    int count(FavoritesExtExample example);

    long sum(@Param("field") String field, @Param("example") FavoritesExtExample example);

    int max(@Param("field") String field, @Param("example") FavoritesExtExample example);

    int min(@Param("field") String field, @Param("example") FavoritesExtExample example);

}

