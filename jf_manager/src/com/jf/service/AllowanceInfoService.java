package com.jf.service;

import com.jf.dao.AllowanceInfoMapper;
import com.jf.entity.AllowanceInfo;
import com.jf.entity.AllowanceInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllowanceInfoService extends BaseService<AllowanceInfo, AllowanceInfoExample> {
    @Autowired
    private AllowanceInfoMapper allowanceInfoMapper;

    @Autowired
    public void setAllowanceInfoMapper(AllowanceInfoMapper allowanceInfoMapper) {
        super.setDao(allowanceInfoMapper);
        this.allowanceInfoMapper = allowanceInfoMapper;
    }

}
