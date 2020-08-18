package com.jf.service;

import com.jf.bean.PDFCreaterEndAgreement;
import com.jf.bean.PDFCreaterPaymentForm;
import com.jf.dao.*;
import com.jf.entity.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtCloseApplicationService extends BaseService<MchtCloseApplication, MchtCloseApplicationExample> {

	@Autowired
	private MchtCloseApplicationMapper mchtCloseApplicationMapper;
	
	@Autowired
	private MchtCloseApplicationCustomMapper mchtCloseApplicationCustomMapper;
	
	@Autowired
	private MchtCloseApplicationRemarksMapper mchtCloseApplicationRemarksMapper;
	
	@Autowired
	private MchtInfoMapper mchtInfoMapper;
	
	@Autowired
	private MchtProductBrandMapper mchtProductBrandMapper;
	
	@Autowired
	private MchtCloseApplicationPicMapper mchtCloseApplicationPicMapper;
	
	@Autowired
	public void setMchtCloseApplicationMapper(MchtCloseApplicationMapper mchtCloseApplicationMapper) {
		super.setDao(mchtCloseApplicationMapper);
		this.dao = mchtCloseApplicationMapper;
	}
	
	public int countByCustomExample(MchtCloseApplicationCustomExample example) {
		return mchtCloseApplicationCustomMapper.countByExample(example);
	}

	public List<MchtCloseApplicationCustom> selectByCustomExample(MchtCloseApplicationCustomExample example) {
		return mchtCloseApplicationCustomMapper.selectByExample(example);
	}

	public void updateByPrimaryKey(MchtCloseApplication mchtCloseApplication,MchtCloseApplicationRemarks mcar) {
		this.updateByPrimaryKeySelective(mchtCloseApplication);
		mchtCloseApplicationRemarksMapper.insertSelective(mcar);
	}
	
	public MchtCloseApplicationCustom selectCustomByPrimaryKey(Integer id) {
		return mchtCloseApplicationCustomMapper.selectCustomByPrimaryKey(id);
	}
	//生成终止协议PDF
	public void createPDF(Integer id) {
		MchtCloseApplication mchtCloseApplication = this.selectByPrimaryKey(id);
		// 生成PDF
		try {
			String filePath = PDFCreaterEndAgreement.createPDF(mchtCloseApplication);
			mchtCloseApplication.setFilePath(filePath);
			this.updateByPrimaryKeySelective(mchtCloseApplication);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//生成付款单的PDF
	public void createPDFOfPayMent(Integer id) {
		MchtCloseApplicationCustom mchtCloseApplicationCustom = this.selectCustomByPrimaryKey(id);
		// 生成PDF
		try {
			String billsPath = PDFCreaterPaymentForm.createPDF(mchtCloseApplicationCustom);
			mchtCloseApplicationCustom.setBillsPath(billsPath);//设置退保单路径
			mchtCloseApplicationCustom.setComfirmBillsStatus("0");//设置退保单是否已打
			this.updateByPrimaryKeySelective(mchtCloseApplicationCustom);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void update(MchtCloseApplication mchtCloseApplication,
			MchtInfo mchtInfo, MchtProductBrand mpb,
			MchtProductBrandExample mpbe) {
		this.updateByPrimaryKeySelective(mchtCloseApplication);
		mchtInfoMapper.updateByPrimaryKeySelective(mchtInfo);
		mchtProductBrandMapper.updateByExampleSelective(mpb, mpbe);
	}
	
	//上传合同照片
	public void mchtClosePicUpload(MchtCloseApplication mchtCloseApplication, String contractPics) {
		//先删除合同下的所有图片，再更新添加回来	
		MchtCloseApplicationPicExample mcapExample = new MchtCloseApplicationPicExample();
		mcapExample.createCriteria().andDelFlagEqualTo("0").andMchtCloseApplicationIdEqualTo(mchtCloseApplication.getId());
		MchtCloseApplicationPic mcap = new MchtCloseApplicationPic();
		mcap.setDelFlag("0");
		mcap.setUpdateDate(new Date());
		mchtCloseApplicationPicMapper.updateByExampleSelective(mcap, mcapExample);
		JSONArray picsJa = JSONArray.fromObject(contractPics);
		List<String> list = (List<String>) JSONArray.toCollection(picsJa, String.class);//相关订单号集合
		String string = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			MchtCloseApplicationPicExample mcaPicExample = new MchtCloseApplicationPicExample();
			mcaPicExample.createCriteria().andPicEqualTo(list.get(i)).andMchtCloseApplicationIdEqualTo(mchtCloseApplication.getId());
			MchtCloseApplicationPic mcap4Update = new MchtCloseApplicationPic();
			mcap4Update.setDelFlag("0");
			int updateCount = mchtCloseApplicationPicMapper.updateByExampleSelective(mcap4Update, mcaPicExample);
			if (updateCount > 0) {
				continue;
			}
			MchtCloseApplicationPic mcap4add = new MchtCloseApplicationPic();
			mcap4add.setPic(list.get(i));
			mcap4add.setMchtCloseApplicationId(mchtCloseApplication.getId());
			mcap4add.setCreateDate(new Date());
			mcap4add.setUpdateDate(new Date());
			mcap4add.setDelFlag("0");
			mchtCloseApplicationPicMapper.insertSelective(mcap4add);
		}
		mchtCloseApplication.setContractAuditStatus("1");
		mchtCloseApplicationMapper.updateByPrimaryKeySelective(mchtCloseApplication);
	}
	
	
	
	
}
