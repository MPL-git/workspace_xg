package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberCouponLogMapper;
import com.jf.entity.MemberCouponLog;
import com.jf.entity.MemberCouponLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class MemberCouponLogService extends BaseService<MemberCouponLog, MemberCouponLogExample> {
	@Autowired
	private MemberCouponLogMapper memberCouponLogMapper;
	@Autowired
	public void setMemberCouponLogMapper(MemberCouponLogMapper memberCouponLogMapper) {
		this.setDao(memberCouponLogMapper);
		this.memberCouponLogMapper = memberCouponLogMapper;
	}
	public void addLog(Integer memberCouponId, String logType,Integer orderId,Integer memberId,String remarks) {
		MemberCouponLog log = new MemberCouponLog();
		log.setMemberCouponId(memberCouponId);
		log.setLogType(logType);
		log.setOrderId(orderId);
		log.setCreateBy(memberId);
		log.setCreateDate(new Date());
		log.setRemarks(remarks);
		insertSelective(log);
	}
}
