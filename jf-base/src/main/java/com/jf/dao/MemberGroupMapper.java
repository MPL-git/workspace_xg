package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberGroup;
import com.jf.entity.MemberGroupExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberGroupMapper extends BaseDao<MemberGroup, MemberGroupExample> {
    int countByExample(MemberGroupExample example);

    int deleteByExample(MemberGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberGroup record);

    int insertSelective(MemberGroup record);

    List<MemberGroup> selectByExample(MemberGroupExample example);

    MemberGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberGroup record, @Param("example") MemberGroupExample example);

    int updateByExample(@Param("record") MemberGroup record, @Param("example") MemberGroupExample example);

    int updateByPrimaryKeySelective(MemberGroup record);

    int updateByPrimaryKey(MemberGroup record);
}
