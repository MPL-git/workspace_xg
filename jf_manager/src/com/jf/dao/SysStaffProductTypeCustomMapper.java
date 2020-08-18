package com.jf.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jf.entity.SysStaffProductTypeCustom;
import com.jf.entity.SysStaffProductTypeCustomExample;

@Repository
public interface SysStaffProductTypeCustomMapper {
    int countByCustomExample(SysStaffProductTypeCustomExample example);

    List<SysStaffProductTypeCustom> selectByCustomExample(SysStaffProductTypeCustomExample example);

    SysStaffProductTypeCustom selectByPrimaryKeyCustom(Integer id);
    
    List<Map<String, Object>> selectByStaffId(Integer staffId);

}