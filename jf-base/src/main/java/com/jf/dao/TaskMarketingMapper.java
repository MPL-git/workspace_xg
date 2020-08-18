package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.TaskMarketing;
import com.jf.entity.TaskMarketingExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMarketingMapper extends BaseDao<TaskMarketing, TaskMarketingExample> {
    int countByExample(TaskMarketingExample example);

    int deleteByExample(TaskMarketingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskMarketing record);

    int insertSelective(TaskMarketing record);

    List<TaskMarketing> selectByExample(TaskMarketingExample example);

    TaskMarketing selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskMarketing record, @Param("example") TaskMarketingExample example);

    int updateByExample(@Param("record") TaskMarketing record, @Param("example") TaskMarketingExample example);

    int updateByPrimaryKeySelective(TaskMarketing record);

    int updateByPrimaryKey(TaskMarketing record);
}
