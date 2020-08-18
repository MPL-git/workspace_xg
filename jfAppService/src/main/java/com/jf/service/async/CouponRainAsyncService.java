package com.jf.service.async;

import com.jf.common.constant.StateConst;
import com.jf.dao.CouponRainMapper;
import com.jf.dao.CouponRainSetupMapper;
import com.jf.entity.CouponRain;
import com.jf.entity.CouponRainExample;
import com.jf.entity.CouponRainSetup;
import com.jf.entity.CouponRainSetupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author luoyb
 * Created on 2019/11/20
 */
@Service
public class CouponRainAsyncService {

    @Autowired
    private CouponRainSetupMapper couponRainSetupMapper;

    @Autowired
    private CouponRainMapper couponRainMapper;

    public List<CouponRainSetup> findSetupWithPicIsEmpty() {
        CouponRainSetupExample example = new CouponRainSetupExample();
        example.createCriteria().andDelFlagEqualTo(StateConst.FALSE).andPicIsNull();
        return couponRainSetupMapper.selectByExample(example);
    }

    @Transactional
    public void initSetupPic(CouponRainSetup couponRainSetup) {
        CouponRainExample example = new CouponRainExample();
        example.createCriteria().andDelFlagEqualTo(StateConst.FALSE).andCouponRainSetupIdEqualTo(couponRainSetup.getId());
        example.setLimitSize(1);
        List<CouponRain> rainList = couponRainMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(rainList)) {
            couponRainSetup.setPic(rainList.get(0).getPic()); //取红包雨入口第一张图片
        }
        couponRainSetup.setIncludeSale(StateConst.TRUE);
        couponRainSetup.setIncludeSalePercent(BigDecimal.ONE);
        couponRainSetup.setIncludePlatformCoupon(StateConst.TRUE);
        couponRainSetup.setIncludePlatformCouponPercent(BigDecimal.ONE);
        couponRainSetupMapper.updateByPrimaryKey(couponRainSetup);
    }
}
