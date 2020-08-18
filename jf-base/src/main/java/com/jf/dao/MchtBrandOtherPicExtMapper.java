package com.jf.dao;

import com.jf.entity.MchtBrandOtherPicExt;
import com.jf.entity.MchtBrandOtherPicExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtBrandOtherPicExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    MchtBrandOtherPicExt findById(int id);

    MchtBrandOtherPicExt find(MchtBrandOtherPicExtExample example);

    List<MchtBrandOtherPicExt> list(MchtBrandOtherPicExtExample example);

    List<Integer> listId(MchtBrandOtherPicExtExample example);

    int count(MchtBrandOtherPicExtExample example);

    long sum(@Param("field") String field, @Param("example") MchtBrandOtherPicExtExample example);

    int max(@Param("field") String field, @Param("example") MchtBrandOtherPicExtExample example);

    int min(@Param("field") String field, @Param("example") MchtBrandOtherPicExtExample example);

}

