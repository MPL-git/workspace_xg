package com.jf.service.allowance;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.AllowanceSettingMapper;
import com.jf.entity.AllowanceSetting;
import com.jf.entity.AllowanceSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
@Service
public class AllowanceSettingService extends AppBaseService<AllowanceSetting , AllowanceSettingExample> {

    @Autowired
    private AllowanceSettingMapper allowanceSettingMapper;

    @Autowired
    public void setMapper() {
        super.setDao(allowanceSettingMapper);
    }

    public AllowanceSetting getActivitySetting() {
        AllowanceSettingExample example = new AllowanceSettingExample();
        example.createCriteria()
                .andStatusEqualTo(StateConst.ONLINE)
                .andDelFlagEqualTo(StateConst.FALSE);
        example.setLimitStart(0);
        example.setLimitSize(1);
        return selectOneByExample(example);
    }
}
