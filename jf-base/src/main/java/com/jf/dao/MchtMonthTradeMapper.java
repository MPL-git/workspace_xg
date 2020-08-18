package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.MchtMonthTrade;
import com.jf.entity.MchtMonthTradeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MchtMonthTradeMapper extends BaseDao<MchtMonthTrade, MchtMonthTradeExample> {
    int countByExample(MchtMonthTradeExample example);

    int deleteByExample(MchtMonthTradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MchtMonthTrade record);

    int insertSelective(MchtMonthTrade record);

    List<MchtMonthTrade> selectByExample(MchtMonthTradeExample example);

    MchtMonthTrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MchtMonthTrade record, @Param("example") MchtMonthTradeExample example);

    int updateByExample(@Param("record") MchtMonthTrade record, @Param("example") MchtMonthTradeExample example);

    int updateByPrimaryKeySelective(MchtMonthTrade record);

    int updateByPrimaryKey(MchtMonthTrade record);
}
