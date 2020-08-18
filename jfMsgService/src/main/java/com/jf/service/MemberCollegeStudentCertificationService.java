package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CombineOrderMapper;
import com.jf.dao.MemberCollegeStudentCertificationMapper;
import com.jf.dao.MemberInfoMapper;
import com.jf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MemberCollegeStudentCertificationService extends BaseService<MemberCollegeStudentCertification,MemberCollegeStudentCertificationExample> {
	@Autowired
	private MemberCollegeStudentCertificationMapper memberCollegeStudentCertificationMapper;

	@Autowired
	private CombineOrderMapper combineOrderMapper;

	@Autowired
	private MemberInfoMapper memberInfoMapper;

	@Autowired
	private SmsService smsService;

	@Autowired
	private keyValueService keyValueService;
	
	@Resource
    private CombineOrderService combineOrderService;


	@Autowired
	public void setMemberCollegeStudentCertificationMapper(MemberCollegeStudentCertificationMapper memberCollegeStudentCertificationMapper) {
		super.setDao(memberCollegeStudentCertificationMapper);
		this.memberCollegeStudentCertificationMapper = memberCollegeStudentCertificationMapper;
	}

	public void memberCollegeStudentCertificationData(MemberCollegeStudentCertification memberCollegeStudentCertification) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("memberId", memberCollegeStudentCertification.getMemberId());
        List<CombineOrderCustom> combineOrderList=combineOrderService.getCombineOrderList(paramsMap);
		if (combineOrderList.size()<=0){
			MemberInfo memberInfo=memberInfoMapper.selectByPrimaryKey(memberCollegeStudentCertification.getMemberId());
			if (memberInfo.getMobile()!=null && !memberInfo.getMobile().equals("")){
				KeyValueExample keyValueExample=new KeyValueExample();
				keyValueExample.createCriteria().andTypeCodeEqualTo("smsNotice1").andMKeyEqualTo(memberInfo.getMobile()).andDelFlagEqualTo("0");
				List<KeyValue> keyValues=keyValueService.selectByExample(keyValueExample);
				if (keyValues.size()<=0) {
					Sms sms = new Sms();
					sms.setMobile(memberInfo.getMobile());
					sms.setSmsType("4");
					sms.setMemberId(memberInfo.getId());
					sms.setContent("恭喜您认证成功，获得1分领取防疫套装资格，数量有限，请尽快下单领取 https://dwz.cn/dPoesWht 退订回T");
					smsService.sendImmediately(sms);
					KeyValue keyValue=new KeyValue();
					keyValue.setTypeCode("smsNotice1");//类型标识:上传认证信息10分钟后未下单
					keyValue.setmKey(memberInfo.getMobile());
					keyValue.setReservedInt(0);
					keyValue.setCreateDate(new Date());
					keyValue.setDelFlag("0");
					keyValueService.insert(keyValue);
				}
			}

		}
	}

}
