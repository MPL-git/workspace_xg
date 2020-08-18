package com.jf.dao;

import com.jf.entity.MemberAllowance;
import com.jf.entity.MemberAllowanceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberAllowanceMapper extends BaseDao<MemberAllowance,MemberAllowanceExample>{
    int countByExample(MemberAllowanceExample example);

    int deleteByExample(MemberAllowanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAllowance record);

    int insertSelective(MemberAllowance record);

    List<MemberAllowance> selectByExample(MemberAllowanceExample example);

    MemberAllowance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberAllowance record, @Param("example") MemberAllowanceExample example);

    int updateByExample(@Param("record") MemberAllowance record, @Param("example") MemberAllowanceExample example);

    int updateByPrimaryKeySelective(MemberAllowance record);

    int updateByPrimaryKey(MemberAllowance record);
}