package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SysAppLoginLog;
import com.jf.entity.SysAppLoginLogExample;
@Repository
public interface SysAppLoginLogMapper extends BaseDao<SysAppLoginLog, SysAppLoginLogExample> {
    int countByExample(SysAppLoginLogExample example);

    int deleteByExample(SysAppLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysAppLoginLog record);

    int insertSelective(SysAppLoginLog record);

    List<SysAppLoginLog> selectByExample(SysAppLoginLogExample example);

    SysAppLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysAppLoginLog record, @Param("example") SysAppLoginLogExample example);

    int updateByExample(@Param("record") SysAppLoginLog record, @Param("example") SysAppLoginLogExample example);

    int updateByPrimaryKeySelective(SysAppLoginLog record);

    int updateByPrimaryKey(SysAppLoginLog record);
}