package com.jf.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.SmsUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.MchtBrandAptitudeChgMapper;
import com.jf.dao.MchtBrandAptitudeMapper;
import com.jf.dao.MchtBrandAptitudePicChgMapper;
import com.jf.dao.MchtBrandAptitudePicMapper;
import com.jf.dao.MchtBrandChgCustomMapper;
import com.jf.dao.MchtBrandChgMapper;
import com.jf.dao.MchtBrandChgProductTypeMapper;
import com.jf.dao.MchtBrandInspectionPicChgMapper;
import com.jf.dao.MchtBrandInspectionPicMapper;
import com.jf.dao.MchtBrandInvoicePicChgMapper;
import com.jf.dao.MchtBrandInvoicePicMapper;
import com.jf.dao.MchtBrandOtherPicChgMapper;
import com.jf.dao.MchtBrandOtherPicMapper;
import com.jf.dao.MchtBrandProductTypeMapper;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.dao.MchtPlatformAuthPicChgMapper;
import com.jf.dao.MchtPlatformAuthPicMapper;
import com.jf.dao.MchtProductBrandMapper;
import com.jf.entity.MchtBrandAptitude;
import com.jf.entity.MchtBrandAptitudeChg;
import com.jf.entity.MchtBrandAptitudeExample;
import com.jf.entity.MchtBrandAptitudePic;
import com.jf.entity.MchtBrandAptitudePicChg;
import com.jf.entity.MchtBrandAptitudePicChgExample;
import com.jf.entity.MchtBrandAptitudePicExample;
import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtBrandChgCustom;
import com.jf.entity.MchtBrandChgExample;
import com.jf.entity.MchtBrandChgProductType;
import com.jf.entity.MchtBrandChgProductTypeCustom;
import com.jf.entity.MchtBrandChgProductTypeExample;
import com.jf.entity.MchtBrandInspectionPic;
import com.jf.entity.MchtBrandInspectionPicChg;
import com.jf.entity.MchtBrandInspectionPicChgExample;
import com.jf.entity.MchtBrandInspectionPicExample;
import com.jf.entity.MchtBrandInvoicePic;
import com.jf.entity.MchtBrandInvoicePicChg;
import com.jf.entity.MchtBrandInvoicePicChgExample;
import com.jf.entity.MchtBrandInvoicePicExample;
import com.jf.entity.MchtBrandOtherPic;
import com.jf.entity.MchtBrandOtherPicChg;
import com.jf.entity.MchtBrandOtherPicChgExample;
import com.jf.entity.MchtBrandOtherPicExample;
import com.jf.entity.MchtBrandProductType;
import com.jf.entity.MchtBrandProductTypeExample;
import com.jf.entity.MchtInfoChangeLog;
import com.jf.entity.MchtPlatformAuthPic;
import com.jf.entity.MchtPlatformAuthPicChg;
import com.jf.entity.MchtPlatformAuthPicChgExample;
import com.jf.entity.MchtPlatformAuthPicExample;
import com.jf.entity.MchtProductBrand;

@Service
@Transactional
public class MchtBrandChgService extends BaseService<MchtBrandChg, MchtBrandChgExample>{
	@Autowired
	private MchtBrandChgCustomMapper mchtBrandChgCustomMapper;
	
	@Autowired
	private MchtBrandChgMapper mchtBrandChgMapper;
	
	@Autowired
	private MchtProductBrandMapper mchtProductBrandMapper;
	
	
	@Autowired
	private MchtPlatformAuthPicMapper mchtPlatformAuthPicMapper;
	@Autowired
	private MchtPlatformAuthPicChgMapper mchtPlatformAuthPicChgMapper;
	
	@Autowired
	private MchtBrandAptitudePicChgMapper mchtBrandAptitudePicChgMapper;
	
	@Autowired
	private MchtBrandAptitudePicMapper mchtBrandAptitudePicMapper;
	
	@Autowired
	private MchtBrandInvoicePicMapper mchtBrandInvoicePicMapper;
	
	@Autowired
	private MchtBrandInvoicePicChgMapper mchtBrandInvoicePicChgMapper;
	
	@Autowired
	private MchtBrandInspectionPicMapper mchtBrandInspectionPicMapper;
	@Autowired
	private MchtBrandInspectionPicChgMapper mchtBrandInspectionPicChgMapper;
	
	@Autowired
	private MchtBrandOtherPicMapper mchtBrandOtherPicMapper;
	@Autowired
	private MchtBrandOtherPicChgMapper mchtBrandOtherPicChgMapper;
	
	@Autowired
	private MchtBrandAptitudeMapper mchtBrandAptitudeMapper;
	
	@Autowired
	private MchtBrandProductTypeService mchtBrandProductTypeService;
	
	@Autowired
	private MchtBrandChgProductTypeService mchtBrandChgProductTypeService;
	
	@Autowired
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Autowired
	private PlatformMsgService platformMsgService;
	
	@Autowired
	private MchtUserService mchtUserService;
	
	@Autowired
	private MchtPlatformContactService mchtPlatformContactService;
	
	@Autowired
	private MchtBrandAptitudeChgMapper mchtBrandAptitudeChgMapper;
	
	@Autowired
	private MchtBrandProductTypeMapper mchtBrandProductTypeMapper;
	
	@Autowired
	private MchtBrandChgProductTypeMapper mchtBrandChgProductTypeMapper;
	
	@Autowired
	public void setMchtProductBrandMapper(MchtBrandChgMapper mchtBrandChgMapper) {
		super.setDao(mchtBrandChgMapper);
		this.mchtBrandChgMapper = mchtBrandChgMapper;
	}
	
