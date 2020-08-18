package com.jf.dao;

import com.jf.entity.MemberLabelType;
import com.jf.entity.MemberLabelTypeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberLabelTypeMapper extends BaseDao<MemberLabelType, MemberLabelTypeExample>{
    int countByExample(MemberLabelTypeExample example);

    int deleteByExample(MemberLabelTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLabelType record);

    int insertSelective(MemberLabelType record);

    List<MemberLabelType> selectByExample(MemberLabelTypeExample example);

    MemberLabelType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLabelType record, @Param("example") MemberLabelTypeExample example);

    int updateByExample(@Param("record") MemberLabelType record, @Param("example") MemberLabelTypeExample example);

    int updateByPrimaryKeySelective(MemberLabelType record);

    int updateByPrimaryKey(MemberLabelType record);
}