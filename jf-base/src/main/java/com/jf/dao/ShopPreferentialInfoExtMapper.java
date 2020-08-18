package com.jf.dao;

import com.jf.entity.ShopPreferentialInfoExt;
import com.jf.entity.ShopPreferentialInfoExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopPreferentialInfoExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopPreferentialInfoExt findById(int id);

    ShopPreferentialInfoExt find(ShopPreferentialInfoExtExample example);

    List<ShopPreferentialInfoExt> list(ShopPreferentialInfoExtExample example);

    List<Integer> listId(ShopPreferentialInfoExtExample example);

    int count(ShopPreferentialInfoExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopPreferentialInfoExtExample example);

    int max(@Param("field") String field, @Param("example") ShopPreferentialInfoExtExample example);

    int min(@Param("field") String field, @Param("example") ShopPreferentialInfoExtExample example);

}

