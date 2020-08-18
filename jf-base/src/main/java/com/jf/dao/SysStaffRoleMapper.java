package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;

@Repository
public interface SysStaffRoleMapper extends BaseDao<SysStaffRole, SysStaffRoleExample> {
    int countByExample(SysStaffRoleExample example);

    int deleteByExample(SysStaffRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysStaffRole record);

    int insertSelective(SysStaffRole record);

    List<SysStaffRole> selectByExample(SysStaffRoleExample example);

    SysStaffRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysStaffRole record, @Param("example") SysStaffRoleExample example);

    int updateByExample(@Param("record") SysStaffRole record, @Param("example") SysStaffRoleExample example);

    int updateByPrimaryKeySelective(SysStaffRole record);

    int updateByPrimaryKey(SysStaffRole record);
}
