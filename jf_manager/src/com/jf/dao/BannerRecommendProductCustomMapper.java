package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.BannerRecommendProductCustom;
import com.jf.entity.BannerRecommendProductExample;

@Repository
public interface BannerRecommendProductCustomMapper {

	
	public int countByCustomExample(BannerRecommendProductExample example);

	public List<BannerRecommendProductCustom> selectByCustomExample(BannerRecommendProductExample example);
	
	public List<BannerRecommendProductCustom> selectByIds(@Param("idsList") List<Integer> idsList);
	
}
