package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.TypeColumnPvStatistical;
import com.jf.entity.TypeColumnPvStatisticalExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeColumnPvStatisticalMapper extends BaseDao<TypeColumnPvStatistical, TypeColumnPvStatisticalExample> {
    int countByExample(TypeColumnPvStatisticalExample example);

    int deleteByExample(TypeColumnPvStatisticalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TypeColumnPvStatistical record);

    int insertSelective(TypeColumnPvStatistical record);

    List<TypeColumnPvStatistical> selectByExample(TypeColumnPvStatisticalExample example);

    TypeColumnPvStatistical selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TypeColumnPvStatistical record, @Param("example") TypeColumnPvStatisticalExample example);

    int updateByExample(@Param("record") TypeColumnPvStatistical record, @Param("example") TypeColumnPvStatisticalExample example);

    int updateByPrimaryKeySelective(TypeColumnPvStatistical record);

    int updateByPrimaryKey(TypeColumnPvStatistical record);
}