package com.jf.dao;

import com.jf.entity.MemberAccount;
import com.jf.entity.MemberAccountExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAccountMapper extends BaseDao<MemberAccount, MemberAccountExample>{
    int countByExample(MemberAccountExample example);

    int deleteByExample(MemberAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberAccount record);

    int insertSelective(MemberAccount record);

    List<MemberAccount> selectByExample(MemberAccountExample example);

    MemberAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberAccount record, @Param("example") MemberAccountExample example);

    int updateByExample(@Param("record") MemberAccount record, @Param("example") MemberAccountExample example);

    int updateByPrimaryKeySelective(MemberAccount record);

    int updateByPrimaryKey(MemberAccount record);
}