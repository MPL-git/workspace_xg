package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberJgRelation;
import com.jf.entity.MemberJgRelationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberJgRelationMapper extends BaseDao<MemberJgRelation, MemberJgRelationExample> {
    int countByExample(MemberJgRelationExample example);

    int deleteByExample(MemberJgRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberJgRelation record);

    int insertSelective(MemberJgRelation record);

    List<MemberJgRelation> selectByExample(MemberJgRelationExample example);

    MemberJgRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberJgRelation record, @Param("example") MemberJgRelationExample example);

    int updateByExample(@Param("record") MemberJgRelation record, @Param("example") MemberJgRelationExample example);

    int updateByPrimaryKeySelective(MemberJgRelation record);

    int updateByPrimaryKey(MemberJgRelation record);
}
