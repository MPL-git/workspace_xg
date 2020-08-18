package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.AppealLog;
import com.jf.entity.AppealLogExample;
@Repository
public interface AppealLogMapper extends BaseDao<AppealLog, AppealLogExample>{
    int countByExample(AppealLogExample example);

    int deleteByExample(AppealLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppealLog record);

    int insertSelective(AppealLog record);

    List<AppealLog> selectByExample(AppealLogExample example);

    AppealLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppealLog record, @Param("example") AppealLogExample example);

    int updateByExample(@Param("record") AppealLog record, @Param("example") AppealLogExample example);

    int updateByPrimaryKeySelective(AppealLog record);

    int updateByPrimaryKey(AppealLog record);
}