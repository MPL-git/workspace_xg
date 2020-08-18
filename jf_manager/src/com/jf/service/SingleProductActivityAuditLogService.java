package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SingleProductActivityAuditLogCustomMapper;
import com.jf.dao.SingleProductActivityAuditLogMapper;
import com.jf.entity.SingleProductActivityAuditLog;
import com.jf.entity.SingleProductActivityAuditLogCustom;
import com.jf.entity.SingleProductActivityAuditLogExample;
import com.jf.entity.SingleProductActivityCustom;
import com.jf.entity.SingleProductActivityCustomExample;

/**
 * 
 * @ClassName SingleProductActivityAuditLogService
 * @Description TODO(单品活动审核流水)
 * @author pengl
 * @date 2017年9月29日 上午10:56:50
 */
@Service
@Transactional
public class SingleProductActivityAuditLogService extends BaseService<SingleProductActivityAuditLog, SingleProductActivityAuditLogExample> {

	@Autowired
	private SingleProductActivityAuditLogMapper singleProductActivityAuditLogMapper;
	
	@Autowired
	private SingleProductActivityAuditLogCustomMapper singleProductActivityAuditLogCustomMapper;
	
	@Autowired
	public void setSingleProductActivityAuditLogMapper(SingleProductActivityAuditLogMapper singleProductActivityAuditLogMapper) {
		super.setDao(singleProductActivityAuditLogMapper);
		this.singleProductActivityAuditLogMapper = singleProductActivityAuditLogMapper;
	}
	
	public List<SingleProductActivityAuditLogCustom> selectByCustomExample(SingleProductActivityAuditLogExample example) {
		return singleProductActivityAuditLogCustomMapper.selectByCustomExample(example);
	} 
	
	public Integer countByCustomExample(SingleProductActivityAuditLogExample example) {
		return singleProductActivityAuditLogCustomMapper.countByCustomExample(example);
	}
	
}
