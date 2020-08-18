package com.jf.dao;

import com.jf.entity.SysRoleInfo;
import com.jf.entity.SysRoleInfoExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface SysRoleInfoMapper extends BaseDao<SysRoleInfo, SysRoleInfoExample> {
    int countByExample(SysRoleInfoExample example);

    int deleteByExample(SysRoleInfoExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRoleInfo record);

    int insertSelective(SysRoleInfo record);

    List<SysRoleInfo> selectByExample(SysRoleInfoExample example);

    SysRoleInfo selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") SysRoleInfo record, @Param("example") SysRoleInfoExample example);

    int updateByExample(@Param("record") SysRoleInfo record, @Param("example") SysRoleInfoExample example);

    int updateByPrimaryKeySelective(SysRoleInfo record);

    int updateByPrimaryKey(SysRoleInfo record);
}