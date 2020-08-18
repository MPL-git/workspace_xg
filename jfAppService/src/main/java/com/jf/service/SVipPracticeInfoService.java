package com.jf.service;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.ext.exception.BizException;
import com.jf.dao.SvipPracticeInfoMapper;
import com.jf.entity.MemberInfo;
import com.jf.entity.SvipPracticeInfo;
import com.jf.entity.SvipPracticeInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author luoyb
 * Created on 2019/9/29
 */
@Service
public class SVipPracticeInfoService extends AppBaseService<SvipPracticeInfo, SvipPracticeInfoExample> {

    @Autowired
    private SvipPracticeInfoMapper svipPracticeInfoMapper;
    @Autowired
    private SVipPracticeRecordService sVipPracticeRecordService;
    @Autowired
    private MemberInfoService memberInfoService;


    @Autowired
    public void setDao() {
        super.setDao(svipPracticeInfoMapper);
    }

    @Transactional
    public void recPractice(Integer memberId) {
        SvipPracticeInfo info = validateBeforeRec(memberId);
        sVipPracticeRecordService.saveMemberRecord(memberId, info);
    }

    private SvipPracticeInfo validateBeforeRec(Integer memberId) {
        SvipPracticeInfo svipPracticeInfo = getLastRecAbleInfo();
        if (svipPracticeInfo == null) {
            throw new BizException("领取时间已结束~");
        }
        Date now = new Date();
        if (svipPracticeInfo.getStartTime() == null || now.before(svipPracticeInfo.getStartTime())) {
            throw new BizException("领取时间还没到哦~");
        }
        if (svipPracticeInfo.getEndTime() == null || now.after(svipPracticeInfo.getEndTime())) {
            throw new BizException("领取时间已结束~");
        }
        MemberInfo memberInfo = memberInfoService.selectByPrimaryKey(memberId);
        if (memberInfoService.isRealSVip(memberInfo, memberInfo.getId())) {
            throw new BizException("您已是Svip会员用户，无需领取");
        }
        if (sVipPracticeRecordService.getMemberLastRecRecord(memberId) != null) {
            throw new BizException("您已经领取SVIP体验卡，不能重复领取哦~");
        }
        return svipPracticeInfo;
    }

    private SvipPracticeInfo getLastRecAbleInfo() {
        SvipPracticeInfoExample example = new SvipPracticeInfoExample();
        example.createCriteria().andStatusEqualTo(StateConst.ONLINE).andDelFlagEqualTo(StateConst.FALSE);
        example.setOrderByClause("id desc");
        return selectOneByExample(example);
    }

    public boolean hadRecSvipPractice(Integer memberId) {
        return sVipPracticeRecordService.getMemberLastRecRecord(memberId) != null;
    }
}
