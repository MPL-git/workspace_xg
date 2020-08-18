package com.jf.dao;

import com.jf.entity.BrandTmkPicTmpExt;
import com.jf.entity.BrandTmkPicTmpExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandTmkPicTmpExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BrandTmkPicTmpExt findById(int id);

    BrandTmkPicTmpExt find(BrandTmkPicTmpExtExample example);

    List<BrandTmkPicTmpExt> list(BrandTmkPicTmpExtExample example);

    List<Integer> listId(BrandTmkPicTmpExtExample example);

    int count(BrandTmkPicTmpExtExample example);

    long sum(@Param("field") String field, @Param("example") BrandTmkPicTmpExtExample example);

    int max(@Param("field") String field, @Param("example") BrandTmkPicTmpExtExample example);

    int min(@Param("field") String field, @Param("example") BrandTmkPicTmpExtExample example);

}

