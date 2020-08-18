package com.jf.service;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.Const;
import com.jf.common.constant.SysConfig;
import com.jf.common.exception.ArgException;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.common.utils.StringUtils;
import com.jf.dao.*;
import com.jf.entity.*;
import com.jf.vo.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class MchtProductBrandService extends BaseService<MchtProductBrand,MchtProductBrandExample> {
	@Autowired
	private MchtProductBrandMapper mchtProductBrandMapper;
	
	@Autowired
	private MchtProductBrandCustomMapper mchtProductBrandCustomMapper;
	
	@Autowired
	private MchtBrandAptitudePicMapper mchtBrandAptitudePicMapper;
	
	@Autowired
	private MchtPlatformAuthPicMapper mchtPlatformAuthPicMapper;
	
	@Autowired
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Autowired
	private MchtBrandInvoicePicMapper mchtBrandInvoicePicMapper;
	
	@Autowired
	private MchtBrandInspectionPicMapper mchtBrandInspectionPicMapper;
	
	@Autowired
	private MchtBrandOtherPicMapper mchtBrandOtherPicMapper;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private MchtContractService mchtContractService;
	
	@Autowired
	private MchtContactMapper mchtContactMapper;
	
	@Autowired
	private MchtBrandChgService mchtBrandChgService;
	
	@Autowired
	private MchtInfoChgMapper mchtInfoChgMapper;
	
	@Autowired
	private MchtLicenseChgService mchtLicenseChgService;
	
	@Autowired
	public void setMchtProductBrandMapper(MchtProductBrandMapper mchtProductBrandMapper) {
		super.setDao(mchtProductBrandMapper);
		this.mchtProductBrandMapper = mchtProductBrandMapper;
	}
	
	public List<MchtProductBrandCustom> selectByCustomExample(MchtProductBrandCustomExample example) {
		return mchtProductBrandCustomMapper.selectByExample3(example);
	}
	
	public List<MchtProductBrandCustom> selectByCustomExample(MchtProductBrandExample example) {
		return mchtProductBrandCustomMapper.selectByExample(example);
	}
	
	public List<MchtProductBrandCustom> selectByCustomExample2(MchtProductBrandExample example) {
		return mchtProductBrandCustomMapper.selectByExample2(example);
	}
	
	public int countByCustomExample(MchtProductBrandCustomExample example) {
		return mchtProductBrandCustomMapper.countByExample3(example);
	}
	
	public int countByCustomExample(MchtProductBrandExample example) {
		return mchtProductBrandCustomMapper.countByExample(example);
	}
	
	public List<MchtProductBrandCustom> getMchtProductBrandCustomsByMchtId(Integer mchtId,Page page){
		return mchtProductBrandCustomMapper.getMchtProductBrandCustomsByMchtId(mchtId,page);
	}
	
	public List<MchtProductBrandCustom> getMchtProductbrandInfoCustom(HashMap<String, Object> paramMap){
		return mchtProductBrandCustomMapper.getMchtProductbrandInfoCustom(paramMap);
	}
	
	
	public MchtProductBrandCustom getMchtProductbrandInfoCustomByID(Integer mchtId){
		return mchtProductBrandCustomMapper.getMchtProductbrandInfoCustomByID(mchtId);
	}
	
	
	public void updateMchtBrandInfo(MchtProductBrand mchtProductBrand) {
		mchtProductBrandMapper.updateByPrimaryKeySelective(mchtProductBrand);
	}
	
	public int getMchtBrandCustomCount(HashMap<String, Object> paramMap){
		return mchtProductBrandCustomMapper.getMchtBrandCustomCount(paramMap);
	}
	
	public List<Map<String, Object>> selectDockingByListExample(MchtProductBrandCustomExample example) {
		   return mchtProductBrandCustomMapper.selectDockingByListExample(example);
	}
	   
	
	@SuppressWarnings("unchecked")
	public void updateMchtBrandInfo(MchtProductBrand mchtProductBrand,String mchtBrandAptitudePics,String mchtPlatformAuthPics) {
		
		
		mchtProductBrandMapper.updateByPrimaryKeySelective(mchtProductBrand);
		
		
		
		//更新资质图
		
		if(mchtProductBrand.getId()!=null){
			MchtBrandAptitudePicExample mchtBrandAptitudePicExample=new MchtBrandAptitudePicExample();
			mchtBrandAptitudePicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtProductBrand.getId()).andDelFlagEqualTo("0");
			MchtBrandAptitudePic mchtBrandAptitudePic4Update=new MchtBrandAptitudePic();
			mchtBrandAptitudePic4Update.setDelFlag("1");
			mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic4Update, mchtBrandAptitudePicExample);
		}
		JSONArray BrandAptitudePicArray =null;
		try {
			BrandAptitudePicArray =JSONArray.fromObject(mchtBrandAptitudePics);
		} catch (Exception e) {
		}
		Iterator<JSONObject> it= BrandAptitudePicArray.iterator();
		while (it.hasNext()) {
			JSONObject mchtBrandAptiudePicObject=it.next();
			
			MchtBrandAptitudePicExample mchtBrandAptitudePicExample=new MchtBrandAptitudePicExample();
			mchtBrandAptitudePicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtProductBrand.getId()).andPicEqualTo(mchtBrandAptiudePicObject.getString("picPath"));
			MchtBrandAptitudePic mchtBrandAptitudePic4Update=new MchtBrandAptitudePic();
			mchtBrandAptitudePic4Update.setDelFlag("0");
			int updateCount=mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic4Update, mchtBrandAptitudePicExample);
			if(updateCount>0){
				continue;
			}
			MchtBrandAptitudePic mchtBrandAptitudePic=new MchtBrandAptitudePic();
			mchtBrandAptitudePic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtBrandAptitudePic.setPic(mchtBrandAptiudePicObject.getString("picPath"));
			mchtBrandAptitudePic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtBrandAptitudePic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtBrandAptitudePic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtBrandAptitudePic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtBrandAptitudePicMapper.insertSelective(mchtBrandAptitudePic);
		}
		
		
		//更新授权图
		if(mchtProductBrand.getId()!=null){
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample=new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtProductBrand.getId()).andDelFlagEqualTo("0");
			MchtPlatformAuthPic mchtPlatformAuthPic4Update=new MchtPlatformAuthPic();
			mchtPlatformAuthPic4Update.setDelFlag("1");
			mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
		}
		
	
		JSONArray platformAuthPicsArray=JSONArray.fromObject(mchtPlatformAuthPics);
		Iterator<JSONObject> itrmAuthPics= platformAuthPicsArray.iterator();
		while (itrmAuthPics.hasNext()) {
			JSONObject platformAuthPicObject=itrmAuthPics.next();
			
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample=new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andMchtProductBrandIdEqualTo(mchtProductBrand.getId()).andPicEqualTo(platformAuthPicObject.getString("picPath"));
			MchtPlatformAuthPic mchtPlatformAuthPic4Update=new MchtPlatformAuthPic();
			mchtPlatformAuthPic4Update.setDelFlag("0");
			int updateCount=mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
			if(updateCount>0){
				continue;
			}
			MchtPlatformAuthPic mchtPlatformAuthPic=new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtPlatformAuthPic.setPic(platformAuthPicObject.getString("picPath"));
			mchtPlatformAuthPic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPicMapper.insertSelective(mchtPlatformAuthPic);
		}
		
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void insertMchtBrandInfo(MchtProductBrand mchtProductBrand,String mchtBrandAptitudeJsonStr,String mchtPlatformAuthPics) {
		
		
		mchtProductBrandMapper.insertSelective(mchtProductBrand);
		JSONArray mchtBrandAptitudeArray=JSONArray.fromObject(mchtBrandAptitudeJsonStr);
		Iterator<JSONObject> it= mchtBrandAptitudeArray.iterator();
		while (it.hasNext()) {
//			JSONObject mchtBrandAptiudeObject=it.next();
//			MchtBrandAptitude mchtBrandAptitude=new MchtBrandAptitude();
//			mchtBrandAptitude.setMchtProductBrandId(mchtProductBrand.getId());
//			mchtBrandAptitudePic.setPic(mchtBrandAptiudePicObject.getString("picPath"));
//			mchtBrandAptitudePic.setCreateBy(mchtProductBrand.getUpdateBy());
//			mchtBrandAptitudePic.setCreateDate(mchtProductBrand.getUpdateDate());
//			mchtBrandAptitudePic.setUpdateBy(mchtProductBrand.getUpdateBy());
//			mchtBrandAptitudePic.setUpdateDate(mchtProductBrand.getUpdateDate());
//			mchtBrandAptitudePicMapper.insertSelective(mchtBrandAptitudePic);
		}
		
		
		//插入授权图
		JSONArray platformAuthPicsArray=JSONArray.fromObject(mchtPlatformAuthPics);
		Iterator<JSONObject> itrmAuthPics= platformAuthPicsArray.iterator();
		while (itrmAuthPics.hasNext()) {
			JSONObject platformAuthPicObject=itrmAuthPics.next();
			
			MchtPlatformAuthPic mchtPlatformAuthPic=new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtPlatformAuthPic.setPic(platformAuthPicObject.getString("picPath"));
			mchtPlatformAuthPic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPicMapper.insertSelective(mchtPlatformAuthPic);
		}
		
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS", mchtProductBrand.getAuditStatus()));
		mchtInfoChangeLog.setCreateBy(mchtProductBrand.getCreateBy());
		mchtInfoChangeLog.setCreateDate(mchtProductBrand.getCreateDate());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(mchtProductBrand.getProductBrandName());
		mchtInfoChangeLog.setLogType("品牌审核");
		mchtInfoChangeLog.setMchtId(mchtProductBrand.getMchtId());
		mchtInfoChangeLog.setRemarks(mchtProductBrand.getAuditRemarks());
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
		
	}


	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtProductBrand> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	private MchtProductBrandExample builderQuery(QueryObject queryObject) {
		MchtProductBrandExample example = new MchtProductBrandExample();
		MchtProductBrandExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getQuery("statusIn") != null){
			List<String> statusIn = queryObject.getQuery("statusIn");
			criteria.andStatusIn(statusIn);
		}
		if(queryObject.getQuery("auditStatus") != null){
			criteria.andAuditStatusEqualTo(queryObject.getQueryToStr("auditStatus"));
		}
		if(queryObject.getQuery("auditStatusIn") != null){
			List<String> auditStatusIn = queryObject.getQuery("auditStatusIn");
			criteria.andAuditStatusIn(auditStatusIn);
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

	public int countExpireBrand(HashMap<String, Object> paramMap) {
		return mchtProductBrandCustomMapper.countExpireBrand(paramMap);
	}
	
	public List<MchtProductBrandCustom> getMchtProductBrandCustoms(HashMap<String, Object> paramMap) {
		return mchtProductBrandCustomMapper.getMchtProductBrandCustoms(paramMap);
	}

	public List<HashMap<String,Object>> getMchtContacts(HashMap<String, Object> paramMap) {
		return mchtProductBrandCustomMapper.getMchtContacts(paramMap);
	}

	public int sendSms(List<JSONObject> sendList) {
		int count = 0;
		for(JSONObject jo:sendList){
			JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(jo).toString()));
			if("0000".equals(result.getString("returnCode"))){
				count++;
			}else{
				break;
			}
		}
		return count;
	}
	
	public void updateMchtProductBrand(MchtProductBrand mchtProductBrand4Update) {
		MchtProductBrand oldMchtProductBrand = mchtProductBrandMapper.selectByPrimaryKey(mchtProductBrand4Update.getId());
		
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS", mchtProductBrand4Update.getAuditStatus()));
		mchtInfoChangeLog.setBeforeChange(DataDicUtil.getStatusDesc("BU_MCHT_PRODUCT_BRAND", "AUDIT_STATUS", oldMchtProductBrand.getAuditStatus()));
		mchtInfoChangeLog.setCreateBy(mchtProductBrand4Update.getUpdateBy());
		mchtInfoChangeLog.setCreateDate(mchtProductBrand4Update.getUpdateDate());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(oldMchtProductBrand.getProductBrandName());
		mchtInfoChangeLog.setLogType("品牌审核");
		mchtInfoChangeLog.setMchtId(oldMchtProductBrand.getMchtId());
		mchtInfoChangeLog.setRemarks(mchtProductBrand4Update.getAuditRemarks());
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
		mchtProductBrandMapper.updateByPrimaryKeySelective(mchtProductBrand4Update);
	}
	
		public void updateMchtProductBrandAndCopy(String mchtPlatformAuthPics,String mchtBrandInvoicePics,String mchtBrandInspectionPics,String mchtBrandOtherPics,String mchtBrandAptitudeJsonStr,MchtProductBrand mchtProductBrand4Update) {
			this.updateMchtProductBrand(mchtPlatformAuthPics,mchtBrandInvoicePics,mchtBrandInspectionPics,mchtBrandOtherPics,mchtBrandAptitudeJsonStr,mchtProductBrand4Update);
			//法务审核通过时将信息copy到品牌更新表
			mchtBrandChgService.copyToMchtBrandChg(mchtProductBrand4Update.getId());
		}
		
		public void updateMchtProductBrand(String mchtPlatformAuthPics,String mchtBrandInvoicePics,String mchtBrandInspectionPics,String mchtBrandOtherPics,String mchtBrandAptitudeJsonStr,MchtProductBrand mchtProductBrand4Update) {
		MchtProductBrand oldMchtProductBrand = mchtProductBrandMapper.selectByPrimaryKey(mchtProductBrand4Update.getId());
		
		// 更新销售授权图
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand4Update.getId());
		MchtPlatformAuthPic mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
		mchtPlatformAuthPic4Update.setDelFlag("1");
		mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);

		JSONArray platformAuthPicArray = JSONArray.fromObject(mchtPlatformAuthPics);
		Iterator<JSONObject> platformAuthPicIt = platformAuthPicArray.iterator();
		while (platformAuthPicIt.hasNext()) {
			JSONObject platformAuthPicObject = platformAuthPicIt.next();
			mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andPicEqualTo(platformAuthPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(mchtProductBrand4Update.getId());
			mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
			mchtPlatformAuthPic4Update.setDelFlag("0");
			int updateCount = mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setMchtProductBrandId(mchtProductBrand4Update.getId());
            try {
                mchtPlatformAuthPic.setPic(platformAuthPicObject.getString("picPath"));
            } catch (Exception e) {
                throw new ArgException("销售授权图片错误,请重新上传");
            }
            mchtPlatformAuthPic.setCreateDate(new Date());
			mchtPlatformAuthPic.setUpdateDate(new Date());
			mchtPlatformAuthPicMapper.insertSelective(mchtPlatformAuthPic);
		}
		
		
		// 更新进货发票图
		
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand4Update.getId());
		MchtBrandInvoicePic mchtBrandInvoicePic4Update = new MchtBrandInvoicePic();
		mchtBrandInvoicePic4Update.setDelFlag("1");
		mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);

		JSONArray invoicePicsArray = JSONArray.fromObject(mchtBrandInvoicePics);
		Iterator<JSONObject> invoicePicsIt = invoicePicsArray.iterator();
		while (invoicePicsIt.hasNext()) {
			JSONObject invoicePicObject = invoicePicsIt.next();

			mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andPicEqualTo(invoicePicObject.getString("picPath")).andMchtProductBrandIdEqualTo(mchtProductBrand4Update.getId());
			mchtBrandInvoicePic4Update = new MchtBrandInvoicePic();
			mchtBrandInvoicePic4Update.setDelFlag("0");
			int updateCount = mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandInvoicePic mchtBrandInvoicePic = new MchtBrandInvoicePic();
			mchtBrandInvoicePic.setMchtProductBrandId(mchtProductBrand4Update.getId());
            try {
                mchtBrandInvoicePic.setPic(invoicePicObject.getString("picPath"));
            } catch (Exception e) {
                throw new ArgException("进货发票图片错误,请重新上传");
            }
            mchtBrandInvoicePic.setCreateDate(new Date());
			mchtBrandInvoicePic.setUpdateDate(new Date());
			mchtBrandInvoicePicMapper.insertSelective(mchtBrandInvoicePic);
		}
		
		
		// 更新质检报告图
		
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand4Update.getId());
		MchtBrandInspectionPic mchtBrandInspectionPic4Update = new MchtBrandInspectionPic();
		mchtBrandInspectionPic4Update.setDelFlag("1");
		mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);

		JSONArray inspectionPicsArray = JSONArray.fromObject(mchtBrandInspectionPics);
		Iterator<JSONObject> inspectionPicsIt = inspectionPicsArray.iterator();
		while (inspectionPicsIt.hasNext()) {
			JSONObject inspectionPicObject = inspectionPicsIt.next();

			mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andPicEqualTo(inspectionPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(mchtProductBrand4Update.getId());
			mchtBrandInspectionPic4Update = new MchtBrandInspectionPic();
			mchtBrandInspectionPic4Update.setDelFlag("0");
			int updateCount = mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandInspectionPic mchtBrandInspectionPic = new MchtBrandInspectionPic();
			mchtBrandInspectionPic.setMchtProductBrandId(mchtProductBrand4Update.getId());
            try {
                mchtBrandInspectionPic.setPic(inspectionPicObject.getString("picPath"));
            } catch (Exception e) {
                throw new ArgException("质检图片错误,请重新上传");
            }
            mchtBrandInspectionPic.setCreateDate(new Date());
			mchtBrandInspectionPic.setUpdateDate(new Date());
			mchtBrandInspectionPicMapper.insertSelective(mchtBrandInspectionPic);
		}
		
		// 更新其他资质图
		MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
		mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand4Update.getId());
		MchtBrandOtherPic mchtBrandOtherPic4Update = new MchtBrandOtherPic();
		mchtBrandOtherPic4Update.setDelFlag("1");
		mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);

		JSONArray ohterPicsArray = JSONArray.fromObject(mchtBrandOtherPics);
		Iterator<JSONObject> otherPicsIt = ohterPicsArray.iterator();
		while (otherPicsIt.hasNext()) {
			JSONObject otherPicObject = otherPicsIt.next();

			mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andPicEqualTo(otherPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(mchtProductBrand4Update.getId());
			mchtBrandOtherPic4Update = new MchtBrandOtherPic();
			mchtBrandOtherPic4Update.setDelFlag("0");
			int updateCount = mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandOtherPic mchtBrandOtherPic = new MchtBrandOtherPic();
			mchtBrandOtherPic.setMchtProductBrandId(mchtProductBrand4Update.getId());
            try {
                mchtBrandOtherPic.setPic(otherPicObject.getString("picPath"));
            } catch (Exception e) {
                throw new ArgException("其他资质图片错误,请重新上传");
            }
            mchtBrandOtherPic.setCreateDate(new Date());
			mchtBrandOtherPic.setUpdateDate(new Date());
			mchtBrandOtherPicMapper.insertSelective(mchtBrandOtherPic);
		}
		
		JSONArray mchtBrandAptitudesArray = JSONArray.fromObject(mchtBrandAptitudeJsonStr);
		for(int j=0;j<mchtBrandAptitudesArray.size();j++){
			JSONObject mchtBrandAptitudeJo = (JSONObject)mchtBrandAptitudesArray.get(j);
			Integer mchtBrandAptitudeId = mchtBrandAptitudeJo.getInt("mchtBrandAptitudeId");
			String mchtBrandAptitudePics = mchtBrandAptitudeJo.getString("mchtBrandAptitudePics");
			JSONArray mchtBrandAptitudePicsArray = JSONArray.fromObject(mchtBrandAptitudePics);
			
			//删除商家品牌注册证id下的所有图片
			MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
			mbape.createCriteria().andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
			MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
			mchtBrandAptitudePic.setDelFlag("1");
			mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic, mbape);
			
			Iterator<JSONObject> mchtBrandAptitudePicsIt = mchtBrandAptitudePicsArray.iterator();
			while (mchtBrandAptitudePicsIt.hasNext()) {
				JSONObject mchtBrandAptitudePicObject = mchtBrandAptitudePicsIt.next();
				MchtBrandAptitudePic mbap = new MchtBrandAptitudePic();
				mbap.setMchtProductBrandId(mchtProductBrand4Update.getId());
				mbap.setMchtBrandAptitudeId(mchtBrandAptitudeId);
				try {
					mbap.setPic(mchtBrandAptitudePicObject.getString("picPath"));
				} catch (Exception e) {
					throw new ArgException("商家品牌注册图片错误,请重新上传");
				}
				mbap.setCreateDate(new Date());
				mbap.setUpdateDate(new Date());
				mchtBrandAptitudePicMapper.insertSelective(mbap);
			}
		}
		
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		if(!oldMchtProductBrand.getZsAuditStatus().equals(mchtProductBrand4Update.getZsAuditStatus())){
			mchtProductBrand4Update.setZsAuditDate(new Date());
			mchtProductBrand4Update.setZsAuditBy(mchtProductBrand4Update.getUpdateBy());
			if(oldMchtProductBrand.getZsAuditStatus().equals("1")){
				mchtInfoChangeLog.setBeforeChange("待审");
			}else if(oldMchtProductBrand.getZsAuditStatus().equals("2")){
				mchtInfoChangeLog.setBeforeChange("审核通过");
			}else if(oldMchtProductBrand.getZsAuditStatus().equals("4")){
				mchtInfoChangeLog.setBeforeChange("驳回");
			}else if(oldMchtProductBrand.getZsAuditStatus().equals("5")){
				mchtInfoChangeLog.setBeforeChange("不签约");
			}else if(oldMchtProductBrand.getZsAuditStatus().equals("6")){
				mchtInfoChangeLog.setBeforeChange("黑名单");
			}
			//TODO 招商审核
			if(!StringUtils.isEmpty(mchtProductBrand4Update.getZsAuditStatus()) && mchtProductBrand4Update.getZsAuditStatus().equals("2")){
				mchtInfoChangeLog.setAfterChange("通过");
				mchtProductBrand4Update.setAuditStatus("1");//法务待审
				mchtProductBrand4Update.setCommitAuditDate(new Date());
				mchtProductBrand4Update.setIsAuditRecommit("0");
			}else if(!StringUtils.isEmpty(mchtProductBrand4Update.getZsAuditStatus()) && mchtProductBrand4Update.getZsAuditStatus().equals("4")){
				mchtInfoChangeLog.setAfterChange("驳回");
			}else if(!StringUtils.isEmpty(mchtProductBrand4Update.getZsAuditStatus()) && mchtProductBrand4Update.getZsAuditStatus().equals("5")){
				mchtInfoChangeLog.setAfterChange("不签约");
				mchtProductBrand4Update.setStatus("3");
				mchtProductBrand4Update.setStatusBy(mchtProductBrand4Update.getUpdateBy());
				mchtProductBrand4Update.setStatusDate(new Date());
			}else if(!StringUtils.isEmpty(mchtProductBrand4Update.getZsAuditStatus()) && mchtProductBrand4Update.getZsAuditStatus().equals("6")){
				mchtInfoChangeLog.setAfterChange("黑名单");
				mchtProductBrand4Update.setStatus("3");
				mchtProductBrand4Update.setStatusBy(mchtProductBrand4Update.getUpdateBy());
				mchtProductBrand4Update.setStatusDate(new Date());
			}
			
			mchtInfoChangeLog.setCreateBy(mchtProductBrand4Update.getUpdateBy());
			mchtInfoChangeLog.setCreateDate(mchtProductBrand4Update.getUpdateDate());
			mchtInfoChangeLog.setDelFlag("0");
			mchtInfoChangeLog.setLogName(oldMchtProductBrand.getProductBrandName());
			mchtInfoChangeLog.setLogType("招商品牌确认");
			mchtInfoChangeLog.setMchtId(oldMchtProductBrand.getMchtId());
			mchtInfoChangeLog.setRemarks(mchtProductBrand4Update.getZsAuditRemarks());
			mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
			
		}
		if(!StringUtils.isEmpty(oldMchtProductBrand.getAuditStatus()) && !oldMchtProductBrand.getAuditStatus().equals(mchtProductBrand4Update.getAuditStatus())){
			mchtProductBrand4Update.setAuditDate(new Date());
			mchtProductBrand4Update.setAuditBy(mchtProductBrand4Update.getUpdateBy());
			if(oldMchtProductBrand.getAuditStatus().equals("1")){
				mchtInfoChangeLog.setBeforeChange("待审");
			}else if(oldMchtProductBrand.getAuditStatus().equals("3")){
				mchtInfoChangeLog.setBeforeChange("审核通过");
			}else if(oldMchtProductBrand.getAuditStatus().equals("4")){
				mchtInfoChangeLog.setBeforeChange("驳回");
			}else if(oldMchtProductBrand.getAuditStatus().equals("5")){
				mchtInfoChangeLog.setBeforeChange("不签约");
			}else if(oldMchtProductBrand.getAuditStatus().equals("6")){
				mchtInfoChangeLog.setBeforeChange("黑名单");
			}
			//法务审核
			if(!StringUtils.isEmpty(mchtProductBrand4Update.getAuditStatus()) && mchtProductBrand4Update.getAuditStatus().equals("3")){
				mchtInfoChangeLog.setAfterChange("通过");
				if(oldMchtProductBrand.getBrandSource().equals("1")){
					mchtProductBrand4Update.setStatus("1");
					mchtProductBrand4Update.setStatusBy(mchtProductBrand4Update.getUpdateBy());
					mchtProductBrand4Update.setStatusDate(new Date());
					mchtProductBrand4Update.setStatusRemarks("入驻的品牌法务审核通过时，品牌运营状态变更为正常");
				}else if(oldMchtProductBrand.getBrandSource().equals("2")){
					mchtProductBrand4Update.setArchiveStatus("0");
				}
			}else if(!StringUtils.isEmpty(mchtProductBrand4Update.getAuditStatus()) && mchtProductBrand4Update.getAuditStatus().equals("4")){
				mchtInfoChangeLog.setAfterChange("驳回");
			}else if(!StringUtils.isEmpty(mchtProductBrand4Update.getAuditStatus()) && mchtProductBrand4Update.getAuditStatus().equals("5")){
				mchtInfoChangeLog.setAfterChange("不签约");
				mchtProductBrand4Update.setStatus("3");
				mchtProductBrand4Update.setStatusBy(mchtProductBrand4Update.getUpdateBy());
				mchtProductBrand4Update.setStatusDate(new Date());
			}else if(!StringUtils.isEmpty(mchtProductBrand4Update.getAuditStatus()) && mchtProductBrand4Update.getAuditStatus().equals("6")){
				mchtInfoChangeLog.setAfterChange("黑名单");
				mchtProductBrand4Update.setStatus("3");
				mchtProductBrand4Update.setStatusBy(mchtProductBrand4Update.getUpdateBy());
				mchtProductBrand4Update.setStatusDate(new Date());
			}
			
			mchtInfoChangeLog.setCreateBy(mchtProductBrand4Update.getUpdateBy());
			mchtInfoChangeLog.setCreateDate(mchtProductBrand4Update.getUpdateDate());
			mchtInfoChangeLog.setDelFlag("0");
			mchtInfoChangeLog.setLogName(oldMchtProductBrand.getProductBrandName());
			mchtInfoChangeLog.setLogType("法务品牌确认");
			mchtInfoChangeLog.setMchtId(oldMchtProductBrand.getMchtId());
			mchtInfoChangeLog.setRemarks(mchtProductBrand4Update.getAuditRemarks());
			mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		}
		if(!StringUtils.isEmpty(mchtProductBrand4Update.getAuditStatus()) && mchtProductBrand4Update.getAuditStatus().equals("3")){
			mchtProductBrand4Update.setArchiveStatus("0");//0.未寄出
		}
		mchtProductBrandMapper.updateByPrimaryKeySelective(mchtProductBrand4Update);
	}

	public void zsCheckMchtProductBrands(MchtInfo mchtInfo,String mchtProductBrandJsonStr,List<MchtInfoChangeLog> mchtInfoChangeLogs) {
		mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		JSONArray mchtProductBrandJsonArray = JSONArray.fromObject(mchtProductBrandJsonStr);
		for(int i=0;i<mchtProductBrandJsonArray.size();i++){
			JSONObject mchtProductBrandJsonObject = (JSONObject)mchtProductBrandJsonArray.get(i);
			MchtProductBrand mpb = new MchtProductBrand();
			String id = mchtProductBrandJsonObject.getString("id");
			MchtProductBrand mchtProductBrand = this.selectByPrimaryKey(Integer.parseInt(id));
			if (StringUtils.isEmpty(mchtProductBrand.getIsZsAuditRecommit())) {
				mpb.setIsZsAuditRecommit("0");
			} else {
				mpb.setIsZsAuditRecommit("1");
			}
			String productBrandName = mchtProductBrandJsonObject.getString("productBrandName");
			if(mchtProductBrandJsonObject.has("popCommissionRate")){
				String popCommissionRate = mchtProductBrandJsonObject.getString("popCommissionRate");
				if (!StringUtil.isEmpty(popCommissionRate)){
				mpb.setPopCommissionRate(new BigDecimal(popCommissionRate));
				}
			}
			if(mchtProductBrandJsonObject.has("priceModel")){
				String priceModel = mchtProductBrandJsonObject.getString("priceModel");
				mpb.setPriceModel(priceModel);
			}
			String zsAuditStatus = mchtProductBrandJsonObject.getString("zsAuditStatus");
			String zsAuditRemarks = mchtProductBrandJsonObject.getString("zsAuditRemarks");
			String zsAuditInnerRemarks = mchtProductBrandJsonObject.getString("zsAuditInnerRemarks");
			mpb.setProductBrandName(productBrandName);
			mpb.setZsAuditStatus(zsAuditStatus);
			mpb.setZsAuditRemarks(zsAuditRemarks);
			mpb.setZsAuditInnerRemarks(zsAuditInnerRemarks);
			if(zsAuditStatus.equals("2")){//2 审核通过
				mpb.setCommitAuditDate(new Date());
				mpb.setAuditStatus("1");//1.待审
			}else{
				mpb.setAuditStatus("0");//0.未提审
				if(zsAuditStatus.equals("5") || zsAuditStatus.equals("6")){//不签约或者黑名单
					mpb.setStatus("3");//关闭
					mpb.setStatusBy(mchtInfo.getUpdateBy());
					mpb.setStatusDate(new Date());
					mpb.setStatusRemarks("因品牌不签约或者被拉入黑名单而关闭（招商审核）");
				}
			}
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
			this.updateByExampleSelective(mpb, mpbe);
			
			String mchtPlatformAuthPics = mchtProductBrandJsonObject.getString("mchtPlatformAuthPics");
			String mchtBrandInvoicePics = mchtProductBrandJsonObject.getString("mchtBrandInvoicePics");
			String mchtBrandInspectionPics = mchtProductBrandJsonObject.getString("mchtBrandInspectionPics");
			String mchtBrandOtherPics = mchtProductBrandJsonObject.getString("mchtBrandOtherPics");
			
			// 更新销售授权图
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.parseInt(id));
			MchtPlatformAuthPic mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
			mchtPlatformAuthPic4Update.setDelFlag("1");
			mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);

			JSONArray platformAuthPicArray = JSONArray.fromObject(mchtPlatformAuthPics);
			Iterator<JSONObject> platformAuthPicIt = platformAuthPicArray.iterator();
			while (platformAuthPicIt.hasNext()) {
				JSONObject platformAuthPicObject = platformAuthPicIt.next();

				mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
				mchtPlatformAuthPicExample.createCriteria().andPicEqualTo(platformAuthPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(Integer.parseInt(id));
				mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
				mchtPlatformAuthPic4Update.setDelFlag("0");
				int updateCount = mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
				mchtPlatformAuthPic.setMchtProductBrandId(Integer.parseInt(id));
				mchtPlatformAuthPic.setPic(platformAuthPicObject.getString("picPath"));
				mchtPlatformAuthPic.setCreateDate(new Date());
				mchtPlatformAuthPic.setUpdateDate(new Date());
				mchtPlatformAuthPicMapper.insertSelective(mchtPlatformAuthPic);
			}
			
			
			// 更新进货发票图
			
			MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.parseInt(id));
			MchtBrandInvoicePic mchtBrandInvoicePic4Update = new MchtBrandInvoicePic();
			mchtBrandInvoicePic4Update.setDelFlag("1");
			mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);

			JSONArray invoicePicsArray = JSONArray.fromObject(mchtBrandInvoicePics);
			Iterator<JSONObject> invoicePicsIt = invoicePicsArray.iterator();
			while (invoicePicsIt.hasNext()) {
				JSONObject invoicePicObject = invoicePicsIt.next();

				mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
				mchtBrandInvoicePicExample.createCriteria().andPicEqualTo(invoicePicObject.getString("picPath")).andMchtProductBrandIdEqualTo(Integer.parseInt(id));
				mchtBrandInvoicePic4Update = new MchtBrandInvoicePic();
				mchtBrandInvoicePic4Update.setDelFlag("0");
				int updateCount = mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandInvoicePic mchtBrandInvoicePic = new MchtBrandInvoicePic();
				mchtBrandInvoicePic.setMchtProductBrandId(Integer.parseInt(id));
				mchtBrandInvoicePic.setPic(invoicePicObject.getString("picPath"));
				mchtBrandInvoicePic.setCreateDate(new Date());
				mchtBrandInvoicePic.setUpdateDate(new Date());
				mchtBrandInvoicePicMapper.insertSelective(mchtBrandInvoicePic);
			}
			
			
			// 更新质检报告图
			
			MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.parseInt(id));
			MchtBrandInspectionPic mchtBrandInspectionPic4Update = new MchtBrandInspectionPic();
			mchtBrandInspectionPic4Update.setDelFlag("1");
			mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);

			JSONArray inspectionPicsArray = JSONArray.fromObject(mchtBrandInspectionPics);
			Iterator<JSONObject> inspectionPicsIt = inspectionPicsArray.iterator();
			while (inspectionPicsIt.hasNext()) {
				JSONObject inspectionPicObject = inspectionPicsIt.next();

				mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
				mchtBrandInspectionPicExample.createCriteria().andPicEqualTo(inspectionPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(Integer.parseInt(id));
				mchtBrandInspectionPic4Update = new MchtBrandInspectionPic();
				mchtBrandInspectionPic4Update.setDelFlag("0");
				int updateCount = mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandInspectionPic mchtBrandInspectionPic = new MchtBrandInspectionPic();
				mchtBrandInspectionPic.setMchtProductBrandId(Integer.parseInt(id));
				mchtBrandInspectionPic.setPic(inspectionPicObject.getString("picPath"));
				mchtBrandInspectionPic.setCreateDate(new Date());
				mchtBrandInspectionPic.setUpdateDate(new Date());
				mchtBrandInspectionPicMapper.insertSelective(mchtBrandInspectionPic);
			}
			
			// 更新其他资质图
			MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.parseInt(id));
			MchtBrandOtherPic mchtBrandOtherPic4Update = new MchtBrandOtherPic();
			mchtBrandOtherPic4Update.setDelFlag("1");
			mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);

			JSONArray ohterPicsArray = JSONArray.fromObject(mchtBrandOtherPics);
			Iterator<JSONObject> otherPicsIt = ohterPicsArray.iterator();
			while (otherPicsIt.hasNext()) {
				JSONObject otherPicObject = otherPicsIt.next();

				mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
				mchtBrandOtherPicExample.createCriteria().andPicEqualTo(otherPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(Integer.parseInt(id));
				mchtBrandOtherPic4Update = new MchtBrandOtherPic();
				mchtBrandOtherPic4Update.setDelFlag("0");
				int updateCount = mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandOtherPic mchtBrandOtherPic = new MchtBrandOtherPic();
				mchtBrandOtherPic.setMchtProductBrandId(Integer.parseInt(id));
				mchtBrandOtherPic.setPic(otherPicObject.getString("picPath"));
				mchtBrandOtherPic.setCreateDate(new Date());
				mchtBrandOtherPic.setUpdateDate(new Date());
				mchtBrandOtherPicMapper.insertSelective(mchtBrandOtherPic);
			}
			
			String mchtBrandAptitudes = mchtProductBrandJsonObject.getString("mchtBrandAptitudes");
			JSONArray mchtBrandAptitudesArray = JSONArray.fromObject(mchtBrandAptitudes);
			for(int j=0;j<mchtBrandAptitudesArray.size();j++){
				JSONObject mchtBrandAptitudeJo = (JSONObject)mchtBrandAptitudesArray.get(j);
				Integer mchtBrandAptitudeId = mchtBrandAptitudeJo.getInt("mchtBrandAptitudeId");
				String mchtBrandAptitudePics = mchtBrandAptitudeJo.getString("mchtBrandAptitudePics");
				JSONArray mchtBrandAptitudePicsArray = JSONArray.fromObject(mchtBrandAptitudePics);
				Iterator<JSONObject> mchtBrandAptitudePicsIt = mchtBrandAptitudePicsArray.iterator();
				while (mchtBrandAptitudePicsIt.hasNext()) {
					JSONObject mchtBrandAptitudePicObject = mchtBrandAptitudePicsIt.next();

					MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
					mbape.createCriteria().andPicEqualTo(mchtBrandAptitudePicObject.getString("picPath")).andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
					MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
					mchtBrandAptitudePic.setDelFlag("0");
					int updateCount = mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic, mbape);
					if (updateCount > 0) {
						continue;
					}
					MchtBrandAptitudePic mbap = new MchtBrandAptitudePic();
					mbap.setMchtProductBrandId(Integer.parseInt(id));
					mbap.setMchtBrandAptitudeId(mchtBrandAptitudeId);
					mbap.setPic(mchtBrandAptitudePicObject.getString("picPath"));
					mbap.setCreateDate(new Date());
					mbap.setUpdateDate(new Date());
					mchtBrandAptitudePicMapper.insertSelective(mbap);
				}
			}
			
			//品牌 审核后，需插入日志
			MchtInfoChangeLog micl = new MchtInfoChangeLog();
			micl.setMchtId(mchtInfo.getId());
			micl.setCreateDate(new Date());
			micl.setCreateBy(mchtInfoChangeLogs.get(0).getCreateBy());
			micl.setDelFlag("0");
			micl.setLogType("招商品牌确认");
			micl.setLogName(productBrandName);
			micl.setBeforeChange("待审");
			if(zsAuditStatus.equals("2")){
				micl.setAfterChange("通过");
			}else if(zsAuditStatus.equals("4")){
				micl.setAfterChange("驳回");
			}else if(zsAuditStatus.equals("5")){
				micl.setAfterChange("不签约");
			}else if(zsAuditStatus.equals("6")){
				micl.setAfterChange("黑名单");
			}
			micl.setRemarks(zsAuditRemarks);
			mchtInfoChangeLogs.add(micl);
		}
		for(MchtInfoChangeLog mchtInfoChangeLog:mchtInfoChangeLogs){
			mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		}
	}
	
	public void fwCheckMchtProductBrands(MchtInfo mchtInfo,MchtContract mchtContract,String mchtProductBrandJsonStr,List<MchtInfoChangeLog> mchtInfoChangeLogs,List<MchtContact> MchtContacts) {
		mchtInfoService.updateByPrimaryKeySelective(mchtInfo);
		JSONArray mchtProductBrandJsonArray = JSONArray.fromObject(mchtProductBrandJsonStr);
		for(int i=0;i<mchtProductBrandJsonArray.size();i++){
			JSONObject mchtProductBrandJsonObject = (JSONObject)mchtProductBrandJsonArray.get(i);
			MchtProductBrand mpb = new MchtProductBrand();
			String id = mchtProductBrandJsonObject.getString("id");
			MchtProductBrand mchtProductBrand = this.selectByPrimaryKey(Integer.parseInt(id));
			if (StringUtils.isEmpty(mchtProductBrand.getIsAuditRecommit())) {
				mpb.setIsAuditRecommit("0");
			} else {
				mpb.setIsAuditRecommit("1");
			}
			String productBrandName = mchtProductBrandJsonObject.getString("productBrandName");
			String productBrandId = mchtProductBrandJsonObject.getString("productBrandId");
			if(mchtProductBrandJsonObject.has("popCommissionRate")){
				String popCommissionRate = mchtProductBrandJsonObject.getString("popCommissionRate");
				mpb.setPopCommissionRate(new BigDecimal(popCommissionRate));
			}
			if(mchtProductBrandJsonObject.has("priceModel")){
				String priceModel = mchtProductBrandJsonObject.getString("priceModel");
				mpb.setPriceModel(priceModel);
			}
			String auditStatus = mchtProductBrandJsonObject.getString("auditStatus");
			String auditRemarks = mchtProductBrandJsonObject.getString("auditRemarks");
			String auditInnerRemarks = mchtProductBrandJsonObject.getString("auditInnerRemarks");
			mpb.setProductBrandName(productBrandName);
			if(!StringUtils.isEmpty(productBrandId)){
				mpb.setProductBrandId(Integer.parseInt(productBrandId));
			}
			mpb.setAuditStatus(auditStatus);
			if(auditStatus.equals("3")){//通过
				mpb.setStatus("1");
				mpb.setStatusDate(new Date());
				mpb.setStatusBy(mchtInfo.getUpdateBy());
				mpb.setArchiveStatus("0");//0.未寄出
			}else if(auditStatus.equals("5") || auditStatus.equals("6")){
				mpb.setStatus("3");
				mpb.setStatusDate(new Date());
				mpb.setStatusBy(mchtInfo.getUpdateBy());
				mpb.setStatusRemarks("因品牌不签约或被拉入黑名单而关闭（法务审核）");
			}
			mpb.setAuditRemarks(auditRemarks);
			mpb.setAuditInnerRemarks(auditInnerRemarks);
			
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(id));
			this.updateByExampleSelective(mpb, mpbe);
			
			String mchtPlatformAuthPics = mchtProductBrandJsonObject.getString("mchtPlatformAuthPics");
			String mchtBrandInvoicePics = mchtProductBrandJsonObject.getString("mchtBrandInvoicePics");
			String mchtBrandInspectionPics = mchtProductBrandJsonObject.getString("mchtBrandInspectionPics");
			String mchtBrandOtherPics = mchtProductBrandJsonObject.getString("mchtBrandOtherPics");
			
			// 更新销售授权图
			MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.parseInt(id));
			MchtPlatformAuthPic mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
			mchtPlatformAuthPic4Update.setDelFlag("1");
			mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
			
			JSONArray platformAuthPicArray = JSONArray.fromObject(mchtPlatformAuthPics);
			Iterator<JSONObject> platformAuthPicIt = platformAuthPicArray.iterator();
			while (platformAuthPicIt.hasNext()) {
				JSONObject platformAuthPicObject = platformAuthPicIt.next();
				
				mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
				mchtPlatformAuthPicExample.createCriteria().andPicEqualTo(platformAuthPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(Integer.parseInt(id));
				mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
				mchtPlatformAuthPic4Update.setDelFlag("0");
				int updateCount = mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
				mchtPlatformAuthPic.setMchtProductBrandId(Integer.parseInt(id));
				mchtPlatformAuthPic.setPic(platformAuthPicObject.getString("picPath"));
				mchtPlatformAuthPic.setCreateDate(new Date());
				mchtPlatformAuthPic.setUpdateDate(new Date());
				mchtPlatformAuthPicMapper.insertSelective(mchtPlatformAuthPic);
			}
			
			
			// 更新进货发票图
			
			MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.parseInt(id));
			MchtBrandInvoicePic mchtBrandInvoicePic4Update = new MchtBrandInvoicePic();
			mchtBrandInvoicePic4Update.setDelFlag("1");
			mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);
			
			JSONArray invoicePicsArray = JSONArray.fromObject(mchtBrandInvoicePics);
			Iterator<JSONObject> invoicePicsIt = invoicePicsArray.iterator();
			while (invoicePicsIt.hasNext()) {
				JSONObject invoicePicObject = invoicePicsIt.next();
				
				mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
				mchtBrandInvoicePicExample.createCriteria().andPicEqualTo(invoicePicObject.getString("picPath")).andMchtProductBrandIdEqualTo(Integer.parseInt(id));
				mchtBrandInvoicePic4Update = new MchtBrandInvoicePic();
				mchtBrandInvoicePic4Update.setDelFlag("0");
				int updateCount = mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandInvoicePic mchtBrandInvoicePic = new MchtBrandInvoicePic();
				mchtBrandInvoicePic.setMchtProductBrandId(Integer.parseInt(id));
				mchtBrandInvoicePic.setPic(invoicePicObject.getString("picPath"));
				mchtBrandInvoicePic.setCreateDate(new Date());
				mchtBrandInvoicePic.setUpdateDate(new Date());
				mchtBrandInvoicePicMapper.insertSelective(mchtBrandInvoicePic);
			}
			
			
			// 更新质检报告图
			
			MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.parseInt(id));
			MchtBrandInspectionPic mchtBrandInspectionPic4Update = new MchtBrandInspectionPic();
			mchtBrandInspectionPic4Update.setDelFlag("1");
			mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);
			
			JSONArray inspectionPicsArray = JSONArray.fromObject(mchtBrandInspectionPics);
			Iterator<JSONObject> inspectionPicsIt = inspectionPicsArray.iterator();
			while (inspectionPicsIt.hasNext()) {
				JSONObject inspectionPicObject = inspectionPicsIt.next();
				
				mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
				mchtBrandInspectionPicExample.createCriteria().andPicEqualTo(inspectionPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(Integer.parseInt(id));
				mchtBrandInspectionPic4Update = new MchtBrandInspectionPic();
				mchtBrandInspectionPic4Update.setDelFlag("0");
				int updateCount = mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandInspectionPic mchtBrandInspectionPic = new MchtBrandInspectionPic();
				mchtBrandInspectionPic.setMchtProductBrandId(Integer.parseInt(id));
				mchtBrandInspectionPic.setPic(inspectionPicObject.getString("picPath"));
				mchtBrandInspectionPic.setCreateDate(new Date());
				mchtBrandInspectionPic.setUpdateDate(new Date());
				mchtBrandInspectionPicMapper.insertSelective(mchtBrandInspectionPic);
			}
			
			// 更新其他资质图
			MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(Integer.parseInt(id));
			MchtBrandOtherPic mchtBrandOtherPic4Update = new MchtBrandOtherPic();
			mchtBrandOtherPic4Update.setDelFlag("1");
			mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);
			
			JSONArray ohterPicsArray = JSONArray.fromObject(mchtBrandOtherPics);
			Iterator<JSONObject> otherPicsIt = ohterPicsArray.iterator();
			while (otherPicsIt.hasNext()) {
				JSONObject otherPicObject = otherPicsIt.next();
				
				mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
				mchtBrandOtherPicExample.createCriteria().andPicEqualTo(otherPicObject.getString("picPath")).andMchtProductBrandIdEqualTo(Integer.parseInt(id));
				mchtBrandOtherPic4Update = new MchtBrandOtherPic();
				mchtBrandOtherPic4Update.setDelFlag("0");
				int updateCount = mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandOtherPic mchtBrandOtherPic = new MchtBrandOtherPic();
				mchtBrandOtherPic.setMchtProductBrandId(Integer.parseInt(id));
				mchtBrandOtherPic.setPic(otherPicObject.getString("picPath"));
				mchtBrandOtherPic.setCreateDate(new Date());
				mchtBrandOtherPic.setUpdateDate(new Date());
				mchtBrandOtherPicMapper.insertSelective(mchtBrandOtherPic);
			}
			
			String mchtBrandAptitudes = mchtProductBrandJsonObject.getString("mchtBrandAptitudes");
			JSONArray mchtBrandAptitudesArray = JSONArray.fromObject(mchtBrandAptitudes);
			for(int j=0;j<mchtBrandAptitudesArray.size();j++){
				JSONObject mchtBrandAptitudeJo = (JSONObject)mchtBrandAptitudesArray.get(j);
				Integer mchtBrandAptitudeId = mchtBrandAptitudeJo.getInt("mchtBrandAptitudeId");
				String mchtBrandAptitudePics = mchtBrandAptitudeJo.getString("mchtBrandAptitudePics");
				JSONArray mchtBrandAptitudePicsArray = JSONArray.fromObject(mchtBrandAptitudePics);
				Iterator<JSONObject> mchtBrandAptitudePicsIt = mchtBrandAptitudePicsArray.iterator();
				while (mchtBrandAptitudePicsIt.hasNext()) {
					JSONObject mchtBrandAptitudePicObject = mchtBrandAptitudePicsIt.next();
					
					MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
					mbape.createCriteria().andPicEqualTo(mchtBrandAptitudePicObject.getString("picPath")).andMchtBrandAptitudeIdEqualTo(mchtBrandAptitudeId);
					MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
					mchtBrandAptitudePic.setDelFlag("0");
					int updateCount = mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic, mbape);
					if (updateCount > 0) {
						continue;
					}
					MchtBrandAptitudePic mbap = new MchtBrandAptitudePic();
					mbap.setMchtProductBrandId(Integer.parseInt(id));
					mbap.setMchtBrandAptitudeId(mchtBrandAptitudeId);
					mbap.setPic(mchtBrandAptitudePicObject.getString("picPath"));
					mbap.setCreateDate(new Date());
					mbap.setUpdateDate(new Date());
					mchtBrandAptitudePicMapper.insertSelective(mbap);
				}
			}
			
			//品牌 审核后，需插入日志
			MchtInfoChangeLog micl = new MchtInfoChangeLog();
			micl.setMchtId(mchtInfo.getId());
			micl.setCreateDate(new Date());
			micl.setCreateBy(mchtInfoChangeLogs.get(0).getCreateBy());
			micl.setDelFlag("0");
			micl.setLogType("法务品牌确认");
			micl.setLogName(productBrandName);
			micl.setBeforeChange("待审");
			if(auditStatus.equals("3")){
				micl.setAfterChange("通过");
			}else if(auditStatus.equals("4")){
				micl.setAfterChange("驳回");
			}else if(auditStatus.equals("5")){
				micl.setAfterChange("不签约");
			}else if(auditStatus.equals("6")){
				micl.setAfterChange("黑名单");
			}
			micl.setRemarks(auditRemarks);
			mchtInfoChangeLogs.add(micl);
			
			//品牌法务审核通过 且 店铺总审通过 时将品牌信息COPY到更新表
			if("3".equals(auditStatus) && "2".equals(mchtInfo.getTotalAuditStatus())){
				mchtBrandChgService.copyToMchtBrandChg(Integer.parseInt(id));
			}
		}
		for(MchtInfoChangeLog mchtInfoChangeLog:mchtInfoChangeLogs){
			mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		}
		if(mchtInfo.getTotalAuditStatus().equals("2")){
			mchtContractService.insertSelective(mchtContract);
			//生成PDF
			if(mchtInfo.getSettledType().equals("1")){//1.企业公司
				mchtContractService.createPDF(mchtContract.getId());
			}else{//2.个体
				mchtContractService.createIndividualPDF(mchtContract.getId());
			}
		}
		//更新联系人审核
		for(MchtContact mchtContact:MchtContacts){
			mchtContactMapper.updateByPrimaryKeySelective(mchtContact);
		}
		//法务总审通过后(公司、经营许可证都写入更新表)
		if(org.apache.commons.lang.StringUtils.equals(mchtInfo.getTotalAuditStatus(), "2")){
			insertMchtInfoChg(mchtInfo);
			mchtLicenseChgService.copyToMchtLicenseChg(mchtInfo.getId());
		}	
	}
	
	/**
	 * @MethodName insertChg
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月15日 下午1:37:26
	 */
	private void insertMchtInfoChg(MchtInfo mchtInfo) {
		// TODO Auto-generated method stub
		//公司信息
		MchtInfoChg mchtInfoChg = new MchtInfoChg();
		mchtInfoChg.setMchtId(mchtInfo.getId());
		mchtInfoChg.setMchtCode(mchtInfo.getMchtCode());
		mchtInfoChg.setStatus("3");
		mchtInfoChg.setCompanyName(mchtInfo.getCompanyName());
		mchtInfoChg.setCompanyType(mchtInfo.getCompanyType());
		mchtInfoChg.setCompanyRegName(mchtInfo.getCompanyRegName());
		mchtInfoChg.setUscc(mchtInfo.getUscc());
		mchtInfoChg.setCompanyAddress(mchtInfo.getCompanyAddress());
		mchtInfoChg.setFoundedDate(mchtInfo.getFoundedDate());
		mchtInfoChg.setRegCapital(mchtInfo.getRegCapital());
		mchtInfoChg.setRegFeeType(mchtInfo.getRegFeeType());
		mchtInfoChg.setCorporationName(mchtInfo.getCorporationName());
		mchtInfoChg.setCorporationIdcardNo(mchtInfo.getCorporationIdcardNo());
		mchtInfoChg.setCorporationMobile(mchtInfo.getCorporationMobile());
		mchtInfoChg.setCorporationIdcardImg1(mchtInfo.getCorporationIdcardImg1());
		mchtInfoChg.setCorporationIdcardImg2(mchtInfo.getCorporationIdcardImg2());
		mchtInfoChg.setBlPic(mchtInfo.getBlPic());
		mchtInfoChg.setOccPic(mchtInfo.getOccPic());
		mchtInfoChg.setTrcPic(mchtInfo.getTrcPic());
		mchtInfoChg.setAtqPic(mchtInfo.getAtqPic());
		mchtInfoChg.setBoaalPic(mchtInfo.getBoaalPic());
		mchtInfoChg.setYearlyInspectionDate(mchtInfo.getYearlyInspectionDate());
		mchtInfoChg.setCompanyTel(mchtInfo.getCompanyTel());
		mchtInfoChg.setContactProvince(mchtInfo.getContactProvince());
		mchtInfoChg.setContactCity(mchtInfo.getContactCity());
		mchtInfoChg.setContactCounty(mchtInfo.getContactCounty());
		mchtInfoChg.setContactAddress(mchtInfo.getContactAddress());
		mchtInfoChg.setCommitDate(mchtInfo.getCommitAuditDate());
		mchtInfoChg.setScopeOfBusiness(mchtInfo.getScopeOfBusiness());
		mchtInfoChg.setCorporationIdcardDate(mchtInfo.getCorporationIdcardDate());
		mchtInfoChg.setCompanyInfoArchiveStatus("0");
		mchtInfoChg.setArchiveDealStatus("0");
		mchtInfoChg.setCreateBy(mchtInfo.getUpdateBy());
		mchtInfoChg.setCreateDate(new Date());
		mchtInfoChg.setUpdateBy(mchtInfo.getUpdateBy());
		mchtInfoChg.setUpdateDate(new Date());
		mchtInfoChg.setDelFlag("0");
		mchtInfoChgMapper.insertSelective(mchtInfoChg);
	}
	
	public void openMchtProductBrand(MchtProductBrand mpb,MchtInfoChangeLog micl) {
		this.updateByPrimaryKeySelective(mpb);
		mchtInfoChangeLogMapper.insertSelective(micl);
	}

	public void mchtProductBrandPicsArchive(Integer mchtProductBrandId,String mchtBrandAptitudeId, Integer staffID, String picType) {
		if(!StringUtils.isEmpty(picType) && picType.equals("1")){
			MchtBrandAptitudePicExample mbapce = new MchtBrandAptitudePicExample();
			mbapce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId).andMchtBrandAptitudeIdEqualTo(Integer.parseInt(mchtBrandAptitudeId));
			MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
			mchtBrandAptitudePic.setUpdateBy(staffID);
			mchtBrandAptitudePic.setUpdateDate(new Date());
			mchtBrandAptitudePic.setArchiveBy(staffID);
			mchtBrandAptitudePic.setArchiveStatus("1");//已归档
			mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic, mbapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("2")){
			MchtPlatformAuthPicExample mpapce = new MchtPlatformAuthPicExample();
			mpapce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setUpdateBy(staffID);
			mchtPlatformAuthPic.setUpdateDate(new Date());
			mchtPlatformAuthPic.setArchiveBy(staffID);
			mchtPlatformAuthPic.setArchiveStatus("1");//已归档
			mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic, mpapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("3")){
			MchtBrandInvoicePicExample mbipce = new MchtBrandInvoicePicExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			MchtBrandInvoicePic mchtBrandInvoicePic = new MchtBrandInvoicePic();
			mchtBrandInvoicePic.setUpdateBy(staffID);
			mchtBrandInvoicePic.setUpdateDate(new Date());
			mchtBrandInvoicePic.setArchiveBy(staffID);
			mchtBrandInvoicePic.setArchiveStatus("1");//已归档
			mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("4")){
			MchtBrandInspectionPicExample mbipce = new MchtBrandInspectionPicExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			MchtBrandInspectionPic mchtBrandInspectionPic = new MchtBrandInspectionPic();
			mchtBrandInspectionPic.setUpdateBy(staffID);
			mchtBrandInspectionPic.setUpdateDate(new Date());
			mchtBrandInspectionPic.setArchiveBy(staffID);
			mchtBrandInspectionPic.setArchiveStatus("1");//已归档
			mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("5")){
			MchtBrandOtherPicExample mbopce = new MchtBrandOtherPicExample();
			mbopce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
			MchtBrandOtherPic mchtBrandOtherPic = new MchtBrandOtherPic();
			mchtBrandOtherPic.setUpdateBy(staffID);
			mchtBrandOtherPic.setUpdateDate(new Date());
			mchtBrandOtherPic.setArchiveBy(staffID);
			mchtBrandOtherPic.setArchiveStatus("1");//已归档
			mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic,mbopce);
		}
		//TODO 判断是不是全部已齐全了
		updateMchtProductBrandArchiveStatus(mchtProductBrandId, staffID);
		
	}
	
	public Integer updateMchtProductBrandArchiveStatus(Integer mchtProductBrandId,Integer staffID) {
		MchtBrandAptitudePicExample mbapce = new MchtBrandAptitudePicExample();
		mbapce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		int mchtBrandAptitudePicCount = mchtBrandAptitudePicMapper.countByExample(mbapce);
		
		MchtPlatformAuthPicExample mpapce = new MchtPlatformAuthPicExample();
		mpapce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		int mchtPlatformAuthPicCount = mchtPlatformAuthPicMapper.countByExample(mpapce);
		
		MchtBrandInvoicePicExample mbipce = new MchtBrandInvoicePicExample();
		mbipce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		int mchtBrandInvoicePicCount = mchtBrandInvoicePicMapper.countByExample(mbipce);
		
		MchtBrandInspectionPicExample mbipce2 = new MchtBrandInspectionPicExample();
		mbipce2.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		int mchtBrandInspectionPicCount = mchtBrandInspectionPicMapper.countByExample(mbipce2);
		
		MchtBrandOtherPicExample mbopce = new MchtBrandOtherPicExample();
		mbopce.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId);
		int mchtBrandOtherPicCount = mchtBrandOtherPicMapper.countByExample(mbopce);
		
		int totalCount = mchtBrandAptitudePicCount+mchtPlatformAuthPicCount+mchtBrandInvoicePicCount+mchtBrandInspectionPicCount+mchtBrandOtherPicCount;
		
		MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
		mchtBrandAptitudePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId).andArchiveStatusEqualTo("1");
		int mchtBrandAptitudePicArchiveCount = mchtBrandAptitudePicMapper.countByExample(mchtBrandAptitudePicExample);
		
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId).andArchiveStatusEqualTo("1");
		int mchtPlatformAuthPicArchiveCount = mchtPlatformAuthPicMapper.countByExample(mchtPlatformAuthPicExample);
		
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId).andArchiveStatusEqualTo("1");
		int mchtBrandInvoicePicArchiveCount = mchtBrandInvoicePicMapper.countByExample(mchtBrandInvoicePicExample);
		
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId).andArchiveStatusEqualTo("1");
		int mchtBrandInspectionPicArchiveCount = mchtBrandInspectionPicMapper.countByExample(mchtBrandInspectionPicExample);
		
		MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
		mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrandId).andArchiveStatusEqualTo("1");
		int mchtBrandOtherPicArchiveCount = mchtBrandOtherPicMapper.countByExample(mchtBrandOtherPicExample);
		
		int totalArchiveCount = mchtBrandAptitudePicArchiveCount+mchtPlatformAuthPicArchiveCount+mchtBrandInvoicePicArchiveCount+mchtBrandInspectionPicArchiveCount+mchtBrandOtherPicArchiveCount;
		MchtProductBrand mpb = new MchtProductBrand();
		if(totalCount == totalArchiveCount){
			mpb.setArchiveStatus("3");
		}else{
			mpb.setArchiveStatus("2");
		}
		mpb.setUpdateBy(staffID);
		mpb.setUpdateDate(new Date());
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		mpbe.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtProductBrandId);
		this.updateByExampleSelective(mpb, mpbe);
		return totalCount-totalArchiveCount;
	}

	public void picArchive(Integer mchtProductBrandId, Integer staffID, String picId, String picType, String archiveStatus) {
		if(!StringUtils.isEmpty(picType) && picType.equals("1")){
			MchtBrandAptitudePicExample mbapce = new MchtBrandAptitudePicExample();
			mbapce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
			mchtBrandAptitudePic.setUpdateBy(staffID);
			mchtBrandAptitudePic.setUpdateDate(new Date());
			mchtBrandAptitudePic.setArchiveBy(staffID);
			mchtBrandAptitudePic.setArchiveStatus(archiveStatus);
			mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic, mbapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("2")){
			MchtPlatformAuthPicExample mpapce = new MchtPlatformAuthPicExample();
			mpapce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setUpdateBy(staffID);
			mchtPlatformAuthPic.setUpdateDate(new Date());
			mchtPlatformAuthPic.setArchiveBy(staffID);
			mchtPlatformAuthPic.setArchiveStatus(archiveStatus);
			mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic, mpapce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("3")){
			MchtBrandInvoicePicExample mbipce = new MchtBrandInvoicePicExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtBrandInvoicePic mchtBrandInvoicePic = new MchtBrandInvoicePic();
			mchtBrandInvoicePic.setUpdateBy(staffID);
			mchtBrandInvoicePic.setUpdateDate(new Date());
			mchtBrandInvoicePic.setArchiveBy(staffID);
			mchtBrandInvoicePic.setArchiveStatus(archiveStatus);
			mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("4")){
			MchtBrandInspectionPicExample mbipce = new MchtBrandInspectionPicExample();
			mbipce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtBrandInspectionPic mchtBrandInspectionPic = new MchtBrandInspectionPic();
			mchtBrandInspectionPic.setUpdateBy(staffID);
			mchtBrandInspectionPic.setUpdateDate(new Date());
			mchtBrandInspectionPic.setArchiveBy(staffID);
			mchtBrandInspectionPic.setArchiveStatus(archiveStatus);
			mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic, mbipce);
		}
		if(!StringUtils.isEmpty(picType) && picType.equals("5")){
			MchtBrandOtherPicExample mbopce = new MchtBrandOtherPicExample();
			mbopce.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(picId));
			MchtBrandOtherPic mchtBrandOtherPic = new MchtBrandOtherPic();
			mchtBrandOtherPic.setUpdateBy(staffID);
			mchtBrandOtherPic.setUpdateDate(new Date());
			mchtBrandOtherPic.setArchiveBy(staffID);
			mchtBrandOtherPic.setArchiveStatus(archiveStatus);
			mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic,mbopce);
		}
		if(archiveStatus.equals("1")){//已归档
			//TODO 判断是不是全部已齐全了
			updateMchtProductBrandArchiveStatus(mchtProductBrandId, staffID);
		}else{//0.未齐
			MchtProductBrand mchtProductBrand = new MchtProductBrand();
			mchtProductBrand.setArchiveStatus("2");//2.未齐全
			mchtProductBrand.setUpdateBy(staffID);
			mchtProductBrand.setUpdateDate(new Date());
			MchtProductBrandExample mpbe = new MchtProductBrandExample();
			mpbe.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(mchtProductBrandId);
			this.updateByExampleSelective(mchtProductBrand, mpbe);
		}
	}

	public List<MchtProductBrandCustom> getMchtProductBrandCustomsByMchtIds(String mchtIds) {
		return mchtProductBrandCustomMapper.getMchtProductBrandCustomsByMchtIds(mchtIds);
	}
	
	public List<MchtProductBrandCustom> selectByExample2(MchtProductBrandExample example){
		return mchtProductBrandCustomMapper.selectByExample2(example);
	}
}
