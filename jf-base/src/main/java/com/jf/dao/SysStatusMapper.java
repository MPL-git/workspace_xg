package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SysStatus;
import com.jf.entity.SysStatusExample;

@Repository
public interface SysStatusMapper extends BaseDao<SysStatus, SysStatusExample> {
    int countByExample(SysStatusExample example);

    int deleteByExample(SysStatusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysStatus record);

    int insertSelective(SysStatus record);

    List<SysStatus> selectByExample(SysStatusExample example);

    SysStatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysStatus record, @Param("example") SysStatusExample example);

    int updateByExample(@Param("record") SysStatus record, @Param("example") SysStatusExample example);

    int updateByPrimaryKeySelective(SysStatus record);

    int updateByPrimaryKey(SysStatus record);
}
