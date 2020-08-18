package com.jf.dao;

import com.jf.entity.ApprovalApplication;
import com.jf.entity.ApprovalApplicationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ApprovalApplicationMapper extends BaseDao<ApprovalApplication, ApprovalApplicationExample> {
    int countByExample(ApprovalApplicationExample example);

    int deleteByExample(ApprovalApplicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ApprovalApplication record);

    int insertSelective(ApprovalApplication record);

    List<ApprovalApplication> selectByExampleWithBLOBs(ApprovalApplicationExample example);

    List<ApprovalApplication> selectByExample(ApprovalApplicationExample example);

    ApprovalApplication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ApprovalApplication record, @Param("example") ApprovalApplicationExample example);

    int updateByExampleWithBLOBs(@Param("record") ApprovalApplication record, @Param("example") ApprovalApplicationExample example);

    int updateByExample(@Param("record") ApprovalApplication record, @Param("example") ApprovalApplicationExample example);

    int updateByPrimaryKeySelective(ApprovalApplication record);

    int updateByPrimaryKeyWithBLOBs(ApprovalApplication record);

    int updateByPrimaryKey(ApprovalApplication record);
}