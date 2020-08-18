package com.jf.service;

import com.jf.dao.MemberLotteryCustomMapper;
import com.jf.dao.MemberLotteryMapper;
import com.jf.entity.MemberLottery;
import com.jf.entity.MemberLotteryCustom;
import com.jf.entity.MemberLotteryCustomExample;
import com.jf.entity.MemberLotteryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberLotteryService extends BaseService<MemberLottery, MemberLotteryExample>{
	@Autowired
	private MemberLotteryMapper memberLotteryMapper;
	@Autowired
	private MemberLotteryCustomMapper memberLotteryCustomMapper;

	@Autowired
	public void setMemberLotteryMapper(MemberLotteryMapper memberLotteryMapper) {
		super.setDao(memberLotteryMapper);
		this.memberLotteryMapper = memberLotteryMapper;
	}

	public Integer countMemberLotteryCustomByExample(MemberLotteryCustomExample example) {
		return memberLotteryCustomMapper.countMemberLotteryCustomByExample(example);
	}

	public List<MemberLotteryCustom> selectMemberLotteryCustomByExample(MemberLotteryCustomExample example) {
		return memberLotteryCustomMapper.selectMemberLotteryCustomByExample(example);
	}
}
