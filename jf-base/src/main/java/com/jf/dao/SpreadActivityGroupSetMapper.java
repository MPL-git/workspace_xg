package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.SpreadActivityGroupSet;
import com.jf.entity.SpreadActivityGroupSetExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpreadActivityGroupSetMapper extends BaseDao<SpreadActivityGroupSet, SpreadActivityGroupSetExample> {
    int countByExample(SpreadActivityGroupSetExample example);

    int deleteByExample(SpreadActivityGroupSetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpreadActivityGroupSet record);

    int insertSelective(SpreadActivityGroupSet record);

    List<SpreadActivityGroupSet> selectByExample(SpreadActivityGroupSetExample example);

    SpreadActivityGroupSet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpreadActivityGroupSet record, @Param("example") SpreadActivityGroupSetExample example);

    int updateByExample(@Param("record") SpreadActivityGroupSet record, @Param("example") SpreadActivityGroupSetExample example);

    int updateByPrimaryKeySelective(SpreadActivityGroupSet record);

    int updateByPrimaryKey(SpreadActivityGroupSet record);
}
