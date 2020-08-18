package com.jf.service.allowance;

import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.MemberAllowanceUsageCustomMapper;
import com.jf.dao.MemberAllowanceUsageMapper;
import com.jf.entity.MemberAllowanceUsage;
import com.jf.entity.MemberAllowanceUsageExample;
import com.jf.vo.request.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<MemberAllowanceUsage> findMemberUsedAllowance(Integer memberId, PageRequest request) {
        MemberAllowanceUsageExample example = new MemberAllowanceUsageExample();
        example.createCriteria()
                .andMemberIdEqualTo(memberId)
                .andDelFlagEqualTo(StateConst.FALSE);
        example.setLimitStart(request.getOffset());
        example.setLimitSize(request.getPageSize());
        example.setOrderByClause("create_date desc");
        return selectByExample(example);
    }

    public void deleteLog(Integer combineOrderId) {
        memberAllowanceUsageCustomMapper.deleteLog(combineOrderId);
    }
}
