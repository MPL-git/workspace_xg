package com.jf.dao;

import com.jf.entity.BrandTmkPicExt;
import com.jf.entity.BrandTmkPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandTmkPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BrandTmkPicExt findById(int id);

    BrandTmkPicExt find(BrandTmkPicExtExample example);

    List<BrandTmkPicExt> list(BrandTmkPicExtExample example);

    List<Integer> listId(BrandTmkPicExtExample example);

    int count(BrandTmkPicExtExample example);

    long sum(@Param("field") String field, @Param("example") BrandTmkPicExtExample example);

    int max(@Param("field") String field, @Param("example") BrandTmkPicExtExample example);

    int min(@Param("field") String field, @Param("example") BrandTmkPicExtExample example);

}

