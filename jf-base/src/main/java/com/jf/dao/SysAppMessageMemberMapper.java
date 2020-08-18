package com.jf.dao;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.jf.common.base.BaseDao;
import com.jf.entity.SysAppMessageMember;
import com.jf.entity.SysAppMessageMemberExample;

@Repository
public interface SysAppMessageMemberMapper extends BaseDao<SysAppMessageMember, SysAppMessageMemberExample> {
    int countByExample(SysAppMessageMemberExample example);

    int deleteByExample(SysAppMessageMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysAppMessageMember record);

    int insertSelective(SysAppMessageMember record);

    List<SysAppMessageMember> selectByExample(SysAppMessageMemberExample example);

    SysAppMessageMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysAppMessageMember record, @Param("example") SysAppMessageMemberExample example);

    int updateByExample(@Param("record") SysAppMessageMember record, @Param("example") SysAppMessageMemberExample example);

    int updateByPrimaryKeySelective(SysAppMessageMember record);

    int updateByPrimaryKey(SysAppMessageMember record);
}
