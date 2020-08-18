package com.jf.dao;

import com.jf.entity.ActivityAuditLogExt;
import com.jf.entity.ActivityAuditLogExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAuditLogExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    ActivityAuditLogExt findById(int id);

    ActivityAuditLogExt find(ActivityAuditLogExtExample example);

    List<ActivityAuditLogExt> list(ActivityAuditLogExtExample example);

    List<Integer> listId(ActivityAuditLogExtExample example);

    int count(ActivityAuditLogExtExample example);

    long sum(@Param("field") String field, @Param("example") ActivityAuditLogExtExample example);

    int max(@Param("field") String field, @Param("example") ActivityAuditLogExtExample example);

    int min(@Param("field") String field, @Param("example") ActivityAuditLogExtExample example);

}

