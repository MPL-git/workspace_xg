package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MchtInformationMapper;
import com.jf.entity.MchtInformation;
import com.jf.entity.MchtInformationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MchtInformationService extends BaseService<MchtInformation, MchtInformationExample> {
    @Autowired
    private MchtInformationMapper mchtInformationMapper;

    @Autowired
    public void setMchtInformationMapper(MchtInformationMapper mchtInformationMapper) {
        super.setDao(mchtInformationMapper);
        this.mchtInformationMapper = mchtInformationMapper;
    }
}
