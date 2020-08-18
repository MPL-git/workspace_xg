package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AppLoginDistinctLog;
import com.jf.entity.AppLoginDistinctLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppLoginDistinctLogMapper extends BaseDao<AppLoginDistinctLog, AppLoginDistinctLogExample> {
    int countByExample(AppLoginDistinctLogExample example);

    int deleteByExample(AppLoginDistinctLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppLoginDistinctLog record);

    int insertSelective(AppLoginDistinctLog record);

    List<AppLoginDistinctLog> selectByExample(AppLoginDistinctLogExample example);

    AppLoginDistinctLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppLoginDistinctLog record, @Param("example") AppLoginDistinctLogExample example);

    int updateByExample(@Param("record") AppLoginDistinctLog record, @Param("example") AppLoginDistinctLogExample example);

    int updateByPrimaryKeySelective(AppLoginDistinctLog record);

    int updateByPrimaryKey(AppLoginDistinctLog record);
}