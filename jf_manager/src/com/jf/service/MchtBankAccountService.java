package com.jf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.exception.ArgException;
import com.jf.common.ext.query.Page;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBankAccountCustomMapper;
import com.jf.dao.MchtBankAccountHisMapper;
import com.jf.dao.MchtBankAccountMapper;
import com.jf.entity.MchtBankAccount;
import com.jf.entity.MchtBankAccountCustom;
import com.jf.entity.MchtBankAccountCustomExample;
import com.jf.entity.MchtBankAccountExample;
import com.jf.entity.MchtBankAccountHis;
import com.jf.entity.MchtInfoChangeLog;

@Service
@Transactional
public class MchtBankAccountService extends BaseService<MchtBankAccount,MchtBankAccountExample> {
	@Autowired
	private MchtBankAccountMapper mchtBankAccountMapper;
	
	@Autowired
	private MchtBankAccountCustomMapper mchtBankAccountCustomMapper;
	
	@Autowired
	private MchtBankAccountHisMapper mchtBankAccountHisMapper;
	
	@Autowired
	private MchtInfoChangeLogService mchtInfoChangeLogService;
	
	@Autowired
	public void setMchtBankAccountMapper(MchtBankAccountMapper mchtBankAccountMapper) {
		super.setDao(mchtBankAccountMapper);
		this.mchtBankAccountMapper = mchtBankAccountMapper;
	}
	
    public List<MchtBankAccountCustom> selectMchtBankAccountCustomByExample(MchtBankAccountCustomExample example){
    	return mchtBankAccountCustomMapper.selectByExample(example);
    }

    public MchtBankAccountCustom selectMchtBankAccountCustomByPrimaryKey(Integer id){
    	return mchtBankAccountCustomMapper.selectByPrimaryKey(id);
    }
    public int countMchtBankAccountCustomByExample(MchtBankAccountCustomExample example){
    	return mchtBankAccountCustomMapper.countByExample(example);
    }
    
	public  void mchtbankAccountAuditSave(Integer auditBy,String status,String auditRemarks, Integer id) throws ArgException {
		MchtBankAccount mchtBankAccount=mchtBankAccountMapper.selectByPrimaryKey(id);
		if("2".equals(mchtBankAccount.getStatus())){
			throw new ArgException("信息已审核通过，无需重新审核。");
		}
		mchtBankAccount.setStatus(status);
		mchtBankAccount.setAuditRemarks(auditRemarks);
		mchtBankAccount.setUpdateBy(auditBy);
		mchtBankAccount.setUpdateDate(new Date());
		mchtBankAccountMapper.updateByPrimaryKeySelective(mchtBankAccount);
		if("2".equals(status)){
			MchtBankAccountHis mchtBankAccountHis=new MchtBankAccountHis();
			BeanUtils.copyProperties(mchtBankAccount, mchtBankAccountHis);
			mchtBankAccountHis.setId(null);
			mchtBankAccountHisMapper.insertSelective(mchtBankAccountHis);
		}
		MchtInfoChangeLog micl = new MchtInfoChangeLog();
		micl.setMchtId(mchtBankAccount.getMchtId());
		micl.setDelFlag("0");
		micl.setCreateDate(new Date());
		micl.setCreateBy(auditBy);
		micl.setLogType("商家银行账号审核");
		micl.setLogName(mchtBankAccount.getAccNumber());
		micl.setBeforeChange("待审");
		if("2".equals(mchtBankAccount.getStatus())){
			micl.setAfterChange("通过");
		}
		if("3".equals(mchtBankAccount.getStatus())){
			micl.setAfterChange("驳回");
		}
		micl.setRemarks(auditRemarks);
		mchtInfoChangeLogService.insertSelective(micl);
	}




	public int count(QueryObject queryObject) {
		return dao.countByExample(builderQuery(queryObject));
	}

	public List<MchtBankAccount> list(QueryObject queryObject) {
		MchtBankAccountExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	public Page<MchtBankAccount> paginate(QueryObject queryObject) {
		MchtBankAccountExample example = builderQuery(queryObject);
		example.setLimitStart(queryObject.getLimitStart());
		example.setLimitSize(queryObject.getPageSize());

		List<MchtBankAccount> list = dao.selectByExample(example);
		int totalCount = dao.countByExample(example);
		return new Page<MchtBankAccount>(list, queryObject.getPageNumber(), queryObject.getPageSize(), queryObject.getTotalPage(totalCount), totalCount);
	}

	private MchtBankAccountExample builderQuery(QueryObject queryObject) {
		MchtBankAccountExample example = new MchtBankAccountExample();
		MchtBankAccountExample.Criteria criteria = example.createCriteria();
		if(queryObject.getQuery(QueryObject.INCLUDE_DELETE) == null){
			criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		}
		if(queryObject.getQuery("mchtId") != null){
			criteria.andMchtIdEqualTo(queryObject.getQueryToInt("mchtId"));
		}
		if(queryObject.getQuery("isDefault") != null){
			criteria.andIsDefaultEqualTo(queryObject.getQueryToStr("isDefault"));
		}
		if(queryObject.getQuery("status") != null){
			criteria.andStatusEqualTo(queryObject.getQueryToStr("status"));
		}
		if(queryObject.getSort().size() > 0){
			example.setOrderByClause(queryObject.getSortString());
		}
		return example;
	}

}
