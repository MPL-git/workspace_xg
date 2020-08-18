package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jf.common.base.ArgException;
import com.jf.common.utils.DateUtil;
import com.jf.dao.MemberControlCustomMapper;
import com.jf.dao.MemberControlMapper;
import com.jf.entity.MemberControl;
import com.jf.entity.MemberControlCustom;
import com.jf.entity.MemberControlExample;

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
		//type 1签到 2签到助力 3帮忙砍价 4砍价 5邀请享免单
		Date currentDate = new Date();
		int count = 1;//>0都是正常的
		MemberControl memberControl = findModelByMemberId(memberId);
		if(memberControl == null || memberControl.getId() == null){
			//没有会员控制数据，只做插入，不进行任务操作
			memberControl.setMemberId(memberId);
			if("1".equals(type)){
				memberControl.setLastSignInDate(currentDate);
			}else if("2".equals(type)){
				memberControl.setSignBeHelpedCount(1);
				memberControl.setSignBeHelpedDate(currentDate);
			}else if("3".equals(type)){
				memberControl.setLastHelpCutPriceDate(currentDate);
				memberControl.setHelpCutPriceCount(1);
			}else if("4".equals(type)){
				memberControl.setLastCutPriceDate(currentDate);
				memberControl.setCutPriceCount(1);
			}else if("5".equals(type)){
				memberControl.setLastInviteDate(currentDate);
				memberControl.setInviteCount(1);
			}
			memberControl.setCreateDate(currentDate);
			memberControl.setCreateBy(memberId);
			memberControl.setUpdateBy(memberId);
			memberControl.setUpdateDate(currentDate);
			memberControl.setDelFlag("0");
			insertSelective(memberControl);
		}else{
			MemberControlCustom custom = new MemberControlCustom();
			//有会员控制数据
			Date lastSignInDate = memberControl.getLastSignInDate();//最后签到时间
			Integer signBeHelpedCount = memberControl.getSignBeHelpedCount();//签到最后被助力当天被助力次数
//			Date signBeHelpedDate = memberControl.getSignBeHelpedDate();//签到最后被助力时间
//			Date lastHelpCutPriceDate = memberControl.getLastHelpCutPriceDate();//最后帮人砍价时间
//			Integer helpCutPriceCount = memberControl.getHelpCutPriceCount();//最后帮人砍价当天帮人砍价次数
			Date beginLastSignInDate = DateUtil.getDateAfterAndBeginTime(currentDate, 0);
			Date endLastSignInDate = DateUtil.getDateAfterAndEndTime(currentDate, 0);
			if("1".equals(type)){//签到
				if(lastSignInDate != null){
					//判断当天是否已经签到过
					if(lastSignInDate.compareTo(beginLastSignInDate) >= 0 && lastSignInDate.compareTo(endLastSignInDate) <= 0){
						//走到这即表示当天已签到过
						throw new ArgException("今天已经签到到过了哦,请勿重复签到..！");
					}
				}
				custom.setId(memberControl.getId());
				custom.setLastSignInDate(currentDate);
				custom.setUpdateBy(memberId);
				custom.setUpdateDate(currentDate);
				custom.setBeginDate(beginLastSignInDate);
				custom.setEndDate(endLastSignInDate);
				count = memberControlCustomMapper.updateLastSignInDateModel(custom);
				if(count <= 0){
					throw new ArgException("今天已经签到到过了哦,请勿重复签到...！");
				}
			}else if("2".equals(type)){//签到助力
				custom.setId(memberControl.getId());
				custom.setSignBeHelpedDate(currentDate);
				custom.setSignBeHelpedCount(signBeHelpedCount);
				custom.setUpdateBy(memberId);
				custom.setUpdateDate(currentDate);
				custom.setBeginDate(beginLastSignInDate);
				custom.setEndDate(endLastSignInDate);
				custom.setInviteLimit(inviteLimit);//6次，暂时没用，直接sql注入
				count = memberControlCustomMapper.updateSignBeHelpedDateModel(custom);
			}else if("3".equals(type)){//帮忙砍价(每个用户每天最多只能砍2次)
				custom.setId(memberControl.getId());
				custom.setLastHelpCutPriceDate(currentDate);
				custom.setUpdateBy(memberId);
				custom.setUpdateDate(currentDate);
				custom.setBeginDate(beginLastSignInDate);
				custom.setEndDate(endLastSignInDate);
				custom.setInviteLimit(inviteLimit);//2次，暂时没用，直接sql注入
				count = memberControlCustomMapper.updateLastHelpCutPriceDateModel(custom);
				if(count <= 0){
					throw new ArgException("每个用户每天最多只能砍2次哦");
				}
			}else if("4".equals(type)){//砍价(每个用户每天最多只能砍1次)
				custom.setId(memberControl.getId());
				custom.setLastCutPriceDate(currentDate);
				custom.setInviteLimit(inviteLimit);//1次，暂时没用，直接sql注入
				custom.setUpdateBy(memberId);
				custom.setUpdateDate(currentDate);
				custom.setBeginDate(beginLastSignInDate);
				custom.setEndDate(endLastSignInDate);
				count = memberControlCustomMapper.updateLastCutPriceDateModel(custom);
				if(count <= 0){
					throw new ArgException("每人每天只能发起一次砍价免费拿哦！");
				}
			}else if("5".equals(type)){//邀请享免单(每个用户每天最多只能砍1次)
				custom.setId(memberControl.getId());
				custom.setLastInviteDate(currentDate);
				custom.setInviteLimit(inviteLimit);//1次，暂时没用，直接sql注入
				custom.setUpdateBy(memberId);
				custom.setUpdateDate(currentDate);
				custom.setBeginDate(beginLastSignInDate);
				custom.setEndDate(endLastSignInDate);
				count = memberControlCustomMapper.updateLastInviteDateModel(custom);
				if(count <= 0){
					throw new ArgException("每人每天只能发起一次邀请享免单哦！");
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
