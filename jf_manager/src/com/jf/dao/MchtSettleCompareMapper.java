package com.jf.dao;

import com.jf.entity.MchtSettleCompare;
import com.jf.entity.MchtSettleCompareExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MchtSettleCompareMapper extends BaseDao<MchtSettleCompare, MchtSettleCompareExample> {
    int countByExample(MchtSettleCompareExample example);

    int deleteByExample(MchtSettleCompareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtSettleCompare record);

    int insertSelective(MchtSettleCompare record);

    List<MchtSettleCompare> selectByExample(MchtSettleCompareExample example);

    MchtSettleCompare selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtSettleCompare record, @Param("example") MchtSettleCompareExample example);

    int updateByExample(@Param("record") MchtSettleCompare record, @Param("example") MchtSettleCompareExample example);

    int updateByPrimaryKeySelective(MchtSettleCompare record);

    int updateByPrimaryKey(MchtSettleCompare record);
}