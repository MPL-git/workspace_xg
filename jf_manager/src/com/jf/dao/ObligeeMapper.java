package com.jf.dao;

import com.jf.entity.Obligee;
import com.jf.entity.ObligeeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ObligeeMapper extends BaseDao<Obligee, ObligeeExample>{
    int countByExample(ObligeeExample example);

    int deleteByExample(ObligeeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Obligee record);

    int insertSelective(Obligee record);

    List<Obligee> selectByExample(ObligeeExample example);

    Obligee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Obligee record, @Param("example") ObligeeExample example);

    int updateByExample(@Param("record") Obligee record, @Param("example") ObligeeExample example);

    int updateByPrimaryKeySelective(Obligee record);

    int updateByPrimaryKey(Obligee record);
}