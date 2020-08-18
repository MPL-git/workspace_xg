package com.jf.dao;

import com.jf.entity.MemberLabelRelation;
import com.jf.entity.MemberLabelRelationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberLabelRelationMapper extends BaseDao<MemberLabelRelation, MemberLabelRelationExample>{
    int countByExample(MemberLabelRelationExample example);

    int deleteByExample(MemberLabelRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLabelRelation record);

    int insertSelective(MemberLabelRelation record);

    List<MemberLabelRelation> selectByExample(MemberLabelRelationExample example);

    MemberLabelRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLabelRelation record, @Param("example") MemberLabelRelationExample example);

    int updateByExample(@Param("record") MemberLabelRelation record, @Param("example") MemberLabelRelationExample example);

    int updateByPrimaryKeySelective(MemberLabelRelation record);

    int updateByPrimaryKey(MemberLabelRelation record);
}