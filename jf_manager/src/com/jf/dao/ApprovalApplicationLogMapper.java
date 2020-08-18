package com.jf.dao;

import com.jf.entity.ApprovalApplicationLog;
import com.jf.entity.ApprovalApplicationLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ApprovalApplicationLogMapper extends BaseDao<ApprovalApplicationLog, ApprovalApplicationLogExample>{
    int countByExample(ApprovalApplicationLogExample example);

    int deleteByExample(ApprovalApplicationLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApprovalApplicationLog record);

    int insertSelective(ApprovalApplicationLog record);

    List<ApprovalApplicationLog> selectByExampleWithBLOBs(ApprovalApplicationLogExample example);

    List<ApprovalApplicationLog> selectByExample(ApprovalApplicationLogExample example);

    ApprovalApplicationLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApprovalApplicationLog record, @Param("example") ApprovalApplicationLogExample example);

    int updateByExampleWithBLOBs(@Param("record") ApprovalApplicationLog record, @Param("example") ApprovalApplicationLogExample example);

    int updateByExample(@Param("record") ApprovalApplicationLog record, @Param("example") ApprovalApplicationLogExample example);

    int updateByPrimaryKeySelective(ApprovalApplicationLog record);

    int updateByPrimaryKeyWithBLOBs(ApprovalApplicationLog record);

    int updateByPrimaryKey(ApprovalApplicationLog record);
}