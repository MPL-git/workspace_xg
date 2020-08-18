package com.jf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.BannerRecommendProductCustomMapper;
import com.jf.dao.BannerRecommendProductMapper;
import com.jf.entity.BannerRecommendProduct;
import com.jf.entity.BannerRecommendProductCustom;
import com.jf.entity.BannerRecommendProductExample;


@Service
@Transactional
public class BannerRecommendProductService extends BaseService<BannerRecommendProduct,BannerRecommendProductExample>{
	
	@Autowired
	private BannerRecommendProductMapper  bannerRecommendProductMapper;
	
	@Autowired
	private BannerRecommendProductCustomMapper  bannerRecommendProductCustomMapper;
	
	
	@Autowired
	public void setBannerRecommendProductMapper(BannerRecommendProductMapper bannerRecommendProductMapper) {
		super.setDao(bannerRecommendProductMapper);
		this.bannerRecommendProductMapper = bannerRecommendProductMapper;
	}
	
	public int countByCustomExample(BannerRecommendProductExample example){
		return bannerRecommendProductCustomMapper.countByCustomExample(example);
	}

	public List<BannerRecommendProductCustom> selectByCustomExample(BannerRecommendProductExample example){
		return bannerRecommendProductCustomMapper.selectByCustomExample(example);
	}
	
	public List<BannerRecommendProductCustom> selectByIds(@Param("idsList") List<Integer> idsList){
		return bannerRecommendProductCustomMapper.selectByIds(idsList);
	}
	
}
