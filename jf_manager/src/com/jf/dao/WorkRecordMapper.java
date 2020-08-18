package com.jf.dao;

import com.jf.entity.WorkRecord;
import com.jf.entity.WorkRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface WorkRecordMapper extends BaseDao<WorkRecord, WorkRecordExample>{
    int countByExample(WorkRecordExample example);

    int deleteByExample(WorkRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WorkRecord record);

    int insertSelective(WorkRecord record);

    List<WorkRecord> selectByExample(WorkRecordExample example);

    WorkRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WorkRecord record, @Param("example") WorkRecordExample example);

    int updateByExample(@Param("record") WorkRecord record, @Param("example") WorkRecordExample example);

    int updateByPrimaryKeySelective(WorkRecord record);

    int updateByPrimaryKey(WorkRecord record);
}