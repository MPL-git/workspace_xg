package com.jf.dao;

import com.jf.entity.ProductAuditLogExt;
import com.jf.entity.ProductAuditLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductAuditLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ProductAuditLogExt findById(int id);

    ProductAuditLogExt find(ProductAuditLogExtExample example);

    List<ProductAuditLogExt> list(ProductAuditLogExtExample example);

    List<Integer> listId(ProductAuditLogExtExample example);

    int count(ProductAuditLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ProductAuditLogExtExample example);

    int max(@Param("field") String field, @Param("example") ProductAuditLogExtExample example);

    int min(@Param("field") String field, @Param("example") ProductAuditLogExtExample example);

}

