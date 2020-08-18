package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.dao.MemberControlCustomMapper;
import com.jf.dao.MemberControlMapper;
import com.jf.entity.MemberControl;
import com.jf.entity.MemberControlCustom;
import com.jf.entity.MemberControlExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class MemberControlService extends BaseService<MemberControl, MemberControlExample> {
	@Autowired
	private MemberControlMapper memberControlMapper;
	@Autowired
	private MemberControlCustomMapper memberControlCustomMapper;
	@Autowired
	public void setMemberControlMapper(MemberControlMapper memberControlMapper) {
		this.setDao(memberControlMapper);
		this.memberControlMapper = memberControlMapper;
	}
	
	public int findMemberIsBlack(Integer memberId, String type, Integer inviteLimit) {
		//type 1签到 2签到助力 3帮忙砍价 4砍价 5邀请享免单 6SVIP领取月积分
		Date currentDate = new Date();
		String currentMonth = DateUtil.getFormatDate(currentDate, "yyyyMM");
		Date beginDate = DateUtil.getMonthBeginData(currentDate);
		Date endDate = DateUtil.getMonthEndData(currentDate);
		int count = 1;//>0都是正常的
		MemberControl memberControl = findModelByMemberId(memberId);
		if(memberControl == null || memberControl.getId() == null){
			//没有会员控制数据，只做插入，不进行任务操作
			memberControl.setMemberId(memberId);
			if("6".equals(type)){
				memberControl.setLastRecSvipIntegralDate(currentDate);
			}
			memberControl.setCreateDate(currentDate);
			memberControl.setCreateBy(memberId);
			memberControl.setUpdateBy(memberId);
			memberControl.setUpdateDate(currentDate);
			memberControl.setDelFlag("0");
			insertSelective(memberControl);
		}else{
			MemberControlCustom custom = new MemberControlCustom();
			Date lastRecSvipIntegralDate =  memberControl.getLastRecSvipIntegralDate();
			if("6".equals(type)){//SVIP
				if(lastRecSvipIntegralDate != null){
					if(DateUtil.getFormatDate(lastRecSvipIntegralDate, "yyyyMM").equals(currentMonth)){//判断当月是否已经领取过svip积分
						throw new ArgException("您当月已领取过积分，请等下月再次领取!");
					}
				}
				custom.setId(memberControl.getId());
				custom.setMemberId(memberId);
				custom.setLastRecSvipIntegralDate(currentDate);
				custom.setUpdateBy(memberId);
				custom.setUpdateDate(currentDate);
				custom.setBeginDate(beginDate);
				custom.setEndDate(endDate);
				count = memberControlCustomMapper.updateLastRecSvipIntegralDateModel(custom);
				if(count <= 0){
					throw new ArgException("您当月已领取过积分，请等下月再次领取!");
				}
			}
		}
		return count;
	}

	private MemberControl findModelByMemberId(Integer memberId) {
		MemberControl memberControl = new MemberControl();
		MemberControlExample memberControlExample = new MemberControlExample();
		memberControlExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		List<MemberControl> memberControls = selectByExample(memberControlExample);
		if(CollectionUtils.isNotEmpty(memberControls)){
			memberControl = memberControls.get(0);
		}
		return memberControl;
	}

	
}