	public void mchtBrandChgHandleArchiveUpdate(HashMap<String, Object> paramMap){
		mchtBrandChgCustomMapper.mchtBrandChgHandleArchiveUpdate(paramMap);
		if(paramMap.containsKey("archiveDealStatus") && paramMap.get("archiveDealStatus").equals("2")){
		//法务审核驳回需发送短信给商家及平台
		MchtBrandChg mchtBrandChg = mchtBrandChgMapper.selectByPrimaryKey((Integer)paramMap.get("id"));
		//发送站内信
		String title="关于品牌资质归档驳回";
		String content="您的【"+mchtBrandChg.getProductBrandName()+"】资料归档被驳回，请在前往品牌管理中查看驳回原因。";
		platformMsgService.save(mchtBrandChg.getMchtId(), title, content, "TZ");
		// 发送短信给商家端主账号
	    String mobile = mchtUserService.selectMobileByMchtId(mchtBrandChg.getMchtId());
	    String mchtCode = mchtInfoMapper.selectByPrimaryKey(mchtBrandChg.getMchtId()).getMchtCode();
	    content = "【醒购】您的店铺【"+mchtCode+"】的【"+mchtBrandChg.getProductBrandName()+"】归档驳回，请登录平台查看驳回原因。";
	    SmsUtil.send(mobile, content, "4");
        // 发送短信给商家店铺负责人
        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtBrandChg.getMchtId(), 1);
        if(!StringUtils.isEmpty(mobile)){
        	SmsUtil.send(mobile, content, "4");
        }
        // 发送短信给商家运营专员
        mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtBrandChg.getMchtId(), 2);
        if(!StringUtils.isEmpty(mobile)){
        	SmsUtil.send(mobile, content, "4");
        }
	    // 发送短信给平台招商员
	    mobile = mchtPlatformContactService.findMobile(mchtBrandChg.getMchtId(), "1");
	    content = "【醒购】您对接的【" + mchtCode + "】的【"+mchtBrandChg.getProductBrandName()+"】归档被驳回，请尽快联系商家将资料重新寄回平台";
	    SmsUtil.send(mobile, content, "4");
		}
	}
	
	public MchtBrandChgCustom getMchtBrandChgCustomByID(Integer mchtBrandChgId){
		return mchtBrandChgCustomMapper.getMchtBrandChgCustomByID(mchtBrandChgId);
	}
	
	
	public List<MchtBrandChgCustom> getMchtBrandChgCustom(HashMap<String, Object> paramMap){
		return mchtBrandChgCustomMapper.getMchtBrandChgCustom(paramMap);
	}
	public Integer getMchtBrandChgCustomCount(HashMap<String, Object> paramMap){
		return mchtBrandChgCustomMapper.getMchtBrandChgCustomCount(paramMap);
	}
	
	
    public void  auditMchtBrandChg(String mchtPlatformAuthPicChgs,String mchtBrandInvoicePicChgs,String mchtBrandInspectionPicChgs,String mchtBrandOtherPicChgs,String mchtBrandAptitudeChgJsonStr,MchtBrandChg mchtBrandChg4Update) throws Exception{
    	
    	Date now=new Date();
    	mchtBrandChg4Update.setArchiveDealStatus("0");
    	mchtBrandChgMapper.updateByPrimaryKeySelective(mchtBrandChg4Update);
    	MchtBrandChg mchtBrandChg=mchtBrandChgMapper.selectByPrimaryKey(mchtBrandChg4Update.getId());
    	//更新授权图
    	MchtPlatformAuthPicChgExample mchtPlatformAuthPicChgExample=new MchtPlatformAuthPicChgExample();
		mchtPlatformAuthPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		MchtPlatformAuthPicChg mchtPlatformAuthPicChg4Update=new MchtPlatformAuthPicChg();
		mchtPlatformAuthPicChg4Update.setDelFlag("1");
		mchtPlatformAuthPicChgMapper.updateByExampleSelective(mchtPlatformAuthPicChg4Update, mchtPlatformAuthPicChgExample);
		
		JSONArray platformAuthPicChgArray = JSONArray.fromObject(mchtPlatformAuthPicChgs);
		Iterator<JSONObject> platformAuthPicChgIt = platformAuthPicChgArray.iterator();
		while (platformAuthPicChgIt.hasNext()) {
			JSONObject platformAuthPicChgObject = platformAuthPicChgIt.next();

			mchtPlatformAuthPicChgExample = new MchtPlatformAuthPicChgExample();
			mchtPlatformAuthPicChgExample.createCriteria().andPicEqualTo(platformAuthPicChgObject.getString("picPath")).andBrandChgIdEqualTo(mchtBrandChg.getId());
			mchtPlatformAuthPicChg4Update = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg4Update.setDelFlag("0");
			int updateCount = mchtPlatformAuthPicChgMapper.updateByExampleSelective(mchtPlatformAuthPicChg4Update, mchtPlatformAuthPicChgExample);
			if (updateCount > 0) {
				continue;
			}
			MchtPlatformAuthPicChg mchtPlatformAuthPicChg = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg.setBrandChgId(mchtBrandChg.getId());
			mchtPlatformAuthPicChg.setPic(platformAuthPicChgObject.getString("picPath"));
			mchtPlatformAuthPicChg.setCreateDate(new Date());
			mchtPlatformAuthPicChg.setUpdateDate(new Date());
			mchtPlatformAuthPicChgMapper.insertSelective(mchtPlatformAuthPicChg);
		}
		
		//更新进货图
		MchtBrandInvoicePicChgExample mchtBrandInvoicePicChgExample=new MchtBrandInvoicePicChgExample();
		mchtBrandInvoicePicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		MchtBrandInvoicePicChg mchtBrandInvoicePicChg4Update=new MchtBrandInvoicePicChg();
		mchtBrandInvoicePicChg4Update.setDelFlag("1");
		mchtBrandInvoicePicChgMapper.updateByExampleSelective(mchtBrandInvoicePicChg4Update, mchtBrandInvoicePicChgExample);
		
		JSONArray mchtBrandInvoicePicChgArray = JSONArray.fromObject(mchtBrandInvoicePicChgs);
		Iterator<JSONObject> invoicePicChgIt = mchtBrandInvoicePicChgArray.iterator();
		while (invoicePicChgIt.hasNext()) {
			JSONObject invoicePicChgObject = invoicePicChgIt.next();

			mchtBrandInvoicePicChgExample = new MchtBrandInvoicePicChgExample();
			mchtBrandInvoicePicChgExample.createCriteria().andPicEqualTo(invoicePicChgObject.getString("picPath")).andBrandChgIdEqualTo(mchtBrandChg.getId());
			mchtBrandInvoicePicChg4Update = new MchtBrandInvoicePicChg();
			mchtBrandInvoicePicChg4Update.setDelFlag("0");
			int updateCount = mchtBrandInvoicePicChgMapper.updateByExampleSelective(mchtBrandInvoicePicChg4Update, mchtBrandInvoicePicChgExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandInvoicePicChg mchtBrandInvoicePicChg = new MchtBrandInvoicePicChg();
			mchtBrandInvoicePicChg.setBrandChgId(mchtBrandChg.getId());
			mchtBrandInvoicePicChg.setPic(invoicePicChgObject.getString("picPath"));
			mchtBrandInvoicePicChg.setCreateDate(new Date());
			mchtBrandInvoicePicChg.setUpdateDate(new Date());
			mchtBrandInvoicePicChgMapper.insertSelective(mchtBrandInvoicePicChg);
		}
    	
		//质检报告图
		MchtBrandInspectionPicChgExample mchtBrandInspectionPicChgExample=new MchtBrandInspectionPicChgExample();
		mchtBrandInspectionPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		MchtBrandInspectionPicChg mchtBrandInspectionPicChg4Update=new MchtBrandInspectionPicChg();
		mchtBrandInspectionPicChg4Update.setDelFlag("1");
		mchtBrandInspectionPicChgMapper.updateByExampleSelective(mchtBrandInspectionPicChg4Update, mchtBrandInspectionPicChgExample);
		
		JSONArray inspectionPicChgsArray = JSONArray.fromObject(mchtBrandInspectionPicChgs);
		Iterator<JSONObject> inspectionPicChgIt = inspectionPicChgsArray.iterator();
		while (inspectionPicChgIt.hasNext()) {
			JSONObject inspectionPicChgObject = inspectionPicChgIt.next();

			mchtBrandInspectionPicChgExample = new MchtBrandInspectionPicChgExample();
			mchtBrandInspectionPicChgExample.createCriteria().andPicEqualTo(inspectionPicChgObject.getString("picPath")).andBrandChgIdEqualTo(mchtBrandChg.getId());
			mchtBrandInspectionPicChg4Update = new MchtBrandInspectionPicChg();
			mchtBrandInspectionPicChg4Update.setDelFlag("0");
			int updateCount = mchtBrandInspectionPicChgMapper.updateByExampleSelective(mchtBrandInspectionPicChg4Update, mchtBrandInspectionPicChgExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandInspectionPicChg mchtBrandInspectionPicChg = new MchtBrandInspectionPicChg();
			mchtBrandInspectionPicChg.setBrandChgId(mchtBrandChg.getId());
			mchtBrandInspectionPicChg.setPic(inspectionPicChgObject.getString("picPath"));
			mchtBrandInspectionPicChg.setCreateDate(new Date());
			mchtBrandInspectionPicChg.setUpdateDate(new Date());
			mchtBrandInspectionPicChgMapper.insertSelective(mchtBrandInspectionPicChg);
		}
		
		//其他资质图
		MchtBrandOtherPicChgExample mchtBrandOtherPicChgExample = new MchtBrandOtherPicChgExample();
		mchtBrandOtherPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		MchtBrandOtherPicChg mchtBrandOtherPicChg4Update = new MchtBrandOtherPicChg();
		mchtBrandOtherPicChg4Update.setDelFlag("1");
		mchtBrandOtherPicChgMapper.updateByExampleSelective(mchtBrandOtherPicChg4Update, mchtBrandOtherPicChgExample);

		JSONArray ohterPicChgsArray = JSONArray.fromObject(mchtBrandOtherPicChgs);
		Iterator<JSONObject> otherPicChgIt = ohterPicChgsArray.iterator();
		while (otherPicChgIt.hasNext()) {
			JSONObject otherPicChgObject = otherPicChgIt.next();

			mchtBrandOtherPicChgExample = new MchtBrandOtherPicChgExample();
			mchtBrandOtherPicChgExample.createCriteria().andPicEqualTo(otherPicChgObject.getString("picPath")).andBrandChgIdEqualTo(mchtBrandChg.getId());
			mchtBrandOtherPicChg4Update = new MchtBrandOtherPicChg();
			mchtBrandOtherPicChg4Update.setDelFlag("0");
			int updateCount = mchtBrandOtherPicChgMapper.updateByExampleSelective(mchtBrandOtherPicChg4Update, mchtBrandOtherPicChgExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandOtherPicChg mchtBrandOtherPicChg = new MchtBrandOtherPicChg();
			mchtBrandOtherPicChg.setBrandChgId(mchtBrandChg.getId());
			mchtBrandOtherPicChg.setPic(otherPicChgObject.getString("picPath"));
			mchtBrandOtherPicChg.setCreateDate(new Date());
			mchtBrandOtherPicChg.setUpdateDate(new Date());
			mchtBrandOtherPicChgMapper.insertSelective(mchtBrandOtherPicChg);
		}
		
		//TODO 品牌注册证
		JSONArray mchtBrandAptitudeChgsArray = JSONArray.fromObject(mchtBrandAptitudeChgJsonStr);
		for(int i=0;i<mchtBrandAptitudeChgsArray.size();i++){
			JSONObject mchtBrandAptitudeChgJo = (JSONObject)mchtBrandAptitudeChgsArray.get(i);
			Integer mchtBrandAptitudeChgId = mchtBrandAptitudeChgJo.getInt("mchtBrandAptitudeChgId");
			
			MchtBrandAptitudePicChgExample mchtBrandAptitudePicChgExample = new MchtBrandAptitudePicChgExample();
			mchtBrandAptitudePicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId()).andMchtBrandAptitudeChgIdEqualTo(mchtBrandAptitudeChgId);
			MchtBrandAptitudePicChg mchtBrandAptitudePicChg4Update = new MchtBrandAptitudePicChg();
			mchtBrandAptitudePicChg4Update.setDelFlag("1");
			mchtBrandAptitudePicChgMapper.updateByExampleSelective(mchtBrandAptitudePicChg4Update, mchtBrandAptitudePicChgExample);
			
			JSONArray mchtBrandAptitudePicChgsArray = JSONArray.fromObject(mchtBrandAptitudeChgJo.getString("mchtBrandAptitudePicChgs"));
			Iterator<JSONObject> mchtBrandAptitudePicChgIt = mchtBrandAptitudePicChgsArray.iterator();
			while (mchtBrandAptitudePicChgIt.hasNext()) {
				JSONObject mchtBrandAptitudePicChgObject = mchtBrandAptitudePicChgIt.next();
				mchtBrandAptitudePicChgExample = new MchtBrandAptitudePicChgExample();
				mchtBrandAptitudePicChgExample.createCriteria().andPicEqualTo(mchtBrandAptitudePicChgObject.getString("picPath")).andMchtBrandAptitudeChgIdEqualTo(mchtBrandAptitudeChgId);
				mchtBrandAptitudePicChg4Update = new MchtBrandAptitudePicChg();
				mchtBrandAptitudePicChg4Update.setDelFlag("0");
				int updateCount = mchtBrandAptitudePicChgMapper.updateByExampleSelective(mchtBrandAptitudePicChg4Update, mchtBrandAptitudePicChgExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandAptitudePicChg mchtBrandAptitudePicChg = new MchtBrandAptitudePicChg();
				mchtBrandAptitudePicChg.setBrandChgId(mchtBrandChg.getId());
				mchtBrandAptitudePicChg.setMchtBrandAptitudeChgId(mchtBrandAptitudeChgId);
				mchtBrandAptitudePicChg.setPic(mchtBrandAptitudePicChgObject.getString("picPath"));
				mchtBrandAptitudePicChg.setCreateDate(new Date());
				mchtBrandAptitudePicChg.setUpdateDate(new Date());
				mchtBrandAptitudePicChgMapper.insertSelective(mchtBrandAptitudePicChg);
			}
		}
		MchtProductBrand mchtProductBrand =	mchtProductBrandMapper.selectByPrimaryKey(mchtBrandChg.getMchtProductBrandId());
		if("4".equals(mchtBrandChg4Update.getAuditStatus())){//驳回
			//法务审核驳回需发送短信给商家及平台
    		//发送站内信
    		String title="关于品牌变更审核通过通知";
    		String content="您的"+mchtProductBrand.getProductBrandName()+"品牌更新审核被驳回，请及时修改并重新上传";
    		platformMsgService.save(mchtProductBrand.getMchtId(), title, content, "TZ");
    		// 发送短信给商家主账号
		    String mobile = mchtUserService.selectMobileByMchtId(mchtProductBrand.getMchtId());
		    String mchtCode = mchtInfoMapper.selectByPrimaryKey(mchtProductBrand.getMchtId()).getMchtCode();
		    content = "【醒购】您的店铺【"+mchtCode+"】的【"+mchtProductBrand.getProductBrandName()+"】已审核被驳回，请尽快登录平台查看修改并重新上传。";
		    SmsUtil.send(mobile, content, "4");
            // 发送短信给商家店铺负责人
            mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtProductBrand.getMchtId(), 1);
            if(!StringUtils.isEmpty(mobile)){
            	SmsUtil.send(mobile, content, "4");
            }
            // 发送短信给商家运营专员
            mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtProductBrand.getMchtId(), 2);
            if(!StringUtils.isEmpty(mobile)){
            	SmsUtil.send(mobile, content, "4");
            }
		    // 发送短信给平台招商员
		    mobile = mchtPlatformContactService.findMobile(mchtProductBrand.getMchtId(), "1");
		    content = "【醒购】您对接的【" + mchtCode + "】审核被驳回，请及时的联系商家修改并重新上传。";
		    SmsUtil.send(mobile, content, "4");
		}
		
    	if("3".equals(mchtBrandChg4Update.getAuditStatus())){//审核通过
    		mchtBrandChg4Update.setArchiveStatus("0");//0.未寄出
    		
    		//法务审核通过需发送短信给商家及平台
    		//发送站内信
    		String title="关于品牌变更审核通过通知";
    		String content="您的"+mchtProductBrand.getProductBrandName()+"品牌更新审核已通过，请尽快将该公司资质文件寄回平台";
    		platformMsgService.save(mchtProductBrand.getMchtId(), title, content, "TZ");
    		// 发送短信给商家端主账号
		    String mobile = mchtUserService.selectMobileByMchtId(mchtProductBrand.getMchtId());
		    String mchtCode = mchtInfoMapper.selectByPrimaryKey(mchtProductBrand.getMchtId()).getMchtCode();
		    content = "【醒购】您的店铺【"+mchtCode+"】的【"+mchtProductBrand.getProductBrandName()+"】已审核通过，请尽快将该公司资质文件寄回平台";
		    SmsUtil.send(mobile, content, "4");
            // 发送短信给商家店铺负责人
            mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtProductBrand.getMchtId(), 1);
            if(!StringUtils.isEmpty(mobile)){
            	SmsUtil.send(mobile, content, "4");
            }
            // 发送短信给商家运营专员
            mobile = mchtUserService.selectMchtContactMobileByMchtId(mchtProductBrand.getMchtId(), 2);
            if(!StringUtils.isEmpty(mobile)){
            	SmsUtil.send(mobile, content, "4");
            }
		    // 发送短信给平台招商员
		    mobile = mchtPlatformContactService.findMobile(mchtProductBrand.getMchtId(), "1");
		    content = "【醒购】您对接的【" + mchtCode + "】的公司资质变更已审核通过，请尽快联系商家将资料寄回平台";
		    SmsUtil.send(mobile, content, "4");
    		
    		
    		mchtProductBrand.setUpdateBy(mchtBrandChg4Update.getUpdateBy());
    		mchtProductBrand.setUpdateDate(now);
    		mchtProductBrand.setRemarks(mchtProductBrand.getRemarks()+";更新备注："+mchtBrandChg.getAuditRemarks());
    		mchtProductBrand.setAptitudeType(mchtBrandChg.getAptitudeType());
    		mchtProductBrand.setProductBrandName(mchtBrandChg.getProductBrandName());
    		mchtProductBrand.setPlatformAuthExpDate(mchtBrandChg.getPlatformAuthExpDate());
    		mchtProductBrand.setInspectionExpDate(mchtBrandChg.getInspectionExpDate());
    		mchtProductBrand.setOtherExpDate(mchtBrandChg.getOtherExpDate());
    		mchtProductBrand.setLogo(mchtBrandChg.getLogo());
    		mchtProductBrand.setStatus("1");
    		mchtProductBrand.setArchiveStatus("1");
    		mchtProductBrandMapper.updateByPrimaryKeySelective(mchtProductBrand);
			
    		//更新授权图
    		MchtPlatformAuthPicExample mchtPlatformAuthPicExample=new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId());
			MchtPlatformAuthPic mchtPlatformAuthPic4Update=new MchtPlatformAuthPic();
			mchtPlatformAuthPic4Update.setDelFlag("1");
			mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
			
			platformAuthPicChgIt = platformAuthPicChgArray.iterator();
			while (platformAuthPicChgIt.hasNext()) {
				JSONObject platformAuthPicChgObject = platformAuthPicChgIt.next();
				
				mchtPlatformAuthPicExample=new MchtPlatformAuthPicExample();
				mchtPlatformAuthPicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId()).andPicEqualTo(platformAuthPicChgObject.getString("picPath"));
				mchtPlatformAuthPic4Update=new MchtPlatformAuthPic();
				mchtPlatformAuthPic4Update.setDelFlag("0");
				int updateCount=mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
				if(updateCount>0){
					continue;
				}
				MchtPlatformAuthPic mchtPlatformAuthPic=new MchtPlatformAuthPic();
				mchtPlatformAuthPic.setMchtProductBrandId(mchtBrandChg.getMchtProductBrandId());
				mchtPlatformAuthPic.setPic(platformAuthPicChgObject.getString("picPath"));
				mchtPlatformAuthPic.setCreateBy(mchtBrandChg4Update.getUpdateBy());
				mchtPlatformAuthPic.setCreateDate(now);
				mchtPlatformAuthPic.setUpdateBy(mchtBrandChg4Update.getUpdateBy());
				mchtPlatformAuthPic.setUpdateDate(now);
				mchtPlatformAuthPicMapper.insertSelective(mchtPlatformAuthPic);
				
			}
			
    		//更新进货发票
    		MchtBrandInvoicePicExample mchtBrandInvoicePicExample=new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId());
			MchtBrandInvoicePic mchtBrandInvoicePic4Update=new MchtBrandInvoicePic();
			mchtBrandInvoicePic4Update.setDelFlag("1");
			mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);
			
			invoicePicChgIt = mchtBrandInvoicePicChgArray.iterator();
			while (invoicePicChgIt.hasNext()) {
				JSONObject invoicePicChgObject = invoicePicChgIt.next();
				mchtBrandInvoicePicExample=new MchtBrandInvoicePicExample();
				mchtBrandInvoicePicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId()).andPicEqualTo(invoicePicChgObject.getString("picPath"));
				mchtBrandInvoicePic4Update=new MchtBrandInvoicePic();
				mchtBrandInvoicePic4Update.setDelFlag("0");
				int updateCount=mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);
				if(updateCount>0){
					continue;
				}
				MchtBrandInvoicePic mchtBrandInvoicePic=new MchtBrandInvoicePic();
				mchtBrandInvoicePic.setMchtProductBrandId(mchtBrandChg.getMchtProductBrandId());
				mchtBrandInvoicePic.setPic(invoicePicChgObject.getString("picPath"));
				mchtBrandInvoicePic.setCreateBy(mchtBrandChg4Update.getUpdateBy());
				mchtBrandInvoicePic.setCreateDate(now);
				mchtBrandInvoicePic.setUpdateBy(mchtBrandChg4Update.getUpdateBy());
				mchtBrandInvoicePic.setUpdateDate(now);
				mchtBrandInvoicePicMapper.insertSelective(mchtBrandInvoicePic);
			}
			
			//质检报告
			MchtBrandInspectionPicExample mchtBrandInspectionPicExample=new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId());
			MchtBrandInspectionPic mchtBrandInspectionPic4Update=new MchtBrandInspectionPic();
			mchtBrandInspectionPic4Update.setDelFlag("1");
			mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);
			
			inspectionPicChgIt = inspectionPicChgsArray.iterator();
			while (inspectionPicChgIt.hasNext()) {
				JSONObject inspectionPicChgObject = inspectionPicChgIt.next();
				mchtBrandInspectionPicExample=new MchtBrandInspectionPicExample();
				mchtBrandInspectionPicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId()).andPicEqualTo(inspectionPicChgObject.getString("picPath"));
				mchtBrandInspectionPic4Update=new MchtBrandInspectionPic();
				mchtBrandInspectionPic4Update.setDelFlag("0");
				int updateCount=mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);
				if(updateCount>0){
					continue;
				}
				MchtBrandInspectionPic mchtBrandInspectionPic=new MchtBrandInspectionPic();
				mchtBrandInspectionPic.setMchtProductBrandId(mchtBrandChg.getMchtProductBrandId());
				mchtBrandInspectionPic.setPic(inspectionPicChgObject.getString("picPath"));
				mchtBrandInspectionPic.setCreateBy(mchtBrandChg4Update.getUpdateBy());
				mchtBrandInspectionPic.setCreateDate(now);
				mchtBrandInspectionPic.setUpdateBy(mchtBrandChg4Update.getUpdateBy());
				mchtBrandInspectionPic.setUpdateDate(now);
				mchtBrandInspectionPicMapper.insertSelective(mchtBrandInspectionPic);
			}
			
			
			//其他资质
			MchtBrandOtherPicExample mchtBrandOtherPicExample=new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId());
			MchtBrandOtherPic mchtBrandOtherPic4Update=new MchtBrandOtherPic();
			mchtBrandOtherPic4Update.setDelFlag("1");
			mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);
			
			otherPicChgIt = ohterPicChgsArray.iterator();
			while (otherPicChgIt.hasNext()) {
				JSONObject otherPicChgObject = otherPicChgIt.next();
				mchtBrandOtherPicExample=new MchtBrandOtherPicExample();
				mchtBrandOtherPicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId()).andPicEqualTo(otherPicChgObject.getString("picPath"));
				mchtBrandOtherPic4Update=new MchtBrandOtherPic();
				mchtBrandOtherPic4Update.setDelFlag("0");
				int updateCount=mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);
				if(updateCount>0){
					continue;
				}
				MchtBrandOtherPic mchtBrandOtherPic=new MchtBrandOtherPic();
				mchtBrandOtherPic.setMchtProductBrandId(mchtBrandChg.getMchtProductBrandId());
				mchtBrandOtherPic.setPic(otherPicChgObject.getString("picPath"));
				mchtBrandOtherPic.setCreateBy(mchtBrandChg4Update.getUpdateBy());
				mchtBrandOtherPic.setCreateDate(now);
				mchtBrandOtherPic.setUpdateBy(mchtBrandChg4Update.getUpdateBy());
				mchtBrandOtherPic.setUpdateDate(now);
				mchtBrandOtherPicMapper.insertSelective(mchtBrandOtherPic);
			}
			
			//TODO 品牌注册证
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(int j=0;j<mchtBrandAptitudeChgsArray.size();j++){
				JSONObject mchtBrandAptitudeChgJo = (JSONObject)mchtBrandAptitudeChgsArray.get(j);
				String certificateNo = mchtBrandAptitudeChgJo.getString("certificateNo");
				String aptitudeExpDate = mchtBrandAptitudeChgJo.getString("aptitudeExpDate");
				JSONArray mchtBrandAptitudePicChgsArray = JSONArray.fromObject(mchtBrandAptitudeChgJo.getString("mchtBrandAptitudePicChgs"));
				MchtBrandAptitudeExample mbae = new MchtBrandAptitudeExample();
				mbae.createCriteria().andDelFlagEqualTo("0").andCertificateNoEqualTo(certificateNo);
				List<MchtBrandAptitude> mchtBrandAptitudes = mchtBrandAptitudeMapper.selectByExample(mbae);
				if(mchtBrandAptitudes!=null && mchtBrandAptitudes.size()>0){//修改注册证里的图片
					Integer mchtBrandAptitudeId = mchtBrandAptitudes.get(0).getId();
					//TODO 先全部删除再更新或者添加
					MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
					mchtBrandAptitudePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId()).andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
					MchtBrandAptitudePic mchtBrandAptitudePic4Update=new MchtBrandAptitudePic();
					mchtBrandAptitudePic4Update.setDelFlag("1");
					mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic4Update, mchtBrandAptitudePicExample);
					
					Iterator<JSONObject> mchtBrandAptitudePicChgIt = mchtBrandAptitudePicChgsArray.iterator();
					while (mchtBrandAptitudePicChgIt.hasNext()) {
						JSONObject mchtBrandAptitudePicChgObject = mchtBrandAptitudePicChgIt.next();
						mchtBrandAptitudePicExample=new MchtBrandAptitudePicExample();
						mchtBrandAptitudePicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId()).andPicEqualTo(mchtBrandAptitudePicChgObject.getString("picPath")).andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
						mchtBrandAptitudePic4Update=new MchtBrandAptitudePic();
						mchtBrandAptitudePic4Update.setDelFlag("0");
						int updateCount=mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic4Update, mchtBrandAptitudePicExample);
						if(updateCount>0){
							continue;
						}
						MchtBrandAptitudePic mchtBrandAptitudePic=new MchtBrandAptitudePic();
						mchtBrandAptitudePic.setMchtProductBrandId(mchtBrandChg.getMchtProductBrandId());
						mchtBrandAptitudePic.setMchtBrandAptitudeId(mchtBrandAptitudeId);
						mchtBrandAptitudePic.setPic(mchtBrandAptitudePicChgObject.getString("picPath"));
						mchtBrandAptitudePic.setCreateBy(mchtBrandChg4Update.getUpdateBy());
						mchtBrandAptitudePic.setCreateDate(now);
						mchtBrandAptitudePic.setUpdateBy(mchtBrandChg4Update.getUpdateBy());
						mchtBrandAptitudePic.setUpdateDate(now);
						mchtBrandAptitudePicMapper.insertSelective(mchtBrandAptitudePic);
					}
					
				}else{//添加整个注册证及图片
					MchtBrandAptitude mba = new MchtBrandAptitude();
					mba.setDelFlag("0");
					mba.setCreateBy(mchtBrandChg4Update.getUpdateBy());
					mba.setCreateDate(new Date());
					mba.setMchtProductBrandId(mchtBrandChg.getMchtProductBrandId());
					mba.setCertificateNo(certificateNo);
					mba.setAptitudeExpDate(sdf.parse(aptitudeExpDate));
					mchtBrandAptitudeMapper.insertSelective(mba);
					//TODO 新增 mchtBrandAptitudePicChgsArray
					Iterator<JSONObject> mchtBrandAptitudePicChgIt = mchtBrandAptitudePicChgsArray.iterator();
					while (mchtBrandAptitudePicChgIt.hasNext()) {
						JSONObject mchtBrandAptitudePicChgObject = mchtBrandAptitudePicChgIt.next();
						MchtBrandAptitudePic mchtBrandAptitudePic=new MchtBrandAptitudePic();
						mchtBrandAptitudePic.setMchtProductBrandId(mchtBrandChg.getMchtProductBrandId());
						mchtBrandAptitudePic.setMchtBrandAptitudeId(mba.getId());
						mchtBrandAptitudePic.setPic(mchtBrandAptitudePicChgObject.getString("picPath"));
						mchtBrandAptitudePic.setCreateBy(mchtBrandChg4Update.getUpdateBy());
						mchtBrandAptitudePic.setCreateDate(now);
						mchtBrandAptitudePic.setUpdateBy(mchtBrandChg4Update.getUpdateBy());
						mchtBrandAptitudePic.setUpdateDate(now);
						mchtBrandAptitudePicMapper.insertSelective(mchtBrandAptitudePic);
					}
				}
			}
    	}
		MchtBrandProductTypeExample mchtBrandProductTypeExample = new MchtBrandProductTypeExample();
		mchtBrandProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtBrandChg.getMchtProductBrandId());
		MchtBrandProductType mchtBrandProductType4Update = new MchtBrandProductType();
		mchtBrandProductType4Update.setDelFlag("1");
		mchtBrandProductTypeService.updateByExampleSelective(mchtBrandProductType4Update, mchtBrandProductTypeExample);
		
		MchtBrandChgProductTypeExample mbcpte = new MchtBrandChgProductTypeExample();
		mbcpte.createCriteria().andDelFlagEqualTo("0").andMchtBrandChgIdEqualTo(mchtBrandChg.getId());
		List<MchtBrandChgProductTypeCustom> mchtBrandChgProductTypeCustoms = mchtBrandChgProductTypeService.selectCustomByExample(mbcpte);
		for(MchtBrandChgProductTypeCustom mchtBrandChgProductTypeCustom:mchtBrandChgProductTypeCustoms){
			MchtBrandProductType mchtBrandProductType = new MchtBrandProductType();
			mchtBrandProductType.setDelFlag("0");
			mchtBrandProductType.setCreateDate(new Date());
			mchtBrandProductType.setCreateBy(mchtBrandChg4Update.getUpdateBy());
			mchtBrandProductType.setMchtProductBrandId(mchtBrandChg.getMchtProductBrandId());
			mchtBrandProductType.setProductTypeId(mchtBrandChgProductTypeCustom.getProductTypeId());
			mchtBrandProductType.settLevel(mchtBrandChgProductTypeCustom.gettLevel());
			mchtBrandProductType.setRemarks(mchtBrandChgProductTypeCustom.getRemarks());
			mchtBrandProductTypeService.insertSelective(mchtBrandProductType);
		}
		// 商家变更日志
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		mchtInfoChangeLog.setLogName(mchtBrandChg.getProductBrandName());
		mchtInfoChangeLog.setCreatorType("1");
		mchtInfoChangeLog.setLogType("品牌信息更新");
		mchtInfoChangeLog.setBeforeChange(mchtBrandChg.getAuditStatus().equals("1")?"待审":"审核中");
		if(mchtBrandChg4Update.getAuditStatus().equals("3") ) { //通过
			mchtInfoChangeLog.setAfterChange("通过");
		}else if(mchtBrandChg4Update.getAuditStatus().equals("4") ) { //驳回
			mchtInfoChangeLog.setAfterChange("驳回");
		}else if(mchtBrandChg4Update.getAuditStatus().equals("5") ) { //不签约
			mchtInfoChangeLog.setAfterChange("不签约");
		}else { //黑名单
			mchtInfoChangeLog.setAfterChange("黑名单");
		}
		mchtInfoChangeLog.setRemarks(mchtBrandChg4Update.getAuditRemarks());
		mchtInfoChangeLog.setMchtId(mchtBrandChg.getMchtId());
		mchtInfoChangeLog.setCreateBy(mchtBrandChg4Update.getUpdateBy());
		mchtInfoChangeLog.setCreateDate(now);
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
    	mchtBrandChgMapper.updateByPrimaryKeySelective(mchtBrandChg4Update);
    }

	public void brandChgPicsArchive(Integer mchtBrandChgId,String mchtBrandAptitudeChgId,Integer staffID,String picType) {
		if(!StringUtils.isEmpty(picType) && picType.equals("1")){
			MchtBrandAptitudePicChgExample mbapce = new MchtBrandAptitudePicChgExample();
			mbapce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId).andMchtBrandAptitudeChgIdEqualTo(Integer.parseInt(mchtBrandAptitudeChgId));
			MchtBrandAptitudePicChg mchtBrandAptitudePicChg = new MchtBrandAptitudePicChg();
			mchtBrandAptitudePicChg.setUpdateBy(staffID);
			mchtBrandAptitudePicChg.setUpdateDate(new Date());
			mchtBrandAptitudePicChg.setArchiveBy(staffID);
			mchtBrandAptitudePicChg.setArchiveStatus("1");//已归档
			mchtBrandAptitudePicChgMapper.updateByExampleSelective(mchtBrandAptitudePicChg, mbapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("2")){
			MchtPlatformAuthPicChgExample mpapce = new MchtPlatformAuthPicChgExample();
			mpapce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
			MchtPlatformAuthPicChg mchtPlatformAuthPicChg = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg.setUpdateBy(staffID);
			mchtPlatformAuthPicChg.setUpdateDate(new Date());
			mchtPlatformAuthPicChg.setArchiveBy(staffID);
			mchtPlatformAuthPicChg.setArchiveStatus("1");//已归档
			mchtPlatformAuthPicChgMapper.updateByExampleSelective(mchtPlatformAuthPicChg, mpapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("3")){
			MchtBrandInvoicePicChgExample mbipce = new MchtBrandInvoicePicChgExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
			MchtBrandInvoicePicChg mchtBrandInvoicePicChg = new MchtBrandInvoicePicChg();
			mchtBrandInvoicePicChg.setUpdateBy(staffID);
			mchtBrandInvoicePicChg.setUpdateDate(new Date());
			mchtBrandInvoicePicChg.setArchiveBy(staffID);
			mchtBrandInvoicePicChg.setArchiveStatus("1");//已归档
			mchtBrandInvoicePicChgMapper.updateByExampleSelective(mchtBrandInvoicePicChg, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("4")){
			MchtBrandInspectionPicChgExample mbipce = new MchtBrandInspectionPicChgExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
			MchtBrandInspectionPicChg mchtBrandInspectionPicChg = new MchtBrandInspectionPicChg();
			mchtBrandInspectionPicChg.setUpdateBy(staffID);
			mchtBrandInspectionPicChg.setUpdateDate(new Date());
			mchtBrandInspectionPicChg.setArchiveBy(staffID);
			mchtBrandInspectionPicChg.setArchiveStatus("1");//已归档
			mchtBrandInspectionPicChgMapper.updateByExampleSelective(mchtBrandInspectionPicChg, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("5")){
			MchtBrandOtherPicChgExample mbopce = new MchtBrandOtherPicChgExample();
			mbopce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
			MchtBrandOtherPicChg mchtBrandOtherPicChg = new MchtBrandOtherPicChg();
			mchtBrandOtherPicChg.setUpdateBy(staffID);
			mchtBrandOtherPicChg.setUpdateDate(new Date());
			mchtBrandOtherPicChg.setArchiveBy(staffID);
			mchtBrandOtherPicChg.setArchiveStatus("1");//已归档
			mchtBrandOtherPicChgMapper.updateByExampleSelective(mchtBrandOtherPicChg,mbopce);
		}
		//TODO 判断是不是全部已齐全了
		updateMchtBrandChgArchiveStatus(mchtBrandChgId, staffID);
	}

	public void editMchtBrandArchiveStatus(Integer mchtBrandChgId,String archiveStatus,Integer staffID) {
		Date date = new Date();
		//更新表
		MchtBrandChg mchtBrandChg = new MchtBrandChg();
		mchtBrandChg.setId(mchtBrandChgId);
		mchtBrandChg.setUpdateBy(staffID);
		mchtBrandChg.setUpdateDate(date);
		mchtBrandChg.setArchiveStatus(archiveStatus);
		this.updateByPrimaryKeySelective(mchtBrandChg);
		//主表
		MchtBrandChg mbc = this.selectByPrimaryKey(mchtBrandChgId);
		MchtProductBrand mchtProductBrand = new MchtProductBrand();
		mchtProductBrand.setId(mbc.getMchtProductBrandId());
		mchtProductBrand.setUpdateBy(staffID);
		mchtProductBrand.setUpdateDate(date);
		mchtProductBrand.setArchiveStatus(archiveStatus);
		mchtProductBrandMapper.updateByPrimaryKeySelective(mchtProductBrand);
	}
	
	public void picArchive(Integer mchtBrandChgId,Integer staffID, String picId,String picType,String archiveStatus) {
		if(!StringUtils.isEmpty(picType) && picType.equals("1")){
			MchtBrandAptitudePicChgExample mbapce = new MchtBrandAptitudePicChgExample();
			mbapce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtBrandAptitudePicChg mchtBrandAptitudePicChg = new MchtBrandAptitudePicChg();
			mchtBrandAptitudePicChg.setUpdateBy(staffID);
			mchtBrandAptitudePicChg.setUpdateDate(new Date());
			mchtBrandAptitudePicChg.setArchiveBy(staffID);
			mchtBrandAptitudePicChg.setArchiveStatus(archiveStatus);
			mchtBrandAptitudePicChgMapper.updateByExampleSelective(mchtBrandAptitudePicChg, mbapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("2")){
			MchtPlatformAuthPicChgExample mpapce = new MchtPlatformAuthPicChgExample();
			mpapce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtPlatformAuthPicChg mchtPlatformAuthPicChg = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg.setUpdateBy(staffID);
			mchtPlatformAuthPicChg.setUpdateDate(new Date());
			mchtPlatformAuthPicChg.setArchiveBy(staffID);
			mchtPlatformAuthPicChg.setArchiveStatus(archiveStatus);
			mchtPlatformAuthPicChgMapper.updateByExampleSelective(mchtPlatformAuthPicChg, mpapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("3")){
			MchtBrandInvoicePicChgExample mbipce = new MchtBrandInvoicePicChgExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtBrandInvoicePicChg mchtBrandInvoicePicChg = new MchtBrandInvoicePicChg();
			mchtBrandInvoicePicChg.setUpdateBy(staffID);
			mchtBrandInvoicePicChg.setUpdateDate(new Date());
			mchtBrandInvoicePicChg.setArchiveBy(staffID);
			mchtBrandInvoicePicChg.setArchiveStatus(archiveStatus);
			mchtBrandInvoicePicChgMapper.updateByExampleSelective(mchtBrandInvoicePicChg, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("4")){
			MchtBrandInspectionPicChgExample mbipce = new MchtBrandInspectionPicChgExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtBrandInspectionPicChg mchtBrandInspectionPicChg = new MchtBrandInspectionPicChg();
			mchtBrandInspectionPicChg.setUpdateBy(staffID);
			mchtBrandInspectionPicChg.setUpdateDate(new Date());
			mchtBrandInspectionPicChg.setArchiveBy(staffID);
			mchtBrandInspectionPicChg.setArchiveStatus(archiveStatus);
			mchtBrandInspectionPicChgMapper.updateByExampleSelective(mchtBrandInspectionPicChg, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("5")){
			MchtBrandOtherPicChgExample mbopce = new MchtBrandOtherPicChgExample();
			mbopce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtBrandOtherPicChg mchtBrandOtherPicChg = new MchtBrandOtherPicChg();
			mchtBrandOtherPicChg.setUpdateBy(staffID);
			mchtBrandOtherPicChg.setUpdateDate(new Date());
			mchtBrandOtherPicChg.setArchiveBy(staffID);
			mchtBrandOtherPicChg.setArchiveStatus(archiveStatus);
			mchtBrandOtherPicChgMapper.updateByExampleSelective(mchtBrandOtherPicChg,mbopce);
		}
		if(archiveStatus.equals("1")){//已归档
			//TODO 判断是不是全部已齐全了
			updateMchtBrandChgArchiveStatus(mchtBrandChgId, staffID);
		}else{//0.未齐
			MchtBrandChg mchtBrandChg = new MchtBrandChg();
			mchtBrandChg.setArchiveStatus("2");//2.未齐全
			mchtBrandChg.setUpdateBy(staffID);
			mchtBrandChg.setUpdateDate(new Date());
			MchtBrandChgExample mbce = new MchtBrandChgExample();
			mbce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtBrandChgId);
			this.updateByExampleSelective(mchtBrandChg, mbce);
		}
	}
	
	private void updateMchtBrandChgArchiveStatus(Integer mchtBrandChgId,Integer staffID) {
		MchtBrandAptitudePicChgExample mbapce = new MchtBrandAptitudePicChgExample();
		mbapce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
		int mchtBrandAptitudePicChgCount = mchtBrandAptitudePicChgMapper.countByExample(mbapce);
		
		MchtPlatformAuthPicChgExample mpapce = new MchtPlatformAuthPicChgExample();
		mpapce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
		int mchtPlatformAuthPicChgCount = mchtPlatformAuthPicChgMapper.countByExample(mpapce);
		
		MchtBrandInvoicePicChgExample mbipce = new MchtBrandInvoicePicChgExample();
		mbipce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
		int mchtBrandInvoicePicChgCount = mchtBrandInvoicePicChgMapper.countByExample(mbipce);
		
		MchtBrandInspectionPicChgExample mbipce2 = new MchtBrandInspectionPicChgExample();
		mbipce2.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
		int mchtBrandInspectionPicChgCount = mchtBrandInspectionPicChgMapper.countByExample(mbipce2);
		
		MchtBrandOtherPicChgExample mbopce = new MchtBrandOtherPicChgExample();
		mbopce.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId);
		int mchtBrandOtherPicChgCount = mchtBrandOtherPicChgMapper.countByExample(mbopce);
		
		int totalCount = mchtBrandAptitudePicChgCount+mchtPlatformAuthPicChgCount+mchtBrandInvoicePicChgCount+mchtBrandInspectionPicChgCount+mchtBrandOtherPicChgCount;
		
		MchtBrandAptitudePicChgExample mchtBrandAptitudePicChgExample = new MchtBrandAptitudePicChgExample();
		mchtBrandAptitudePicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId).andArchiveStatusEqualTo("1");
		int mchtBrandAptitudePicChgArchiveCount = mchtBrandAptitudePicChgMapper.countByExample(mchtBrandAptitudePicChgExample);
		
		MchtPlatformAuthPicChgExample mchtPlatformAuthPicChgExample = new MchtPlatformAuthPicChgExample();
		mchtPlatformAuthPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId).andArchiveStatusEqualTo("1");
		int mchtPlatformAuthPicChgArchiveCount = mchtPlatformAuthPicChgMapper.countByExample(mchtPlatformAuthPicChgExample);
		
		MchtBrandInvoicePicChgExample mchtBrandInvoicePicChgExample = new MchtBrandInvoicePicChgExample();
		mchtBrandInvoicePicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId).andArchiveStatusEqualTo("1");
		int mchtBrandInvoicePicChgArchiveCount = mchtBrandInvoicePicChgMapper.countByExample(mchtBrandInvoicePicChgExample);
		
		MchtBrandInspectionPicChgExample mchtBrandInspectionPicChgExample = new MchtBrandInspectionPicChgExample();
		mchtBrandInspectionPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId).andArchiveStatusEqualTo("1");
		int mchtBrandInspectionPicChgArchiveCount = mchtBrandInspectionPicChgMapper.countByExample(mchtBrandInspectionPicChgExample);
		
		MchtBrandOtherPicChgExample mchtBrandOtherPicChgExample = new MchtBrandOtherPicChgExample();
		mchtBrandOtherPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChgId).andArchiveStatusEqualTo("1");
		int mchtBrandOtherPicChgArchiveCount = mchtBrandOtherPicChgMapper.countByExample(mchtBrandOtherPicChgExample);
		
		int totalArchiveCount = mchtBrandAptitudePicChgArchiveCount+mchtPlatformAuthPicChgArchiveCount+mchtBrandInvoicePicChgArchiveCount+mchtBrandInspectionPicChgArchiveCount+mchtBrandOtherPicChgArchiveCount;
		MchtBrandChg mbc = new MchtBrandChg();
		if(totalCount == totalArchiveCount){
			mbc.setArchiveStatus("3");
		}else{
			mbc.setArchiveStatus("2");
		}
		mbc.setUpdateBy(staffID);
		mbc.setUpdateDate(new Date());
		MchtBrandChgExample mbce = new MchtBrandChgExample();
		mbce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtBrandChgId);
		this.updateByExampleSelective(mbc, mbce);
	}
	
	public void copyToMchtBrandChg(Integer mchtProductBrandId) {
		MchtProductBrand mchtProductBrand = mchtProductBrandMapper.selectByPrimaryKey(mchtProductBrandId);
		//copy到 商家品牌更新表
		MchtBrandChg mchtBrandChg = new MchtBrandChg();
		BeanUtils.copyProperties(mchtProductBrand, mchtBrandChg);
		mchtBrandChg.setId(null);
		mchtBrandChg.setMchtProductBrandId(mchtProductBrandId);
		mchtBrandChg.setCommitDate(mchtProductBrand.getCreateDate());
		mchtBrandChg.setArchiveDealStatus("0");
		mchtBrandChgMapper.insert(mchtBrandChg);
		//copy到 更新销售授权图
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		List<MchtPlatformAuthPic> mchtPlatformAuthPics = mchtPlatformAuthPicMapper.selectByExample(mchtPlatformAuthPicExample);
		for (MchtPlatformAuthPic mchtPlatformAuthPic : mchtPlatformAuthPics) {
			MchtPlatformAuthPicChg mchtPlatformAuthPicChg = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg.setBrandChgId(mchtBrandChg.getId());
			mchtPlatformAuthPicChg.setCreateBy(mchtProductBrand.getStatusBy());;
			mchtPlatformAuthPicChg.setCreateDate(mchtProductBrand.getStatusDate());
			mchtPlatformAuthPicChg.setDelFlag("0");
			mchtPlatformAuthPicChg.setArchiveStatus(mchtPlatformAuthPic.getArchiveStatus());
			mchtPlatformAuthPicChg.setPic(mchtPlatformAuthPic.getPic());
			mchtPlatformAuthPicChgMapper.insertSelective(mchtPlatformAuthPicChg);
		}
		//copy到 更新进货发票图
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		List<MchtBrandInvoicePic> mchtBrandInvoicePics = mchtBrandInvoicePicMapper.selectByExample(mchtBrandInvoicePicExample);
		for (MchtBrandInvoicePic mchtBrandInvoicePic : mchtBrandInvoicePics) {
			MchtBrandInvoicePicChg mchtBrandInvoicePicChg = new MchtBrandInvoicePicChg();
			mchtBrandInvoicePicChg.setBrandChgId(mchtBrandChg.getId());
			mchtBrandInvoicePicChg.setCreateBy(mchtProductBrand.getStatusBy());;
			mchtBrandInvoicePicChg.setCreateDate(mchtProductBrand.getStatusDate());
			mchtBrandInvoicePicChg.setDelFlag("0");
			mchtBrandInvoicePicChg.setArchiveStatus(mchtBrandInvoicePic.getArchiveStatus());
			mchtBrandInvoicePicChg.setPic(mchtBrandInvoicePic.getPic());
			mchtBrandInvoicePicChgMapper.insertSelective(mchtBrandInvoicePicChg);
		}
		//copy到 更新质检报告图
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		List<MchtBrandInspectionPic> mchtBrandInspectionPics = mchtBrandInspectionPicMapper.selectByExample(mchtBrandInspectionPicExample);
		for (MchtBrandInspectionPic mchtBrandInspectionPic : mchtBrandInspectionPics) {
			MchtBrandInspectionPicChg mchtBrandInspectionPicChg = new MchtBrandInspectionPicChg();
			mchtBrandInspectionPicChg.setBrandChgId(mchtBrandChg.getId());
			mchtBrandInspectionPicChg.setCreateBy(mchtProductBrand.getStatusBy());;
			mchtBrandInspectionPicChg.setCreateDate(mchtProductBrand.getStatusDate());
			mchtBrandInspectionPicChg.setDelFlag("0");
			mchtBrandInspectionPicChg.setArchiveStatus(mchtBrandInspectionPic.getArchiveStatus());
			mchtBrandInspectionPicChg.setPic(mchtBrandInspectionPic.getPic());
			mchtBrandInspectionPicChgMapper.insertSelective(mchtBrandInspectionPicChg);
		}
		//copy到 更新其他资质图
		MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
		mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		List<MchtBrandOtherPic> mchtBrandOtherPics = mchtBrandOtherPicMapper.selectByExample(mchtBrandOtherPicExample);
		for (MchtBrandOtherPic mchtBrandOtherPic : mchtBrandOtherPics) {
			MchtBrandOtherPicChg mchtBrandOtherPicChg = new MchtBrandOtherPicChg();
			mchtBrandOtherPicChg.setBrandChgId(mchtBrandChg.getId());
			mchtBrandOtherPicChg.setCreateBy(mchtProductBrand.getStatusBy());;
			mchtBrandOtherPicChg.setCreateDate(mchtProductBrand.getStatusDate());
			mchtBrandOtherPicChg.setDelFlag("0");
			mchtBrandOtherPicChg.setArchiveStatus(mchtBrandOtherPic.getArchiveStatus());
			mchtBrandOtherPicChg.setPic(mchtBrandOtherPic.getPic());
			mchtBrandOtherPicChgMapper.insertSelective(mchtBrandOtherPicChg);
		}
		//copy到 商家品牌资质
		MchtBrandAptitudeExample mchtBrandAptitudeExample = new MchtBrandAptitudeExample();
		mchtBrandAptitudeExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		List<MchtBrandAptitude> mchtBrandAptitudes = mchtBrandAptitudeMapper.selectByExample(mchtBrandAptitudeExample);
		for (MchtBrandAptitude mchtBrandAptitude : mchtBrandAptitudes) {
			MchtBrandAptitudeChg mchtBrandAptitudeChg = new MchtBrandAptitudeChg();
			mchtBrandAptitudeChg.setMchtBrandChgId(mchtBrandChg.getId());
			mchtBrandAptitudeChg.setCreateBy(mchtProductBrand.getStatusBy());;
			mchtBrandAptitudeChg.setCreateDate(mchtProductBrand.getStatusDate());
			mchtBrandAptitudeChg.setDelFlag("0");
			mchtBrandAptitudeChg.setCertificateNo(mchtBrandAptitude.getCertificateNo());
			mchtBrandAptitudeChg.setAptitudeExpDate(mchtBrandAptitude.getAptitudeExpDate());
			mchtBrandAptitudeChgMapper.insertSelective(mchtBrandAptitudeChg);
			MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
			mchtBrandAptitudePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId).andMchtBrandAptitudeIdEqualTo(mchtBrandAptitude.getId());
			List<MchtBrandAptitudePic> mchtBrandAptitudePics = mchtBrandAptitudePicMapper.selectByExample(mchtBrandAptitudePicExample);
			for (MchtBrandAptitudePic mchtBrandAptitudePic : mchtBrandAptitudePics) {
				MchtBrandAptitudePicChg mchtBrandAptitudePicChg = new MchtBrandAptitudePicChg();
				mchtBrandAptitudePicChg.setBrandChgId(mchtBrandChg.getId());
				mchtBrandAptitudePicChg.setCreateBy(mchtProductBrand.getStatusBy());;
				mchtBrandAptitudePicChg.setCreateDate(mchtProductBrand.getStatusDate());
				mchtBrandAptitudePicChg.setDelFlag("0");
				mchtBrandAptitudePicChg.setMchtBrandAptitudeChgId(mchtBrandAptitudeChg.getId());
				mchtBrandAptitudePicChg.setArchiveStatus(mchtBrandAptitudePic.getArchiveStatus());
				mchtBrandAptitudePicChg.setPic(mchtBrandAptitudePic.getPic());
				mchtBrandAptitudePicChgMapper.insertSelective(mchtBrandAptitudePicChg);
			}
		}
		//copy到 品牌经营的类目
		MchtBrandProductTypeExample mchtBrandProductTypeExample = new MchtBrandProductTypeExample();
		mchtBrandProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		List<MchtBrandProductType> mchtBrandProductTypes = mchtBrandProductTypeMapper.selectByExample(mchtBrandProductTypeExample);
		for (MchtBrandProductType mchtBrandProductType : mchtBrandProductTypes) {
			MchtBrandChgProductType mchtBrandChgProductType = new MchtBrandChgProductType();
			BeanUtils.copyProperties(mchtBrandProductType, mchtBrandChgProductType);
			mchtBrandChgProductType.setId(null);
			mchtBrandChgProductType.setMchtBrandChgId(mchtBrandChg.getId());
			mchtBrandChgProductTypeMapper.insertSelective(mchtBrandChgProductType);
		}
	}
	
	/**
	 * @MethodName selectByExampleCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月2日 上午11:11:10
	 */
	public List<MchtBrandChgCustom> selectByExampleCustom(MchtBrandChgExample mchtBrandChgExample) {
		// TODO Auto-generated method stub
		return mchtBrandChgCustomMapper.selectByExampleCustom(mchtBrandChgExample);
	}
}
