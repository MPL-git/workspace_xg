package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.StaffMchtContactPermission;
import com.jf.entity.StaffMchtContactPermissionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffMchtContactPermissionMapper extends BaseDao<StaffMchtContactPermission, StaffMchtContactPermissionExample> {
    int countByExample(StaffMchtContactPermissionExample example);

    int deleteByExample(StaffMchtContactPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StaffMchtContactPermission record);

    int insertSelective(StaffMchtContactPermission record);

    List<StaffMchtContactPermission> selectByExample(StaffMchtContactPermissionExample example);

    StaffMchtContactPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StaffMchtContactPermission record, @Param("example") StaffMchtContactPermissionExample example);

    int updateByExample(@Param("record") StaffMchtContactPermission record, @Param("example") StaffMchtContactPermissionExample example);

    int updateByPrimaryKeySelective(StaffMchtContactPermission record);

    int updateByPrimaryKey(StaffMchtContactPermission record);
}
