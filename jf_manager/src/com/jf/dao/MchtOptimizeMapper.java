package com.jf.dao;

import com.jf.entity.MchtOptimize;
import com.jf.entity.MchtOptimizeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MchtOptimizeMapper extends BaseDao<MchtOptimize, MchtOptimizeExample> {
    int countByExample(MchtOptimizeExample example);

    int deleteByExample(MchtOptimizeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtOptimize record);

    int insertSelective(MchtOptimize record);

    List<MchtOptimize> selectByExample(MchtOptimizeExample example);

    MchtOptimize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtOptimize record, @Param("example") MchtOptimizeExample example);

    int updateByExample(@Param("record") MchtOptimize record, @Param("example") MchtOptimizeExample example);

    int updateByPrimaryKeySelective(MchtOptimize record);

    int updateByPrimaryKey(MchtOptimize record);
}