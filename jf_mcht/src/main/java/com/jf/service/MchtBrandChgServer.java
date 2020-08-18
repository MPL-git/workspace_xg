package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtBrandAptitudeChgMapper;
import com.jf.dao.MchtBrandAptitudePicChgMapper;
import com.jf.dao.MchtBrandChgCustomMapper;
import com.jf.dao.MchtBrandChgMapper;
import com.jf.dao.MchtBrandInspectionPicChgMapper;
import com.jf.dao.MchtBrandInvoicePicChgMapper;
import com.jf.dao.MchtBrandOtherPicChgMapper;
import com.jf.dao.MchtPlatformAuthPicChgMapper;
import com.jf.dao.MchtProductBrandMapper;
import com.jf.entity.MchtBrandAptitudeChg;
import com.jf.entity.MchtBrandAptitudeChgExample;
import com.jf.entity.MchtBrandAptitudePicChg;
import com.jf.entity.MchtBrandAptitudePicChgExample;
import com.jf.entity.MchtBrandChg;
import com.jf.entity.MchtBrandChgCustom;
import com.jf.entity.MchtBrandChgExample;
import com.jf.entity.MchtBrandChgProductType;
import com.jf.entity.MchtBrandChgProductTypeExample;
import com.jf.entity.MchtBrandInspectionPicChg;
import com.jf.entity.MchtBrandInspectionPicChgExample;
import com.jf.entity.MchtBrandInvoicePicChg;
import com.jf.entity.MchtBrandInvoicePicChgExample;
import com.jf.entity.MchtBrandOtherPicChg;
import com.jf.entity.MchtBrandOtherPicChgExample;
import com.jf.entity.MchtPlatformAuthPicChg;
import com.jf.entity.MchtPlatformAuthPicChgExample;
import com.jf.entity.MchtProductBrand;
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
public class MchtBrandChgServer extends BaseService<MchtBrandChg, MchtBrandChgExample> {
	
	@Autowired
	private MchtBrandChgMapper mchtBrandChgMapper;
	
	@Autowired
	private MchtProductBrandMapper mchtProductBrandMapper;
	
	@Autowired
	private MchtBrandAptitudePicChgMapper mchtBrandAptitudePicChgMapper;
	

	@Autowired
	private MchtPlatformAuthPicChgMapper mchtPlatformAuthPicChgMapper;
	
	
	@Autowired
	private MchtBrandInvoicePicChgMapper mchtBrandInvoicePicChgMapper;
	
	@Autowired
	private MchtBrandInspectionPicChgMapper mchtBrandInspectionPicChgMapper;
	
	@Autowired
	private MchtBrandOtherPicChgMapper mchtBrandOtherPicChgMapper;
	
	@Autowired
	private MchtBrandChgCustomMapper mchtBrandChgCustomMapper;
	
	@Autowired
	private MchtBrandAptitudeChgMapper mchtBrandAptitudeChgMapper;
	
	@Autowired
	private MchtBrandAptitudeChgService mchtBrandAptitudeChgService;
	
	@Autowired
	private MchtBrandChgProductTypeService mchtBrandChgProductTypeService;
	
	
	@Autowired
	public void setMchtProductBrandMapper(MchtBrandChgMapper mchtBrandChgMapper) {
		super.setDao(mchtBrandChgMapper);
		this.mchtBrandChgMapper = mchtBrandChgMapper;
	}
	
