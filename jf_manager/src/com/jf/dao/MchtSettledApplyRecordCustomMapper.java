package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSettledApplyRecordCustom;
import com.jf.entity.MchtSettledApplyRecordExample;
@Repository
public interface MchtSettledApplyRecordCustomMapper{
    int countByExample(MchtSettledApplyRecordExample example);
    List<MchtSettledApplyRecordCustom> selectByExample(MchtSettledApplyRecordExample example);
    MchtSettledApplyRecordCustom selectByPrimaryKey(Integer id);
}