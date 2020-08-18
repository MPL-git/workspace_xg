package com.jf.dao;

import com.jf.entity.StaffMchtcontactPermission;
import com.jf.entity.StaffMchtcontactPermissionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface StaffMchtcontactPermissionMapper extends BaseDao<StaffMchtcontactPermission, StaffMchtcontactPermissionExample>{
    int countByExample(StaffMchtcontactPermissionExample example);

    int deleteByExample(StaffMchtcontactPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffMchtcontactPermission record);

    int insertSelective(StaffMchtcontactPermission record);

    List<StaffMchtcontactPermission> selectByExample(StaffMchtcontactPermissionExample example);

    StaffMchtcontactPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StaffMchtcontactPermission record, @Param("example") StaffMchtcontactPermissionExample example);

    int updateByExample(@Param("record") StaffMchtcontactPermission record, @Param("example") StaffMchtcontactPermissionExample example);

    int updateByPrimaryKeySelective(StaffMchtcontactPermission record);

    int updateByPrimaryKey(StaffMchtcontactPermission record);
}