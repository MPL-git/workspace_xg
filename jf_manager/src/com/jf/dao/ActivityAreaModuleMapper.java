package com.jf.dao;

import com.jf.entity.ActivityAreaModule;
import com.jf.entity.ActivityAreaModuleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ActivityAreaModuleMapper extends BaseDao<ActivityAreaModule,ActivityAreaModuleExample> {
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