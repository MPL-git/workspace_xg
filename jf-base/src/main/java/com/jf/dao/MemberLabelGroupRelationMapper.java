package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberLabelGroupRelation;
import com.jf.entity.MemberLabelGroupRelationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelGroupRelationMapper extends BaseDao<MemberLabelGroupRelation, MemberLabelGroupRelationExample> {
    int countByExample(MemberLabelGroupRelationExample example);

    int deleteByExample(MemberLabelGroupRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLabelGroupRelation record);

    int insertSelective(MemberLabelGroupRelation record);

    List<MemberLabelGroupRelation> selectByExample(MemberLabelGroupRelationExample example);

    MemberLabelGroupRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLabelGroupRelation record, @Param("example") MemberLabelGroupRelationExample example);

    int updateByExample(@Param("record") MemberLabelGroupRelation record, @Param("example") MemberLabelGroupRelationExample example);

    int updateByPrimaryKeySelective(MemberLabelGroupRelation record);

    int updateByPrimaryKey(MemberLabelGroupRelation record);
}
