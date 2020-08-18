package com.jf.dao;

import com.jf.entity.MemberShopFootprintExt;
import com.jf.entity.MemberShopFootprintExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberShopFootprintExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MemberShopFootprintExt findById(int id);

    MemberShopFootprintExt find(MemberShopFootprintExtExample example);

    List<MemberShopFootprintExt> list(MemberShopFootprintExtExample example);

    List<Integer> listId(MemberShopFootprintExtExample example);

    int count(MemberShopFootprintExtExample example);

    long sum(@Param("field") String field, @Param("example") MemberShopFootprintExtExample example);

    int max(@Param("field") String field, @Param("example") MemberShopFootprintExtExample example);

    int min(@Param("field") String field, @Param("example") MemberShopFootprintExtExample example);

}

