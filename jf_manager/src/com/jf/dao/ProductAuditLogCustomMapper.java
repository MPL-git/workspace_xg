package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.Product;
import com.jf.entity.ProductAuditLog;
import com.jf.entity.ProductAuditLogCustom;
import com.jf.entity.ProductAuditLogCustomExample;
@Repository
public interface ProductAuditLogCustomMapper {
	
	public List<ProductAuditLogCustom> selectByCustomExample(ProductAuditLogCustomExample example);
	
	public Integer countByCustomExample(ProductAuditLogCustomExample example);
	
	//批量插入审核流水表日志
	public void insertProductAuditLogList(List<ProductAuditLog> productAuditLogs);
}