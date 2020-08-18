package com.jf.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.DataDicUtil;
import com.jf.dao.MchtInfoChangeLogMapper;
import com.jf.dao.MchtTaxInvoiceInfoCustomMapper;
import com.jf.dao.MchtTaxInvoiceInfoMapper;
import com.jf.entity.MchtInfoChangeLog;
import com.jf.entity.MchtTaxInvoiceInfo;
import com.jf.entity.MchtTaxInvoiceInfoCustom;
import com.jf.entity.MchtTaxInvoiceInfoExample;

@Service
@Transactional
public class MchtTaxInvoiceInfoService extends BaseService<MchtTaxInvoiceInfo,MchtTaxInvoiceInfoExample> {
	@Autowired
	private MchtTaxInvoiceInfoMapper mchtTaxInvoiceInfoMapper;
	
	@Resource
	private MchtTaxInvoiceInfoCustomMapper mchtTaxInvoiceInfoCustomMapper;
	
	@Resource
	private MchtInfoChangeLogMapper mchtInfoChangeLogMapper;
	
	@Autowired
	public void setMchtTaxInvoiceInfoMapper(MchtTaxInvoiceInfoMapper mchtTaxInvoiceInfoMapper) {
		super.setDao(mchtTaxInvoiceInfoMapper);
		this.mchtTaxInvoiceInfoMapper = mchtTaxInvoiceInfoMapper;
	}
	
	public List<MchtTaxInvoiceInfoCustom> mchtTaxInvoiceInfoCustomList(HashMap<String, Object> paramMap)
	{
		List<MchtTaxInvoiceInfoCustom> list = mchtTaxInvoiceInfoCustomMapper.mchtTaxInvoiceInfoCustomList(paramMap);
		return list;
	}
	
	public int mchtTaxInvoiceInfoCustomListCount(HashMap<String, Object> paramMap)
	{
		return mchtTaxInvoiceInfoCustomMapper.mchtTaxInvoiceInfoCustomListCount(paramMap);
	}
	
	public void updateMchtTaxInvoiceInfo(MchtTaxInvoiceInfo mchtTaxInvoiceInfo) {
		MchtTaxInvoiceInfo oldMchtTaxInvoiceInfo = mchtTaxInvoiceInfoMapper.selectByPrimaryKey(mchtTaxInvoiceInfo.getId());
		
		MchtInfoChangeLog mchtInfoChangeLog = new MchtInfoChangeLog();
		mchtInfoChangeLog.setAfterChange(DataDicUtil.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS", mchtTaxInvoiceInfo.getAuditStatus()));
		mchtInfoChangeLog.setBeforeChange(DataDicUtil.getStatusDesc("BU_MCHT_TAX_INVOICE_INFO", "AUDIT_STATUS", oldMchtTaxInvoiceInfo.getAuditStatus()));
		mchtInfoChangeLog.setCreateBy(mchtTaxInvoiceInfo.getUpdateBy());
		mchtInfoChangeLog.setCreateDate(mchtTaxInvoiceInfo.getUpdateDate());
		mchtInfoChangeLog.setDelFlag("0");
		mchtInfoChangeLog.setLogName(oldMchtTaxInvoiceInfo.getTaxNumber());
		mchtInfoChangeLog.setLogType("税务审核");
		mchtInfoChangeLog.setMchtId(oldMchtTaxInvoiceInfo.getMchtId());
		mchtInfoChangeLog.setRemarks(mchtTaxInvoiceInfo.getAuditRemarks());
		mchtInfoChangeLogMapper.insertSelective(mchtInfoChangeLog);
		
		mchtTaxInvoiceInfoMapper.updateByPrimaryKeySelective(mchtTaxInvoiceInfo);
	}
	
}
