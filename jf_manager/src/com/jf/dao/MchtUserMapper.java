package com.jf.dao;

import com.jf.entity.MchtUser;
import com.jf.entity.MchtUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface MchtUserMapper extends BaseDao<MchtUser, MchtUserExample>{
    int countByExample(MchtUserExample example);

    int deleteByExample(MchtUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtUser record);

    int insertSelective(MchtUser record);

    List<MchtUser> selectByExample(MchtUserExample example);

    MchtUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtUser record, @Param("example") MchtUserExample example);

    int updateByExample(@Param("record") MchtUser record, @Param("example") MchtUserExample example);

    int updateByPrimaryKeySelective(MchtUser record);

    int updateByPrimaryKey(MchtUser record);
}