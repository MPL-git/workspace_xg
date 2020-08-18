package com.jf.dao;

import com.jf.entity.ProcedureNode;
import com.jf.entity.ProcedureNodeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProcedureNodeMapper extends BaseDao<ProcedureNode, ProcedureNodeExample> {
    int countByExample(ProcedureNodeExample example);

    int deleteByExample(ProcedureNodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProcedureNode record);

    int insertSelective(ProcedureNode record);

    List<ProcedureNode> selectByExample(ProcedureNodeExample example);

    ProcedureNode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProcedureNode record, @Param("example") ProcedureNodeExample example);

    int updateByExample(@Param("record") ProcedureNode record, @Param("example") ProcedureNodeExample example);

    int updateByPrimaryKeySelective(ProcedureNode record);

    int updateByPrimaryKey(ProcedureNode record);
}