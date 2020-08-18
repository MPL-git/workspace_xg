package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WorkHistory;
import com.jf.entity.WorkHistoryExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkHistoryMapper extends BaseDao<WorkHistory, WorkHistoryExample> {
    int countByExample(WorkHistoryExample example);

    int deleteByExample(WorkHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkHistory record);

    int insertSelective(WorkHistory record);

    List<WorkHistory> selectByExample(WorkHistoryExample example);

    WorkHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkHistory record, @Param("example") WorkHistoryExample example);

    int updateByExample(@Param("record") WorkHistory record, @Param("example") WorkHistoryExample example);

    int updateByPrimaryKeySelective(WorkHistory record);

    int updateByPrimaryKey(WorkHistory record);

    List<WorkHistory> selectByExampleWithBLOBs(WorkHistoryExample example);
    int updateByPrimaryKeyWithBLOBs(WorkHistory record);
    int updateByExampleWithBLOBs(@Param("record") WorkHistory record, @Param("example") WorkHistoryExample example);
}
