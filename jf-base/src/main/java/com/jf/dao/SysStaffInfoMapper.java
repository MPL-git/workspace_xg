package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;

@Repository
public interface SysStaffInfoMapper extends BaseDao<SysStaffInfo, SysStaffInfoExample> {
    int countByExample(SysStaffInfoExample example);

    int deleteByExample(SysStaffInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysStaffInfo record);

    int insertSelective(SysStaffInfo record);

    List<SysStaffInfo> selectByExample(SysStaffInfoExample example);

    SysStaffInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysStaffInfo record, @Param("example") SysStaffInfoExample example);

    int updateByExample(@Param("record") SysStaffInfo record, @Param("example") SysStaffInfoExample example);

    int updateByPrimaryKeySelective(SysStaffInfo record);

    int updateByPrimaryKey(SysStaffInfo record);
}
