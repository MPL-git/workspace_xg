package com.jf.dao;

import com.jf.entity.BannerRecommendProduct;
import com.jf.entity.BannerRecommendProductExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRecommendProductMapper extends BaseDao<BannerRecommendProduct, BannerRecommendProductExample> {
    int countByExample(BannerRecommendProductExample example);

    int deleteByExample(BannerRecommendProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BannerRecommendProduct record);

    int insertSelective(BannerRecommendProduct record);

    List<BannerRecommendProduct> selectByExample(BannerRecommendProductExample example);

    BannerRecommendProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BannerRecommendProduct record, @Param("example") BannerRecommendProductExample example);

    int updateByExample(@Param("record") BannerRecommendProduct record, @Param("example") BannerRecommendProductExample example);

    int updateByPrimaryKeySelective(BannerRecommendProduct record);

    int updateByPrimaryKey(BannerRecommendProduct record);
}