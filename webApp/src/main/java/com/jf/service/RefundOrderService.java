package com.jf.service;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.RefundOrderMapper;
import com.jf.entity.RefundOrder;
import com.jf.entity.RefundOrderExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月10日 下午2:45:55 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class RefundOrderService extends BaseService<RefundOrder, RefundOrderExample> {
	
	@Resource
	private RefundOrderMapper refundOrderMapper;

	@Resource
	public void setRefundOrderMapper(RefundOrderMapper refundOrderMapper) {
		this.setDao(refundOrderMapper);
		this.refundOrderMapper = refundOrderMapper;
	}
}
