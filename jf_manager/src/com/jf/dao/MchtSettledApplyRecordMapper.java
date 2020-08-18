package com.jf.dao;

import com.jf.entity.MchtSettledApplyRecord;
import com.jf.entity.MchtSettledApplyRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtSettledApplyRecordMapper extends BaseDao<MchtSettledApplyRecord, MchtSettledApplyRecordExample>{
    int countByExample(MchtSettledApplyRecordExample example);

    int deleteByExample(MchtSettledApplyRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtSettledApplyRecord record);

    int insertSelective(MchtSettledApplyRecord record);

    List<MchtSettledApplyRecord> selectByExample(MchtSettledApplyRecordExample example);

    MchtSettledApplyRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtSettledApplyRecord record, @Param("example") MchtSettledApplyRecordExample example);

    int updateByExample(@Param("record") MchtSettledApplyRecord record, @Param("example") MchtSettledApplyRecordExample example);

    int updateByPrimaryKeySelective(MchtSettledApplyRecord record);

    int updateByPrimaryKey(MchtSettledApplyRecord record);
}