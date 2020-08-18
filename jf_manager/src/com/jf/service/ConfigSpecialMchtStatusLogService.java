package com.jf.service;


import com.jf.dao.ConfigSpecialMchtCustomMapper;
import com.jf.dao.ConfigSpecialMchtMapper;
import com.jf.dao.ConfigSpecialMchtStatusLogMapper;
import com.jf.entity.ConfigSpecialMchtStatusLog;
import com.jf.entity.ConfigSpecialMchtStatusLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConfigSpecialMchtStatusLogService extends BaseService< ConfigSpecialMchtStatusLog, ConfigSpecialMchtStatusLogExample>  {
    @Autowired
    private ConfigSpecialMchtStatusLogMapper configSpecialMchtStatusLogMapper;

    @Autowired
    public void setConfigSpecialMchtMapper(ConfigSpecialMchtStatusLogMapper configSpecialMchtStatusLogMapper) {
        super.setDao(configSpecialMchtStatusLogMapper);
        this.configSpecialMchtStatusLogMapper = configSpecialMchtStatusLogMapper;
    }

}
