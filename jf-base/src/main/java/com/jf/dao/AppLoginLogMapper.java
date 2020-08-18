package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.AppLoginLog;
import com.jf.entity.AppLoginLogExample;

@Repository
public interface AppLoginLogMapper extends BaseDao<AppLoginLog, AppLoginLogExample> {
    int countByExample(AppLoginLogExample example);

    int deleteByExample(AppLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppLoginLog record);

    int insertSelective(AppLoginLog record);

    List<AppLoginLog> selectByExample(AppLoginLogExample example);

    AppLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppLoginLog record, @Param("example") AppLoginLogExample example);

    int updateByExample(@Param("record") AppLoginLog record, @Param("example") AppLoginLogExample example);

    int updateByPrimaryKeySelective(AppLoginLog record);

    int updateByPrimaryKey(AppLoginLog record);
}
