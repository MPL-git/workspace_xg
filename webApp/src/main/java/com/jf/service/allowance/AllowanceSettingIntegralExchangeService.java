package com.jf.service.allowance;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.AllowanceSettingIntegralExchangeMapper;
import com.jf.entity.AllowanceSettingIntegralExchange;
import com.jf.entity.AllowanceSettingIntegralExchangeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
@Service
public class AllowanceSettingIntegralExchangeService extends AppBaseService<AllowanceSettingIntegralExchange, AllowanceSettingIntegralExchangeExample> {

    @Autowired
    private AllowanceSettingIntegralExchangeMapper allowanceSettingIntegralExchangeMapper;

    @Autowired
    public void setMapper() {
        super.setDao(allowanceSettingIntegralExchangeMapper);
    }

    public List<AllowanceSettingIntegralExchange> findByAllowanceId(Integer allowanceId) {
        AllowanceSettingIntegralExchangeExample example = new AllowanceSettingIntegralExchangeExample();
        example.createCriteria()
                .andAllowanceIdEqualTo(allowanceId)
                .andDelFlagEqualTo(StateConst.FALSE);
        example.setLimitStart(0);
        example.setLimitSize(2);
        return selectByExample(example);
    }

}
