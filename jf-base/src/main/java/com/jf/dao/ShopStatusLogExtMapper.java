package com.jf.dao;

import com.jf.entity.ShopStatusLogExt;
import com.jf.entity.ShopStatusLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopStatusLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ShopStatusLogExt findById(int id);

    ShopStatusLogExt find(ShopStatusLogExtExample example);

    List<ShopStatusLogExt> list(ShopStatusLogExtExample example);

    List<Integer> listId(ShopStatusLogExtExample example);

    int count(ShopStatusLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ShopStatusLogExtExample example);

    int max(@Param("field") String field, @Param("example") ShopStatusLogExtExample example);

    int min(@Param("field") String field, @Param("example") ShopStatusLogExtExample example);

}

