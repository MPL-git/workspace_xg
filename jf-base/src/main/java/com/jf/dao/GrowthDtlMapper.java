package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.GrowthDtl;
import com.jf.entity.GrowthDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowthDtlMapper extends BaseDao<GrowthDtl, GrowthDtlExample> {
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
