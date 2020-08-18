package com.jf.dao;

import com.jf.entity.ActivityProductAuditLogExt;
import com.jf.entity.ActivityProductAuditLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityProductAuditLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityProductAuditLogExt findById(int id);

    ActivityProductAuditLogExt find(ActivityProductAuditLogExtExample example);

    List<ActivityProductAuditLogExt> list(ActivityProductAuditLogExtExample example);

    List<Integer> listId(ActivityProductAuditLogExtExample example);

    int count(ActivityProductAuditLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityProductAuditLogExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityProductAuditLogExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityProductAuditLogExtExample example);

}

