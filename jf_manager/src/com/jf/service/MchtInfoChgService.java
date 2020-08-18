package com.jf.service;

import com.jf.common.utils.StringUtils;
import com.jf.dao.*;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtInfoChgService extends BaseService<MchtInfoChg, MchtInfoChgExample> {
	@Autowired
	private MchtInfoChgMapper mchtInfoChgMapper;
	
	@Autowired
	private MchtInfoChgCustomMapper mchtInfoChgCustomMapper;
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Autowired
	private MchtInfoCustomMapper mchtInfoCustomMapper;
	
	@Autowired
	private MchtInfoHisMapper mhcInfoHisMapper;
	
	@Autowired
	private MchtBlPicMapper mchtBlPicMapper;
	
	@Autowired
	private MchtBlPicChgMapper mchtBlPicChgMapper;
	
	@Autowired
	private MchtBlPicHisMapper mhcBlPicHisMapper;
	
	@Autowired
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Autowired
	public void setMchtInfoChgMapper(MchtInfoChgMapper mchtInfoChgMapper) {
		super.setDao(mchtInfoChgMapper);
		this.mchtInfoChgMapper = mchtInfoChgMapper;
	}
	
    public List<MchtInfoChgCustom> selectMchtInfoChgCustomByExample(MchtInfoChgExample example){
    	return mchtInfoChgCustomMapper.selectByExample(example);
    }
    
    public int countMchtInfoChgCustomByExample(MchtInfoChgExample example){
    	return mchtInfoChgCustomMapper.countByExample(example);
    }
    
    public  MchtInfoChgCustom selectMchtInfoChgCustomByPrimaryKey(Integer id){
    	return mchtInfoChgCustomMapper.selectByPrimaryKey(id);
    }
    
    public MchtInfoChg auditMchtCompanyInfoChg(Integer id, String status, String auditRemarks, Integer auditUser, JSONArray corporationIdcardImgPicstures, JSONObject mchtClPicPicsPicstures){
    	Date now=new Date();
    	MchtInfoChg mchtInfoChg=mchtInfoChgMapper.selectByPrimaryKey(id);
    	mchtInfoChg.setStatus(status);
    	mchtInfoChg.setAuditRemarks(auditRemarks);
    	mchtInfoChg.setCommitDate(new Date());
    	mchtInfoChg.setUpdateBy(auditUser);
    	mchtInfoChg.setUpdateDate(now);
    	mchtInfoChg.setCorporationIdcardImg1((String) corporationIdcardImgPicstures.get(0));
    	if(corporationIdcardImgPicstures.size()>1){
			mchtInfoChg.setCorporationIdcardImg2((String) corporationIdcardImgPicstures.get(1));
		}

    	String clPicPicsPicstures = "";
    	String boaalPicPicstures = "";
		if(mchtClPicPicsPicstures.has("clPicPicsPicstures")){
			clPicPicsPicstures = mchtClPicPicsPicstures.getString("clPicPicsPicstures");
		}
		if(mchtClPicPicsPicstures.has("boaalPicPicstures")){
			boaalPicPicstures = mchtClPicPicsPicstures.getString("boaalPicPicstures");
		}
    	mchtInfoChg.setBlPic(clPicPicsPicstures);
    	mchtInfoChg.setBoaalPic(boaalPicPicstures);
    	if("4".equals(status)){   		
    		mchtInfoChgMapper.updateByPrimaryKey(mchtInfoChg);
    	}
    	MchtInfoCustom mchtInfoCustom = null;
    	if("3".equals(status) || "4".equals(status) ) {
    		mchtInfoCustom = mchtInfoCustomMapper.selectMchtInfoCustomById(mchtInfoChg.getMchtId());
    		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
			mchtInfoChangeLog.setLogName(mchtInfoCustom.getCompanyName());
			mchtInfoChangeLog.setCreatorType("1");
			mchtInfoChangeLog.setLogType("公司信息更新");
			mchtInfoChangeLog.setBeforeChange(mchtInfoCustom.getStatusDesc());
			mchtInfoChangeLog.setAfterChange("3".equals(status)?"通过":"驳回");
			mchtInfoChangeLog.setRemarks(auditRemarks);
			mchtInfoChangeLog.setMchtId(mchtInfoCustom.getId());
			mchtInfoChangeLog.setCreateBy(auditUser);
			mchtInfoChangeLog.setCreateDate(now);
			mchtInfoChangeLog.setDelFlag("0");
			mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
    	}
    	
    	if("3".equals(status)){   		
    		mchtInfoChg.setCompanyInfoArchiveStatus("0");
    		mchtInfoChgMapper.updateByPrimaryKey(mchtInfoChg);
    		
    		MchtInfoHis mhctInfoHis=new MchtInfoHis();
    		BeanUtils.copyProperties(mchtInfoCustom, mhctInfoHis);
    		mhctInfoHis.setMchtId(mchtInfoCustom.getId());
    		mhctInfoHis.setBizId(id);
    		mhctInfoHis.setId(null);
    		if(!StringUtils.isEmpty(mchtInfoCustom.getRemarks())){
    			if(mchtInfoCustom.getRemarks().length()>=256){
    				mhctInfoHis.setRemarks(mchtInfoCustom.getRemarks().substring(mchtInfoCustom.getRemarks().length()-256, mchtInfoCustom.getRemarks().length()));
    			}else{
    				mhctInfoHis.setRemarks(mchtInfoCustom.getRemarks());
    			}
    		}
    		mhcInfoHisMapper.insertSelective(mhctInfoHis);
    			
    		MchtInfo mchtInfo4Update=new MchtInfo();
    		mchtInfo4Update.setId(mchtInfoChg.getMchtId());
    		mchtInfo4Update.setContactCity(mchtInfoChg.getContactCity());
    		mchtInfo4Update.setContactCounty(mchtInfoChg.getContactCounty());
    		mchtInfo4Update.setContactProvince(mchtInfoChg.getContactProvince());
    		mchtInfo4Update.setUpdateBy(auditUser);
    		mchtInfo4Update.setUpdateDate(now);
    		mchtInfo4Update.setRemarks(mchtInfo4Update.getRemarks()+";更新备注："+mchtInfoChg.getAuditRemarks());
    		//合作状态
    		mchtInfo4Update.setStatus("1");
    		//身份证
    		mchtInfo4Update.setCorporationIdcardImg1((String) corporationIdcardImgPicstures.get(0));
    		mchtInfo4Update.setCorporationIdcardImg2((String) corporationIdcardImgPicstures.get(1));
    		mchtInfo4Update.setCompanyAddress(mchtInfoChg.getCompanyAddress());
    		mchtInfo4Update.setCompanyName(mchtInfoChg.getCompanyName());
    		mchtInfo4Update.setCompanyRegName(mchtInfoChg.getCompanyRegName());
    		mchtInfo4Update.setCompanyTel(mchtInfoChg.getCompanyTel());
    		mchtInfo4Update.setCompanyType(mchtInfoChg.getCompanyType());
    		mchtInfo4Update.setContactAddress(mchtInfoChg.getContactAddress()); 		
    		mchtInfo4Update.setCorporationIdcardNo(mchtInfoChg.getCorporationIdcardNo());
    		mchtInfo4Update.setCorporationMobile(mchtInfoChg.getCorporationMobile());
    		mchtInfo4Update.setCorporationName(mchtInfoChg.getCorporationName());
    		mchtInfo4Update.setFoundedDate(mchtInfoChg.getFoundedDate());
    		mchtInfo4Update.setRegCapital(mchtInfoChg.getRegCapital());
    		mchtInfo4Update.setRegFeeType(mchtInfoChg.getRegFeeType());
    		mchtInfo4Update.setUscc(mchtInfoChg.getUscc());
    		mchtInfo4Update.setYearlyInspectionDate(mchtInfoChg.getYearlyInspectionDate());
    		mchtInfo4Update.setScopeOfBusiness(mchtInfoChg.getScopeOfBusiness());
    		mchtInfo4Update.setCorporationIdcardDate(mchtInfoChg.getCorporationIdcardDate());
    		mchtInfo4Update.setBrandType(mchtInfoChg.getBrandType());
    		mchtInfo4Update.setBrandTypeDesc(mchtInfoChg.getBrandTypeDesc());
    		//营业执照副本
    		mchtInfo4Update.setBlPic(clPicPicsPicstures);
    		mchtInfo4Update.setOccPic(mchtInfoChg.getOccPic());
    		mchtInfo4Update.setTrcPic(mchtInfoChg.getTrcPic());
    		mchtInfo4Update.setAtqPic(mchtInfoChg.getAtqPic());
    		//银行开户许可证
    		mchtInfo4Update.setBlPic(clPicPicsPicstures);
    		mchtInfo4Update.setBoaalPic(boaalPicPicstures);
    		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo4Update);
    	}
    	return mchtInfoChg;
    }
    
	/**
	 * @MethodName updateByPrimaryKeyCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月24日 下午3:56:43
	 */
	public void updateByPrimaryKeyCustom(MchtInfoChg mchtInfoChg) {
		// TODO Auto-generated method stub
		mchtInfoChgCustomMapper.updateByPrimaryKeyCustom(mchtInfoChg);
	}

	/**
	 * @MethodName updateByPrimaryKeyCustoms
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月14日 上午10:12:52
	 */
	public void updateByPrimaryKeyCustoms(MchtInfoChg mchtInfoChg) {
		// TODO Auto-generated method stub
		mchtInfoChgCustomMapper.updateByPrimaryKeyCustoms(mchtInfoChg);
	}
}
