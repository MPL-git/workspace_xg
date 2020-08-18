package com.jf.dao;

import com.jf.entity.Procedure;
import com.jf.entity.ProcedureExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProcedureMapper extends BaseDao<Procedure, ProcedureExample> {
    int countByExample(ProcedureExample example);

    int deleteByExample(ProcedureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Procedure record);

    int insertSelective(Procedure record);

    List<Procedure> selectByExample(ProcedureExample example);

    Procedure selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Procedure record, @Param("example") ProcedureExample example);

    int updateByExample(@Param("record") Procedure record, @Param("example") ProcedureExample example);

    int updateByPrimaryKeySelective(Procedure record);

    int updateByPrimaryKey(Procedure record);
}