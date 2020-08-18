package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.GrowthDtl;
import com.jf.entity.GrowthDtlExample;
@Repository
public interface GrowthDtlMapper extends BaseDao<GrowthDtl, GrowthDtlExample>{
    int countByExample(GrowthDtlExample example);

    int deleteByExample(GrowthDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GrowthDtl record);

    int insertSelective(GrowthDtl record);

    List<GrowthDtl> selectByExample(GrowthDtlExample example);

    GrowthDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GrowthDtl record, @Param("example") GrowthDtlExample example);

    int updateByExample(@Param("record") GrowthDtl record, @Param("example") GrowthDtlExample example);

    int updateByPrimaryKeySelective(GrowthDtl record);

    int updateByPrimaryKey(GrowthDtl record);
}