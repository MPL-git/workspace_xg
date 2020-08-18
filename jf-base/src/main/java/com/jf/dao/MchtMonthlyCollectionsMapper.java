package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtMonthlyCollections;
import com.jf.entity.MchtMonthlyCollectionsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtMonthlyCollectionsMapper extends BaseDao<MchtMonthlyCollections, MchtMonthlyCollectionsExample> {
    int countByExample(MchtMonthlyCollectionsExample example);

    int deleteByExample(MchtMonthlyCollectionsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtMonthlyCollections record);

    int insertSelective(MchtMonthlyCollections record);

    List<MchtMonthlyCollections> selectByExample(MchtMonthlyCollectionsExample example);

    MchtMonthlyCollections selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtMonthlyCollections record, @Param("example") MchtMonthlyCollectionsExample example);

    int updateByExample(@Param("record") MchtMonthlyCollections record, @Param("example") MchtMonthlyCollectionsExample example);

    int updateByPrimaryKeySelective(MchtMonthlyCollections record);

    int updateByPrimaryKey(MchtMonthlyCollections record);
}
