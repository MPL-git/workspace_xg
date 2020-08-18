package com.jf.dao;

import com.jf.entity.StaffMchtContactPermissionExt;
import com.jf.entity.StaffMchtContactPermissionExtExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffMchtContactPermissionExtMapper {


    // -----------------------------------------------------------------------------------------------------------------
    // 基本方法
    // -----------------------------------------------------------------------------------------------------------------

    StaffMchtContactPermissionExt findById(int id);

    StaffMchtContactPermissionExt find(StaffMchtContactPermissionExtExample example);

    List<StaffMchtContactPermissionExt> list(StaffMchtContactPermissionExtExample example);

    List<Integer> listId(StaffMchtContactPermissionExtExample example);

    int count(StaffMchtContactPermissionExtExample example);

    long sum(@Param("field") String field, @Param("example") StaffMchtContactPermissionExtExample example);

    int max(@Param("field") String field, @Param("example") StaffMchtContactPermissionExtExample example);

    int min(@Param("field") String field, @Param("example") StaffMchtContactPermissionExtExample example);

}

