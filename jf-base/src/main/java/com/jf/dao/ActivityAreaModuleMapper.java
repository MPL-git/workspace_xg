package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.common.base.BaseDao;
import com.jf.entity.ActivityAreaModule;
import com.jf.entity.ActivityAreaModuleExample;
@Repository
public interface ActivityAreaModuleMapper extends BaseDao<ActivityAreaModule,ActivityAreaModuleExample>{
    int countByExample(ActivityAreaModuleExample example);

    int deleteByExample(ActivityAreaModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActivityAreaModule record);

    int insertSelective(ActivityAreaModule record);

    List<ActivityAreaModule> selectByExample(ActivityAreaModuleExample example);

    ActivityAreaModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActivityAreaModule record, @Param("example") ActivityAreaModuleExample example);

    int updateByExample(@Param("record") ActivityAreaModule record, @Param("example") ActivityAreaModuleExample example);

    int updateByPrimaryKeySelective(ActivityAreaModule record);

    int updateByPrimaryKey(ActivityAreaModule record);
}