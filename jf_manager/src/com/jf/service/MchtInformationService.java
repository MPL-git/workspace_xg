package com.jf.service;

import com.jf.dao.MchtInformationCustomMapper;
import com.jf.entity.MchtInformation;
import com.jf.entity.MchtInformationCustomExample;
import com.jf.entity.MchtInformationExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MchtInformationService extends BaseService<MchtInformation, MchtInformationExample> {

    @Resource
    private MchtInformationCustomMapper mchtInformationCustomMapper;

    public List<MchtInformation> selectByCustomExample(MchtInformationCustomExample mchtInformationCustomExample) {
        return mchtInformationCustomMapper.selectByCustomExample(mchtInformationCustomExample);
    }

    public Integer countByCustomExample(MchtInformationCustomExample mchtInformationCustomExample) {
        return mchtInformationCustomMapper.countByCustomExample(mchtInformationCustomExample);
    }
}
