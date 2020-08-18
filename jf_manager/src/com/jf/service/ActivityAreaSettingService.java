package com.jf.service;


import com.jf.dao.ActivityAreaSettingMapper;
import com.jf.entity.ActivityAreaSetting;
import com.jf.entity.ActivityAreaSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityAreaSettingService extends BaseService<ActivityAreaSetting, ActivityAreaSettingExample> {

    @Autowired
    private ActivityAreaSettingMapper activityAreaSettingMapper;


    @Autowired
    public void setActivityAreaSettingMapper(ActivityAreaSettingMapper activityAreaSettingMapper) {
        super.setDao(activityAreaSettingMapper);
        this.activityAreaSettingMapper = activityAreaSettingMapper;
    }
}
