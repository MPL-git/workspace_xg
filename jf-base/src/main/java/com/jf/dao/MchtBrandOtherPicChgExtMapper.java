package com.jf.dao;

import com.jf.entity.MchtBrandOtherPicChgExt;
import com.jf.entity.MchtBrandOtherPicChgExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandOtherPicChgExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandOtherPicChgExt findById(int id);

    MchtBrandOtherPicChgExt find(MchtBrandOtherPicChgExtExample example);

    List<MchtBrandOtherPicChgExt> list(MchtBrandOtherPicChgExtExample example);

    List<Integer> listId(MchtBrandOtherPicChgExtExample example);

    int count(MchtBrandOtherPicChgExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandOtherPicChgExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandOtherPicChgExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandOtherPicChgExtExample example);

}

