package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ProductMapper;
import com.jf.dao.ThirdPlatformProductInfoMapper;
import com.jf.entity.Product;
import com.jf.entity.ProductExample;
import com.jf.entity.ThirdPlatformProductInfo;
import com.jf.entity.ThirdPlatformProductInfoExample;


@Service
@Transactional
public class ThirdPlatformProductInfoService extends BaseService<ThirdPlatformProductInfo, ThirdPlatformProductInfoExample> {
	
	@Autowired
	private ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper;
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	public void setThirdPlatformProductInfoMapper(ThirdPlatformProductInfoMapper thirdPlatformProductInfoMapper) {
		this.setDao(thirdPlatformProductInfoMapper);
		this.thirdPlatformProductInfoMapper = thirdPlatformProductInfoMapper;
	}

	public void autoUp(ThirdPlatformProductInfo thirdPlatformProductInfo,
			ThirdPlatformProductInfoExample e, List<Integer> productIds) {
		this.updateByExampleSelective(thirdPlatformProductInfo, e);
		if(productIds!=null && productIds.size()>0){
			Product p = new Product();
			p.setStatus("1");
			p.setUpdateDate(new Date());
			ProductExample example = new ProductExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdIn(productIds);
			productMapper.updateByExampleSelective(p, example);
		}
	}

	public void autoDown(ThirdPlatformProductInfo thirdPlatformProductInfo,
			ThirdPlatformProductInfoExample e, List<Integer> productIds) {
		this.updateByExampleSelective(thirdPlatformProductInfo, e);
		if(productIds!=null && productIds.size()>0){
			Product p = new Product();
			p.setStatus("0");
			p.setUpdateDate(new Date());
			ProductExample example = new ProductExample();
			example.createCriteria().andDelFlagEqualTo("0").andIdIn(productIds);
			productMapper.updateByExampleSelective(p, example);
		}
	}
}
