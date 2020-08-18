package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SpreadActivityGroupSetDtl;
import com.jf.entity.SpreadActivityGroupSetDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
