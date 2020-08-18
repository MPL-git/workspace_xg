package com.jf.dao;

import com.jf.entity.FavoritesProductTypeExt;
import com.jf.entity.FavoritesProductTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesProductTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    FavoritesProductTypeExt findById(int id);

    FavoritesProductTypeExt find(FavoritesProductTypeExtExample example);

    List<FavoritesProductTypeExt> list(FavoritesProductTypeExtExample example);

    List<Integer> listId(FavoritesProductTypeExtExample example);

    int count(FavoritesProductTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") FavoritesProductTypeExtExample example);

    int max(@Param("field") String field, @Param("example") FavoritesProductTypeExtExample example);

    int min(@Param("field") String field, @Param("example") FavoritesProductTypeExtExample example);

}

