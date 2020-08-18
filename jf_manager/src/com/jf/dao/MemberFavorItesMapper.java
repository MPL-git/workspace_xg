package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MemberFavorItes;
import com.jf.entity.MemberFavorItesExample;
@Repository
public interface MemberFavorItesMapper extends BaseDao<MemberFavorItes, MemberFavorItesExample>{
    int countByExample(MemberFavorItesExample example);

    int deleteByExample(MemberFavorItesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberFavorItes record);

    int insertSelective(MemberFavorItes record);

    List<MemberFavorItes> selectByExample(MemberFavorItesExample example);

    MemberFavorItes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberFavorItes record, @Param("example") MemberFavorItesExample example);

    int updateByExample(@Param("record") MemberFavorItes record, @Param("example") MemberFavorItesExample example);

    int updateByPrimaryKeySelective(MemberFavorItes record);

    int updateByPrimaryKey(MemberFavorItes record);
}