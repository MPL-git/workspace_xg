package com.jf.dao;

import com.jf.entity.SingleProductActivityAuditLogExt;
import com.jf.entity.SingleProductActivityAuditLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleProductActivityAuditLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    SingleProductActivityAuditLogExt findById(int id);

    SingleProductActivityAuditLogExt find(SingleProductActivityAuditLogExtExample example);

    List<SingleProductActivityAuditLogExt> list(SingleProductActivityAuditLogExtExample example);

    List<Integer> listId(SingleProductActivityAuditLogExtExample example);

    int count(SingleProductActivityAuditLogExtExample example);

    long sum(@Param("field") String field, @Param("example") SingleProductActivityAuditLogExtExample example);

    int max(@Param("field") String field, @Param("example") SingleProductActivityAuditLogExtExample example);

    int min(@Param("field") String field, @Param("example") SingleProductActivityAuditLogExtExample example);

}

