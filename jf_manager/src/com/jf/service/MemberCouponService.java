package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MemberCouponCustomMapper;
import com.jf.dao.MemberCouponMapper;
import com.jf.entity.MemberCoupon;
import com.jf.entity.MemberCouponCustom;
import com.jf.entity.MemberCouponExample;

@Service
@Transactional
public class MemberCouponService extends BaseService<MemberCoupon,MemberCouponExample> {
	@Autowired
	private MemberCouponMapper memberCouponMapper;
	@Autowired
	private MemberCouponCustomMapper memberCouponCustomMapper;
	
	@Autowired
	public void setMemberCouponMapper(MemberCouponMapper memberCouponMapper) {
		super.setDao(memberCouponMapper);
		this.memberCouponMapper = memberCouponMapper;
	}
	
	public int countMemberCouponCustomByExample(MemberCouponExample example){
		return memberCouponCustomMapper.countByExample(example);
	}
    public List<MemberCouponCustom> selectMemberCouponCustomByExample(MemberCouponExample example){
    	return memberCouponCustomMapper.selectByExample(example);
    }
    public MemberCouponCustom selectMemberCouponCustomByPrimaryKey(Integer id){
    	return memberCouponCustomMapper.selectByPrimaryKey(id);
    }
}
