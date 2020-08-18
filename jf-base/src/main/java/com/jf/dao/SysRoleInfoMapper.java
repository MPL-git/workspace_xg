package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SysRoleInfo;
import com.jf.entity.SysRoleInfoExample;

@Repository
public interface SysRoleInfoMapper extends BaseDao<SysRoleInfo, SysRoleInfoExample> {
    int countByExample(SysRoleInfoExample example);

    int deleteByExample(SysRoleInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleInfo record);

    int insertSelective(SysRoleInfo record);

    List<SysRoleInfo> selectByExample(SysRoleInfoExample example);

    SysRoleInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRoleInfo record, @Param("example") SysRoleInfoExample example);

    int updateByExample(@Param("record") SysRoleInfo record, @Param("example") SysRoleInfoExample example);

    int updateByPrimaryKeySelective(SysRoleInfo record);

    int updateByPrimaryKey(SysRoleInfo record);
}
