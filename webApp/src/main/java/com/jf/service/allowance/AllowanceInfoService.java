package com.jf.service.allowance;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.AllowanceInfoCustomMapper;
import com.jf.dao.AllowanceInfoMapper;
import com.jf.entity.AllowanceInfo;
import com.jf.entity.AllowanceInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
@Service
public class AllowanceInfoService extends AppBaseService<AllowanceInfo , AllowanceInfoExample> {

    @Autowired
    private AllowanceInfoMapper allowanceInfoMapper;
    @Autowired
    private AllowanceInfoCustomMapper allowanceInfoCustomMapper;

    @Autowired
    public void setMapper() {
        super.setDao(allowanceInfoMapper);
    }

    public AllowanceInfo getByActivityAreaId(Integer activityAreaId) {
        AllowanceInfoExample example = new AllowanceInfoExample();
        example.createCriteria()
                .andActivityAreaIdEqualTo(activityAreaId)
                .andDelFlagEqualTo(StateConst.FALSE);
        return selectOneByExample(example);
    }

    public AllowanceInfo getMaxPreAllowanceInfo() {
        return allowanceInfoCustomMapper.getMaxPreAllowanceInfo();
    }

    public List<AllowanceInfo> findActiveAllowance() {
        return allowanceInfoCustomMapper.findActiveAllowance();
    }

    public List<AllowanceInfo> findByActivityAreaId(Integer activityAreaId) {
        AllowanceInfoExample example = new AllowanceInfoExample();
        example.createCriteria()
                .andDelFlagEqualTo(StateConst.FALSE)
                .andActivityAreaIdEqualTo(activityAreaId);
        return selectByExample(example);
    }

}
