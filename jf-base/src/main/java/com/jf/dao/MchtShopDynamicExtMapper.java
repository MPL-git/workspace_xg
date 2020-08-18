package com.jf.dao;

import com.jf.entity.MchtShopDynamicExt;
import com.jf.entity.MchtShopDynamicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtShopDynamicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtShopDynamicExt findById(int id);

    MchtShopDynamicExt find(MchtShopDynamicExtExample example);

    List<MchtShopDynamicExt> list(MchtShopDynamicExtExample example);

    List<Integer> listId(MchtShopDynamicExtExample example);

    int count(MchtShopDynamicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtShopDynamicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtShopDynamicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtShopDynamicExtExample example);

}

