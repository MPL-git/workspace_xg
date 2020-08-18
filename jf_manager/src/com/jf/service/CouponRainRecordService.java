package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponRainRecordCustomMapper;
import com.jf.dao.CouponRainRecordMapper;
import com.jf.entity.CouponRainRecord;
import com.jf.entity.CouponRainRecordCustom;
import com.jf.entity.CouponRainRecordCustomExample;
import com.jf.entity.CouponRainRecordExample;

@Service
@Transactional
public class CouponRainRecordService extends BaseService<CouponRainRecord,CouponRainRecordExample> {
	@Autowired
	private CouponRainRecordMapper couponRainRecordMapper;
	@Autowired
	private CouponRainRecordCustomMapper couponRainRecordCustomMapper;
	
	@Autowired
	public void setCouponRainRecordMapper(CouponRainRecordMapper couponRainRecordMapper) {
		super.setDao(couponRainRecordMapper);
		this.couponRainRecordMapper = couponRainRecordMapper;
	}
	
	public int countByCouponRainRecordCustomExample(CouponRainRecordCustomExample example){
		return couponRainRecordCustomMapper.countByCouponRainRecordCustomExample(example);
	}
    public List<CouponRainRecordCustom> selectByCouponRainSetupCustomExample(CouponRainRecordCustomExample example){
    	return couponRainRecordCustomMapper.selectByCouponRainRecordCustomExample(example);
    }
    public CouponRainRecordCustom selectByCouponRainRecordCustomPrimaryKey(Integer id){
    	return couponRainRecordCustomMapper.selectByCouponRainRecordCustomPrimaryKey(id);
    }

}
