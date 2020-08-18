package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.dao.MchtTaxInvoiceInfoChgCustomMapper;
import com.jf.dao.MchtTaxInvoiceInfoChgMapper;
import com.jf.entity.MchtTaxInvoiceInfoChg;
import com.jf.entity.MchtTaxInvoiceInfoChgCustom;
import com.jf.entity.MchtTaxInvoiceInfoChgExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MchtTaxInvoiceInfoChgService extends BaseService<MchtTaxInvoiceInfoChg, MchtTaxInvoiceInfoChgExample> {
	@Autowired
	private MchtTaxInvoiceInfoChgMapper mchtTaxInvoiceInfoChgMapper;
	
	@Autowired
	private MchtTaxInvoiceInfoChgCustomMapper mchtTaxInvoiceInfoChgCustomMapper;

	@Autowired
	public void setMchtTaxInvoiceInfoChgMapper(MchtTaxInvoiceInfoChgMapper mchtTaxInvoiceInfoChgMapper) {
		super.setDao(mchtTaxInvoiceInfoChgMapper);
		this.mchtTaxInvoiceInfoChgMapper = mchtTaxInvoiceInfoChgMapper;
	}
	
    public List<MchtTaxInvoiceInfoChgCustom> selectMchtTaxInvoiceInfoChgCustomByExample(MchtTaxInvoiceInfoChgExample example){
    	return mchtTaxInvoiceInfoChgCustomMapper.selectByExample(example);
    }

	/**
	 * 申请修改，保存暂不提审
	 * 
	 * @param mchtTaxInvoiceInfoChg
	 * @throws ArgException
	 */
	public void mchtTaxInvoiceInfoChgCommitSave(MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg) throws ArgException {
		if (mchtTaxInvoiceInfoChg.getId() == null) {
			// 判断是否有提交申请修改了
			MchtTaxInvoiceInfoChgExample mchtTaxInvoiceInfoChgExample = new MchtTaxInvoiceInfoChgExample();
			mchtTaxInvoiceInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtTaxInvoiceInfoChg.getMchtId()).andAuditStatusNotEqualTo("3");
			int infoChgCount = mchtTaxInvoiceInfoChgMapper.countByExample(mchtTaxInvoiceInfoChgExample);
			if (infoChgCount > 0) {
				throw new ArgException("已有申请再处理中，不能再次提交");
			}
			mchtTaxInvoiceInfoChg.setCreateBy(mchtTaxInvoiceInfoChg.getUpdateBy());
			mchtTaxInvoiceInfoChg.setCreateDate(mchtTaxInvoiceInfoChg.getUpdateDate());
			mchtTaxInvoiceInfoChg.setAuditStatus("0");
			mchtTaxInvoiceInfoChgMapper.insertSelective(mchtTaxInvoiceInfoChg);
		} else {
			// 判断申请是否已经在处理中
			MchtTaxInvoiceInfoChg oldMchtTaxInvoiceInfoChg = mchtTaxInvoiceInfoChgMapper.selectByPrimaryKey(mchtTaxInvoiceInfoChg.getId());
			if (oldMchtTaxInvoiceInfoChg.getAuditStatus() == "1" || oldMchtTaxInvoiceInfoChg.getAuditStatus() == "2") {
				throw new ArgException("申请已在审核中，不能再次提交");
			}
			if (oldMchtTaxInvoiceInfoChg.getAuditStatus() == "3") {
				throw new ArgException("申请已审核通过，不能再次提交");
			}
			mchtTaxInvoiceInfoChgMapper.updateByPrimaryKeySelective(mchtTaxInvoiceInfoChg);
		}
	}

	/**
	 * 申请修改，提审
	 * 
	 * @param mchtTaxInvoiceInfoChg
	 * @throws ArgException
	 */
	public void mchtTaxInvoiceInfoChgCommitAudit(MchtTaxInvoiceInfoChg mchtTaxInvoiceInfoChg) throws ArgException {
		
		if(mchtTaxInvoiceInfoChg.getId()==null){
			MchtTaxInvoiceInfoChgExample mchtTaxInvoiceInfoChgExample = new MchtTaxInvoiceInfoChgExample();
			mchtTaxInvoiceInfoChgExample.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtTaxInvoiceInfoChg.getMchtId()).andAuditStatusNotEqualTo("3");
			int infoChgCount = mchtTaxInvoiceInfoChgMapper.countByExample(mchtTaxInvoiceInfoChgExample);
			if (infoChgCount > 0) {
				throw new ArgException("已有申请再处理中，不能再次提交");
			}
			mchtTaxInvoiceInfoChg.setCreateBy(mchtTaxInvoiceInfoChg.getUpdateBy());
			mchtTaxInvoiceInfoChg.setCreateDate(mchtTaxInvoiceInfoChg.getUpdateDate());
			mchtTaxInvoiceInfoChg.setAuditStatus("1");
			mchtTaxInvoiceInfoChgMapper.insertSelective(mchtTaxInvoiceInfoChg);
			
		}else{
			// 判断申请是否已经在处理中
			MchtTaxInvoiceInfoChg oldMchtTaxInvoiceInfoChg = mchtTaxInvoiceInfoChgMapper.selectByPrimaryKey(mchtTaxInvoiceInfoChg.getId());
			if (oldMchtTaxInvoiceInfoChg.getAuditStatus() == "1" || oldMchtTaxInvoiceInfoChg.getAuditStatus() == "2") {
				throw new ArgException("申请已在审核中，不能再次提交");
			}
			if (oldMchtTaxInvoiceInfoChg.getAuditStatus() == "3") {
				throw new ArgException("申请已审核通过，不能再次提交");
			}
			mchtTaxInvoiceInfoChg.setAuditStatus("1");
			mchtTaxInvoiceInfoChgMapper.updateByPrimaryKeySelective(mchtTaxInvoiceInfoChg);
		}
		

	}
}
