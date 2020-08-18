package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinXcxSprPlanMapper;
import com.jf.entity.WeixinXcxSprPlan;
import com.jf.entity.WeixinXcxSprPlanExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:29:58 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WeixinXcxSprPlanService extends BaseService<WeixinXcxSprPlan, WeixinXcxSprPlanExample> {
	@Autowired
	private WeixinXcxSprPlanMapper weixinXcxSprPlanMapper;

	@Autowired
	public void setWeixinXcxSprPlanMapper(WeixinXcxSprPlanMapper weixinXcxSprPlanMapper) {
		this.setDao(weixinXcxSprPlanMapper);
		this.weixinXcxSprPlanMapper = weixinXcxSprPlanMapper;
	}
	
	
}
