package com.jf.dao;

import com.jf.entity.LotterySettings;
import com.jf.entity.LotterySettingsExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotterySettingsMapper extends BaseDao<LotterySettings, LotterySettingsExample>{
    int countByExample(LotterySettingsExample example);

    int deleteByExample(LotterySettingsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LotterySettings record);

    int insertSelective(LotterySettings record);

    List<LotterySettings> selectByExample(LotterySettingsExample example);

    LotterySettings selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LotterySettings record, @Param("example") LotterySettingsExample example);

    int updateByExample(@Param("record") LotterySettings record, @Param("example") LotterySettingsExample example);

    int updateByPrimaryKeySelective(LotterySettings record);

    int updateByPrimaryKey(LotterySettings record);
}