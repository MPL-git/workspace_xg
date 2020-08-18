package com.jf.service.couponrain;

import com.jf.common.AppBaseService;
import com.jf.common.constant.StateConst;
import com.jf.dao.CouponRainRecordMapper;
import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainRecordExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luoyb
 * Created on 2019/9/26
 */
@Service
public class CouponRainRecordService extends AppBaseService<CouponRainRecord, CouponRainRecordExample> {

    @Autowired
    private CouponRainRecordMapper couponRainRecordMapper;

    @Autowired
    public void setDao() {
        super.setDao(couponRainRecordMapper);
    }

    public CouponRainRecord getById(Integer recordId) {
        return selectByPrimaryKey(recordId);
    }

    public int countCouponRainMemberParticipate(Integer couponRainId, Integer memberId) {
        CouponRainRecordExample example = new CouponRainRecordExample();
        example.createCriteria().andCouponRainIdEqualTo(couponRainId).andMemberIdEqualTo(memberId).andDelFlagEqualTo(StateConst.FALSE);
        return countByExample(example);
    }
}
