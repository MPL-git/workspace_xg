package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.dao.SvipProductRecommendCustomMapper;
import com.jf.dao.SvipProductRecommendMapper;
import com.jf.entity.ProductCustom;
import com.jf.entity.ProductType;
import com.jf.entity.SvipProductRecommend;
import com.jf.entity.SvipProductRecommendExample;

/**
  * @author  yjc: 
  * @date 创建时间：2019年8月14日14:13:40
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SvipProductRecommendService extends BaseService<SvipProductRecommend, SvipProductRecommendExample>{
	
	@Autowired
	private SvipProductRecommendMapper svipProductRecommendMapper;
	@Autowired
	private SvipProductRecommendCustomMapper svipProductRecommendCustomMapper;
	
	@Autowired
	public void setSvipProductRecommendMapper(SvipProductRecommendMapper svipProductRecommendMapper) {
		this.setDao(svipProductRecommendMapper);
		this.svipProductRecommendMapper = svipProductRecommendMapper;
	}

	public List<ProductType> getProductTypeList() {
		return svipProductRecommendCustomMapper.getProductTypeList();
	}

	public List<ProductCustom> getSvipProductRecommendList(Map<String, Object> paramMap) {
		return svipProductRecommendCustomMapper.getSvipProductRecommendList(paramMap);
	}
	
	
	
}
