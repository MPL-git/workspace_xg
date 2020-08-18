package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtBrandAptitudeMapper;
import com.jf.dao.MchtBrandAptitudePicMapper;
import com.jf.dao.MchtBrandChangeAgreementPicMapper;
import com.jf.dao.MchtBrandChgMapper;
import com.jf.dao.MchtBrandInspectionPicMapper;
import com.jf.dao.MchtBrandInvoicePicMapper;
import com.jf.dao.MchtBrandOtherPicMapper;
import com.jf.dao.MchtBrandRateChangeMapper;
import com.jf.dao.MchtPlatformAuthPicMapper;
import com.jf.dao.MchtProductBrandCustomMapper;
import com.jf.dao.MchtProductBrandMapper;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class MchtProductBrandService extends BaseService<MchtProductBrand, MchtProductBrandExample> {
	@Autowired
	private MchtProductBrandMapper mchtProductBrandMapper;

	@Autowired
	private MchtProductBrandCustomMapper mchtProductBrandCustomMapper;

	@Autowired
	private MchtBrandAptitudePicMapper mchtBrandAptitudePicMapper;

	@Autowired
	private MchtPlatformAuthPicMapper mchtPlatformAuthPicMapper;
	
	@Autowired
	private MchtBrandInvoicePicMapper mchtBrandInvoicePicMapper;
	
	@Autowired
	private MchtBrandInspectionPicMapper mchtBrandInspectionPicMapper;
	
	@Autowired
	private MchtBrandOtherPicMapper mchtBrandOtherPicMapper;
	
	@Autowired
	private MchtBrandAptitudeMapper mchtBrandAptitudeMapper;
	
	@Autowired
	private MchtBrandChangeAgreementPicMapper mchtBrandChangeAgreementPicMapper;
	
	@Autowired
	private MchtBrandProductTypeService mchtBrandProductTypeService;
	
	@Autowired
	private MchtBrandAptitudeService mchtBrandAptitudeService;
	
	@Autowired
	private MchtBrandRateChangeMapper mchtBrandRateChangeMapper;
	
	@Autowired
	private MchtBrandChgMapper mchtBrandChgMapper;

	@Autowired
	public void setMchtProductBrandMapper(MchtProductBrandMapper mchtProductBrandMapper) {
		super.setDao(mchtProductBrandMapper);
		this.mchtProductBrandMapper = mchtProductBrandMapper;
	}

	public List<MchtProductBrandCustom> selectMchtProductBrandCustomByExample(MchtProductBrandExample example) {
		return mchtProductBrandCustomMapper.selectByExample(example);
	}

	public MchtProductBrandCustom selectMchtProductBrandCustomByPrimaryKey(Integer id) {
		return mchtProductBrandCustomMapper.selectByPrimaryKey(id);
	}
	
	public List<MchtProductBrandCustom> getMchtUsebleProductBrand(Integer mchtId){
		return mchtProductBrandCustomMapper.getMchtUsebleProductBrand(mchtId);
	}
	/**
	 * 修改品牌表物流状态且copy到更新表
	 * @param mchtId
	 * @return
	 */
	public void sendAndCopy(MchtProductBrand mchtProductBrand,MchtBrandChg mchtBrandChg){
		this.updateByPrimaryKeySelective(mchtProductBrand);
		mchtBrandChgMapper.updateByPrimaryKeySelective(mchtBrandChg);
	}

	/**
	 * 获取商家品牌列表
	 * 
	 * @param mchtId
	 * @return
	 */
	public List<ProductBrand> getMchtProductBrandList(Integer mchtId) {
		return mchtProductBrandCustomMapper.getMchtProductBrandList(mchtId);
	}

	public void perfectCommitSave(MchtProductBrand mchtProductBrand, String aptitudePics, String platformAuthPics) throws ArgException {
		MchtProductBrand oldMchtProductBrand = mchtProductBrandMapper.selectByPrimaryKey(mchtProductBrand.getId());
		if (!oldMchtProductBrand.getAuditStatus().equals("0") && !oldMchtProductBrand.getAuditStatus().equals("1") && !oldMchtProductBrand.getAuditStatus().equals("4")) {
			throw new ArgException("信息不可修改");
		}
		mchtProductBrandMapper.updateByPrimaryKeySelective(mchtProductBrand);

		// 更新品牌资质图
		MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
		mchtBrandAptitudePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		MchtBrandAptitudePic chtBrandAptitudePic4Update = new MchtBrandAptitudePic();
		chtBrandAptitudePic4Update.setDelFlag("1");
		mchtBrandAptitudePicMapper.updateByExampleSelective(chtBrandAptitudePic4Update, mchtBrandAptitudePicExample);
		
		JSONArray aptitudePicArray = JSONArray.fromObject(aptitudePics);
		Iterator<JSONObject> it = aptitudePicArray.iterator();
		while (it.hasNext()) {
			JSONObject aptitudePicObject = it.next();

			mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
			mchtBrandAptitudePicExample.createCriteria().andPicEqualTo(aptitudePicObject.getString("pic")).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			chtBrandAptitudePic4Update = new MchtBrandAptitudePic();
			chtBrandAptitudePic4Update.setDelFlag("0");
			int updateCount = mchtBrandAptitudePicMapper.updateByExampleSelective(chtBrandAptitudePic4Update, mchtBrandAptitudePicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
			mchtBrandAptitudePic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtBrandAptitudePic.setPic(aptitudePicObject.getString("pic"));
			mchtBrandAptitudePic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtBrandAptitudePic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtBrandAptitudePic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtBrandAptitudePic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtBrandAptitudePicMapper.insertSelective(mchtBrandAptitudePic);
		}
		
		// 更新销售授权图
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		MchtPlatformAuthPic mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
		mchtPlatformAuthPic4Update.setDelFlag("1");
		mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);

		JSONArray platformAuthPicArray = JSONArray.fromObject(platformAuthPics);
		Iterator<JSONObject> platformAuthPicIt = platformAuthPicArray.iterator();
		while (platformAuthPicIt.hasNext()) {
			JSONObject mchtBlPicObject = platformAuthPicIt.next();

			mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andPicEqualTo(mchtBlPicObject.getString("pic")).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
			mchtPlatformAuthPic4Update.setDelFlag("0");
			int updateCount = mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtPlatformAuthPic.setPic(mchtBlPicObject.getString("pic"));
			mchtPlatformAuthPic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPicMapper.insertSelective(mchtPlatformAuthPic);
		}
		
	}

	public void perfectCommitAudit(MchtProductBrand mchtProductBrand,String mchtBrandAptitudeJsonStr,String mchtBrandProductTypeJsonStr,String platformAuthPics,String invoicePics,String inspectionPics,String otherPics,String mchtBrandChangeAgreementPics) throws Exception {
		
		if(mchtProductBrand.getId()==null){
			mchtProductBrand.setStatus("0");
			mchtProductBrand.setStatusDate(new Date());
			mchtProductBrand.setStatusBy(mchtProductBrand.getCreateBy());
			if(mchtProductBrand.getBrandSource().equals("2")){//2.追加品牌
				mchtProductBrand.setZsAuditStatus("1");
				mchtProductBrand.setZsCommitAuditDate(new Date());
			}else if(mchtProductBrand.getBrandSource().equals("1")){//1.入驻品牌
				mchtProductBrand.setZsAuditStatus("0");
			}
			mchtProductBrand.setArchiveStatus("0");
			mchtProductBrandMapper.insertSelective(mchtProductBrand);
		}else{
			MchtProductBrand oldMchtProductBrand = mchtProductBrandMapper.selectByPrimaryKey(mchtProductBrand.getId());
			if(!StringUtil.isEmpty(oldMchtProductBrand.getZsAuditStatus()) && oldMchtProductBrand.getZsAuditStatus().equals("1")){
				throw new ArgException("您的信息已在审核中，不能重新提交！");
			}
			if(!StringUtil.isEmpty(oldMchtProductBrand.getZsAuditStatus()) && (oldMchtProductBrand.getZsAuditStatus().equals("5") || oldMchtProductBrand.getZsAuditStatus().equals("6"))){
				throw new ArgException("您的品牌信息异常，无法提交。");
			}
			if (!StringUtil.isEmpty(oldMchtProductBrand.getAuditStatus()) && oldMchtProductBrand.getAuditStatus().equals("1")) {
				throw new ArgException("您的信息已在审核中，不能重新提交！");
			}
			if (!StringUtil.isEmpty(oldMchtProductBrand.getAuditStatus()) && oldMchtProductBrand.getAuditStatus().equals("3")) {
				throw new ArgException("您的信息审核通过，无需重新提交。");
			}
			if (!StringUtil.isEmpty(oldMchtProductBrand.getAuditStatus()) && (oldMchtProductBrand.getAuditStatus().equals("5") || oldMchtProductBrand.getAuditStatus().equals("6"))) {
				throw new ArgException("您的品牌信息异常，无法提交。");
			}
			if(!StringUtil.isEmpty(oldMchtProductBrand.getZsAuditStatus()) && oldMchtProductBrand.getZsAuditStatus().equals("4")){//招商审核没通过时即驳回
				mchtProductBrand.setZsAuditStatus("1");
				mchtProductBrand.setZsCommitAuditDate(new Date());
				mchtProductBrand.setIsZsAuditRecommit("1");
			}else if(!StringUtil.isEmpty(oldMchtProductBrand.getZsAuditStatus()) && oldMchtProductBrand.getZsAuditStatus().equals("2") && !StringUtil.isEmpty(oldMchtProductBrand.getAuditStatus()) && oldMchtProductBrand.getAuditStatus().equals("4")){//招商审核通过，法务审核没通过
				mchtProductBrand.setAuditStatus("1");
				mchtProductBrand.setCommitAuditDate(new Date());
				mchtProductBrand.setIsAuditRecommit("1");
			}
			mchtProductBrand.setPopCommissionRate(null);
			mchtProductBrandMapper.updateByPrimaryKeySelective(mchtProductBrand);
		}

		// 更新销售授权图
		MchtPlatformAuthPicExample mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
		mchtPlatformAuthPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		MchtPlatformAuthPic mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
		mchtPlatformAuthPic4Update.setDelFlag("1");
		mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);

		JSONArray platformAuthPicArray = JSONArray.fromObject(platformAuthPics);
		Iterator<JSONObject> platformAuthPicIt = platformAuthPicArray.iterator();
		while (platformAuthPicIt.hasNext()) {
			JSONObject platformAuthPicObject = platformAuthPicIt.next();

			mchtPlatformAuthPicExample = new MchtPlatformAuthPicExample();
			mchtPlatformAuthPicExample.createCriteria().andPicEqualTo(platformAuthPicObject.getString("pic")).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			mchtPlatformAuthPic4Update = new MchtPlatformAuthPic();
			mchtPlatformAuthPic4Update.setDelFlag("0");
			int updateCount = mchtPlatformAuthPicMapper.updateByExampleSelective(mchtPlatformAuthPic4Update, mchtPlatformAuthPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtPlatformAuthPic mchtPlatformAuthPic = new MchtPlatformAuthPic();
			mchtPlatformAuthPic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtPlatformAuthPic.setPic(platformAuthPicObject.getString("pic"));
			mchtPlatformAuthPic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPicMapper.insertSelective(mchtPlatformAuthPic);
		}
		
		// 更新进货发票图
		MchtBrandInvoicePicExample mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
		mchtBrandInvoicePicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		MchtBrandInvoicePic mchtBrandInvoicePic4Update = new MchtBrandInvoicePic();
		mchtBrandInvoicePic4Update.setDelFlag("1");
		mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);

		JSONArray invoicePicsArray = JSONArray.fromObject(invoicePics);
		Iterator<JSONObject> invoicePicsIt = invoicePicsArray.iterator();
		while (invoicePicsIt.hasNext()) {
			JSONObject invoicePicObject = invoicePicsIt.next();

			mchtBrandInvoicePicExample = new MchtBrandInvoicePicExample();
			mchtBrandInvoicePicExample.createCriteria().andPicEqualTo(invoicePicObject.getString("pic")).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			mchtBrandInvoicePic4Update = new MchtBrandInvoicePic();
			mchtBrandInvoicePic4Update.setDelFlag("0");
			int updateCount = mchtBrandInvoicePicMapper.updateByExampleSelective(mchtBrandInvoicePic4Update, mchtBrandInvoicePicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandInvoicePic mchtBrandInvoicePic = new MchtBrandInvoicePic();
			mchtBrandInvoicePic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtBrandInvoicePic.setPic(invoicePicObject.getString("pic"));
			mchtBrandInvoicePic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtBrandInvoicePic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtBrandInvoicePic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtBrandInvoicePic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtBrandInvoicePicMapper.insertSelective(mchtBrandInvoicePic);
		}
		
		
		// 更新质检报告图
		MchtBrandInspectionPicExample mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
		mchtBrandInspectionPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		MchtBrandInspectionPic mchtBrandInspectionPic4Update = new MchtBrandInspectionPic();
		mchtBrandInspectionPic4Update.setDelFlag("1");
		mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);

		JSONArray inspectionPicsArray = JSONArray.fromObject(inspectionPics);
		Iterator<JSONObject> inspectionPicsIt = inspectionPicsArray.iterator();
		while (inspectionPicsIt.hasNext()) {
			JSONObject inspectionPicObject = inspectionPicsIt.next();

			mchtBrandInspectionPicExample = new MchtBrandInspectionPicExample();
			mchtBrandInspectionPicExample.createCriteria().andPicEqualTo(inspectionPicObject.getString("pic")).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			mchtBrandInspectionPic4Update = new MchtBrandInspectionPic();
			mchtBrandInspectionPic4Update.setDelFlag("0");
			int updateCount = mchtBrandInspectionPicMapper.updateByExampleSelective(mchtBrandInspectionPic4Update, mchtBrandInspectionPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandInspectionPic mchtBrandInspectionPic = new MchtBrandInspectionPic();
			mchtBrandInspectionPic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtBrandInspectionPic.setPic(inspectionPicObject.getString("pic"));
			mchtBrandInspectionPic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtBrandInspectionPic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtBrandInspectionPic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtBrandInspectionPic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtBrandInspectionPicMapper.insertSelective(mchtBrandInspectionPic);
		}
		
		// 更新其他资质图
		MchtBrandOtherPicExample mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
		mchtBrandOtherPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		MchtBrandOtherPic mchtBrandOtherPic4Update = new MchtBrandOtherPic();
		mchtBrandOtherPic4Update.setDelFlag("1");
		mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);

		JSONArray ohterPicsArray = JSONArray.fromObject(otherPics);
		Iterator<JSONObject> otherPicsIt = ohterPicsArray.iterator();
		while (otherPicsIt.hasNext()) {
			JSONObject otherPicObject = otherPicsIt.next();

			mchtBrandOtherPicExample = new MchtBrandOtherPicExample();
			mchtBrandOtherPicExample.createCriteria().andPicEqualTo(otherPicObject.getString("pic")).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			mchtBrandOtherPic4Update = new MchtBrandOtherPic();
			mchtBrandOtherPic4Update.setDelFlag("0");
			int updateCount = mchtBrandOtherPicMapper.updateByExampleSelective(mchtBrandOtherPic4Update, mchtBrandOtherPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandOtherPic mchtBrandOtherPic = new MchtBrandOtherPic();
			mchtBrandOtherPic.setMchtProductBrandId(mchtProductBrand.getId());
			mchtBrandOtherPic.setPic(otherPicObject.getString("pic"));
			mchtBrandOtherPic.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtBrandOtherPic.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtBrandOtherPic.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtBrandOtherPic.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtBrandOtherPicMapper.insertSelective(mchtBrandOtherPic);
		}
		
		// 更新合作协议变更申请函图
		if(!StringUtil.isEmpty(mchtBrandChangeAgreementPics)){
			MchtBrandChangeAgreementPicExample mchtBrandChangeAgreementPicExample = new MchtBrandChangeAgreementPicExample();
			mchtBrandChangeAgreementPicExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			MchtBrandChangeAgreementPic mchtBrandChangeAgreementPic4Update = new MchtBrandChangeAgreementPic();
			mchtBrandChangeAgreementPic4Update.setDelFlag("1");
			mchtBrandChangeAgreementPicMapper.updateByExampleSelective(mchtBrandChangeAgreementPic4Update, mchtBrandChangeAgreementPicExample);
			
			JSONArray mchtBrandChangeAgreementPicsArray = JSONArray.fromObject(mchtBrandChangeAgreementPics);
			Iterator<JSONObject> mchtBrandChangeAgreementPicsIt = mchtBrandChangeAgreementPicsArray.iterator();
			while (mchtBrandChangeAgreementPicsIt.hasNext()) {
				JSONObject mchtBrandChangeAgreementPicObject = mchtBrandChangeAgreementPicsIt.next();
				
				mchtBrandChangeAgreementPicExample = new MchtBrandChangeAgreementPicExample();
				mchtBrandChangeAgreementPicExample.createCriteria().andPicEqualTo(mchtBrandChangeAgreementPicObject.getString("pic")).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
				mchtBrandChangeAgreementPic4Update = new MchtBrandChangeAgreementPic();
				mchtBrandChangeAgreementPic4Update.setDelFlag("0");
				int updateCount = mchtBrandChangeAgreementPicMapper.updateByExampleSelective(mchtBrandChangeAgreementPic4Update, mchtBrandChangeAgreementPicExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandChangeAgreementPic mchtBrandChangeAgreementPic = new MchtBrandChangeAgreementPic();
				mchtBrandChangeAgreementPic.setMchtProductBrandId(mchtProductBrand.getId());
				mchtBrandChangeAgreementPic.setPic(mchtBrandChangeAgreementPicObject.getString("pic"));
				mchtBrandChangeAgreementPic.setArchiveStatus("0");
				mchtBrandChangeAgreementPic.setCreateBy(mchtProductBrand.getUpdateBy());
				mchtBrandChangeAgreementPic.setCreateDate(mchtProductBrand.getUpdateDate());
				mchtBrandChangeAgreementPic.setUpdateBy(mchtProductBrand.getUpdateBy());
				mchtBrandChangeAgreementPic.setUpdateDate(mchtProductBrand.getUpdateDate());
				mchtBrandChangeAgreementPic.setDelFlag("0");
				mchtBrandChangeAgreementPicMapper.insertSelective(mchtBrandChangeAgreementPic);
			}
		}
		
		//TODO 商家品牌注册证,商家品牌资质证明图表
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtil.isEmpty(mchtBrandAptitudeJsonStr)){
			List<Integer> delMchtBrandAptitudeIds = mchtBrandAptitudeService.getIdsByMchtProductBrandId(mchtProductBrand.getId());
			JSONArray mchtBrandAptitudeArray = JSONArray.fromObject(mchtBrandAptitudeJsonStr);
			for(int i=0;i<mchtBrandAptitudeArray.size();i++){
				JSONObject mchtBrandAptitude = (JSONObject)mchtBrandAptitudeArray.get(i);
				String eachMchtBrandAptitudeId = mchtBrandAptitude.getString("eachMchtBrandAptitudeId");
				String eachCertificateNo = mchtBrandAptitude.getString("eachCertificateNo");
				String eachAptitudeExpDate = mchtBrandAptitude.getString("eachAptitudeExpDate");
				JSONArray eachAptitudePics = mchtBrandAptitude.getJSONArray("eachAptitudePics");
				Iterator<JSONObject> eachAptitudePicIt = eachAptitudePics.iterator();
				if(!StringUtil.isEmpty(eachMchtBrandAptitudeId) && !eachMchtBrandAptitudeId.equals("0")){//不是新增,更新图片
					if(delMchtBrandAptitudeIds!=null && delMchtBrandAptitudeIds.size()>0){
						if(delMchtBrandAptitudeIds.contains(Integer.parseInt(eachMchtBrandAptitudeId))){
							Integer id = Integer.parseInt(eachMchtBrandAptitudeId);
							delMchtBrandAptitudeIds.remove(id);
						}
					}
					MchtBrandAptitude mba = new MchtBrandAptitude();
					mba.setCertificateNo(eachCertificateNo);
					mba.setAptitudeExpDate(sdf.parse(eachAptitudeExpDate));
					mba.setUpdateBy(mchtProductBrand.getUpdateBy());
					mba.setUpdateDate(mchtProductBrand.getUpdateDate());
					MchtBrandAptitudeExample mchtBrandAptitudeExample = new MchtBrandAptitudeExample();
					mchtBrandAptitudeExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(eachMchtBrandAptitudeId));
					mchtBrandAptitudeMapper.updateByExampleSelective(mba, mchtBrandAptitudeExample);
					
					MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
					mchtBrandAptitudePicExample.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeIdEqualTo(Integer.parseInt(eachMchtBrandAptitudeId)).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
					MchtBrandAptitudePic mchtBrandAptitudePic4Update = new MchtBrandAptitudePic();
					mchtBrandAptitudePic4Update.setDelFlag("1");
					mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic4Update, mchtBrandAptitudePicExample);
					
					while (eachAptitudePicIt.hasNext()) {
						JSONObject aptitudePicObject = eachAptitudePicIt.next();
						MchtBrandAptitudePicExample mbape = new MchtBrandAptitudePicExample();
						mbape.createCriteria().andPicEqualTo(aptitudePicObject.getString("pic")).andMchtBrandAptitudeIdEqualTo(Integer.parseInt(eachMchtBrandAptitudeId)).andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
						mchtBrandAptitudePic4Update = new MchtBrandAptitudePic();
						mchtBrandAptitudePic4Update.setDelFlag("0");
						int updateCount = mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic4Update, mbape);
						if (updateCount > 0) {
							continue;
						}
						MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
						mchtBrandAptitudePic.setMchtBrandAptitudeId(Integer.parseInt(eachMchtBrandAptitudeId));
						mchtBrandAptitudePic.setPic(aptitudePicObject.getString("pic"));
						mchtBrandAptitudePic.setCreateBy(mchtProductBrand.getUpdateBy());
						mchtBrandAptitudePic.setCreateDate(mchtProductBrand.getUpdateDate());
						mchtBrandAptitudePic.setUpdateBy(mchtProductBrand.getUpdateBy());
						mchtBrandAptitudePic.setUpdateDate(mchtProductBrand.getUpdateDate());
						mchtBrandAptitudePic.setDelFlag("0");
						mchtBrandAptitudePicMapper.insertSelective(mchtBrandAptitudePic);
					}
				}else{//新增,插入数据，图片
					MchtBrandAptitude mba = new MchtBrandAptitude();
					mba.setMchtProductBrandId(mchtProductBrand.getId());
					mba.setCertificateNo(eachCertificateNo);
					mba.setAptitudeExpDate(sdf.parse(eachAptitudeExpDate));
					mba.setCreateBy(mchtProductBrand.getUpdateBy());
					mba.setCreateDate(mchtProductBrand.getUpdateDate());
					mba.setDelFlag("0");
					mchtBrandAptitudeMapper.insertSelective(mba);
					
					while (eachAptitudePicIt.hasNext()) {
						JSONObject aptitudePicObject = eachAptitudePicIt.next();
						MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
						mchtBrandAptitudePic.setMchtProductBrandId(mchtProductBrand.getId());
						mchtBrandAptitudePic.setMchtBrandAptitudeId(mba.getId());
						mchtBrandAptitudePic.setPic(aptitudePicObject.getString("pic"));
						mchtBrandAptitudePic.setCreateBy(mchtProductBrand.getUpdateBy());
						mchtBrandAptitudePic.setCreateDate(mchtProductBrand.getUpdateDate());
						mchtBrandAptitudePic.setUpdateBy(mchtProductBrand.getUpdateBy());
						mchtBrandAptitudePic.setUpdateDate(mchtProductBrand.getUpdateDate());
						mchtBrandAptitudePic.setDelFlag("0");
						mchtBrandAptitudePicMapper.insertSelective(mchtBrandAptitudePic);
					}
				}
			}
			if(delMchtBrandAptitudeIds!=null && delMchtBrandAptitudeIds.size()>0){
				MchtBrandAptitude mba = new MchtBrandAptitude();
				mba.setDelFlag("1");
				mba.setUpdateDate(new Date());
				MchtBrandAptitudeExample mbae = new MchtBrandAptitudeExample();
				MchtBrandAptitudeExample.Criteria mbaec = mbae.createCriteria();
				mbaec.andDelFlagEqualTo("0");
				mbaec.andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
				mbaec.andIdIn(delMchtBrandAptitudeIds);
				mchtBrandAptitudeService.updateByExampleSelective(mba, mbae);
				MchtBrandAptitudePicExample mchtBrandAptitudePicExample = new MchtBrandAptitudePicExample();
				mchtBrandAptitudePicExample.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeIdIn(delMchtBrandAptitudeIds);
				MchtBrandAptitudePic mchtBrandAptitudePic = new MchtBrandAptitudePic();
				mchtBrandAptitudePic.setDelFlag("1");
				mchtBrandAptitudePic.setUpdateDate(new Date());
				mchtBrandAptitudePicMapper.updateByExampleSelective(mchtBrandAptitudePic, mchtBrandAptitudePicExample);
			}
		}

		//TODO 商家品牌经营类目
		if(!StringUtil.isEmpty(mchtBrandProductTypeJsonStr)){
			MchtBrandProductType mchtBrandProductType = new MchtBrandProductType();
			mchtBrandProductType.setDelFlag("1");
			MchtBrandProductTypeExample mchtBrandProductTypeExample = new MchtBrandProductTypeExample();
			mchtBrandProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
			mchtBrandProductTypeService.updateByExampleSelective(mchtBrandProductType, mchtBrandProductTypeExample);
			JSONArray mchtBrandAptitudeArray = JSONArray.fromObject(mchtBrandProductTypeJsonStr);
			List<MchtBrandProductType> mchtBrandProductTypeList = new ArrayList<MchtBrandProductType>();
			for(int i=0;i<mchtBrandAptitudeArray.size();i++){
				JSONObject mchtBrandAptitudeJo = (JSONObject)mchtBrandAptitudeArray.get(i);
				String productTypeId = mchtBrandAptitudeJo.getString("productTypeId");
				int tLevel = mchtBrandAptitudeJo.getInt("tLevel");
				if(tLevel == 3){
					String[] productTypeIdArray = productTypeId.split(",");
					for(String ptId:productTypeIdArray){
						MchtBrandProductType mbpt = new MchtBrandProductType();
						mbpt.setMchtProductBrandId(mchtProductBrand.getId());
						mbpt.setProductTypeId(Integer.parseInt(ptId));
						mbpt.settLevel(tLevel);
						mbpt.setCreateBy(mchtProductBrand.getUpdateBy());
						mbpt.setCreateDate(mchtProductBrand.getUpdateDate());
						mbpt.setDelFlag("0");
						mchtBrandProductTypeList.add(mbpt);
					}
				}else{
					MchtBrandProductType mbpt = new MchtBrandProductType();
					mbpt.setMchtProductBrandId(mchtProductBrand.getId());
					mbpt.setProductTypeId(Integer.parseInt(productTypeId));
					mbpt.settLevel(tLevel);
					mbpt.setCreateBy(mchtProductBrand.getUpdateBy());
					mbpt.setCreateDate(mchtProductBrand.getUpdateDate());
					mbpt.setDelFlag("0");
					mchtBrandProductTypeList.add(mbpt);
				}
			}
			mchtBrandProductTypeService.batchInsert(mchtBrandProductTypeList);
		}		
				
	}






	public List<MchtProductBrand> listByMchtId(int mchtId) {
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
		return list(queryObject);
	}

	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtProductBrand> list(QueryObject queryObject) {
		return dao.selectByExample(builderQuery(queryObject));
	}

	public Page<MchtProductBrand> paginate(QueryObject queryObject) {
		MchtProductBrandExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtProductBrand> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
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

	/**
	 * 获取商家特卖品牌列表
	 *
	 * @param mchtId
	 * @return
	 */
	public List<ProductBrand> getActivityMchtProductBrandList(Integer mchtId) {
		return mchtProductBrandCustomMapper.getActivityMchtProductBrandList(mchtId);
	}

	public void delete(MchtProductBrand mchtProductBrand) {
		this.updateByPrimaryKeySelective(mchtProductBrand);
		MchtBrandRateChangeExample e = new MchtBrandRateChangeExample();
		e.createCriteria().andDelFlagEqualTo("0").andMchtProductBrandIdEqualTo(mchtProductBrand.getId());
		MchtBrandRateChange mchtBrandRateChange = new MchtBrandRateChange();
		mchtBrandRateChange.setUpdateDate(new Date());
		mchtBrandRateChange.setDelFlag("1");
		mchtBrandRateChangeMapper.updateByExampleSelective(mchtBrandRateChange,e);
	}

	/**
	 * @MethodName selectByExampleCustom
	 * @Description TODO
	 * @author chengh
	 * @date 2019年7月25日 下午4:28:24
	 */
	public List<MchtProductBrandCustom> selectByExampleCustom(
			MchtProductBrandCustomExample mchtProductBrandCustomExample) {
		// TODO Auto-generated method stub
		return mchtProductBrandCustomMapper.selectByExampleCustom(mchtProductBrandCustomExample);
	}

}
