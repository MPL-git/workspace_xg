package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.ImpeachMember;
import com.jf.entity.ImpeachMemberExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImpeachMemberMapper extends BaseDao<ImpeachMember, ImpeachMemberExample> {
    int countByExample(ImpeachMemberExample example);

    int deleteByExample(ImpeachMemberExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImpeachMember record);

    int insertSelective(ImpeachMember record);

    List<ImpeachMember> selectByExample(ImpeachMemberExample example);

    ImpeachMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImpeachMember record, @Param("example") ImpeachMemberExample example);

    int updateByExample(@Param("record") ImpeachMember record, @Param("example") ImpeachMemberExample example);

    int updateByPrimaryKeySelective(ImpeachMember record);

    int updateByPrimaryKey(ImpeachMember record);
}