package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityProductAuditLog;
import com.jf.entity.ActivityProductAuditLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityProductAuditLogMapper extends BaseDao<ActivityProductAuditLog, ActivityProductAuditLogExample> {
    int countByExample(ActivityProductAuditLogExample example);

    int deleteByExample(ActivityProductAuditLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityProductAuditLog record);

    int insertSelective(ActivityProductAuditLog record);

    List<ActivityProductAuditLog> selectByExample(ActivityProductAuditLogExample example);

    ActivityProductAuditLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityProductAuditLog record, @Param("example") ActivityProductAuditLogExample example);

    int updateByExample(@Param("record") ActivityProductAuditLog record, @Param("example") ActivityProductAuditLogExample example);

    int updateByPrimaryKeySelective(ActivityProductAuditLog record);

    int updateByPrimaryKey(ActivityProductAuditLog record);
}
