package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberAttention;
import com.jf.entity.MemberAttentionExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAttentionMapper extends BaseDao<MemberAttention, MemberAttentionExample> {
    int countByExample(MemberAttentionExample example);

    int deleteByExample(MemberAttentionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAttention record);

    int insertSelective(MemberAttention record);

    List<MemberAttention> selectByExample(MemberAttentionExample example);

    MemberAttention selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberAttention record, @Param("example") MemberAttentionExample example);

    int updateByExample(@Param("record") MemberAttention record, @Param("example") MemberAttentionExample example);

    int updateByPrimaryKeySelective(MemberAttention record);

    int updateByPrimaryKey(MemberAttention record);
}
