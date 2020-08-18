package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.SysStaffRoleCustom;
import com.jf.entity.SysStaffRoleCustomExample;

@Repository
public interface SysStaffRoleCustomMapper {
    int countByCustomExample(SysStaffRoleCustomExample example);

    List<SysStaffRoleCustom> selectByCustomExample(SysStaffRoleCustomExample example);

    SysStaffRoleCustom selectByPrimaryKeyCustom(Integer staffRoleId);
    
    /**
     * 通过roleId 获取系统用户列表
     * 
     * @param roleId
     * @return
     */
    List<SysStaffRoleCustom> selectStaffRoleList(Integer roleId);

}