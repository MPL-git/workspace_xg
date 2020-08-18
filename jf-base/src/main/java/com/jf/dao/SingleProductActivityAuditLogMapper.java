package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SingleProductActivityAuditLog;
import com.jf.entity.SingleProductActivityAuditLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleProductActivityAuditLogMapper extends BaseDao<SingleProductActivityAuditLog, SingleProductActivityAuditLogExample> {
    int countByExample(SingleProductActivityAuditLogExample example);

    int deleteByExample(SingleProductActivityAuditLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SingleProductActivityAuditLog record);

    int insertSelective(SingleProductActivityAuditLog record);

    List<SingleProductActivityAuditLog> selectByExample(SingleProductActivityAuditLogExample example);

    SingleProductActivityAuditLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SingleProductActivityAuditLog record, @Param("example") SingleProductActivityAuditLogExample example);

    int updateByExample(@Param("record") SingleProductActivityAuditLog record, @Param("example") SingleProductActivityAuditLogExample example);

    int updateByPrimaryKeySelective(SingleProductActivityAuditLog record);

    int updateByPrimaryKey(SingleProductActivityAuditLog record);
}
