package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtBrandRateChange;
import com.jf.entity.MchtBrandRateChangeExample;
@Repository
public interface MchtBrandRateChangeMapper extends BaseDao<MchtBrandRateChange, MchtBrandRateChangeExample>{
    int countByExample(MchtBrandRateChangeExample example);

    int deleteByExample(MchtBrandRateChangeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtBrandRateChange record);

    int insertSelective(MchtBrandRateChange record);

    List<MchtBrandRateChange> selectByExample(MchtBrandRateChangeExample example);

    MchtBrandRateChange selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtBrandRateChange record, @Param("example") MchtBrandRateChangeExample example);

    int updateByExample(@Param("record") MchtBrandRateChange record, @Param("example") MchtBrandRateChangeExample example);

    int updateByPrimaryKeySelective(MchtBrandRateChange record);

    int updateByPrimaryKey(MchtBrandRateChange record);
}