package com.jf.service.allowance;

import com.jf.common.AppBaseService;
import com.jf.dao.MemberAllowanceCustomMapper;
import com.jf.dao.MemberAllowanceMapper;
import com.jf.dao.MemberAllowanceUsageCustomMapper;
import com.jf.entity.MemberAllowance;
import com.jf.entity.MemberAllowanceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author luoyb
 * Created on 2020/5/25
 */
@Service
public class MemberAllowanceService extends AppBaseService<MemberAllowance, MemberAllowanceExample> {

    @Autowired
    private MemberAllowanceMapper memberAllowanceMapper;
    @Autowired
    private MemberAllowanceCustomMapper memberAllowanceCustomMapper;
    @Autowired
    private MemberAllowanceUsageCustomMapper memberAllowanceUsageCustomMapper;

    @Autowired
    public void setMapper() {
        super.setDao(memberAllowanceMapper);
    }

    /**
     * 获取用户津贴余额
     */
    public BigDecimal getMemberAllowanceBalance(Integer memberId) {
        if (memberId == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal totalAllowance = memberAllowanceCustomMapper.getMemberAllowanceBalance(memberId);
        BigDecimal usedAllowance = memberAllowanceUsageCustomMapper.getMemberAllowanceUsage(memberId);
        if (totalAllowance.compareTo(usedAllowance) > 0) {
            return totalAllowance.subtract(usedAllowance);
        }
        return BigDecimal.ZERO;
    }

}
