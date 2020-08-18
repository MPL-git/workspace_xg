package com.jf.dao;

import com.jf.entity.NodeApprover;
import com.jf.entity.NodeApproverExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface NodeApproverMapper extends BaseDao<NodeApprover, NodeApproverExample> {
    int countByExample(NodeApproverExample example);

    int deleteByExample(NodeApproverExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NodeApprover record);

    int insertSelective(NodeApprover record);

    List<NodeApprover> selectByExample(NodeApproverExample example);

    NodeApprover selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NodeApprover record, @Param("example") NodeApproverExample example);

    int updateByExample(@Param("record") NodeApprover record, @Param("example") NodeApproverExample example);

    int updateByPrimaryKeySelective(NodeApprover record);

    int updateByPrimaryKey(NodeApprover record);
}