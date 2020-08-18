package com.jf.service;

import com.jf.dao.SeckillModuleColorSettingMapper;
import com.jf.entity.SeckillModuleColorSetting;
import com.jf.entity.SeckillModuleColorSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeckillModuleColorSettingService extends BaseService<SeckillModuleColorSetting, SeckillModuleColorSettingExample>{

    @Autowired
    private SeckillModuleColorSettingMapper seckillModuleColorSettingMapper;

    @Autowired
    public void setCouponMapper(SeckillModuleColorSettingMapper seckillModuleColorSettingMapper) {
        super.setDao(seckillModuleColorSettingMapper);
        this.seckillModuleColorSettingMapper = seckillModuleColorSettingMapper;
    }

}
