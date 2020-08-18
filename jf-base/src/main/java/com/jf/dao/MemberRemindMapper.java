package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberRemind;
import com.jf.entity.MemberRemindExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRemindMapper extends BaseDao<MemberRemind, MemberRemindExample> {
    int countByExample(MemberRemindExample example);

    int deleteByExample(MemberRemindExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberRemind record);

    int insertSelective(MemberRemind record);

    List<MemberRemind> selectByExample(MemberRemindExample example);

    MemberRemind selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberRemind record, @Param("example") MemberRemindExample example);

    int updateByExample(@Param("record") MemberRemind record, @Param("example") MemberRemindExample example);

    int updateByPrimaryKeySelective(MemberRemind record);

    int updateByPrimaryKey(MemberRemind record);
}
