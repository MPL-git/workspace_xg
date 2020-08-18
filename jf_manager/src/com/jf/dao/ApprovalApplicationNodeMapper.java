package com.jf.dao;

import com.jf.entity.ApprovalApplicationNode;
import com.jf.entity.ApprovalApplicationNodeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ApprovalApplicationNodeMapper extends BaseDao<ApprovalApplicationNode, ApprovalApplicationNodeExample> {
    int countByExample(ApprovalApplicationNodeExample example);

    int deleteByExample(ApprovalApplicationNodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApprovalApplicationNode record);

    int insertSelective(ApprovalApplicationNode record);

    List<ApprovalApplicationNode> selectByExampleWithBLOBs(ApprovalApplicationNodeExample example);

    List<ApprovalApplicationNode> selectByExample(ApprovalApplicationNodeExample example);

    ApprovalApplicationNode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApprovalApplicationNode record, @Param("example") ApprovalApplicationNodeExample example);

    int updateByExampleWithBLOBs(@Param("record") ApprovalApplicationNode record, @Param("example") ApprovalApplicationNodeExample example);

    int updateByExample(@Param("record") ApprovalApplicationNode record, @Param("example") ApprovalApplicationNodeExample example);

    int updateByPrimaryKeySelective(ApprovalApplicationNode record);

    int updateByPrimaryKeyWithBLOBs(ApprovalApplicationNode record);

    int updateByPrimaryKey(ApprovalApplicationNode record);
}