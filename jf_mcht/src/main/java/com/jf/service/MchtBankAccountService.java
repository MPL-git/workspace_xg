package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MchtBankAccountCustomMapper;
import com.jf.dao.MchtBankAccountMapper;
import com.jf.entity.MchtBankAccount;
import com.jf.entity.MchtBankAccountCustom;
import com.jf.entity.MchtBankAccountExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MchtBankAccountService extends BaseService<MchtBankAccount,MchtBankAccountExample> {
	@Autowired
	private MchtBankAccountMapper mchtBankAccountMapper;
	
	@Autowired
	private MchtBankAccountCustomMapper mchtBankAccountCustomMapper;
	
	@Autowired
	public void setMchtBankAccountMapper(MchtBankAccountMapper mchtBankAccountMapper) {
		super.setDao(mchtBankAccountMapper);
		this.mchtBankAccountMapper = mchtBankAccountMapper;
	}
	
	
    public int countMchtBankAccountCustomByExample(MchtBankAccountExample example){
    	return mchtBankAccountCustomMapper.countByExample(example);
    }


    public List<MchtBankAccountCustom> selectMchtBankAccountCustomByExample(MchtBankAccountExample example){
    	return mchtBankAccountCustomMapper.selectByExample(example);
    }

    public MchtBankAccountCustom selectMchtBankAccountCustomByPrimaryKey(Integer id){
    	return mchtBankAccountCustomMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 商家银行帐号信息保存
     * @param mchtBankAccount
     * @throws ArgException
     */
    
    public void mchtBankAccountSave(MchtBankAccount mchtBankAccount) throws ArgException{
    	
    	if("1".equals(mchtBankAccount.getIsDefault())){
    		MchtBankAccountExample mchtBankAccountExample=new MchtBankAccountExample();
    		mchtBankAccountExample.createCriteria().andMchtIdEqualTo(mchtBankAccount.getMchtId());
    		MchtBankAccount mchtBankAccount4update=new MchtBankAccount();
    		mchtBankAccount4update.setIsDefault("0");
    		mchtBankAccountMapper.updateByExampleSelective(mchtBankAccount4update, mchtBankAccountExample);
    	}
    	
    	if(mchtBankAccount.getId()==null){
    		MchtBankAccountExample mchtBankAccountExample=new MchtBankAccountExample();
    		mchtBankAccountExample.createCriteria().andMchtIdEqualTo(mchtBankAccount.getMchtId()).andDelFlagEqualTo("0");
    		int count=mchtBankAccountMapper.countByExample(mchtBankAccountExample);
    		if(count>=1){
    			throw new ArgException("最多只能添加1条银行帐号");
    		}
    		mchtBankAccount.setStatus("0");
    		mchtBankAccount.setCreateBy(mchtBankAccount.getCreateBy());
    		mchtBankAccount.setCreateDate(mchtBankAccount.getCreateDate());
    		if(mchtBankAccount.getAccType() == null || "".equals(mchtBankAccount.getAccType()))
    			mchtBankAccount.setAccType("2");
    		mchtBankAccountMapper.insertSelective(mchtBankAccount);
    	}else{
    		mchtBankAccount.setStatus("0");
    		mchtBankAccount.setCommitDate(new Date());
    		mchtBankAccountMapper.updateByPrimaryKeySelective(mchtBankAccount);
    	}
    }
    
    public void setDefault(Integer id) throws ArgException{
    	
    	MchtBankAccount mchtBankAccount=mchtBankAccountMapper.selectByPrimaryKey(id);
    	
    	MchtBankAccountExample mchtBankAccountExample=new MchtBankAccountExample();
		mchtBankAccountExample.createCriteria().andMchtIdEqualTo(mchtBankAccount.getMchtId());
		MchtBankAccount mchtBankAccount4update=new MchtBankAccount();
		mchtBankAccount4update.setIsDefault("0");
		mchtBankAccountMapper.updateByExampleSelective(mchtBankAccount4update, mchtBankAccountExample);
		
		mchtBankAccount4update.setIsDefault("1");
		mchtBankAccount4update.setId(mchtBankAccount.getId());
		mchtBankAccountMapper.updateByPrimaryKeySelective(mchtBankAccount4update);
    }



	/**
	 * 获取商家默认结算银行
	 * @param mchtId
	 * @return
	 */
	public MchtBankAccount findByMchtId(int mchtId){
		QueryObject queryObject = new QueryObject();
		queryObject.addQuery("mchtId", mchtId);
//		queryObject.addQuery("isDefault", Const.FLAG_TRUE);
		queryObject.setLimitSize(1);
		List<MchtBankAccount> list = list(queryObject);
		return list.size() > 0 ? list.get(0) : null;
	}

	public List<MchtBankAccount> list(QueryObject queryObject) {
		MchtBankAccountExample example = builderQuery(queryObject);
		if(queryObject.getLimitSize() > 0){
			example.setLimitStart(0);
			example.setLimitSize(queryObject.getLimitSize());
		}
		return dao.selectByExample(example);
	}

	private MchtBankAccountExample builderQuery(QueryObject queryObject) {
		MchtBankAccountExample example = new MchtBankAccountExample();
		example.setOrderByClause("id desc");
		MchtBankAccountExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
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
