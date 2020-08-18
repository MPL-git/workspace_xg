package com.jf.service;

import com.jf.bean.PDFCreaterChangeAgreement;
import com.jf.common.utils.StringUtils;
import com.jf.dao.*;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CooperationChangeApplyService extends BaseService<CooperationChangeApply,CooperationChangeApplyExample> {
	@Autowired
	private CooperationChangeApplyMapper cooperationChangeApplyMapper;
	@Autowired
	private CooperationChangeApplyCustomMapper cooperationChangeApplyCustomMapper;
	@Autowired
	private MchtBrandRateChangeMapper mchtBrandRateChangeMapper;
	@Autowired
	private MchtProductTypeService mchtProductTypeService;
	@Autowired
	private MchtInfoService mchtInfoService;
	@Autowired
	private MchtDepositService mchtDepositService;
	@Autowired
	private MchtProductBrandService mchtProductBrandService;
	@Autowired
	private CooperationChangeUploadPicMapper cooperationChangeUploadPicMapper;

	@Autowired
	private MchtProductBrandMapper mchtProductBrandMapper;

	@Autowired
	public void setCooperationChangeApplyMapper(CooperationChangeApplyMapper cooperationChangeApplyMapper) {
		super.setDao(cooperationChangeApplyMapper);
		this.cooperationChangeApplyMapper = cooperationChangeApplyMapper;
	}
	
	public int countByCustomExample(CooperationChangeApplyCustomExample example){
		return cooperationChangeApplyCustomMapper.countByExample(example);
	}
    public List<CooperationChangeApplyCustom> selectByCustomExample(CooperationChangeApplyCustomExample example){
    	return cooperationChangeApplyCustomMapper.selectByExample(example);
    }

	public void save(CooperationChangeApply cooperationChangeApply,String mchtBrandRateChangeJsonStr) {
		MchtBrandRateChangeExample example = new MchtBrandRateChangeExample();
		example.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApply.getMchtId()).andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId());
		MchtBrandRateChange mchtBrandRateChange = new MchtBrandRateChange();
		mchtBrandRateChange.setUpdateDate(new Date());
		mchtBrandRateChange.setDelFlag("1");
		mchtBrandRateChangeMapper.updateByExampleSelective(mchtBrandRateChange, example);
		if(!StringUtils.isEmpty(mchtBrandRateChangeJsonStr)){
			JSONArray ja = JSONArray.fromObject(mchtBrandRateChangeJsonStr);
			for(int i=0;i<ja.size();i++){
				MchtBrandRateChange mbrc = new MchtBrandRateChange();
				mbrc.setCreateDate(new Date());
				mbrc.setCreateBy(cooperationChangeApply.getUpdateBy());
				mbrc.setDelFlag("0");
				mbrc.setMchtId(cooperationChangeApply.getMchtId());
				mbrc.setCooperationChangeApplyId(cooperationChangeApply.getId());
				JSONObject jo = (JSONObject)ja.get(i);
				Integer mchtProductBrandId = jo.getInt("mchtProductBrandId");
				String popCommissionRate = jo.getString("popCommissionRate");
				String prePopCommissionRate =
						mchtProductBrandMapper.selectByPrimaryKey(mchtProductBrandId).getPopCommissionRate()==null?"":
								mchtProductBrandMapper.selectByPrimaryKey(mchtProductBrandId).getPopCommissionRate().toString();
				mbrc.setPrePopCommissionRate(new BigDecimal(prePopCommissionRate));
				mbrc.setMchtProductBrandId(mchtProductBrandId);
				mbrc.setPopCommissionRate(new BigDecimal(popCommissionRate));

				mchtBrandRateChangeMapper.insertSelective(mbrc);
			}
		}
		if(cooperationChangeApply.getZsAuditStatus().equals("1")){
			String filePath;
			try {
				filePath = PDFCreaterChangeAgreement.createPDF(cooperationChangeApply);
				cooperationChangeApply.setFilePath(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.updateByPrimaryKeySelective(cooperationChangeApply);
	}

	public void createContract(CooperationChangeApply cooperationChangeApply) {
//		String filePath;
		try {
//			filePath = PDFCreaterChangeAgreement.createPDF(cooperationChangeApply);
//			cooperationChangeApply.setFilePath(filePath);
			this.updateByPrimaryKeySelective(cooperationChangeApply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void  updateMchtRelevant(CooperationChangeApply cooperationChangeApply) {
		this.updateByPrimaryKeySelective(cooperationChangeApply);
		//1、店铺名称
        if(!StringUtils.isEmpty(cooperationChangeApply.getShopName())) {
            MchtInfo mi = new MchtInfo();
            mi.setShopType(cooperationChangeApply.getShopType());
            mi.setBrand(cooperationChangeApply.getBrand());
            mi.setProductType(cooperationChangeApply.getProductType()); //品类更新
            mi.setBusinessFirms(cooperationChangeApply.getBusinessFirms());
            mi.setShopName(cooperationChangeApply.getShopName());
            MchtInfoExample mie = new MchtInfoExample();
            mie.createCriteria().andDelFlagEqualTo("0").andIdEqualTo(cooperationChangeApply.getMchtId());
            mchtInfoService.updateByExampleSelective(mi, mie);
        }

		//2.主营类目
        if(!StringUtils.isEmpty(cooperationChangeApply.getProductTypeId())) {
            MchtProductType mpt = new MchtProductType();
            MchtProductTypeExample mcte = new MchtProductTypeExample();
            mcte.createCriteria().andMchtIdEqualTo(cooperationChangeApply.getMchtId()).andProductTypeIdEqualTo(cooperationChangeApply.getProductTypeId());
            List<MchtProductType> mchtProductTypeList = mchtProductTypeService.selectByExample(mcte);
            if (mchtProductTypeList.size() > 0 && mchtProductTypeList.get(0).getIsMain().equals("0")) {
                mchtProductTypeService.deleteByPrimaryKey(mchtProductTypeList.get(0).getId());    //过滤
            }
            mpt.setProductTypeId(cooperationChangeApply.getProductTypeId());
            mpt.setUpdateBy(cooperationChangeApply.getUpdateBy());
            mpt.setUpdateDate(new Date());
            MchtProductTypeExample e = new MchtProductTypeExample();
            e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApply.getMchtId()).andStatusEqualTo("1").andIsMainEqualTo("1");
            mchtProductTypeService.updateByExampleSelective(mpt, e);
        }
		//3.保证金
            MchtDepositExample mde = new MchtDepositExample();
            mde.createCriteria().andMchtIdEqualTo(cooperationChangeApply.getMchtId()).andDelFlagEqualTo("0");
            List<MchtDeposit> mchtDeposits = mchtDepositService.selectByExample(mde);
            MchtDeposit md = mchtDeposits.get(0);
            md.setTotalAmt(cooperationChangeApply.getDeposit());
            if (md.getTotalAmt() != null) {
                if (md.getPayAmt() != null) {
                    md.setUnpayAmt(md.getTotalAmt().subtract(md.getPayAmt()));
                }
            }
            md.setUpdateDate(new Date());
            md.setUpdateBy(cooperationChangeApply.getUpdateBy());
            mchtDepositService.updateByPrimaryKeySelective(md);
		//4.服务费率
            MchtBrandRateChangeExample mbrce = new MchtBrandRateChangeExample();
            mbrce.createCriteria().andDelFlagEqualTo("0").andCooperationChangeApplyIdEqualTo(cooperationChangeApply.getId()).andMchtIdEqualTo(cooperationChangeApply.getMchtId());
            List<MchtBrandRateChange> mchtBrandRateChanges = mchtBrandRateChangeMapper.selectByExample(mbrce);
            MchtProductBrandExample mpbe = new MchtProductBrandExample();
            mpbe.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(cooperationChangeApply.getMchtId()).andStatusEqualTo("1");
            List<MchtProductBrand> mchtProductBrands = mchtProductBrandService.selectByExample(mpbe);
            for (MchtBrandRateChange mchtBrandRateChange : mchtBrandRateChanges) {
                for (MchtProductBrand mchtProductBrand : mchtProductBrands) {
                    if (mchtBrandRateChange.getMchtProductBrandId().equals(mchtProductBrand.getId())) {
                        mchtProductBrand.setPopCommissionRate(mchtBrandRateChange.getPopCommissionRate());
                        mchtProductBrand.setUpdateBy(cooperationChangeApply.getUpdateBy());
                        mchtProductBrand.setUpdateDate(new Date());
                        mchtProductBrandService.updateByPrimaryKeySelective(mchtProductBrand);
                    }
                }
            }
	}

	public void toUploadPicture(CooperationChangeApply cooperationChangeApply, JSONArray cooperationChangeUploadPics){
		cooperationChangeApplyMapper.updateByPrimaryKey(cooperationChangeApply);
		if(cooperationChangeUploadPics != null){
			for(int i=0; i<cooperationChangeUploadPics.size(); i++){
				JSONObject cooperationChangeUploadPicsJSONObject = cooperationChangeUploadPics.getJSONObject(i);
				CooperationChangeUploadPic cooperationChangeUploadPic = new CooperationChangeUploadPic();
				cooperationChangeUploadPic.setCooperationChangeApplyId(cooperationChangeApply.getId());
				cooperationChangeUploadPic.setPic(cooperationChangeUploadPicsJSONObject.getString("picPath"));
				cooperationChangeUploadPic.setCreateBy(cooperationChangeApply.getCreateBy());
				cooperationChangeUploadPic.setCreateDate(new Date());
				cooperationChangeUploadPic.setDelFlag("0");
				cooperationChangeUploadPic.setStatus("0");
				cooperationChangeUploadPicMapper.insertSelective(cooperationChangeUploadPic);
			}
		}
	}
}
