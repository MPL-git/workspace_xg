package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.IntegralDtlMapper;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IntegralDtlService extends BaseService<IntegralDtl,IntegralDtlExample> {
	@Autowired
	private IntegralDtlMapper integralDtlMapper;
	@Autowired
	public void setIntegralDtlMapper(IntegralDtlMapper integralDtlMapper) {
		super.setDao(integralDtlMapper);
		this.integralDtlMapper = integralDtlMapper;
	}

}
