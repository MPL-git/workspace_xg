package com.jf.dao;

import com.jf.entity.AllowanceInfo;
import com.jf.entity.AllowanceInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllowanceInfoMapper extends BaseDao<AllowanceInfo,AllowanceInfoExample>{
    int countByExample(AllowanceInfoExample example);

    int deleteByExample(AllowanceInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AllowanceInfo record);

    int insertSelective(AllowanceInfo record);

    List<AllowanceInfo> selectByExample(AllowanceInfoExample example);

    AllowanceInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AllowanceInfo record, @Param("example") AllowanceInfoExample example);

    int updateByExample(@Param("record") AllowanceInfo record, @Param("example") AllowanceInfoExample example);

    int updateByPrimaryKeySelective(AllowanceInfo record);

    int updateByPrimaryKey(AllowanceInfo record);
}