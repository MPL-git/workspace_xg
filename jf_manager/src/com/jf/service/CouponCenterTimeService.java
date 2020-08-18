package com.jf.service;

import com.jf.common.utils.StringUtils;
import com.jf.dao.CouponCenterTimeMapper;
import com.jf.entity.CouponCenterTime;
import com.jf.entity.CouponCenterTimeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class CouponCenterTimeService extends BaseService<CouponCenterTime,CouponCenterTimeExample> {
	@Autowired
	private CouponCenterTimeMapper couponCenterTimeMapper;

	@Autowired
	public void setCouponCenterTimeMapper(CouponCenterTimeMapper couponCenterTimeMapper) {
		super.setDao(couponCenterTimeMapper);
		this.couponCenterTimeMapper = couponCenterTimeMapper;
	}

    public void saveEditCouponCenterTime(CouponCenterTime couponCenterTime,Integer staffID) {
		if(!StringUtils.isEmpty(couponCenterTime.getId())){
			couponCenterTime.setUpdateBy(staffID);
			couponCenterTime.setUpdateDate(new Date());
			couponCenterTime.setStatus("0");//关闭
			this.updateByPrimaryKeySelective(couponCenterTime);
		}else{
			couponCenterTime.setCreateBy(staffID);
			couponCenterTime.setCreateDate(new Date());
			couponCenterTime.setStatus("0");//关闭
			couponCenterTime.setDelFlag("0");
			this.insertSelective(couponCenterTime);
		}
    }


}
