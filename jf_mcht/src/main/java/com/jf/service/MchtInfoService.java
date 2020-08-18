package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtBlPicMapper;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.dao.MchtInfoCustomMapper;
import com.jf.dao.MchtInfoMapper;
import com.jf.entity.MchtBankAccount;
import com.jf.entity.MchtBankAccountExample;
import com.jf.entity.MchtBlPic;
import com.jf.entity.MchtBlPicExample;
import com.jf.entity.MchtHomeInfo;
import com.jf.entity.MchtInfo;
import com.jf.entity.MchtInfoChangeLog;
import com.jf.entity.MchtInfoCustom;
import com.jf.entity.MchtInfoExample;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtProductBrandExample;
import com.jf.entity.MchtProductType;
import com.jf.entity.MchtProductTypeExample;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class MchtInfoService extends BaseService<MchtInfo, MchtInfoExample> {
	@Autowired
	private MchtInfoMapper mchtInfoMapper;

	@Resource
	private MchtInfoCustomMapper mchtInfoCustomMapper;
	
	@Resource
	private MchtBlPicMapper mchtBlPicMapper;

	@Resource
	private MchtContractService mchtContractService;

	@Resource
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Resource
	private MchtProductBrandService mchtProductBrandService;
	
	@Resource
	private MchtProductTypeService mchtProductTypeService;
	
	@Resource
	private MchtBankAccountService mchtBankAccountService;
	
	@Autowired
	public void setMchtInfoMapper(MchtInfoMapper mchtInfoMapper) {
		super.setDao(mchtInfoMapper);
		this.mchtInfoMapper = mchtInfoMapper;
	}

	public MchtInfoCustom selectMchtInfoCustomById(Integer id) {
		return mchtInfoCustomMapper.selectByPrimaryKey(id);
	}
	
	public MchtHomeInfo selectMchtHomeByPrimaryKey(Integer id) {
		return mchtInfoCustomMapper.selectMchtHomeByPrimaryKey(id);
	}
	
	
	/**
	 * 公司信息保存暂不提审
	 * @param mchtInfo
	 * @throws ArgException
	 */
	public void perfectCommitSave(MchtInfo mchtInfo) throws ArgException{
		MchtInfo oldMchtInfo=mchtInfoMapper.selectByPrimaryKey(mchtInfo.getId());
		if(oldMchtInfo.getIsCompanyInfPerfect().equals("1")||oldMchtInfo.getIsCompanyInfPerfect().equals("3")){
			throw new ArgException("信息审核中或已审核通过，不可修改");
		}
		mchtInfo.setIsCompanyInfPerfect("2");//2 已填 （填完资料保存后的状态）
		//更新公司营业执照图
		MchtBlPicExample mchtBlPicExample=new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andDelFlagEqualTo("0");
		MchtBlPic mchtBlPic4Update=new MchtBlPic();
		mchtBlPic4Update.setDelFlag("1");
		mchtBlPicMapper.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
		mchtBlPicExample=new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andPicEqualTo(mchtInfo.getBlPic());
		mchtBlPic4Update=new MchtBlPic();
		mchtBlPic4Update.setDelFlag("0");
		int updateCount=mchtBlPicMapper.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
		if(updateCount == 0){
			MchtBlPic mchtBlPic=new MchtBlPic();
			mchtBlPic.setMchtId(mchtInfo.getId());
			mchtBlPic.setPic(mchtInfo.getBlPic());
			mchtBlPic.setCreateBy(mchtInfo.getUpdateBy());
			mchtBlPic.setCreateDate(mchtInfo.getUpdateDate());
			mchtBlPic.setUpdateBy(mchtInfo.getUpdateBy());
			mchtBlPic.setUpdateDate(mchtInfo.getUpdateDate());
			mchtBlPic.setDelFlag("0");
			mchtBlPicMapper.insertSelective(mchtBlPic);
		}
		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
	}
	/**
	 * 公司信息保存暂不提审
	 * @param mchtInfo
	 * @throws ArgException
	 */
	public void perfectCommitSave(MchtInfo mchtInfo,String blPics) throws ArgException{
		MchtInfo oldMchtInfo=mchtInfoMapper.selectByPrimaryKey(mchtInfo.getId());
		if(oldMchtInfo.getIsCompanyInfPerfect().equals("1")||oldMchtInfo.getIsCompanyInfPerfect().equals("3")){
			throw new ArgException("信息审核中或已审核通过，不可修改");
		}
		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
		
		//更新公司营业执照图
		MchtBlPicExample mchtBlPicExample=new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andDelFlagEqualTo("0");
		MchtBlPic mchtBlPic4Update=new MchtBlPic();
		mchtBlPic4Update.setDelFlag("1");
		mchtBlPicMapper.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
		
		JSONArray blPicArray=JSONArray.fromObject(blPics);
		Iterator<JSONObject> it= blPicArray.iterator();
		while (it.hasNext()) {
			JSONObject mchtBlPicObject=it.next();
			
			mchtBlPicExample=new MchtBlPicExample();
			mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andPicEqualTo(mchtBlPicObject.getString("pic"));
			mchtBlPic4Update=new MchtBlPic();
			mchtBlPic4Update.setDelFlag("0");
			int updateCount=mchtBlPicMapper.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
			if(updateCount>0){
				continue;
			}
			MchtBlPic mchtBlPic=new MchtBlPic();
			mchtBlPic.setMchtId(mchtInfo.getId());
			mchtBlPic.setPic(mchtBlPicObject.getString("pic"));
			mchtBlPic.setCreateBy(mchtInfo.getUpdateBy());
			mchtBlPic.setCreateDate(mchtInfo.getUpdateDate());
			mchtBlPic.setUpdateBy(mchtInfo.getUpdateBy());
			mchtBlPic.setUpdateDate(mchtInfo.getUpdateDate());
			mchtBlPicMapper.insertSelective(mchtBlPic);
		}
	}
	
	/**
	 * 公司信息保存，提审
	 * @param mchtInfo
	 * @throws ArgException
	 */
	public void perfectCommitAudit(MchtInfo mchtInfo) throws ArgException{
		MchtInfo oldMchtInfo=mchtInfoMapper.selectByPrimaryKey(mchtInfo.getId());
		if(!StringUtil.isEmpty(oldMchtInfo.getIsCompanyInfPerfect()) && oldMchtInfo.getIsCompanyInfPerfect().equals("3")){
			throw new ArgException("您的信息已在审核中，不能重新提交！");
		}
		if(!StringUtil.isEmpty(oldMchtInfo.getIsCompanyInfPerfect()) && oldMchtInfo.getIsCompanyInfPerfect().equals("1")){
			throw new ArgException("您的信息审核通过，无需重新提交。");
		}
		mchtInfo.setIsCompanyInfPerfect("2");
		MchtBlPicExample e = new MchtBlPicExample();
		e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(oldMchtInfo.getId());
		List<MchtBlPic> mchtBlPics = mchtBlPicMapper.selectByExample(e);
		if(mchtBlPics!=null && mchtBlPics.size()>0){
			MchtBlPic mbp = mchtBlPics.get(0);
			if(!mbp.getPic().equals(mchtInfo.getBlPic())){
				mbp.setPic(mchtInfo.getBlPic());
				mbp.setUpdateDate(new Date());
				mchtBlPicMapper.updateByPrimaryKeySelective(mbp);
			}
		}else{
			MchtBlPic mchtBlPic = new MchtBlPic();
			mchtBlPic.setCreateBy(mchtInfo.getUpdateBy());
			mchtBlPic.setCreateDate(new Date());
			mchtBlPic.setDelFlag("0");
			mchtBlPic.setMchtId(mchtInfo.getId());
			mchtBlPic.setPic(mchtInfo.getBlPic());
			mchtBlPic.setBlPicArchiveStatus("0");
			mchtBlPicMapper.insertSelective(mchtBlPic);
		}
		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
	}
	/**
	 * 公司信息保存，提审
	 * @param mchtInfo
	 * @throws ArgException
	 */
	public void perfectCommitAudit(MchtInfo mchtInfo,String blPics) throws ArgException{
		MchtInfo oldMchtInfo=mchtInfoMapper.selectByPrimaryKey(mchtInfo.getId());
		if(oldMchtInfo.getIsCompanyInfPerfect().equals("3")){
			throw new ArgException("您的信息已在审核中，不能重新提交！");
		}
		if(oldMchtInfo.getIsCompanyInfPerfect().equals("1")){
			throw new ArgException("您的信息审核通过，无需重新提交。");
		}
		mchtInfo.setIsCompanyInfPerfect("2");
		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
		
		//更新公司营业执照图
		MchtBlPicExample mchtBlPicExample=new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andDelFlagEqualTo("0");
		MchtBlPic mchtBlPic4Update=new MchtBlPic();
		mchtBlPic4Update.setDelFlag("1");
		mchtBlPicMapper.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
	
	JSONArray blPicArray=JSONArray.fromObject(blPics);
	Iterator<JSONObject> it= blPicArray.iterator();
	while (it.hasNext()) {
		JSONObject mchtBlPicObject=it.next();
		
		mchtBlPicExample=new MchtBlPicExample();
		mchtBlPicExample.createCriteria().andMchtIdEqualTo(mchtInfo.getId()).andPicEqualTo(mchtBlPicObject.getString("pic"));
		mchtBlPic4Update=new MchtBlPic();
		mchtBlPic4Update.setDelFlag("0");
		int updateCount=mchtBlPicMapper.updateByExampleSelective(mchtBlPic4Update, mchtBlPicExample);
		if(updateCount>0){
			continue;
		}
		MchtBlPic mchtBlPic=new MchtBlPic();
		mchtBlPic.setMchtId(mchtInfo.getId());
		mchtBlPic.setPic(mchtBlPicObject.getString("pic"));
		mchtBlPic.setCreateBy(mchtInfo.getUpdateBy());
		mchtBlPic.setCreateDate(mchtInfo.getUpdateDate());
		mchtBlPic.setUpdateBy(mchtInfo.getUpdateBy());
		mchtBlPic.setUpdateDate(mchtInfo.getUpdateDate());
		mchtBlPicMapper.insertSelective(mchtBlPic);
	}
}


	/**
	 * 公司总资质提审
	 * @throws ParseException 
     */
	public void commitTotalInfo(int id) throws ParseException {
		MchtInfo model = this.selectByPrimaryKey(id);
		MchtInfoChangeLog mchtInfoChangeLog = new  MchtInfoChangeLog();
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setCreateBy(id);
		mchtInfoChangeLog.setCreateDate(new Date());
		mchtInfoChangeLog.setMchtId(id);
		mchtInfoChangeLog.setLogType("提交资质总审核");
		mchtInfoChangeLog.setLogName(model.getCompanyName());
		if(StringUtil.isEmpty(model.getZsTotalAuditStatus()) || model.getZsTotalAuditStatus().equals("4")){//4.未提交
			mchtInfoChangeLog.setBeforeChange("未提交");
		}else if(!StringUtil.isEmpty(model.getZsTotalAuditStatus()) && model.getZsTotalAuditStatus().equals("3")){//3.驳回
			mchtInfoChangeLog.setBeforeChange("招商驳回");
		}else if(!StringUtil.isEmpty(model.getTotalAuditStatus()) && model.getTotalAuditStatus().equals("3")){//3.驳回
			mchtInfoChangeLog.setBeforeChange("法务驳回");
			model.setIsTotalAuditReCommit("1");
		}
		
		if(StringUtil.isEmpty(model.getIsZsTotalAuditReCommit())){
			mchtInfoChangeLog.setAfterChange("待审核(首次提交)");
			model.setIsZsTotalAuditReCommit("0");
			if(!StringUtil.isEmpty(model.getSettledType()) && model.getSettledType().equals("2")){//2.个体商户
				MchtProductBrandExample mpbe = new MchtProductBrandExample();
				mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(id).andProductBrandIdEqualTo(0);
				List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
				if(mchtProductBrands == null || mchtProductBrands.size() == 0){
					MchtProductBrand mpb = new MchtProductBrand();
					Date date = new Date();
					mpb.setCreateDate(date);
					mpb.setDelFlag("0");
					mpb.setMchtId(id);
					mpb.setProductBrandId(0);
					mpb.setProductBrandName("无品牌");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date limitDate = sdf.parse("2099-12-31");
					mpb.setInspectionExpDate(limitDate);
					mpb.setOtherExpDate(limitDate);
					mpb.setAptitudeType("1");
					mpb.setAptitudeExpDate(limitDate);
					mpb.setPlatformAuthExpDate(limitDate);
					mpb.setPopCommissionRate(new BigDecimal(0));
					mpb.setStatus("1");//1.正常
					mpb.setAuditStatus("3");//3.审核通过
					mpb.setStatusDate(date);
					mpb.setZsAuditStatus("2");
					mpb.setZsCommitAuditDate(date);
					mpb.setZsAuditDate(date);
					mpb.setIsZsAuditRecommit("0");
					mpb.setCommitAuditDate(date);
					mpb.setAuditDate(new Date());
					mpb.setIsAuditRecommit("0");
					mpb.setArchiveStatus("3");
					mpb.setBrandSource("1");
					mchtProductBrandService.insertSelective(mpb);
				}
			}
			
		}else if(!model.getZsTotalAuditStatus().equals("2") && !StringUtil.isEmpty(model.getIsZsTotalAuditReCommit()) && (model.getIsZsTotalAuditReCommit().equals("0") || model.getIsZsTotalAuditReCommit().equals("1"))){
			model.setIsZsTotalAuditReCommit("1");
			mchtInfoChangeLog.setAfterChange("待审核(重新提交)");
		}
		
		if(!StringUtil.isEmpty(model.getZsTotalAuditStatus()) && model.getZsTotalAuditStatus().equals("2")){
			model.setIsTotalAuditReCommit("1");//设置为法务重新提审
		}
		
		mchtInfoChangeLog.setCreatorType("2");//商家
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
		//TODO 商家品牌,招商驳回时
		MchtProductBrand mchtProductBrand = new MchtProductBrand();
		mchtProductBrand.setZsAuditStatus("1");
		mchtProductBrand.setZsCommitAuditDate(new Date());
		mchtProductBrand.setUpdateBy(id);
		mchtProductBrand.setUpdateDate(new Date());
		MchtProductBrandExample mpbe = new MchtProductBrandExample();
		MchtProductBrandExample.Criteria mpbec = mpbe.createCriteria();
		mpbec.andDelFlagEqualTo("0");
		mpbec.andMchtIdEqualTo(id);
		List<String> zsAuditStatusIn = new ArrayList<>();
		zsAuditStatusIn.add("0");	//未提交
		zsAuditStatusIn.add("4");	//驳回
		mpbec.andZsAuditStatusIn(zsAuditStatusIn);
		mchtProductBrandService.updateByExampleSelective(mchtProductBrand, mpbe);
		
		//TODO 法务驳回
		MchtProductBrand mpb = new MchtProductBrand();
		mpb.setAuditStatus("1");
		mpb.setCommitAuditDate(new Date());
		mpb.setIsAuditRecommit("1");//重新提审
		mpb.setUpdateBy(id);
		mpb.setUpdateDate(new Date());
		MchtProductBrandExample mchtProductBrandExample = new MchtProductBrandExample();
		MchtProductBrandExample.Criteria criteria = mchtProductBrandExample.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andMchtIdEqualTo(id);
		criteria.andAuditStatusEqualTo("4");//4.驳回
		mchtProductBrandService.updateByExampleSelective(mpb, mchtProductBrandExample);
		
		
		//TODO 商家品类
		MchtProductType mchtProductType = new MchtProductType();
		mchtProductType.setStatus("1");//1 正常（商家提交后不用审核直接变成正常状态）
		mchtProductType.setUpdateBy(id);
		mchtProductType.setUpdateDate(new Date());
		MchtProductTypeExample mpte = new MchtProductTypeExample();
		MchtProductTypeExample.Criteria mptec = mpte.createCriteria();
		mptec.andDelFlagEqualTo("0");
		mptec.andMchtIdEqualTo(id);
		mptec.andStatusEqualTo("0");//0申请者（未提交）
		mptec.andIsMainEqualTo("1");//1.主营
		mchtProductTypeService.updateByExampleSelective(mchtProductType, mpte);
		
		//TODO 商家表状态
		if(!StringUtil.isEmpty(model.getTotalAuditStatus()) && !StringUtil.isEmpty(model.getZsTotalAuditStatus()) && model.getZsTotalAuditStatus().equals("2")){//法务驳回情况
			model.setTotalAuditStatus("0");//0.已提交
			model.setCommitAuditDate(new Date());
		}else{//招商驳回
			model.setZsTotalAuditStatus("0");//0.已提交
			model.setZsCommitAuditDate(new Date());
			model.setTotalAuditStatus("4");//4.未提交
		}
		model.setUpdateDate(new Date());
		updateByPrimaryKeySelective(model);
		
		//TODO 商家银行账号
		MchtBankAccountExample mbae = new MchtBankAccountExample();
		mbae.createCriteria().andDelFlagEqualTo("0").andStatusEqualTo("0").andMchtIdEqualTo(id);
		MchtBankAccount mba = new MchtBankAccount();
		mba.setCommitDate(new Date());
		mba.setUpdateDate(new Date());
		mchtBankAccountService.updateByExampleSelective(mba, mbae);
	}

}
