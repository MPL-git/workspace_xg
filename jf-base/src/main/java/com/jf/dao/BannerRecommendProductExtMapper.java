package com.jf.dao;

import com.jf.entity.BannerRecommendProductExt;
import com.jf.entity.BannerRecommendProductExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRecommendProductExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    BannerRecommendProductExt findById(int id);

    BannerRecommendProductExt find(BannerRecommendProductExtExample example);

    List<BannerRecommendProductExt> list(BannerRecommendProductExtExample example);

    List<Integer> listId(BannerRecommendProductExtExample example);

    int count(BannerRecommendProductExtExample example);

    long sum(@Param("field") String field, @Param("example") BannerRecommendProductExtExample example);

    int max(@Param("field") String field, @Param("example") BannerRecommendProductExtExample example);

    int min(@Param("field") String field, @Param("example") BannerRecommendProductExtExample example);

}

