package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberLabel;
import com.jf.entity.MemberLabelExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelMapper extends BaseDao<MemberLabel, MemberLabelExample> {
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
