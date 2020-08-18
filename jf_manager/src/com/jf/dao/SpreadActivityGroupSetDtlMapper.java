package com.jf.dao;

import com.jf.entity.SpreadActivityGroupSetDtl;
import com.jf.entity.SpreadActivityGroupSetDtlExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpreadActivityGroupSetDtlMapper extends BaseDao<SpreadActivityGroupSetDtl, SpreadActivityGroupSetDtlExample> {
    int countByExample(SpreadActivityGroupSetDtlExample example);

    int deleteByExample(SpreadActivityGroupSetDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpreadActivityGroupSetDtl record);

    int insertSelective(SpreadActivityGroupSetDtl record);

    List<SpreadActivityGroupSetDtl> selectByExample(SpreadActivityGroupSetDtlExample example);

    SpreadActivityGroupSetDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpreadActivityGroupSetDtl record, @Param("example") SpreadActivityGroupSetDtlExample example);

    int updateByExample(@Param("record") SpreadActivityGroupSetDtl record, @Param("example") SpreadActivityGroupSetDtlExample example);

    int updateByPrimaryKeySelective(SpreadActivityGroupSetDtl record);

    int updateByPrimaryKey(SpreadActivityGroupSetDtl record);
}