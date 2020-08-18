package com.jf.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtSettledApplyMapper;
import com.jf.entity.MchtSettledApply;
import com.jf.entity.MchtSettledApplyExample;
import com.jf.entity.MchtSettledApplyPic;
import com.jf.entity.MemberExtend;
import com.jf.entity.MemberExtendExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.ProductType;

@Service
@Transactional
public class MchtSettledApplyService extends BaseService<MchtSettledApply,MchtSettledApplyExample> {
	@Autowired
	private MchtSettledApplyMapper mchtSettledApplyMapper;
	@Resource
	private MchtSettledApplyPicService mchtSettledApplyPicService;
	@Resource
	private MemberExtendService memberExtendService;
	@Resource
	private ProductTypeService productTypeService;
	@Resource
	private MemberInfoService memberInfoService;
	
	@Autowired
	public void setMchtSettledApplyMapper(MchtSettledApplyMapper mchtSettledApplyMapper) {
		super.setDao(mchtSettledApplyMapper);
		this.mchtSettledApplyMapper = mchtSettledApplyMapper;
	}


	public void saveMchtApply(HttpServletRequest request, MchtSettledApply mchtSettledApply) {
		Date date = new Date();
		String ip = StringUtil.getIpAddr(request);
		if(StringUtil.isEmpty(mchtSettledApply.getRegFeeType())){
			mchtSettledApply.setRegFeeType("CNY");
		}
		mchtSettledApply.setCreateDate(date);
		mchtSettledApply.setClientType("2");
		mchtSettledApply.setSourceType("9");
		mchtSettledApply.setCustomerSource("1");
		mchtSettledApply.setIp(ip);
		String memberId = request.getParameter("memberId");
		MemberExtend memberExtend = new MemberExtend();
		if(StringUtil.isEmpty(memberId)){//pc web网页 H5来入驻
			MemberInfoExample example = new MemberInfoExample();
			example.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(mchtSettledApply.getPhone());
			List<MemberInfo> memberInfos = memberInfoService.selectByExample(example);
			if(memberInfos!=null && memberInfos.size()>0){
				memberId = memberInfos.get(0).getId().toString();
				MemberExtendExample e = new MemberExtendExample();
				e.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(memberInfos.get(0).getId());
				List<MemberExtend> memberExtends = memberExtendService.selectByExample(e);
				if(memberExtends!=null && memberExtends.size()>0){
					memberExtend = memberExtends.get(0);
					if(memberExtend.getZsPlatformContactId()!=null){
						if(mchtSettledApply.getPlatformContactId()==null){
							mchtSettledApply.setPlatformContactId(memberExtend.getZsPlatformContactId());
						}
					}
				}
			}
		}else{
			//通过APP来入驻
			MemberExtendExample e = new MemberExtendExample();
			e.createCriteria().andDelFlagEqualTo("0").andMemberIdEqualTo(Integer.parseInt(memberId));
			List<MemberExtend> memberExtends = memberExtendService.selectByExample(e);
			if(memberExtends!=null && memberExtends.size()>0){
				memberExtend = memberExtends.get(0);
				if(memberExtend.getZsPlatformContactId()!=null){
					if(mchtSettledApply.getPlatformContactId()==null){
						mchtSettledApply.setPlatformContactId(memberExtend.getZsPlatformContactId());
					}
				}
			}
		}
		if(mchtSettledApply.getProductTypeId()!=null){
			ProductType productType = productTypeService.selectByPrimaryKey(mchtSettledApply.getProductTypeId());
			mchtSettledApply.setProductTypeMain(productType.getName());
		}
		
		this.insertSelective(mchtSettledApply);
		if(!StringUtil.isEmpty(memberId)){
			MemberExtend me = new MemberExtend();
			me.setCreateDate(new Date());
			me.setDelFlag("0");
			me.setMemberId(Integer.parseInt(memberId));
			me.setZsPlatformContactId(mchtSettledApply.getPlatformContactId());
			me.setMchtSettledApplyId(mchtSettledApply.getId());
			memberExtendService.insertSelective(me);
		}
		String pics = request.getParameter("pics");
		String productPic = request.getParameter("productPic");
		if(!StringUtil.isBlank(pics)){
			String[] picList = pics.split(",");
			for (String pic : picList) {
				pic = StringUtil.replace(pic,"xgbuy.cc");
				MchtSettledApplyPic mchtSettledApplyPic = new MchtSettledApplyPic();
				mchtSettledApplyPic.setSettledApplyId(mchtSettledApply.getId());
				mchtSettledApplyPic.setType("1");
				mchtSettledApplyPic.setPic(pic);
				mchtSettledApplyPic.setCreateDate(date);
				mchtSettledApplyPic.setDelFlag("0");
				mchtSettledApplyPicService.insertSelective(mchtSettledApplyPic);
			}
		}
		if(!StringUtil.isBlank(productPic)){
			String[] picList = productPic.split(",");
			for (String pic : picList) {
				pic = StringUtil.replace(pic,"xgbuy.cc");
				MchtSettledApplyPic mchtSettledApplyPic = new MchtSettledApplyPic();
				mchtSettledApplyPic.setSettledApplyId(mchtSettledApply.getId());
				mchtSettledApplyPic.setType("2");
				mchtSettledApplyPic.setPic(pic);
				mchtSettledApplyPic.setCreateDate(date);
				mchtSettledApplyPic.setDelFlag("0");
				mchtSettledApplyPicService.insertSelective(mchtSettledApplyPic);
			}
		}
	}

}
