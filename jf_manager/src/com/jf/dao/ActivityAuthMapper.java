package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.ActivityAuth;
import com.jf.entity.ActivityAuthExample;
@Repository
public interface ActivityAuthMapper extends BaseDao<ActivityAuth, ActivityAuthExample>{
    int countByExample(ActivityAuthExample example);

    int deleteByExample(ActivityAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAuth record);

    int insertSelective(ActivityAuth record);

    List<ActivityAuth> selectByExample(ActivityAuthExample example);

    ActivityAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAuth record, @Param("example") ActivityAuthExample example);

    int updateByExample(@Param("record") ActivityAuth record, @Param("example") ActivityAuthExample example);

    int updateByPrimaryKeySelective(ActivityAuth record);

    int updateByPrimaryKey(ActivityAuth record);
}