package com.jf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jf.entity.MchtStatisticalInfo;
import com.jf.entity.MchtStatisticalInfoExample;
@Repository
public interface MchtStatisticalInfoMapper extends BaseDao<MchtStatisticalInfo, MchtStatisticalInfoExample>{
    int countByExample(MchtStatisticalInfoExample example);

    int deleteByExample(MchtStatisticalInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtStatisticalInfo record);

    int insertSelective(MchtStatisticalInfo record);

    List<MchtStatisticalInfo> selectByExample(MchtStatisticalInfoExample example);

    MchtStatisticalInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtStatisticalInfo record, @Param("example") MchtStatisticalInfoExample example);

    int updateByExample(@Param("record") MchtStatisticalInfo record, @Param("example") MchtStatisticalInfoExample example);

    int updateByPrimaryKeySelective(MchtStatisticalInfo record);

    int updateByPrimaryKey(MchtStatisticalInfo record);
}