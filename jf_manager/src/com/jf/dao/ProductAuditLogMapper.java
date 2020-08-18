package com.jf.dao;

import com.jf.entity.ProductAuditLog;
import com.jf.entity.ProductAuditLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductAuditLogMapper extends BaseDao<ProductAuditLog, ProductAuditLogExample> {
    int countByExample(ProductAuditLogExample example);

    int deleteByExample(ProductAuditLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductAuditLog record);

    int insertSelective(ProductAuditLog record);

    List<ProductAuditLog> selectByExample(ProductAuditLogExample example);

    ProductAuditLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductAuditLog record, @Param("example") ProductAuditLogExample example);

    int updateByExample(@Param("record") ProductAuditLog record, @Param("example") ProductAuditLogExample example);

    int updateByPrimaryKeySelective(ProductAuditLog record);

    int updateByPrimaryKey(ProductAuditLog record);
}