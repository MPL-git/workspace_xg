package com.jf.service.allowance;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberAllowanceUsageCustomMapper;
import com.jf.dao.MemberAllowanceUsageMapper;
import com.jf.entity.MemberAllowanceUsage;
import com.jf.entity.MemberAllowanceUsageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
@Service
public class MemberAllowanceUsageService extends BaseService<MemberAllowanceUsage, MemberAllowanceUsageExample> {

    @Autowired
    private MemberAllowanceUsageMapper memberAllowanceUsageMapper;
    @Autowired
    private MemberAllowanceUsageCustomMapper memberAllowanceUsageCustomMapper;

    @Autowired
    public void setMapper() {
        super.setDao(memberAllowanceUsageMapper);
    }

    public void deleteLog(Integer combineOrderId) {
        memberAllowanceUsageCustomMapper.deleteLog(combineOrderId);
    }
}
