package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberLabelGroup;
import com.jf.entity.MemberLabelGroupExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberLabelGroupMapper extends BaseDao<MemberLabelGroup, MemberLabelGroupExample> {
    int countByExample(MemberLabelGroupExample example);

    int deleteByExample(MemberLabelGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLabelGroup record);

    int insertSelective(MemberLabelGroup record);

    List<MemberLabelGroup> selectByExample(MemberLabelGroupExample example);

    MemberLabelGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLabelGroup record, @Param("example") MemberLabelGroupExample example);

    int updateByExample(@Param("record") MemberLabelGroup record, @Param("example") MemberLabelGroupExample example);

    int updateByPrimaryKeySelective(MemberLabelGroup record);

    int updateByPrimaryKey(MemberLabelGroup record);
}
