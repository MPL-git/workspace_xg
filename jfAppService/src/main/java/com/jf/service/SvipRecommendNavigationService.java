package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.SvipRecommendNavigationMapper;
import com.jf.entity.SvipRecommendNavigation;
import com.jf.entity.SvipRecommendNavigationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  * @author  yjc: 
  * @date 创建时间：2019年8月14日14:13:40
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SvipRecommendNavigationService extends BaseService<SvipRecommendNavigation, SvipRecommendNavigationExample>{
	
	@Autowired
	private SvipRecommendNavigationMapper svipProductRecommendMapper;

	@Autowired
	public void setMapper() {
		this.setDao(svipProductRecommendMapper);
	}

	public List<SvipRecommendNavigation> findOnline() {
		SvipRecommendNavigationExample example = new SvipRecommendNavigationExample();
		example.createCriteria()
				.andStatusEqualTo(StateConst.ONLINE)
				.andDelFlagEqualTo(StateConst.FALSE);
		example.setOrderByClause("ifnull(seq_no,99999)");
		return this.selectByExample(example);
	}
}
