package com.jf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jf.entity.MchtMonthTradeCustom;
import com.jf.entity.MchtMonthTradeCustomExample;

@Repository
public interface MchtMonthTradeCustomMapper {
    public int countByCustomExample(MchtMonthTradeCustomExample example);

    public List<MchtMonthTradeCustom> selectByCustomExample(MchtMonthTradeCustomExample example);

    public MchtMonthTradeCustom selectByPrimaryCustomKey(Integer id);

}