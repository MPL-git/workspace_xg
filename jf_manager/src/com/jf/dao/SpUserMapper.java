package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.SpUser;
import com.jf.entity.SpUserExample;
@Repository
public interface SpUserMapper extends BaseDao<SpUser, SpUserExample>{
    int countByExample(SpUserExample example);

    int deleteByExample(SpUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SpUser record);

    int insertSelective(SpUser record);

    List<SpUser> selectByExample(SpUserExample example);

    SpUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SpUser record, @Param("example") SpUserExample example);

    int updateByExample(@Param("record") SpUser record, @Param("example") SpUserExample example);

    int updateByPrimaryKeySelective(SpUser record);

    int updateByPrimaryKey(SpUser record);
}