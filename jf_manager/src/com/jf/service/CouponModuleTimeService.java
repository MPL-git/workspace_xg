package com.jf.service;

import com.jf.dao.CouponModuleTimeMapper;
import com.jf.entity.CouponModuleTime;
import com.jf.entity.CouponModuleTimeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class CouponModuleTimeService extends  BaseService<CouponModuleTime, CouponModuleTimeExample> {

    @Autowired
    private CouponModuleTimeMapper couponModuleTimeMapper;


    @Autowired
    public void setCouponMapper(CouponModuleTimeMapper couponModuleTimeMapper) {
        super.setDao(couponModuleTimeMapper);
        this.couponModuleTimeMapper = couponModuleTimeMapper;
    }


    /**
     * 保存领劵秒杀的时间点
     * @Title
     * @Description
     */
    public void save(String startHours,String startMin,String decorateModuleId,Integer staffId) {
        CouponModuleTime couponModuleTime = new CouponModuleTime();
        couponModuleTime.setStartHours(startHours);
        couponModuleTime.setStartMin(startMin);
        couponModuleTime.setDecorateModuleId(Integer.parseInt(decorateModuleId));
        couponModuleTime.setCreateBy(staffId);
        couponModuleTime.setCreateDate(new Date());
        couponModuleTime.setDelFlag("0");
        couponModuleTimeMapper.insert(couponModuleTime);
    }
}
