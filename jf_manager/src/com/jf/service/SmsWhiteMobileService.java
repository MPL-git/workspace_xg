package com.jf.service;

import com.jf.dao.SmsWhiteMobileMapper;
import com.jf.entity.SmsWhiteMobile;
import com.jf.entity.SmsWhiteMobileExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName SmsWhiteMobileService
 * @Author YRD
 * @Date 2020/7/24 15:01
 **/

@Service
@Transactional
public class SmsWhiteMobileService extends  BaseService<SmsWhiteMobile, SmsWhiteMobileExample>{

    @Autowired
    private SmsWhiteMobileMapper smsWhiteMobileMapper;
    @Autowired
    public void setExpressMapper(SmsWhiteMobileMapper smsWhiteMobileMapper) {
        super.setDao(smsWhiteMobileMapper);
        this.smsWhiteMobileMapper = smsWhiteMobileMapper;
    }
}
