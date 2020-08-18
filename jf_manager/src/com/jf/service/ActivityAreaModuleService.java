package com.jf.service;

import com.jf.dao.ActivityAreaModuleMapper;
import com.jf.entity.ActivityAreaModule;
import com.jf.entity.ActivityAreaModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityAreaModuleService extends  BaseService<ActivityAreaModule, ActivityAreaModuleExample>  {
    @Autowired
    private ActivityAreaModuleMapper activityAreaModuleMapper;


    @Autowired
    public void setActivityAuthMapper(ActivityAreaModuleMapper activityAreaModuleMapper) {
        super.setDao(activityAreaModuleMapper);
        this.activityAreaModuleMapper = activityAreaModuleMapper;
    }

}