    public List<MchtBrandChgCustom> selectMchtBrandChgCustomByExample(MchtBrandChgExample example){
    	return mchtBrandChgCustomMapper.selectByExample(example);
    }
	
	
	public void mchtBrandChgCommitSave(MchtBrandChg mchtBrandChg,String aptitudePicChgs,String platformAuthPicChgs) throws ArgException{
		
		MchtProductBrand mchtProductBrand=mchtProductBrandMapper.selectByPrimaryKey(mchtBrandChg.getMchtProductBrandId());
		if(mchtProductBrand==null){
			throw new ArgException("品牌信息不存在！");
		}
		if(!mchtProductBrand.getAuditStatus().equals("3")){
			throw new ArgException("品牌信息未完善，请先完善！");
		}
		
		if(mchtBrandChg.getId()!=null){
			MchtBrandChg oldMchtBrandChg=mchtBrandChgMapper.selectByPrimaryKey(mchtBrandChg.getId());
			if(!oldMchtBrandChg.getAuditStatus().equals("0")&&!oldMchtBrandChg.getAuditStatus().equals("4")){
				throw new ArgException("您的信息在审核或已审核通过，暂不可修改！");
			}
			mchtBrandChgMapper.updateByPrimaryKeySelective(mchtBrandChg);
		}else{
			mchtBrandChg.setAuditStatus("0");
			mchtBrandChgMapper.insertSelective(mchtBrandChg);
		}
		
		
		// 更新品牌资质图
		MchtBrandAptitudePicChgExample mchtBrandAptitudePicChgExample = new MchtBrandAptitudePicChgExample();
		mchtBrandAptitudePicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		MchtBrandAptitudePicChg chtBrandAptitudePicChg4Update = new MchtBrandAptitudePicChg();
		chtBrandAptitudePicChg4Update.setDelFlag("1");
		mchtBrandAptitudePicChgMapper.updateByExampleSelective(chtBrandAptitudePicChg4Update, mchtBrandAptitudePicChgExample);

		JSONArray aptitudePicArray = JSONArray.fromObject(aptitudePicChgs);
		Iterator<JSONObject> it = aptitudePicArray.iterator();
		while (it.hasNext()) {
			JSONObject aptitudePicObject = it.next();

			mchtBrandAptitudePicChgExample = new MchtBrandAptitudePicChgExample();
			mchtBrandAptitudePicChgExample.createCriteria().andPicEqualTo(aptitudePicObject.getString("pic")).andBrandChgIdEqualTo(mchtBrandChg.getId());
			chtBrandAptitudePicChg4Update = new MchtBrandAptitudePicChg();
			chtBrandAptitudePicChg4Update.setDelFlag("0");
			int updateCount = mchtBrandAptitudePicChgMapper.updateByExampleSelective(chtBrandAptitudePicChg4Update, mchtBrandAptitudePicChgExample);
			if (updateCount > 0) {
				continue;
			}
			MchtBrandAptitudePicChg mchtBrandAptitudePicChg = new MchtBrandAptitudePicChg();
			mchtBrandAptitudePicChg.setBrandChgId(mchtBrandChg.getId());
			mchtBrandAptitudePicChg.setPic(aptitudePicObject.getString("pic"));
			mchtBrandAptitudePicChg.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtBrandAptitudePicChg.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtBrandAptitudePicChg.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtBrandAptitudePicChg.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtBrandAptitudePicChgMapper.insertSelective(mchtBrandAptitudePicChg);
		}

		// 更新销售授权图
		MchtPlatformAuthPicChgExample mchtPlatformAuthPicChgExample = new MchtPlatformAuthPicChgExample();
		mchtPlatformAuthPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		MchtPlatformAuthPicChg mchtPlatformAuthPicChg4Update = new MchtPlatformAuthPicChg();
		mchtPlatformAuthPicChg4Update.setDelFlag("1");
		mchtPlatformAuthPicChgMapper.updateByExampleSelective(mchtPlatformAuthPicChg4Update, mchtPlatformAuthPicChgExample);

		JSONArray platformAuthPicArray = JSONArray.fromObject(platformAuthPicChgs);
		Iterator<JSONObject> platformAuthPicIt = platformAuthPicArray.iterator();
		while (platformAuthPicIt.hasNext()) {
			JSONObject mchtPlatformAuthPicChgObject = platformAuthPicIt.next();

			mchtPlatformAuthPicChgExample = new MchtPlatformAuthPicChgExample();
			mchtPlatformAuthPicChgExample.createCriteria().andPicEqualTo(mchtPlatformAuthPicChgObject.getString("pic")).andBrandChgIdEqualTo(mchtBrandChg.getId());
			mchtPlatformAuthPicChg4Update = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg4Update.setDelFlag("0");
			int updateCount = mchtPlatformAuthPicChgMapper.updateByExampleSelective(mchtPlatformAuthPicChg4Update, mchtPlatformAuthPicChgExample);
			if (updateCount > 0) {
				continue;
			}
			MchtPlatformAuthPicChg mchtPlatformAuthPicChg = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg.setBrandChgId(mchtBrandChg.getId());
			mchtPlatformAuthPicChg.setPic(mchtPlatformAuthPicChgObject.getString("pic"));
			mchtPlatformAuthPicChg.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPicChg.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPicChg.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPicChg.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPicChgMapper.insertSelective(mchtPlatformAuthPicChg);
		}
		
		
		
		
	}
	
