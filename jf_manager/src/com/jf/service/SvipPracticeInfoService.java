package com.jf.service;

import com.jf.dao.SvipPracticeInfoMapper;
import com.jf.entity.SvipPracticeInfo;
import com.jf.entity.SvipPracticeInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pengl
 * @create 2019-09-29 下午 1:53
 */
@Service
@Transactional
public class SvipPracticeInfoService extends BaseService<SvipPracticeInfo, SvipPracticeInfoExample> {

    @Autowired
    private SvipPracticeInfoMapper svipPracticeInfoMapper;

    @Autowired
    public void setSvipPracticeInfoMapper(SvipPracticeInfoMapper svipPracticeInfoMapper) {
        super.setDao(svipPracticeInfoMapper);
        this.svipPracticeInfoMapper = svipPracticeInfoMapper;
    }

    public void statusSvipPracticeInfo(SvipPracticeInfo svipPracticeInfo) {
        if("1".equals(svipPracticeInfo.getStatus()) ) {
            SvipPracticeInfoExample svipPracticeInfoExample = new SvipPracticeInfoExample();
            svipPracticeInfoExample.createCriteria().andStatusEqualTo("1");
            SvipPracticeInfo svipPractice = new SvipPracticeInfo();
            svipPractice.setStatus("0");
            svipPracticeInfoMapper.updateByExampleSelective(svipPractice, svipPracticeInfoExample);
        }
        svipPracticeInfoMapper.updateByPrimaryKeySelective(svipPracticeInfo);
    }

}
