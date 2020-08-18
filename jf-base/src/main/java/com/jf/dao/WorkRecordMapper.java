package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.WorkRecord;
import com.jf.entity.WorkRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRecordMapper extends BaseDao<WorkRecord, WorkRecordExample> {
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

    List<WorkRecord> selectByExampleWithBLOBs(WorkRecordExample example);
    int updateByPrimaryKeyWithBLOBs(WorkRecord record);
    int updateByExampleWithBLOBs(@Param("record") WorkRecord record, @Param("example") WorkRecordExample example);
}
