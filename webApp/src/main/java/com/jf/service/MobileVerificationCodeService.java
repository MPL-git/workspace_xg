package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.MobileVerificationCodeMapper;
import com.jf.entity.MobileVerificationCode;
import com.jf.entity.MobileVerificationCodeExample;

@Service
@Transactional
public class MobileVerificationCodeService extends BaseService<MobileVerificationCode,MobileVerificationCodeExample> {
	@Autowired
	private MobileVerificationCodeMapper mobileVerificationCodeMapper;
	
	@Autowired
	public void setMobileVerificationCodeMapper(MobileVerificationCodeMapper mobileVerificationCodeMapper) {
		super.setDao(mobileVerificationCodeMapper);
		this.mobileVerificationCodeMapper = mobileVerificationCodeMapper;
	}
	
	public boolean verify(String mobile,String verificationCode){
		MobileVerificationCodeExample mobileVerificationCodeExample=new MobileVerificationCodeExample();
		mobileVerificationCodeExample.createCriteria().andMobileEqualTo(mobile).andIsCheckedEqualTo("0").andDelFlagEqualTo("0");
		mobileVerificationCodeExample.setOrderByClause("create_date desc");
		
		List<MobileVerificationCode> mobileVerificationCodes=mobileVerificationCodeMapper.selectByExample(mobileVerificationCodeExample);
		if(mobileVerificationCodes==null||mobileVerificationCodes.size()==0||!mobileVerificationCodes.get(0).getVerificationCode().equals(verificationCode)){
			return false;
		}else{
			MobileVerificationCode mobileVerificationCode =	mobileVerificationCodes.get(0);
			mobileVerificationCode.setIsChecked("1");
			mobileVerificationCode.setUpdateDate(new Date());
			mobileVerificationCodeMapper.updateByPrimaryKeySelective(mobileVerificationCode);
			return true;
		}
		
	}

	public MobileVerificationCode findModelByMobile(String mobile) {
		MobileVerificationCode mobileVerificationCode=new MobileVerificationCode();
		MobileVerificationCodeExample mobileVerificationCodeExample=new MobileVerificationCodeExample();
		mobileVerificationCodeExample.createCriteria().andMobileEqualTo(mobile).andDelFlagEqualTo("0");
		mobileVerificationCodeExample.setOrderByClause("create_date desc");
		List<MobileVerificationCode> mobileVerificationCodes=mobileVerificationCodeMapper.selectByExample(mobileVerificationCodeExample);
		if(CollectionUtils.isNotEmpty(mobileVerificationCodes)){
			mobileVerificationCode = mobileVerificationCodes.get(0);
			return mobileVerificationCode;
		}
		return null;
	}
}
