package com.jf.dao;

import com.jf.entity.MchtProductBrandExt;
import com.jf.entity.MchtProductBrandExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtProductBrandExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtProductBrandExt findById(int id);

    MchtProductBrandExt find(MchtProductBrandExtExample example);

    List<MchtProductBrandExt> list(MchtProductBrandExtExample example);

    List<Integer> listId(MchtProductBrandExtExample example);

    int count(MchtProductBrandExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtProductBrandExtExample example);

    int max(@Param("field") String field, @Param("example") MchtProductBrandExtExample example);

    int min(@Param("field") String field, @Param("example") MchtProductBrandExtExample example);

}

