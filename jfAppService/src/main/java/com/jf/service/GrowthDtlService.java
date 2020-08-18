package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.GrowthDtlMapper;
import com.jf.entity.GrowthDtl;
import com.jf.entity.GrowthDtlExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月31日 下午4:39:36 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class GrowthDtlService extends BaseService<GrowthDtl, GrowthDtlExample> {
	@Autowired
	private GrowthDtlMapper growthDtlMapper;

	@Autowired
	public void setGrowthDtlMapper(GrowthDtlMapper growthDtlMapper) {
		this.growthDtlMapper = growthDtlMapper;
	}

	public GrowthDtl addGrowthDtl(Integer balance, Integer giveGrowth, Integer accId, Integer memberId, Integer orderId, String type) {
		GrowthDtl model = new GrowthDtl();
		model.setAccId(accId);
		model.setgValue(giveGrowth);
		model.setBalance(balance);
		model.setOrderId(orderId);
		model.setType(Integer.valueOf(type));
		model.setCreateBy(memberId);
		model.setDelFlag("0");
		
		return saveModel(model);
	}

	public GrowthDtl saveModel(GrowthDtl model) {
		Date date = new Date();
		model.setCreateDate(date);
		growthDtlMapper.insertSelective(model);
		return model;
	}
	
	
}
