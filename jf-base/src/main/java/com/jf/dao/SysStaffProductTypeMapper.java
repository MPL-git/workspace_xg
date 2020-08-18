package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SysStaffProductType;
import com.jf.entity.SysStaffProductTypeExample;

@Repository
public interface SysStaffProductTypeMapper extends BaseDao<SysStaffProductType, SysStaffProductTypeExample> {
    int countByExample(SysStaffProductTypeExample example);

    int deleteByExample(SysStaffProductTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysStaffProductType record);

    int insertSelective(SysStaffProductType record);

    List<SysStaffProductType> selectByExample(SysStaffProductTypeExample example);

    SysStaffProductType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysStaffProductType record, @Param("example") SysStaffProductTypeExample example);

    int updateByExample(@Param("record") SysStaffProductType record, @Param("example") SysStaffProductTypeExample example);

    int updateByPrimaryKeySelective(SysStaffProductType record);

    int updateByPrimaryKey(SysStaffProductType record);
}
