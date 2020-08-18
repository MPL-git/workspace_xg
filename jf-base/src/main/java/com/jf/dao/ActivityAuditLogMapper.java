package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityAuditLog;
import com.jf.entity.ActivityAuditLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityAuditLogMapper extends BaseDao<ActivityAuditLog, ActivityAuditLogExample> {
    int countByExample(ActivityAuditLogExample example);

    int deleteByExample(ActivityAuditLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAuditLog record);

    int insertSelective(ActivityAuditLog record);

    List<ActivityAuditLog> selectByExample(ActivityAuditLogExample example);

    ActivityAuditLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAuditLog record, @Param("example") ActivityAuditLogExample example);

    int updateByExample(@Param("record") ActivityAuditLog record, @Param("example") ActivityAuditLogExample example);

    int updateByPrimaryKeySelective(ActivityAuditLog record);

    int updateByPrimaryKey(ActivityAuditLog record);
}
