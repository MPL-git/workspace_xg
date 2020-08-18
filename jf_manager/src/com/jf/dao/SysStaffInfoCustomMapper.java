package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoCustom;
import com.jf.entity.SysStaffInfoCustomExample;

@Repository
public interface SysStaffInfoCustomMapper {
    int countByCustomExample(SysStaffInfoCustomExample example);

    List<SysStaffInfoCustom> selectByCustomExample(SysStaffInfoCustomExample example);

    SysStaffInfoCustom selectByCustomPrimaryKey(Integer staffId);

    int updateByCustomExampleSelective(@Param("record") SysStaffInfo record, @Param("example") SysStaffInfoCustomExample example);
    
    //查询用户的上级人员
    List<SysStaffInfoCustom> selectSuperior(Integer staffId);

}