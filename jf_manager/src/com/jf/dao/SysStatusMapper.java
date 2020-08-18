package com.jf.dao;

import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SysStatusMapper extends BaseDao<SysStatus, SysStatusExample> {
    int countByExample(SysStatusExample example);

    int deleteByExample(SysStatusExample example);

    int insert(SysStatus record);

    int insertSelective(SysStatus record);

    List<SysStatus> selectByExample(SysStatusExample example);

    int updateByExampleSelective(@Param("record") SysStatus record, @Param("example") SysStatusExample example);

    int updateByExample(@Param("record") SysStatus record, @Param("example") SysStatusExample example);
}