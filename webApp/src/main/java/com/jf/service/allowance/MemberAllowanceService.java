package com.jf.service.allowance;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.MemberAllowanceCustomMapper;
import com.jf.dao.MemberAllowanceMapper;
import com.jf.dao.MemberAllowanceUsageCustomMapper;
import com.jf.entity.MemberAllowance;
import com.jf.entity.MemberAllowanceExample;
import com.jf.vo.request.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    /**
     * 获取用户最近一次兑换津贴记录
     */
    public MemberAllowance getLatestRecord(Integer memberId) {
        MemberAllowanceExample example = new MemberAllowanceExample();
        example.createCriteria()
                .andMemberIdEqualTo(memberId)
                .andDelFlagEqualTo(StateConst.FALSE);
        example.setOrderByClause("id desc");
        example.setLimitStart(0);
        example.setLimitSize(1);
        return selectOneByExample(example);
    }

    public List<MemberAllowance> findMemberAllowance(Integer memberId, PageRequest request) {
        MemberAllowanceExample example = new MemberAllowanceExample();
        example.createCriteria()
                .andMemberIdEqualTo(memberId)
                .andDelFlagEqualTo(StateConst.FALSE);
        example.setLimitStart(request.getOffset());
        example.setLimitSize(request.getPageSize());
        example.setOrderByClause("create_date desc");
        return selectByExample(example);
    }
}
