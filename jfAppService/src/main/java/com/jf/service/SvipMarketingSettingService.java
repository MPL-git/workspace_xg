package com.jf.service;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.CollectionUtil;
import com.jf.dao.SvipMarketingSettingMapper;
import com.jf.entity.SvipMarketingSetting;
import com.jf.entity.SvipMarketingSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2020/7/10
 */
@Service
public class SvipMarketingSettingService extends AppBaseService<SvipMarketingSetting, SvipMarketingSettingExample> {

    @Autowired
    private SvipMarketingSettingMapper svipMarketingSettingMapper;

    @Autowired
    public void setMapper() {
        super.setDao(svipMarketingSettingMapper);
    }

    public SvipMarketingSetting getSetting() {
        Date now = new Date();
        SvipMarketingSettingExample example = new SvipMarketingSettingExample();
        example.createCriteria()
                .andBeginDateLessThanOrEqualTo(now)
                .andEndDateGreaterThanOrEqualTo(now)
                .andDelFlagEqualTo(StateConst.FALSE);
        List<SvipMarketingSetting> list = this.selectByExample(example);
        return CollectionUtil.isEmpty(list) ? null : list.get(0);
    }
}
