package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.DateUtil;
import com.jf.dao.MemberShareMapper;
import com.jf.entity.MemberShare;
import com.jf.entity.MemberShareExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author luoyb
 * Created on 2020/7/21
 */
@Service
public class MemberShareService extends BaseService<MemberShare, MemberShareExample> {

    @Autowired
    private MemberShareMapper memberShareMapper;

    @Autowired
    private void setMapper() {
        this.setDao(memberShareMapper);
    }

    /**
     * 统计用户在 type 类型下的分享次数
     *
     * @param memberId   用户ID
     * @param type       类型：1.积分转盘
     * @param relevantId 类型：业务ID
     */
    public int countMemberShareTimes(int memberId, String type, Integer relevantId) {
        MemberShareExample example = new MemberShareExample();
        MemberShareExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(memberId)
                .andTypeEqualTo(type)
                .andCreateDateGreaterThanOrEqualTo(DateUtil.getDateAfterAndBeginTime(new Date(), 0))
                .andDelFlagEqualTo(StateConst.FALSE);
        if (relevantId != null) {
            criteria.andRelevantIdEqualTo(relevantId);
        }
        return this.countByExample(example);
    }

    /**
     * @param memberId   用户ID
     * @param type       类型：1.积分转盘
     * @param relevantId 类型：业务ID
     */
    @Transactional
    public void saveMemberShare(int memberId, String type, Integer relevantId) {
        MemberShare memberShare = new MemberShare();
        memberShare.setMemberId(memberId);
        memberShare.setType(type);
        memberShare.setRelevantId(relevantId);
        memberShare.setCreateBy(memberId);
        memberShare.setCreateDate(new Date());
        memberShare.setDelFlag(StateConst.FALSE);
        this.insert(memberShare);
    }
}
