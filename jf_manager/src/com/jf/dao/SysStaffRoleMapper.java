package com.jf.dao;

import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysStaffRoleMapper extends BaseDao<SysStaffRole, SysStaffRoleExample> {
    int countByExample(SysStaffRoleExample example);

    int deleteByExample(SysStaffRoleExample example);

    int deleteByPrimaryKey(Integer staffRoleId);

    int insert(SysStaffRole record);

    int insertSelective(SysStaffRole record);

    List<SysStaffRole> selectByExample(SysStaffRoleExample example);

    SysStaffRole selectByPrimaryKey(Integer staffRoleId);

    int updateByExampleSelective(@Param("record") SysStaffRole record, @Param("example") SysStaffRoleExample example);

    int updateByExample(@Param("record") SysStaffRole record, @Param("example") SysStaffRoleExample example);

    int updateByPrimaryKeySelective(SysStaffRole record);

    int updateByPrimaryKey(SysStaffRole record);
}