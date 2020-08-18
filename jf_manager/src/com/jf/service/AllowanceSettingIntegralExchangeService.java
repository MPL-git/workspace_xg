package com.jf.service;

import com.jf.dao.AllowanceSettingIntegralExchangeMapper;
import com.jf.entity.AllowanceSettingIntegralExchange;
import com.jf.entity.AllowanceSettingIntegralExchangeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllowanceSettingIntegralExchangeService extends  BaseService<AllowanceSettingIntegralExchange, AllowanceSettingIntegralExchangeExample> {
    @Autowired
    private AllowanceSettingIntegralExchangeMapper allowanceSettingIntegralExchangeMapper;
    @Autowired
    public void setAllowanceSettingIntegralExchangeMapper(AllowanceSettingIntegralExchangeMapper allowanceSettingIntegralExchangeMapper) {
        super.setDao(allowanceSettingIntegralExchangeMapper);
        this.allowanceSettingIntegralExchangeMapper = allowanceSettingIntegralExchangeMapper;
    }


}
