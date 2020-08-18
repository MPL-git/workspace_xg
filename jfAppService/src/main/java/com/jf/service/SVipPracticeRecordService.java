package com.jf.service;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.DateUtil;
import com.jf.dao.SvipPracticeRecordMapper;
import com.jf.entity.SvipPracticeInfo;
import com.jf.entity.SvipPracticeRecord;
import com.jf.entity.SvipPracticeRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/9/29
 */
@Service
public class SVipPracticeRecordService extends AppBaseService<SvipPracticeRecord, SvipPracticeRecordExample> {

    @Autowired
    private SvipPracticeRecordMapper svipPracticeRecordMapper;

    @Autowired
    public void setDao() {
        super.setDao(svipPracticeRecordMapper);
    }

    public SvipPracticeRecord getMemberLastRecRecord(Integer memberId) {
        SvipPracticeRecordExample example = new SvipPracticeRecordExample();
        example.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo(StateConst.FALSE);
        example.setOrderByClause("id desc");
        List<SvipPracticeRecord> recRecords = svipPracticeRecordMapper.selectByExample(example);
        return CollectionUtils.isEmpty(recRecords) ? null : recRecords.get(0);
    }

    @Transactional
    public void saveMemberRecord(Integer memberId, SvipPracticeInfo info) {
        Date now = new Date();
        SvipPracticeRecord recRecord = new SvipPracticeRecord();
        recRecord.setSvipPracticeInfoId(info.getId());
        recRecord.setMemberId(memberId);
        recRecord.setRecTime(now);
        if ("1".equals(info.getPracticeTimeType())) { //绝对时间
            recRecord.setPracticeStartTime(info.getPracticeStartTime());
            recRecord.setPracticeEndTime(info.getPracticeEndTime());
        } else if ("2".equals(info.getPracticeTimeType())) { //相对时间
            recRecord.setPracticeStartTime(now);
            recRecord.setPracticeEndTime(DateUtil.addHour(now, info.getPracticeHours()));
        }
        recRecord.setCreateBy(memberId);
        recRecord.setCreateDate(now);
        recRecord.setDelFlag(StateConst.FALSE);
        insert(recRecord);
    }
}
