package com.jf.dao;

import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysStaffInfoMapper extends BaseDao<SysStaffInfo, SysStaffInfoExample>{
    int countByExample(SysStaffInfoExample example);

    int deleteByExample(SysStaffInfoExample example);

    int deleteByPrimaryKey(Integer staffId);

    int insert(SysStaffInfo record);

    int insertSelective(SysStaffInfo record);

    List<SysStaffInfo> selectByExample(SysStaffInfoExample example);

    SysStaffInfo selectByPrimaryKey(Integer staffId);

    int updateByExampleSelective(@Param("record") SysStaffInfo record, @Param("example") SysStaffInfoExample example);

    int updateByExample(@Param("record") SysStaffInfo record, @Param("example") SysStaffInfoExample example);

    int updateByPrimaryKeySelective(SysStaffInfo record);

    int updateByPrimaryKey(SysStaffInfo record);
}