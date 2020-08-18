package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtRemarksLog;
import com.jf.entity.MchtRemarksLogExample;
@Repository
public interface MchtRemarksLogMapper extends BaseDao<MchtRemarksLog, MchtRemarksLogExample>{
    int countByExample(MchtRemarksLogExample example);

    int deleteByExample(MchtRemarksLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtRemarksLog record);

    int insertSelective(MchtRemarksLog record);

    List<MchtRemarksLog> selectByExample(MchtRemarksLogExample example);

    MchtRemarksLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtRemarksLog record, @Param("example") MchtRemarksLogExample example);

    int updateByExample(@Param("record") MchtRemarksLog record, @Param("example") MchtRemarksLogExample example);

    int updateByPrimaryKeySelective(MchtRemarksLog record);

    int updateByPrimaryKey(MchtRemarksLog record);
}