package com.jf.dao;

import com.jf.entity.TypeColumnPvStatistical;
import com.jf.entity.TypeColumnPvStatisticalCustom;
import com.jf.entity.TypeColumnPvStatisticalExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeColumnPvStatisticalMapper extends BaseDao<TypeColumnPvStatistical,TypeColumnPvStatisticalExample>{
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