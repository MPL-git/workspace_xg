package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.DouyinSprDtlMapper;
import com.jf.entity.DouyinSprDtl;
import com.jf.entity.DouyinSprDtlExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class DouyinSprDtlService extends BaseService<DouyinSprDtl, DouyinSprDtlExample> {
	@Autowired
	private DouyinSprDtlMapper adSprDtlMapper;
	@Autowired
	public void setAdSprDtlMapper(DouyinSprDtlMapper adSprDtlMapper) {
		this.setDao(adSprDtlMapper);
		this.adSprDtlMapper = adSprDtlMapper;
	}
	public void addModel(Integer adSprId, Integer combineOrderId, Integer memberId) {
		DouyinSprDtl adSprDtl = new DouyinSprDtl();
		adSprDtl.setSprPlanId(adSprId);
		adSprDtl.setMemberId(memberId);
		adSprDtl.setCombineOrderId(combineOrderId);
		adSprDtl.setCreateDate(new Date());
		adSprDtl.setDelFlag("0");
		insertSelective(adSprDtl);
	}
	
	
}
