package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SingleProductActivityAuditLogCustom;
import com.jf.entity.SingleProductActivityAuditLogExample;

/**
 * 
 * @ClassName SingleProductActivityAuditLogCustomMapper
 * @Description TODO(单品活动审核流水Custom)
 * @author pengl
 * @date 2017年9月30日 下午4:08:12
 */
@Repository
public interface SingleProductActivityAuditLogCustomMapper {
	
	public List<SingleProductActivityAuditLogCustom> selectByCustomExample(SingleProductActivityAuditLogExample example);
	
	public Integer countByCustomExample(SingleProductActivityAuditLogExample example);
	
}