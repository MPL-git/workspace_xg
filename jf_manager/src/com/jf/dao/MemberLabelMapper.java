package com.jf.dao;

import com.jf.entity.MemberLabel;
import com.jf.entity.MemberLabelExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberLabelMapper extends BaseDao<MemberLabel, MemberLabelExample>{
    int countByExample(MemberLabelExample example);

    int deleteByExample(MemberLabelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLabel record);

    int insertSelective(MemberLabel record);

    List<MemberLabel> selectByExample(MemberLabelExample example);

    MemberLabel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLabel record, @Param("example") MemberLabelExample example);

    int updateByExample(@Param("record") MemberLabel record, @Param("example") MemberLabelExample example);

    int updateByPrimaryKeySelective(MemberLabel record);

    int updateByPrimaryKey(MemberLabel record);
}