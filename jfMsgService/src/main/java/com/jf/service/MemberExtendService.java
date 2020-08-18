package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MemberExtendCustomMapper;
import com.jf.dao.MemberExtendMapper;
import com.jf.entity.MemberExtend;
import com.jf.entity.MemberExtendCustom;
import com.jf.entity.MemberExtendExample;
import com.jf.task.JpushTask;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MemberExtendService extends BaseService<MemberExtend, MemberExtendExample> {
	private static Logger logger = LoggerFactory.getLogger(MemberExtendService.class);
	private static Integer EXCEPTION_NUM = 0;
	@Autowired
	private MemberExtendMapper memberExtendMapper;
	@Autowired
	private MemberExtendCustomMapper memberExtendCustomMapper;
	@Autowired
	private CommonService commonService;
	@Autowired
	public void setMemberExtendMapper(MemberExtendMapper memberExtendMapper) {
		this.setDao(memberExtendMapper);
		this.memberExtendMapper = memberExtendMapper;
	}

	public List<MemberExtend> findModelByMemberId(Integer memberId) {
		MemberExtendExample memberExtendExample = new MemberExtendExample();
		memberExtendExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		return selectByExample(memberExtendExample);
	}
	
	public void findSignInRemindMember(Integer currentPage,Integer pageSize) {
		logger.info("签到提醒推送: currentPage{}pageSize{}", currentPage, pageSize);
		try {
//			if(currentPage <= 5){
//				throw new ArgException("test");
//			}
			Map<String, Integer> paramsMap = new HashMap<>();
			paramsMap.put("currentPage", currentPage * pageSize);
			paramsMap.put("pageSize", pageSize);
			List<MemberExtendCustom> memberExtends = memberExtendCustomMapper.findSignInRemindMember(paramsMap);
			if(CollectionUtils.isNotEmpty(memberExtends)){
				logger.info("扫描到的推送用户：{}", memberExtends.size());
				List<String> aliasList = new ArrayList<String>();
				Map<String, String> extras = new HashMap<String, String>();
				String alert = "签到即得积分，现金红包，更有海量神券和惊喜累签大礼包等你来！";
				for (MemberExtendCustom memberExtend : memberExtends) {
					aliasList.add(memberExtend.getMemberId().toString());
				}
				extras.put("source", "107");
				extras.put("name", alert);
				extras.put("activityAreaId", "");
				extras.put("activityId", "");
				extras.put("id", "");
				extras.put("remark1", commonService.getCloumnLinkUrl("", "1"));
				extras.put("remark2", "");
				extras.put("remark3", "");
				extras.put("title", "【醒购喊你来签到了！】");
				if(CollectionUtils.isNotEmpty(aliasList)){
					logger.info("每日10:30签到提醒推送: start");
					JpushTask.sendAlias(alert, aliasList, extras);
					logger.info("每日10:30签到提醒推送: end");
				}
				if(memberExtends.size() < pageSize){
					return;
				}else{
					currentPage++;
					findSignInRemindMember(currentPage, pageSize);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(EXCEPTION_NUM > 2){//异常超过5次直接跳出不跑了
				return;
			}
			EXCEPTION_NUM++;
			currentPage++;
			findSignInRemindMember(currentPage, pageSize);
		}
	}

    public void updateCouponRemindStatus(List<Integer> aliasList) {
		MemberExtendExample memberExtendExample = new MemberExtendExample();
		memberExtendExample.createCriteria().andMemberIdIn(aliasList).andDelFlagEqualTo("0");
		MemberExtend memberExtend = new MemberExtend();
		memberExtend.setNewUser500CouponRemind("1");
		memberExtendMapper.updateByExampleSelective(memberExtend,memberExtendExample);
    }
}
