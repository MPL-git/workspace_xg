package com.jf.dao;

import com.jf.entity.ShopownerOrderExt;
import com.jf.entity.ShopownerOrderExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopownerOrderExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopownerOrderExt findById(int id);

    ShopownerOrderExt find(ShopownerOrderExtExample example);

    List<ShopownerOrderExt> list(ShopownerOrderExtExample example);

    List<Integer> listId(ShopownerOrderExtExample example);

    int count(ShopownerOrderExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopownerOrderExtExample example);

    int max(@Param("field") String field, @Param("example") ShopownerOrderExtExample example);

    int min(@Param("field") String field, @Param("example") ShopownerOrderExtExample example);

}