	public void mchtBrandChgCommitAudit(MchtBrandChg mchtBrandChg,String mchtBrandAptitudeJsonStr,String mchtBrandProductTypeJsonStr,String platformAuthPicChgs,String invoicePics,String inspectionPics,String otherPics,String mchtBrandChangeAgreementPics) throws Exception{
		
		MchtProductBrand mchtProductBrand=mchtProductBrandMapper.selectByPrimaryKey(mchtBrandChg.getMchtProductBrandId());
		mchtBrandChg.setMchtProductBrandId(mchtProductBrand.getId());
		if(mchtProductBrand==null){
			throw new ArgException("品牌信息不存在！");
		}
		if(!mchtProductBrand.getAuditStatus().equals("3")){
			throw new ArgException("品牌信息未完善，请先完善！");
		}
		
		if(mchtBrandChg.getId()!=null){
			MchtBrandChg oldMchtBrandChg=mchtBrandChgMapper.selectByPrimaryKey(mchtBrandChg.getId());
			if(!oldMchtBrandChg.getAuditStatus().equals("0")&&!oldMchtBrandChg.getAuditStatus().equals("4")){
				throw new ArgException("您的信息在审核中或已审核通过，暂不可修改！");
			}
			mchtBrandChg.setAuditStatus("1");//置为待审状态
			mchtBrandChgMapper.updateByPrimaryKeySelective(mchtBrandChg);
		}else{
			mchtBrandChg.setAuditStatus("1");
			mchtBrandChgMapper.insertSelective(mchtBrandChg);
		}
		
		// 更新销售授权图
		MchtPlatformAuthPicChgExample mchtPlatformAuthPicChgExample = new MchtPlatformAuthPicChgExample();
		mchtPlatformAuthPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
		MchtPlatformAuthPicChg mchtPlatformAuthPicChg4Update = new MchtPlatformAuthPicChg();
		mchtPlatformAuthPicChg4Update.setDelFlag("1");
		mchtPlatformAuthPicChgMapper.updateByExampleSelective(mchtPlatformAuthPicChg4Update, mchtPlatformAuthPicChgExample);

		JSONArray platformAuthPicArray = JSONArray.fromObject(platformAuthPicChgs);
		Iterator<JSONObject> platformAuthPicIt = platformAuthPicArray.iterator();
		while (platformAuthPicIt.hasNext()) {
			JSONObject mchtPlatformAuthPicChgObject = platformAuthPicIt.next();

			mchtPlatformAuthPicChgExample = new MchtPlatformAuthPicChgExample();
			mchtPlatformAuthPicChgExample.createCriteria().andPicEqualTo(mchtPlatformAuthPicChgObject.getString("pic")).andBrandChgIdEqualTo(mchtBrandChg.getId());
			mchtPlatformAuthPicChg4Update = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg4Update.setDelFlag("0");
			int updateCount = mchtPlatformAuthPicChgMapper.updateByExampleSelective(mchtPlatformAuthPicChg4Update, mchtPlatformAuthPicChgExample);
			if (updateCount > 0) {
				continue;
			}
			MchtPlatformAuthPicChg mchtPlatformAuthPicChg = new MchtPlatformAuthPicChg();
			mchtPlatformAuthPicChg.setBrandChgId(mchtBrandChg.getId());
			mchtPlatformAuthPicChg.setPic(mchtPlatformAuthPicChgObject.getString("pic"));
			mchtPlatformAuthPicChg.setCreateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPicChg.setCreateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPicChg.setUpdateBy(mchtProductBrand.getUpdateBy());
			mchtPlatformAuthPicChg.setUpdateDate(mchtProductBrand.getUpdateDate());
			mchtPlatformAuthPicChgMapper.insertSelective(mchtPlatformAuthPicChg);
		}
				
			// 更新进货发票图
			
			MchtBrandInvoicePicChgExample mchtBrandInvoicePicChgExample = new MchtBrandInvoicePicChgExample();
			mchtBrandInvoicePicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
			MchtBrandInvoicePicChg mchtBrandInvoicePicChg4Update = new MchtBrandInvoicePicChg();
			mchtBrandInvoicePicChg4Update.setDelFlag("1");
			mchtBrandInvoicePicChgMapper.updateByExampleSelective(mchtBrandInvoicePicChg4Update, mchtBrandInvoicePicChgExample);

			JSONArray invoicePicsArray = JSONArray.fromObject(invoicePics);
			Iterator<JSONObject> invoicePicsIt = invoicePicsArray.iterator();
			while (invoicePicsIt.hasNext()) {
				JSONObject invoicePicObject = invoicePicsIt.next();

				mchtBrandInvoicePicChgExample = new MchtBrandInvoicePicChgExample();
				mchtBrandInvoicePicChgExample.createCriteria().andPicEqualTo(invoicePicObject.getString("pic")).andBrandChgIdEqualTo(mchtBrandChg.getId());
				mchtBrandInvoicePicChg4Update = new MchtBrandInvoicePicChg();
				mchtBrandInvoicePicChg4Update.setDelFlag("0");
				int updateCount = mchtBrandInvoicePicChgMapper.updateByExampleSelective(mchtBrandInvoicePicChg4Update, mchtBrandInvoicePicChgExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandInvoicePicChg mchtBrandInvoicePicChg = new MchtBrandInvoicePicChg();
				mchtBrandInvoicePicChg.setBrandChgId(mchtBrandChg.getId());
				mchtBrandInvoicePicChg.setPic(invoicePicObject.getString("pic"));
				mchtBrandInvoicePicChg.setCreateBy(mchtProductBrand.getUpdateBy());
				mchtBrandInvoicePicChg.setCreateDate(mchtProductBrand.getUpdateDate());
				mchtBrandInvoicePicChg.setUpdateBy(mchtProductBrand.getUpdateBy());
				mchtBrandInvoicePicChg.setUpdateDate(mchtProductBrand.getUpdateDate());
				mchtBrandInvoicePicChgMapper.insertSelective(mchtBrandInvoicePicChg);
			}
				
				
			// 更新质检报告图
			
			MchtBrandInspectionPicChgExample mchtBrandInspectionPicChgExample = new MchtBrandInspectionPicChgExample();
			mchtBrandInspectionPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
			MchtBrandInspectionPicChg mchtBrandInspectionPicChg4Update = new MchtBrandInspectionPicChg();
			mchtBrandInspectionPicChg4Update.setDelFlag("1");
			mchtBrandInspectionPicChgMapper.updateByExampleSelective(mchtBrandInspectionPicChg4Update, mchtBrandInspectionPicChgExample);

			JSONArray inspectionPicsArray = JSONArray.fromObject(inspectionPics);
			Iterator<JSONObject> inspectionPicsIt = inspectionPicsArray.iterator();
			while (inspectionPicsIt.hasNext()) {
				JSONObject inspectionPicObject = inspectionPicsIt.next();

				mchtBrandInspectionPicChgExample = new MchtBrandInspectionPicChgExample();
				mchtBrandInspectionPicChgExample.createCriteria().andPicEqualTo(inspectionPicObject.getString("pic")).andBrandChgIdEqualTo(mchtBrandChg.getId());
				mchtBrandInspectionPicChg4Update = new MchtBrandInspectionPicChg();
				mchtBrandInspectionPicChg4Update.setDelFlag("0");
				int updateCount = mchtBrandInspectionPicChgMapper.updateByExampleSelective(mchtBrandInspectionPicChg4Update, mchtBrandInspectionPicChgExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandInspectionPicChg mchtBrandInspectionPicChg = new MchtBrandInspectionPicChg();
				mchtBrandInspectionPicChg.setBrandChgId(mchtBrandChg.getId());
				mchtBrandInspectionPicChg.setPic(inspectionPicObject.getString("pic"));
				mchtBrandInspectionPicChg.setCreateBy(mchtProductBrand.getUpdateBy());
				mchtBrandInspectionPicChg.setCreateDate(mchtProductBrand.getUpdateDate());
				mchtBrandInspectionPicChg.setUpdateBy(mchtProductBrand.getUpdateBy());
				mchtBrandInspectionPicChg.setUpdateDate(mchtProductBrand.getUpdateDate());
				mchtBrandInspectionPicChgMapper.insertSelective(mchtBrandInspectionPicChg);
			}
				
				
				
			// 更新其他资质图
			
			MchtBrandOtherPicChgExample mchtBrandOtherPicChgExample = new MchtBrandOtherPicChgExample();
			mchtBrandOtherPicChgExample.createCriteria().andDelFlagEqualTo("0").andBrandChgIdEqualTo(mchtBrandChg.getId());
			MchtBrandOtherPicChg mchtBrandOtherPicChg4Update = new MchtBrandOtherPicChg();
			mchtBrandOtherPicChg4Update.setDelFlag("1");
			mchtBrandOtherPicChgMapper.updateByExampleSelective(mchtBrandOtherPicChg4Update, mchtBrandOtherPicChgExample);

			JSONArray ohterPicsArray = JSONArray.fromObject(otherPics);
			Iterator<JSONObject> otherPicsIt = ohterPicsArray.iterator();
			while (otherPicsIt.hasNext()) {
				JSONObject otherPicObject = otherPicsIt.next();

				mchtBrandOtherPicChgExample = new MchtBrandOtherPicChgExample();
				mchtBrandOtherPicChgExample.createCriteria().andPicEqualTo(otherPicObject.getString("pic")).andBrandChgIdEqualTo(mchtBrandChg.getId());
				mchtBrandOtherPicChg4Update = new MchtBrandOtherPicChg();
				mchtBrandOtherPicChg4Update.setDelFlag("0");
				int updateCount = mchtBrandOtherPicChgMapper.updateByExampleSelective(mchtBrandOtherPicChg4Update, mchtBrandOtherPicChgExample);
				if (updateCount > 0) {
					continue;
				}
				MchtBrandOtherPicChg mchtBrandOtherPicChg = new MchtBrandOtherPicChg();
				mchtBrandOtherPicChg.setBrandChgId(mchtBrandChg.getId());
				mchtBrandOtherPicChg.setPic(otherPicObject.getString("pic"));
				mchtBrandOtherPicChg.setCreateBy(mchtProductBrand.getUpdateBy());
				mchtBrandOtherPicChg.setCreateDate(mchtProductBrand.getUpdateDate());
				mchtBrandOtherPicChg.setUpdateBy(mchtProductBrand.getUpdateBy());
				mchtBrandOtherPicChg.setUpdateDate(mchtProductBrand.getUpdateDate());
				mchtBrandOtherPicChgMapper.insertSelective(mchtBrandOtherPicChg);
			}
				
			//TODO 商家品牌注册证,商家品牌资质证明图表
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(!StringUtil.isEmpty(mchtBrandAptitudeJsonStr)){
				List<Integer> delMchtBrandAptitudeIds = mchtBrandAptitudeChgService.getIdsByMchtBrandChgId(mchtBrandChg.getId());
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
						MchtBrandAptitudeChg mbac = new MchtBrandAptitudeChg();
						mbac.setCertificateNo(eachCertificateNo);
						mbac.setAptitudeExpDate(sdf.parse(eachAptitudeExpDate));
						mbac.setUpdateDate(new Date());
						MchtBrandAptitudeChgExample mchtBrandAptitudeChgExample = new MchtBrandAptitudeChgExample();
						mchtBrandAptitudeChgExample.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(Integer.parseInt(eachMchtBrandAptitudeId));
						mchtBrandAptitudeChgMapper.updateByExampleSelective(mbac, mchtBrandAptitudeChgExample);
						
						MchtBrandAptitudePicChgExample mchtBrandAptitudePicChgExample = new MchtBrandAptitudePicChgExample();
						mchtBrandAptitudePicChgExample.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeChgIdEqualTo(Integer.parseInt(eachMchtBrandAptitudeId)).andBrandChgIdEqualTo(mchtBrandChg.getId());
						MchtBrandAptitudePicChg mchtBrandAptitudePicChg4Update = new MchtBrandAptitudePicChg();
						mchtBrandAptitudePicChg4Update.setDelFlag("1");
						mchtBrandAptitudePicChgMapper.updateByExampleSelective(mchtBrandAptitudePicChg4Update, mchtBrandAptitudePicChgExample);
						
						while (eachAptitudePicIt.hasNext()) {
							JSONObject aptitudePicObject = eachAptitudePicIt.next();
							MchtBrandAptitudePicChgExample mbapce = new MchtBrandAptitudePicChgExample();
							mbapce.createCriteria().andPicEqualTo(aptitudePicObject.getString("pic")).andMchtBrandAptitudeChgIdEqualTo(Integer.parseInt(eachMchtBrandAptitudeId)).andBrandChgIdEqualTo(mchtBrandChg.getId());
							mchtBrandAptitudePicChg4Update = new MchtBrandAptitudePicChg();
							mchtBrandAptitudePicChg4Update.setDelFlag("0");
							int updateCount = mchtBrandAptitudePicChgMapper.updateByExampleSelective(mchtBrandAptitudePicChg4Update, mbapce);
							if (updateCount > 0) {
								continue;
							}
							MchtBrandAptitudePicChg mchtBrandAptitudePicChg = new MchtBrandAptitudePicChg();
							mchtBrandAptitudePicChg.setMchtBrandAptitudeChgId(Integer.parseInt(eachMchtBrandAptitudeId));
							mchtBrandAptitudePicChg.setPic(aptitudePicObject.getString("pic"));
							mchtBrandAptitudePicChg.setCreateDate(new Date());
							mchtBrandAptitudePicChg.setUpdateDate(new Date());
							mchtBrandAptitudePicChg.setDelFlag("0");
							mchtBrandAptitudePicChgMapper.insertSelective(mchtBrandAptitudePicChg);
						}
					}else{//新增,插入数据，图片
						MchtBrandAptitudeChg mbac = new MchtBrandAptitudeChg();
						mbac.setMchtBrandChgId(mchtBrandChg.getId());
						mbac.setCertificateNo(eachCertificateNo);
						mbac.setAptitudeExpDate(sdf.parse(eachAptitudeExpDate));
						mbac.setCreateDate(new Date());
						mbac.setDelFlag("0");
						mchtBrandAptitudeChgMapper.insertSelective(mbac);
						
						while (eachAptitudePicIt.hasNext()) {
							JSONObject aptitudePicObject = eachAptitudePicIt.next();
							MchtBrandAptitudePicChg mchtBrandAptitudePicChg = new MchtBrandAptitudePicChg();
							mchtBrandAptitudePicChg.setBrandChgId(mchtBrandChg.getId());
							mchtBrandAptitudePicChg.setMchtBrandAptitudeChgId(mbac.getId());
							mchtBrandAptitudePicChg.setPic(aptitudePicObject.getString("pic"));
							mchtBrandAptitudePicChg.setCreateDate(new Date());
							mchtBrandAptitudePicChg.setUpdateDate(new Date());
							mchtBrandAptitudePicChg.setDelFlag("0");
							mchtBrandAptitudePicChgMapper.insertSelective(mchtBrandAptitudePicChg);
						}
					}
				}
				if(delMchtBrandAptitudeIds!=null && delMchtBrandAptitudeIds.size()>0){
					MchtBrandAptitudeChg mbac = new MchtBrandAptitudeChg();
					mbac.setDelFlag("1");
					mbac.setUpdateDate(new Date());
					MchtBrandAptitudeChgExample mbace = new MchtBrandAptitudeChgExample();
					MchtBrandAptitudeChgExample.Criteria mbacec = mbace.createCriteria();
					mbacec.andDelFlagEqualTo("0");
					mbacec.andMchtBrandChgIdEqualTo(mchtBrandChg.getId());
					mbacec.andIdIn(delMchtBrandAptitudeIds);
					mchtBrandAptitudeChgService.updateByExampleSelective(mbac, mbace);
					MchtBrandAptitudePicChgExample mchtBrandAptitudePicChgExample = new MchtBrandAptitudePicChgExample();
					mchtBrandAptitudePicChgExample.createCriteria().andDelFlagEqualTo("0").andMchtBrandAptitudeChgIdIn(delMchtBrandAptitudeIds);
					MchtBrandAptitudePicChg mchtBrandAptitudePicChg = new MchtBrandAptitudePicChg();
					mchtBrandAptitudePicChg.setDelFlag("1");
					mchtBrandAptitudePicChg.setUpdateDate(new Date());
					mchtBrandAptitudePicChgMapper.updateByExampleSelective(mchtBrandAptitudePicChg, mchtBrandAptitudePicChgExample);
				}
			}	
				
				//TODO 商家品牌经营类目
				if(!StringUtil.isEmpty(mchtBrandProductTypeJsonStr)){
					MchtBrandChgProductType mchtBrandChgProductType = new MchtBrandChgProductType();
					mchtBrandChgProductType.setDelFlag("1");
					MchtBrandChgProductTypeExample mchtBrandChgProductTypeExample = new MchtBrandChgProductTypeExample();
					mchtBrandChgProductTypeExample.createCriteria().andDelFlagEqualTo("0").andMchtBrandChgIdEqualTo(mchtBrandChg.getId());
					mchtBrandChgProductTypeService.updateByExampleSelective(mchtBrandChgProductType, mchtBrandChgProductTypeExample);
					JSONArray mchtBrandChgProductTypeArray = JSONArray.fromObject(mchtBrandProductTypeJsonStr);
					List<MchtBrandChgProductType> mchtBrandChgProductTypeList = new ArrayList<MchtBrandChgProductType>();
					for(int i=0;i<mchtBrandChgProductTypeArray.size();i++){
						JSONObject mchtBrandChgProductTypeJo = (JSONObject)mchtBrandChgProductTypeArray.get(i);
						String productTypeId = mchtBrandChgProductTypeJo.getString("productTypeId");
						int tLevel = mchtBrandChgProductTypeJo.getInt("tLevel");
						if(tLevel == 3){
							String[] productTypeIdArray = productTypeId.split(",");
							for(String ptId:productTypeIdArray){
								MchtBrandChgProductType mbcpt = new MchtBrandChgProductType();
								mbcpt.setMchtBrandChgId(mchtBrandChg.getId());
								mbcpt.setProductTypeId(Integer.parseInt(ptId));
								mbcpt.settLevel(tLevel);
								mbcpt.setCreateDate(new Date());
								mbcpt.setDelFlag("0");
								mchtBrandChgProductTypeList.add(mbcpt);
							}
						}else{
							MchtBrandChgProductType mbcpt = new MchtBrandChgProductType();
							mbcpt.setMchtBrandChgId(mchtBrandChg.getId());
							mbcpt.setProductTypeId(Integer.parseInt(productTypeId));
							mbcpt.settLevel(tLevel);
							mbcpt.setCreateBy(mchtProductBrand.getUpdateBy());
							if(mchtProductBrand.getUpdateDate()!=null){
								mbcpt.setCreateDate(mchtProductBrand.getUpdateDate());
							}else{
								mbcpt.setCreateDate(new Date());
							}
							mbcpt.setDelFlag("0");
							mchtBrandChgProductTypeList.add(mbcpt);
						}
					}
					mchtBrandChgProductTypeService.batchInsert(mchtBrandChgProductTypeList);
				}
		
	}
	
}
