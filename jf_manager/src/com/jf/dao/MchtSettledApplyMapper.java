package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtSettledApply;
import com.jf.entity.MchtSettledApplyExample;
@Repository
public interface MchtSettledApplyMapper extends BaseDao<MchtSettledApply, MchtSettledApplyExample>{
    int countByExample(MchtSettledApplyExample example);

    int deleteByExample(MchtSettledApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtSettledApply record);

    int insertSelective(MchtSettledApply record);

    List<MchtSettledApply> selectByExample(MchtSettledApplyExample example);

    MchtSettledApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtSettledApply record, @Param("example") MchtSettledApplyExample example);

    int updateByExample(@Param("record") MchtSettledApply record, @Param("example") MchtSettledApplyExample example);

    int updateByPrimaryKeySelective(MchtSettledApply record);

    int updateByPrimaryKey(MchtSettledApply record);
}