package com.jf.dao;

import com.jf.entity.ShopProductCustomTypeExt;
import com.jf.entity.ShopProductCustomTypeExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopProductCustomTypeExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopProductCustomTypeExt findById(int id);

    ShopProductCustomTypeExt find(ShopProductCustomTypeExtExample example);

    List<ShopProductCustomTypeExt> list(ShopProductCustomTypeExtExample example);

    List<Integer> listId(ShopProductCustomTypeExtExample example);

    int count(ShopProductCustomTypeExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopProductCustomTypeExtExample example);

    int max(@Param("field") String field, @Param("example") ShopProductCustomTypeExtExample example);

    int min(@Param("field") String field, @Param("example") ShopProductCustomTypeExtExample example);

}

