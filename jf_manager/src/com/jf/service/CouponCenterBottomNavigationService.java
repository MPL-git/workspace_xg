package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CouponCenterBottomNavigationCustomMapper;
import com.jf.dao.CouponCenterBottomNavigationMapper;
import com.jf.entity.CouponCenterBottomNavigation;
import com.jf.entity.CouponCenterBottomNavigationCustom;
import com.jf.entity.CouponCenterBottomNavigationExample;

@Service
@Transactional
public class CouponCenterBottomNavigationService extends BaseService<CouponCenterBottomNavigation, CouponCenterBottomNavigationExample> {
	
	@Autowired
	private CouponCenterBottomNavigationMapper couponCenterBottomNavigationMapper;
	
	@Autowired
	private CouponCenterBottomNavigationCustomMapper couponCenterBottomNavigationCustomMapper;

	@Autowired
	public void setCouponCenterBottomNavigationMapper(CouponCenterBottomNavigationMapper couponCenterBottomNavigationMapper) {
		super.setDao(couponCenterBottomNavigationMapper);
		this.couponCenterBottomNavigationMapper = couponCenterBottomNavigationMapper;
	}
	
	public List<CouponCenterBottomNavigationCustom> selectByCouponCustomExample(CouponCenterBottomNavigationExample example){
		return couponCenterBottomNavigationCustomMapper.selectByCouponCustomExample(example);
	};
	
	public void exchangeSeq(Integer id ,Integer seqNo) {
		CouponCenterBottomNavigationExample couponCenterBottomNavigationExample = new CouponCenterBottomNavigationExample();
		couponCenterBottomNavigationExample.createCriteria().andDelFlagEqualTo("0").andSeqNoEqualTo(seqNo);
		List<CouponCenterBottomNavigation> changeOne = couponCenterBottomNavigationMapper.selectByExample(couponCenterBottomNavigationExample);
		CouponCenterBottomNavigation changeTwo = couponCenterBottomNavigationMapper.selectByPrimaryKey(id);
		if(changeOne.size()>0 && changeOne != null  && changeTwo!= null ){
			CouponCenterBottomNavigation couponCenterBottomNavigation = changeOne.get(0);
			int template = couponCenterBottomNavigation.getSeqNo();
			couponCenterBottomNavigation.setSeqNo(changeTwo.getSeqNo());
			changeTwo.setSeqNo(template);
			couponCenterBottomNavigationMapper.updateByPrimaryKeySelective(changeTwo);
			couponCenterBottomNavigationMapper.updateByPrimaryKeySelective(couponCenterBottomNavigation);
			
		}
	}

}
