package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.IntegralDtl;
import com.jf.entity.IntegralDtlExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegralDtlMapper extends BaseDao<IntegralDtl, IntegralDtlExample> {
    int countByExample(IntegralDtlExample example);

    int deleteByExample(IntegralDtlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IntegralDtl record);

    int insertSelective(IntegralDtl record);

    List<IntegralDtl> selectByExample(IntegralDtlExample example);

    IntegralDtl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IntegralDtl record, @Param("example") IntegralDtlExample example);

    int updateByExample(@Param("record") IntegralDtl record, @Param("example") IntegralDtlExample example);

    int updateByPrimaryKeySelective(IntegralDtl record);

    int updateByPrimaryKey(IntegralDtl record);
}
