package com.jf.dao;

import com.jf.common.base.BaseDao;
import com.jf.entity.AllowanceSettingIntegralExchange;
import com.jf.entity.AllowanceSettingIntegralExchangeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllowanceSettingIntegralExchangeMapper extends BaseDao<AllowanceSettingIntegralExchange, AllowanceSettingIntegralExchangeExample> {
    int countByExample(AllowanceSettingIntegralExchangeExample example);

    int deleteByExample(AllowanceSettingIntegralExchangeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AllowanceSettingIntegralExchange record);

    int insertSelective(AllowanceSettingIntegralExchange record);

    List<AllowanceSettingIntegralExchange> selectByExample(AllowanceSettingIntegralExchangeExample example);

    AllowanceSettingIntegralExchange selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AllowanceSettingIntegralExchange record, @Param("example") AllowanceSettingIntegralExchangeExample example);

    int updateByExample(@Param("record") AllowanceSettingIntegralExchange record, @Param("example") AllowanceSettingIntegralExchangeExample example);

    int updateByPrimaryKeySelective(AllowanceSettingIntegralExchange record);

    int updateByPrimaryKey(AllowanceSettingIntegralExchange record);
}