package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MemberShare;
import com.jf.entity.MemberShareExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberShareMapper extends BaseDao<MemberShare,MemberShareExample> {
    int countByExample(MemberShareExample example);

    int deleteByExample(MemberShareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberShare record);

    int insertSelective(MemberShare record);

    List<MemberShare> selectByExample(MemberShareExample example);

    MemberShare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberShare record, @Param("example") MemberShareExample example);

    int updateByExample(@Param("record") MemberShare record, @Param("example") MemberShareExample example);

    int updateByPrimaryKeySelective(MemberShare record);

    int updateByPrimaryKey(MemberShare record);
}