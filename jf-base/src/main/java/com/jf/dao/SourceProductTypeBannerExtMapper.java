package com.jf.dao;

import com.jf.entity.SourceProductTypeBannerExt;
import com.jf.entity.SourceProductTypeBannerExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceProductTypeBannerExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SourceProductTypeBannerExt findById(int id);

    SourceProductTypeBannerExt find(SourceProductTypeBannerExtExample example);

    List<SourceProductTypeBannerExt> list(SourceProductTypeBannerExtExample example);

    List<Integer> listId(SourceProductTypeBannerExtExample example);

    int count(SourceProductTypeBannerExtExample example);

    long sum(@Param("field") String field, @Param("example") SourceProductTypeBannerExtExample example);

    int max(@Param("field") String field, @Param("example") SourceProductTypeBannerExtExample example);

    int min(@Param("field") String field, @Param("example") SourceProductTypeBannerExtExample example);

}

