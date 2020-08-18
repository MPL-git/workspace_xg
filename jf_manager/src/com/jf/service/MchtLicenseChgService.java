package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.SmsUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.MchtInfoMapper;
import com.jf.dao.MchtLicenseChgCustomMapper;
import com.jf.dao.MchtLicenseChgMapper;
import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MchtLicenseChg;
import com.jf.entity.MchtLicenseChgCustom;
import com.jf.entity.MchtLicenseChgCustomExample;
import com.jf.entity.MchtLicenseChgExample;

@Service
@Transactional
public class MchtLicenseChgService extends BaseService<MchtLicenseChg,MchtLicenseChgExample> {
	@Autowired
	private MchtLicenseChgMapper mchtLicenseChgMapper;
	@Autowired
	private MchtLicenseChgCustomMapper mchtLicenseChgCustomMapper;
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Autowired
	private PlatformMsgService platformMsgService;
	
	@Autowired
	private MchtUserService mchtUserService;
	
	@Autowired
	private MchtPlatformContactService mchtPlatformContactService;
	
	@Autowired
	public void setMchtLicenseChgMapper(MchtLicenseChgMapper mchtLicenseChgMapper) {
		super.setDao(mchtLicenseChgMapper);
		this.mchtLicenseChgMapper = mchtLicenseChgMapper;
	}
	
	
	public void mchtLicenseChgHandleArchiveUpdate(HashMap<String, Object> paramMap){
		mchtLicenseChgCustomMapper.mchtLicenseChgHandleArchiveUpdate(paramMap);
		if(paramMap.containsKey("archiveDealStatus") && paramMap.get("archiveDealStatus").equals("2")){
		//法务审核驳回需发送短信给商家及平台
		MchtLicenseChg mchtLicenseChg = mchtLicenseChgMapper.selectByPrimaryKey((Integer)paramMap.get("id"));
		//发送站内信
		String title="关于经营许可证归档驳回";
		String content="您的经营许可证归资料归档被驳回，请在前往品牌管理中查看驳回原因。";
		platformMsgService.save(mchtLicenseChg.getMchtId(), title, content, "TZ");
		// 发送短信给商家端主账号
	    String mobile = mchtUserService.selectMobileByMchtId(mchtLicenseChg.getMchtId());
	    String mchtCode = mchtInfoMapper.selectByPrimaryKey(mchtLicenseChg.getMchtId()).getMchtCode();
	    content = "【醒购】您的店铺【"+mchtCode+"】的经营许可证归档驳回归档驳回，请登录平台查看驳回原因。";
	    SmsUtil.send(mobile, content, "4");
        // 发送短信给商家店铺负责人
        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtLicenseChg.getMchtId(), 1);
        if(!StringUtils.isEmpty(mobile)){
        	SmsUtil.send(mobile, content, "4");
        }
        // 发送短信给商家运营专员
        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtLicenseChg.getMchtId(), 2);
        if(!StringUtils.isEmpty(mobile)){
        	SmsUtil.send(mobile, content, "4");
        }
	    // 发送短信给平台招商员
	    mobile = mchtPlatformContactService.findMobile(mchtLicenseChg.getMchtId(), "1");
	    content = "【醒购】您对接的【" + mchtCode + "】的经营许可证归档驳回归档驳回，请尽快联系商家将资料重新寄回平台";
	    SmsUtil.send(mobile, content, "4");
		}
	}
	public int countMchtLicenseChgCustomByExample(MchtLicenseChgCustomExample example){
		return mchtLicenseChgCustomMapper.countByExample(example);
	}
    public List<MchtLicenseChgCustom> selectMchtLicenseChgCustomByExample(MchtLicenseChgCustomExample example){
    	return mchtLicenseChgCustomMapper.selectByExample(example);
    }

	public void update(MchtLicenseChg mchtLicenseChg, MchtLicenseChgExample e,Integer id) {
		this.updateByExampleSelective(mchtLicenseChg, e);
		MchtLicenseChg mlc = this.selectByPrimaryKey(id);
		MchtInfo mi = new MchtInfo();
		mi.setLicenseStatus("2");//2.通过
		mi.setBusinessLicensePic(mchtLicenseChg.getBusinessLicensePic());
		mi.setLicenseArchiveStatus("0");
		mi.setUpdateBy(mchtLicenseChg.getUpdateBy());
		mi.setUpdateDate(mchtLicenseChg.getUpdateDate());
		MchtInfoExample mie = new MchtInfoExample();
		mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mlc.getMchtId());
		mchtInfoMapper.updateByExampleSelective(mi, mie);
	}

	public void updateArchiveStatus(MchtLicenseChg mchtLicenseChg,MchtLicenseChgExample e, Integer id) {
		mchtLicenseChg.setArchiveStatus("3");//3.已归档
		this.updateByExampleSelective(mchtLicenseChg, e);
		MchtLicenseChg mlc = this.selectByPrimaryKey(id);
		MchtInfo mi = new MchtInfo();
		mi.setLicenseArchiveStatus("1");//已归档
		mi.setBusinessLicensePic(mlc.getBusinessLicensePic());//行业经营许可证图片更新为更新表的图片
		mi.setUpdateBy(mchtLicenseChg.getUpdateBy());
		mi.setUpdateDate(mchtLicenseChg.getUpdateDate());
		MchtInfoExample mie = new MchtInfoExample();
		mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mlc.getMchtId());
		mchtInfoMapper.updateByExampleSelective(mi, mie);
	}
	
	public void copyToMchtLicenseChg(Integer mchtInfoId){ 
		MchtInfo mchtInfo = mchtInfoMapper.selectByPrimaryKey(mchtInfoId);
		MchtLicenseChg mchtLicenseChg = new MchtLicenseChg();
		mchtLicenseChg.setMchtId(mchtInfoId);
		mchtLicenseChg.setBusinessLicensePic(mchtInfo.getBusinessLicensePic());
		mchtLicenseChg.setAuditStatus("1");//1.审核通过
		mchtLicenseChg.setArchiveStatus("0");//0.未寄出
		mchtLicenseChg.setArchiveDealStatus("0");//0.未处理
		mchtLicenseChg.setCreateBy(mchtInfo.getCreateBy());
		mchtLicenseChg.setCreateDate(mchtInfo.getCreateDate());
		mchtLicenseChg.setDelFlag("0");
		this.insertSelective(mchtLicenseChg);
	}
}
